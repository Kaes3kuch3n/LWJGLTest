package me.kaes3kuch3n.lwjgltest.enginetester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import me.kaes3kuch3n.lwjgltest.entities.Camera;
import me.kaes3kuch3n.lwjgltest.entities.Entity;
import me.kaes3kuch3n.lwjgltest.models.RawModel;
import me.kaes3kuch3n.lwjgltest.models.TexturedModel;
import me.kaes3kuch3n.lwjgltest.renderengine.DisplayManager;
import me.kaes3kuch3n.lwjgltest.renderengine.Loader;
import me.kaes3kuch3n.lwjgltest.renderengine.Renderer;
import me.kaes3kuch3n.lwjgltest.shaders.StaticShader;
import me.kaes3kuch3n.lwjgltest.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		/*float[] vertices = {
				-0.5f, 0.5f, 0, 	//V0
				-0.5f, -0.5f, 0, 	//V1
				0.5f, -0.5f, 0, 	//V2
				0.5f, 0.5f, 0 		//V3
		};
		
		int[] indices = {
				0, 1, 3, 	//Top left triangle (V0, V1, V3)
				3, 1, 2		//Bottom right triangle (V3, V1, V2)
		};
		
		float[] textureCoords = {
				0, 0, 	//V0
				0, 1, 	//V1
				1, 1, 	//V2
				1, 0	//V3
		};*/
		
		float[] vertices = {			
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel staticModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			entity.increaseRotation(1, 1, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
