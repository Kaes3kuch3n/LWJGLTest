package me.kaes3kuch3n.lwjgltest.enginetester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import me.kaes3kuch3n.lwjgltest.entities.Camera;
import me.kaes3kuch3n.lwjgltest.entities.Entity;
import me.kaes3kuch3n.lwjgltest.entities.Light;
import me.kaes3kuch3n.lwjgltest.models.RawModel;
import me.kaes3kuch3n.lwjgltest.models.TexturedModel;
import me.kaes3kuch3n.lwjgltest.renderengine.DisplayManager;
import me.kaes3kuch3n.lwjgltest.renderengine.Loader;
import me.kaes3kuch3n.lwjgltest.renderengine.MasterRenderer;
import me.kaes3kuch3n.lwjgltest.renderengine.OBJLoader;
import me.kaes3kuch3n.lwjgltest.terrains.Terrain;
import me.kaes3kuch3n.lwjgltest.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
				
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TexturedModel staticModel = new TexturedModel(model, texture);
//		texture.setShineDamper(10);
//		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(5, -3, -20), 0, 120, 0, 1);
		Entity secondEntify = new Entity(staticModel, new Vector3f(-5, -3, -20), 0, 210, 0, 1);
		
		Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0, -800, loader, new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1, -800, loader, new ModelTexture(loader.loadTexture("grass")));
		
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()) {
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			renderer.processEntity(secondEntify);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
