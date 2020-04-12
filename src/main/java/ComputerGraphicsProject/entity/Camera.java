package ComputerGraphicsProject.entity;

import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import ComputerGraphicsProject.util.KeyboardInput;

public class Camera {

    private Vector3f pos = new Vector3f(0,0,0);
    private float pitch, yaw, roll;

    public Camera(long window){
        glfwSetKeyCallback(window, new KeyboardInput());
    }

    public void move(){
        if(KeyboardInput.keys[GLFW_KEY_W]){
            pos.z -= 0.02f;
        }
        if(KeyboardInput.keys[GLFW_KEY_S]){
            pos.z += 0.02f;
        }
        if(KeyboardInput.keys[GLFW_KEY_D]){
            pos.x += 0.02f;
        }
        if(KeyboardInput.keys[GLFW_KEY_A]){
            pos.x -= 0.02f;
        }
    }

    public Vector3f getPos() {
        return this.pos;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getRoll() {
        return this.roll;
    }


}