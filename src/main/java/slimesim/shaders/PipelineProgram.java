package slimesim.shaders;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;
import processing.core.PApplet;

import java.io.File;

public class PipelineProgram {
    
    private final GL4 gl;
    private final int shaderProgram;
    
    public PipelineProgram(GL4 gl, File vert, File frag) {
        this.gl = gl;
        this.shaderProgram = gl.glCreateProgram();

        String vertSrc = PApplet.join(PApplet.loadStrings(vert), "\n");
        String fragSrc = PApplet.join(PApplet.loadStrings(frag), "\n");

        int vertShader = ShaderHelper.createAndCompileShader(gl, GL4.GL_VERTEX_SHADER, vertSrc);
        int fragShader = ShaderHelper.createAndCompileShader(gl, GL4.GL_FRAGMENT_SHADER, fragSrc);
        
        ShaderHelper.linkShaders(gl, shaderProgram, vertShader, fragShader);
    }
    
    public void draw(int count) {
        gl.glUseProgram(shaderProgram);
        gl.glDrawArrays(GL4.GL_POINTS, 0, count);
        gl.glUseProgram(0);
    }
}