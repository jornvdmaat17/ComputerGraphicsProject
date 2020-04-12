package ComputerGraphicsProject.entity;

import org.lwjgl.util.vector.Vector3f;

public class Light {

    private Vector3f pos;
    private Vector3f colour;


    public Light(Vector3f pos, Vector3f colour) {
        this.pos = pos;
        this.colour = colour;
    }

    public Vector3f getPos() {
        return this.pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getColour() {
        return this.colour;
    }

    public void setColour(Vector3f colour) {
        this.colour = colour;
    }

}