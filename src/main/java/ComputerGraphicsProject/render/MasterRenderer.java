package ComputerGraphicsProject.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ComputerGraphicsProject.entity.Camera;
import ComputerGraphicsProject.entity.Entity;
import ComputerGraphicsProject.entity.Light;
import ComputerGraphicsProject.model.TextureModel;
import ComputerGraphicsProject.shader.EntityShader;

public class MasterRenderer {

    private EntityShader shader = new EntityShader();
    private Renderer renderer = new Renderer(shader);

    private Map<TextureModel, List<Entity>> entities = new HashMap<>();

    public void render(Light light, Camera camera){
        renderer.prepare();
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        entities.clear();
    }

    public void addEnitty(Entity entity){
        TextureModel entityModel = entity.getModel();
        List<Entity> e = entities.get(entityModel);
        if(e != null){
            e.add(entity);
        }else{
            List<Entity> newE = new ArrayList<>();
            newE.add(entity);
            entities.put(entityModel, newE);
        }
    }

    public void clean(){
        shader.clean();
    }
}