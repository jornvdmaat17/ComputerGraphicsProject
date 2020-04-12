package ComputerGraphicsProject.render;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

public class WindowManager {

    private long window;
    private static long lastFrameTime;
    private static long delta;

    public static int WIDTH;
    public static int HEIGHT;

    public WindowManager(String title, int width, int height){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        GLFWErrorCallback.createPrint(System.err).set();

        WIDTH = width;
        HEIGHT = height;

        if(!glfwInit())
            throw new IllegalStateException("No glfw");
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(width, height, title, 0, 0);
        lastFrameTime = System.currentTimeMillis();
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        glfwSwapInterval(1);
        GL.createCapabilities();
    }

    public void updateDisplay(){
        glfwSwapBuffers(window);
        glfwPollEvents();
        delta = System.currentTimeMillis() - lastFrameTime;
        lastFrameTime = System.currentTimeMillis();
    }

    public static float getFrameTimeSeconds(){
        return delta / 1000f;
    }

    public void closeDisplay(){
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public long getWindow(){
        return window;
    }

    public boolean shouldCloseWindow(){
        return glfwWindowShouldClose(window);
    }
}
