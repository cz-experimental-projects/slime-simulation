package slimesim;

import processing.core.PApplet;
import slimesim.data.Agent;

import java.util.ArrayList;
import java.util.List;

public class SlimeSimulation extends PApplet {
    private List<Agent> agents;
    
    @Override
    public void setup() {
        surface.setSize(1600, 900);
    
        agents = new ArrayList<>();
        agents.add(new Agent(this));
    }

    @Override
    public void draw() {
        background(10);
        agents.forEach(agent -> {
            agent.render();
            agent.update();
        });
    }
}
