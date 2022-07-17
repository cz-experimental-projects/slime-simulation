package slimesim.data;

import processing.core.PVector;
import slimesim.SlimeSimulation;

public class Agent {
    private float facingAngle;
    private PVector position;
    
    private final float speed;
    private final Trail trail;
    private final SlimeSimulation simulation;
    
    public Agent(SlimeSimulation sim) {
        facingAngle = 0;
        position = new PVector(100, 100);
        speed = 1;
        trail = new Trail(0.006f, 5);
        simulation = sim;
    }

    public void move() {
        trail.addPrevPosition(position);
        position = PVector.lerp(position, position.add(angleToVec().mult(speed)), 0.5f);
    }
    
    public void update() {
        trail.update();
        move();
    }
    
    public void render() {
        trail.render(simulation);
        simulation.fill(255, 255, 255, 255);
        simulation.ellipse(position.x, position.y, 5, 5);
    }
    
    public PVector angleToVec() {
        return new PVector((float) Math.cos(Math.toRadians(facingAngle)), (float) Math.sin(Math.toRadians(facingAngle)));
    }
}
