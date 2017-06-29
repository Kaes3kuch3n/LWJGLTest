package me.kaes3kuch3n.lwjgltest.enginetester;

import org.lwjgl.opengl.Display;

import me.kaes3kuch3n.lwjgltest.renderengine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		while(!Display.isCloseRequested()) {
			
			//game logic
			//render
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();
		
	}

}
