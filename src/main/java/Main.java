import processing.core.PApplet;
import slimesim.SlimeSimulation;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Class<Main> mainClass = Main.class;
        SlimeSimulation.vertexShader = new File(mainClass.getResource("shaders/vert.glsl").getFile());
        SlimeSimulation.fragmentShader = new File(mainClass.getResource("shaders/frag.glsl").getFile());
        SlimeSimulation.computeShader = new File(mainClass.getResource("shaders/comp.glsl").getFile());
        PApplet.main("slimesim.SlimeSimulation");
    }
}
