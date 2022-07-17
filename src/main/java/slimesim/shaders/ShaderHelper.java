package slimesim.shaders;

import com.jogamp.opengl.GL4;

public class ShaderHelper {
    public static int createAndCompileShader(GL4 gl, int type, String shaderString) {
        int shader = gl.glCreateShader(type);

        String[] vlines = new String[]{shaderString};
        int[] vlengths = new int[]{vlines[0].length()};

        gl.glShaderSource(shader, vlines.length, vlines, vlengths, 0);
        gl.glCompileShader(shader);

        int[] compiled = new int[1];
        gl.glGetShaderiv(shader, GL4.GL_COMPILE_STATUS, compiled, 0);

        if (compiled[0] == 0) {
            int[] logLength = new int[1];
            gl.glGetShaderiv(shader, GL4.GL_INFO_LOG_LENGTH, logLength, 0);

            byte[] log = new byte[logLength[0]];
            gl.glGetShaderInfoLog(shader, logLength[0], null, 0, log, 0);

            gl.glDeleteShader(shader);
            
            throw new IllegalStateException("Error compiling the shader: " + new String(log));
        }

        return shader;
    }
    
    public static void linkShaders(GL4 gl, int program, int... shaders) {
        for (int i : shaders) {
            gl.glAttachShader(program, i);
        }
        
        gl.glLinkProgram(program);
        gl.glValidateProgram(program);
        
        for (int i : shaders) {
            gl.glDetachShader(program, i);
            gl.glDeleteShader(i);
        }
    }

    public static void updateUniform1f(GL4 gl, int program, String uniformName, float uniformValue) {
        int loc = gl.glGetUniformLocation(program, uniformName);
        if (loc != -1)
        {
            gl.glUniform1f(loc, uniformValue);
        }
    }


    public static void updateUniform2f(GL4 gl, int program, String uniformName, float uniformValue1, float uniformValue2) {
        int loc = gl.glGetUniformLocation(program, uniformName);
        if (loc != -1)
        {
            gl.glUniform2f(loc, uniformValue1, uniformValue2);
        }
    }

    public static void updateUniform3f(GL4 gl, int program, String uniformName, float uniformValue1, float uniformValue2, float uniformValue3) {
        int loc = gl.glGetUniformLocation(program, uniformName);
        if (loc != -1)
        {
            gl.glUniform3f(loc, uniformValue1, uniformValue2, uniformValue3);
        }
    }
}
