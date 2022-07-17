package slimesim.shaders;

import com.jogamp.opengl.GL4;
import processing.core.PApplet;

import java.io.File;
import java.nio.FloatBuffer;

public class ComputeProgram {
    
    private final GL4 gl;
    private final int computeProgram;

    public ComputeProgram(GL4 gl, File compute, FloatBuffer agentsInfo) {
        this.gl = gl;
        this.computeProgram = gl.glCreateProgram();
        
        String compSrc = PApplet.join(PApplet.loadStrings(compute), "\n");
        int compShader = ShaderHelper.createAndCompileShader(gl, GL4.GL_COMPUTE_SHADER, compSrc);
        
        ShaderHelper.linkShaders(gl, computeProgram, compShader);
        
        int[] bufferID = new int[1];
        
        gl.glGenBuffers(1, bufferID, 0);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, bufferID[0]);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, agentsInfo.limit() * 4L, agentsInfo, GL4.GL_DYNAMIC_DRAW);
        gl.glEnableVertexAttribArray(0);
        gl.glEnableVertexAttribArray(1);
        // each float is 4 bytes, and the agent is composed of 2 vec2 therefore 16 bytes in total for stride
        gl.glVertexAttribPointer(0, 2, GL4.GL_FLOAT, false, 16, 0); // for the pos
        gl.glVertexAttribPointer(1, 2, GL4.GL_FLOAT, false, 16, 8); // for the dir
        gl.glBindBufferBase(GL4.GL_SHADER_STORAGE_BUFFER, 0, bufferID[0]);
    }

    public void beginDispatch(int x, int y, int z) {
        gl.glUseProgram(computeProgram);
        gl.glDispatchCompute(x, y, z);
        gl.glUseProgram(0);
    }

    public void dispose() {
        gl.glDeleteProgram(computeProgram);
    }
}
