package ComputerGraphicsProject.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Abstract class for the Different shaders coming
 */
public abstract class Shader {

    private Map<String, Integer> uniforms;
    private int vertexID;
    private int fragmentID;
    private int programID;

    private static final int MATRIX_SIZE = 4 * 4;
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(MATRIX_SIZE);

    /**
     * Constructor, loads the shaders and links them to a new program
     * @param vertexPath path to the vertexShader
     * @param fragmentPath path to the fragmentShader
     */
    public Shader(String vertexPath, String fragmentPath){
        uniforms = new HashMap<String, Integer>();
        vertexID = loadShader(vertexPath, GL20.GL_VERTEX_SHADER);
        fragmentID = loadShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexID);
        GL20.glAttachShader(programID, fragmentID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
    }

    /**
     * Starts the program
     */
    public void start(){
        GL20.glUseProgram(programID);
    }

    /**
     * Stops the program
     */
    public void stop(){
        GL20.glUseProgram(0);
    }

    /**
     * Cleans up the shaders and the program
     */
    public void clean(){
        stop();
        GL20.glDetachShader(programID, vertexID);
        GL20.glDetachShader(programID, fragmentID);
        GL20.glDeleteShader(vertexID);
        GL20.glDeleteShader(fragmentID);
        GL20.glDeleteProgram(programID);
    }

    /**
     * Loads a shader and returns its id
     * @param file path to the specific shader 
     * @param type what type of shader 
     * @return id of the compiled shader
     */
    private int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) != null){
                shaderSource.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e){
            System.err.println("Could not read: " + file);
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader: " + file);
            System.exit(-1);
        }
        return shaderID;
    }

    /**
     * Searches the shader for specific uniform
     * @param uniform String to be searched
     * @return id of the uniform or -1 if no uniform is found
     */
    private int findUniformLocation(String uniform){
        try {
            return uniforms.get(uniform);
        } catch (Exception e) {
        }
        int location = GL20.glGetUniformLocation(programID, uniform);
        if(location == -1)
            System.out.println("No uniform called " + uniform);
        uniforms.put(uniform, location);
        return location;
    }
    
    public void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    public void loadFloatUniform(String uniform, float value){
        GL20.glUniform1f(findUniformLocation(uniform), value);
    }

    public void loadVector3fUniform(String uniform, Vector3f vector){
        GL20.glUniform3f(findUniformLocation(uniform), vector.x, vector.y, vector.z);
    }

    public void loadVector4fUniform(String uniform, Vector4f vector){
        GL20.glUniform4f(findUniformLocation(uniform), vector.x, vector.y, vector.z, vector.w);
    }

    public void loadBooleanUniform(String uniform, boolean value){
        GL20.glUniform1f(findUniformLocation(uniform), value == true ? 1 : 0);
    }

    public void loadMatrix4f(String uniform, Matrix4f matrix4f){
        matrix4f.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4fv(findUniformLocation(uniform), false, matrixBuffer);
    }

    public void loadIntUniform(String uniform, int value){
        GL20.glUniform1i(findUniformLocation(uniform), value);
    }

    public void loadVector2fUniform(String uniform, Vector2f v){
        GL20.glUniform2f(findUniformLocation(uniform), v.x, v.y);
    }

    protected abstract void bindAttributes();

}