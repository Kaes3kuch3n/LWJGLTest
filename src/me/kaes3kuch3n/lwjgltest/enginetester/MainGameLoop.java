package me.kaes3kuch3n.lwjgltest.enginetester;

import org.lwjgl.opengl.Display;

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
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
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
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()) {
			//game logic
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
