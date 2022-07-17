package slimesim.obj;

import com.jogamp.common.nio.Buffers;
import processing.core.PVector;
import processing.data.FloatList;
import slimesim.SlimeSimulation;
import slimesim.shaders.ComputeProgram;
import slimesim.shaders.PipelineProgram;

import java.nio.FloatBuffer;

public class Bureau {

    private final int agentCount;

    private final PipelineProgram shaderProgram;
    private final ComputeProgram computeProgram;
    
    public Bureau(SlimeSimulation simulation, float agentIncrement) {
        agentCount = (int) (360 / agentIncrement);
        
        FloatList buf = new FloatList();
        
        for (int i = 0; i < agentCount; i++) {
            // x value
            buf.append(0);
            // y value
            buf.append(0);
            // angle
            PVector dir = angleToVec(i * agentIncrement);
            buf.append(dir.x);
            buf.append(dir.y);
        }

        FloatBuffer agentsPositionBuffer = Buffers.newDirectFloatBuffer(buf.array());

        shaderProgram = new PipelineProgram(simulation.getGl(), SlimeSimulation.vertexShader, SlimeSimulation.fragmentShader);
        computeProgram = new ComputeProgram(simulation.getGl(), SlimeSimulation.computeShader, agentsPositionBuffer);
    }

    public void draw() {
        computeProgram.beginDispatch(agentCount, 1, 1);
        shaderProgram.draw(agentCount);
    }
    
    public void dispose() {
        computeProgram.dispose();
    }

    private PVector angleToVec(float angle) {
        return new PVector((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle))).normalize();
    }
}

