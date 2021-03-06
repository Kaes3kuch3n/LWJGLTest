package me.kaes3kuch3n.lwjgltest.enginetester;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import me.kaes3kuch3n.lwjgltest.entities.Camera;
import me.kaes3kuch3n.lwjgltest.entities.Entity;
import me.kaes3kuch3n.lwjgltest.entities.Light;
import me.kaes3kuch3n.lwjgltest.entities.Player;
import me.kaes3kuch3n.lwjgltest.models.RawModel;
import me.kaes3kuch3n.lwjgltest.models.TexturedModel;
import me.kaes3kuch3n.lwjgltest.renderengine.DisplayManager;
import me.kaes3kuch3n.lwjgltest.renderengine.Loader;
import me.kaes3kuch3n.lwjgltest.renderengine.MasterRenderer;
import me.kaes3kuch3n.lwjgltest.renderengine.OBJLoader;
import me.kaes3kuch3n.lwjgltest.terrains.Terrain;
import me.kaes3kuch3n.lwjgltest.textures.ModelTexture;
import me.kaes3kuch3n.lwjgltest.textures.TerrainTexture;
import me.kaes3kuch3n.lwjgltest.textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		
		//********** TERRAIN TEXTURE PACK **********
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy2"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		//******************************************
		
		
		//********** STALL TEXTURED MODEL **********
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		TexturedModel staticModel = new TexturedModel(model, texture);
//		texture.setShineDamper(10);
//		texture.setReflectivity(1);
		//******************************************
		
		
		//********** LOAD TEXTURED MODELS **********
		
		//Coniferous tree
		TexturedModel treeModel = new TexturedModel(OBJLoader.loadObjModel("tree", loader), new ModelTexture(loader.loadTexture("tree")));
		
		//Deciduous tree
		TexturedModel tree2Model = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader), new ModelTexture(loader.loadTexture("lowPolyTree")));
		
		//Fern
		TexturedModel fernModel = new TexturedModel(OBJLoader.loadObjModel("fern", loader), new ModelTexture(loader.loadTexture("fern")));
		fernModel.getTexture().setHasTransparency(true);
		
		//Grass
		TexturedModel grassModel = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("grassTexture")));
		grassModel.getTexture().setHasTransparency(true);
		grassModel.getTexture().setUseFakeLighting(true);
		
		//Flowers
		TexturedModel flowerModel = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader), new ModelTexture(loader.loadTexture("flower")));
		flowerModel.getTexture().setHasTransparency(true);
		flowerModel.getTexture().setUseFakeLighting(true);
		
		//Bunny
		TexturedModel stanfordBunny = new TexturedModel(OBJLoader.loadObjModel("stanfordBunny", loader), new ModelTexture(loader.loadTexture("white")));
		
		//******************************************
		
		
		//********** CREATE ENTITIES **********
		
		//Stalls
		Entity entity = new Entity(staticModel, new Vector3f(5, -3, -20), 0, 120, 0, 1);
		Entity secondEntity = new Entity(staticModel, new Vector3f(-5, -3, -20), 0, 210, 0, 1);
		
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		
		//Coniferous trees
		List<Entity> trees = new ArrayList<Entity>();
		for(int i = 0; i < 200; i++) {
			int randZ = rand.nextInt(-800, 0);
			int randX = rand.nextInt(-800, 800);
			trees.add(new Entity(treeModel, new Vector3f(randX, -3, randZ), 0, 0, 0, 5));
		}
		
		//Deciduous trees
		List<Entity> trees2 = new ArrayList<Entity>();
		for(int i = 0; i < 300; i++) {
			int randZ = rand.nextInt(-800, 0);
			int randX = rand.nextInt(-800, 800);
			trees2.add(new Entity(tree2Model, new Vector3f(randX, -3, randZ), 0, 0, 0, 0.6f));
		}
		
		//Ferns
		List<Entity> ferns = new ArrayList<Entity>();
		for(int i = 0; i < 500; i++) {
			int randZ = rand.nextInt(-800, 0);
			int randX = rand.nextInt(-800, 800);
			ferns.add(new Entity(fernModel, new Vector3f(randX, -3, randZ), 0, 0, 0, 0.6f));
		}
		
		//Grass
		List<Entity> grass = new ArrayList<Entity>();
		for(int i = 0; i < 500; i++) {
			int randZ = rand.nextInt(-800, 0);
			int randX = rand.nextInt(-800, 800);
			grass.add(new Entity(grassModel, new Vector3f(randX, -3, randZ), 0, 0, 0, 2));
		}
		
		//Flowers
		List<Entity> flowers = new ArrayList<Entity>();
		for(int i = 0; i < 300; i++) {
			int randZ = rand.nextInt(-800, 0);
			int randX = rand.nextInt(-800, 800);
			flowers.add(new Entity(flowerModel, new Vector3f(randX, -3, randZ), 0, 0, 0, 1.5f));
		}
		
		//*************************************
		
		
		//********** CREATE PLAYER **********
		Player player = new Player(stanfordBunny, new Vector3f(100, 0, -50), 0, 0, 0, 1);
		
		
		//********** CREATE LIGHT **********
		Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));
		
		
		//********** CREATE TERRAIN **********
		Terrain terrain = new Terrain(0, -800, loader, texturePack, blendMap);
		Terrain terrain2 = new Terrain(1, -800, loader, texturePack, blendMap);
		
		
		//********** CREATE CAMERA **********
		Camera camera = new Camera(player);
		
		
		//********** RENDER SCENE **********
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()) {
			camera.move();
			player.move();
			
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			renderer.processEntity(entity);
			renderer.processEntity(secondEntity);
			trees.forEach(tree -> renderer.processEntity(tree));
			trees2.forEach(tree -> renderer.processEntity(tree));
			ferns.forEach(fern -> renderer.processEntity(fern));
			grass.forEach(grassPatch -> renderer.processEntity(grassPatch));
			flowers.forEach(flower -> renderer.processEntity(flower));
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
			
		}
		//**********************************
		
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
