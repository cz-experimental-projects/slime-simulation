package slimesim.obj;

import processing.core.PVector;
import slimesim.SlimeSimulation;

public class Agent {
    private static final float size = 5;

    private PVector headingDirection;
    private PVector position;

    private final float speed;
    private final Trail trail;
    private final SlimeSimulation simulation;

    public Agent(SlimeSimulation sim, float headingAngle) {
        position = new PVector(sim.width / 2f, sim.height/2f);
        speed = 1;
        trail = new Trail(0.005f, size * 0.75f);
        simulation = sim;
        setHeadingAngle(headingAngle);
    }

    public void move() {
        trail.addPrevPosition(position);
        position = PVector.lerp(position, position.add(headingDirection.mult(speed)), 0.5f);
    }

    public void update() {
        trail.update();
        move();

        if (position.x > simulation.width || position.x <= 0) { // hit right side or left side
            headingDirection = new PVector(-headingDirection.x, headingDirection.y);
        } else if (position.y >= simulation.height || position.y <= 0) {// hit bottom side or top side
            headingDirection = new PVector(headingDirection.x, -headingDirection.y);
        }
    }

    public void render() {
        trail.render(simulation);
        simulation.noStroke();
        simulation.fill(255, 255, 255, 255);
        simulation.ellipse(position.x, position.y, size, size);
    }
    
    public void setHeadingAngle(float angle) {
        headingDirection = angleToVec(angle);
    }
    
    private PVector angleToVec(float angle) {
        return new PVector((float) Math.cos(Math.toRadians(angle)), (float) Math.sin(Math.toRadians(angle))).normalize();
    }
    
    public float getCurrentFacingAngle() {
        PVector normalized = headingDirection.normalize();
        return (float) Math.atan2(normalized.y, normalized.x);
    }
}
