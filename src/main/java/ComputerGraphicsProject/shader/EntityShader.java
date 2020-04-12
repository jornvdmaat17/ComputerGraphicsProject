package ComputerGraphicsProject.shader;

import org.lwjgl.util.vector.Matrix4f;

import ComputerGraphicsProject.entity.Camera;
import ComputerGraphicsProject.entity.Light;
import ComputerGraphicsProject.util.Maths;

public class EntityShader extends Shader {

    public static final String VERTEX_SHADER = "src/main/resources/shaders/entity/vertexShader.glsl";
    public static final String FRAGMENT_SHADER = "src/main/resources/shaders/entity/fragmentShader.glsl";

    public EntityShader(){
        super(VERTEX_SHADER, FRAGMENT_SHADER);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "pos");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    public void loadTransformationMatrix(Matrix4f tMatrix){
        super.loadMatrix4f("transformationMatrix", tMatrix);
    }

    public void loadProjectionMatrix(Matrix4f pMatrix){
        super.loadMatrix4f("projectionMatrix", pMatrix);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix4f("viewMatrix", viewMatrix);
    }

    public void loadLight(Light light){
        super.loadVector3fUniform("lightPos", light.getPos());
        super.loadVector3fUniform("lightColour", light.getColour());
    }

    public void loadShineVariables(float shineDamper, float reflectivity){
        super.loadFloatUniform("shineDamper", shineDamper);
        super.loadFloatUniform("reflectivity", reflectivity);
    }
}