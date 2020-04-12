package ComputerGraphicsProject.entity;

import org.lwjgl.util.vector.Vector3f;

import ComputerGraphicsProject.model.TextureModel;

public class Entity {

    private TextureModel model;
    private Vector3f pos;
    private float rotX, rotY, rotZ;
    private float scale;


    public Entity(TextureModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.pos = pos;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.pos.x += dx;
        this.pos.y += dy;
        this.pos.z += dz;
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }

    public TextureModel getModel() {
        return this.model;
    }

    public void setModel(TextureModel model) {
        this.model = model;
    }

    public Vector3f getPos() {
        return this.pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public float getRotX() {
        return this.rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return this.rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return this.rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }


}