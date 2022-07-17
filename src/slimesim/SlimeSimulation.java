package slimesim;

import processing.core.PApplet;
import processing.opengl.PShader;
import slimesim.obj.Agent;

import java.util.ArrayList;
import java.util.List;

public class SlimeSimulation extends PApplet {
    
    private List<Agent> agents;

    @Override
    public void setup() {
        surface.setSize(1600, 900);
        
        agents = new ArrayList<>();
        for (int i = 0; i < 360; i += 1) {
            agents.add(new Agent(this, i));
        }
    }

    @Override
    public void draw() {
        background(10);
        agents.forEach(agent -> {
            agent.update();
            agent.render();
        });
    }
}
