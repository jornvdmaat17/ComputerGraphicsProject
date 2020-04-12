package ComputerGraphicsProject.util;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardInput extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[65535];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if(key != -1)
            keys[key] = action != GLFW_RELEASE;        
    }
    
}