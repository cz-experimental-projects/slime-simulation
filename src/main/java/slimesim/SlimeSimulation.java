package slimesim;

import com.jogamp.opengl.GL4;
import processing.core.PApplet;
import processing.opengl.PGL;
import processing.opengl.PGraphicsOpenGL;
import processing.opengl.PJOGL;
import slimesim.obj.Bureau;

import java.io.File;

public class SlimeSimulation extends PApplet {

    public static File computeShader;
    public static File fragmentShader;
    public static File vertexShader;
    
    private GL4 gl;
    private Bureau bureau;
    
    @Override
    public void setup() {
        PGL pgl = ((PGraphicsOpenGL)g).pgl;
        gl = ((PJOGL)pgl).gl.getGL4();
        bureau = new Bureau(this, 1);
    }

    @Override
    public void settings() {
        super.settings();
        size(1600, 900, P3D);
    }

    @Override
    public void draw() {
        background(10);
        bureau.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        bureau.dispose();
    }

    public GL4 getGl() {
        return gl;
    }
}
