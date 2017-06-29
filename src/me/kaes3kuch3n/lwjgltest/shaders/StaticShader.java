package me.kaes3kuch3n.lwjgltest.shaders;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "src/me/kaes3kuch3n/lwjgltest/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/me/kaes3kuch3n/lwjgltest/shaders/fragmentShader.txt";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}
	
}
