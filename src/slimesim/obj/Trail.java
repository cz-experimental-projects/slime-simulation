package slimesim.obj;

import processing.core.PVector;
import slimesim.SlimeSimulation;
import slimesim.data.ReferencedFloat;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Trail {
    private final Map<ReferencedFloat, PVector> trail;
    private final float disappearSpeed;
    private final float trailSize;
    
    public Trail(float disappearSpeed, float trailSize) {
        this.trail = new HashMap<>();
        this.disappearSpeed = disappearSpeed;
        this.trailSize = trailSize;
    }
    
    public void addPrevPosition(PVector position) {
        trail.put(new ReferencedFloat(1f), position);
    }
    
    public void update() {
        List<ReferencedFloat> toRemove = new ArrayList<>();

        for (ReferencedFloat progress : trail.keySet()) {
            progress.add(-disappearSpeed);
            if (progress.getValue() <= 0) {
                toRemove.add(progress);
            }
        }

        for (ReferencedFloat referencedFloat : toRemove) {
            trail.remove(referencedFloat);
        }
    }
    
    public void render(SlimeSimulation simulation) {
        for (ReferencedFloat f : trail.keySet()) {
            PVector p = trail.get(f);
            if (p == null) continue;
            simulation.noStroke();
            simulation.fill(255, 255, 255, 255 * f.getValue());
            simulation.ellipse(p.x, p.y, trailSize * f.getValue(), trailSize * f.getValue());
        }
    }
}
