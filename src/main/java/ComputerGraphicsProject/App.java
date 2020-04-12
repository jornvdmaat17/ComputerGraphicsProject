package ComputerGraphicsProject;

import org.lwjgl.util.vector.Vector3f;

import ComputerGraphicsProject.entity.Camera;
import ComputerGraphicsProject.entity.Entity;
import ComputerGraphicsProject.entity.Light;
import ComputerGraphicsProject.model.RawModel;
import ComputerGraphicsProject.model.TextureModel;
import ComputerGraphicsProject.objLoading.ModelData;
import ComputerGraphicsProject.objLoading.OBJLoader;
import ComputerGraphicsProject.render.Loader;
import ComputerGraphicsProject.render.MasterRenderer;
import ComputerGraphicsProject.render.WindowManager;
import ComputerGraphicsProject.textures.ModelTexture;

public class App {
    
    public static void main(String[] args) {
        mainLoop();
    }

    private static void mainLoop(){
        WindowManager windowManager = new WindowManager("Solar System", 1920, 1080);
        Loader loader = new Loader();
        
        ModelData stallData = OBJLoader.loadOBJ("dragon.obj");

        RawModel model = loader.loadToVAO(stallData.getVertices(), stallData.getTextureCoords(), stallData.getNormals(), stallData.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("white.png"));
        TextureModel textureModel = new TextureModel(model, texture);
        texture.setReflectivity(1);
        texture.setShineDamper(30);

        Entity entity = new Entity(textureModel, new Vector3f(0, -5, -10), 0,0,0,1);

        Light light = new Light(new Vector3f(-4,10,0), new Vector3f(1,1,1));

        Camera camera = new Camera(windowManager.getWindow());

        MasterRenderer renderer = new MasterRenderer();
        while(!windowManager.shouldCloseWindow()){
            camera.move();


            renderer.render(light, camera);
            windowManager.updateDisplay();
        }

        renderer.clean();
        loader.clean();
        windowManager.closeDisplay();
    }
}
