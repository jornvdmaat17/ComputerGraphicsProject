package ComputerGraphicsProject.model;

import ComputerGraphicsProject.textures.ModelTexture;

public class TextureModel {

    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TextureModel(RawModel rawModel, ModelTexture texture){
        this.rawModel = rawModel;
        this.modelTexture = texture;
    }

    public RawModel getRawModel() {
        return this.rawModel;
    }

    public ModelTexture getModelTexture() {
        return this.modelTexture;
    }

}