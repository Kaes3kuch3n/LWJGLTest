package me.kaes3kuch3n.lwjgltest.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float distanceFromPlayer = 50;
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(100, 35, 50);
	private float pitch = 10;
	private float yaw = 0;
	private float roll = 0;
	
	private Player player;
	private static final float Y_CORRECTION_FACTOR = 4;
	
	public Camera(Player player) {
		this.player = player;
	}
	
	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
		if(Keyboard.isKeyDown(Keyboard.KEY_T)) System.out.println(angleAroundPlayer);
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	private void calculateCameraPosition(float horizDistance, float verticDistance) {
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + Y_CORRECTION_FACTOR + verticDistance;
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer -= zoomLevel;
		if(distanceFromPlayer < 15) {
			distanceFromPlayer = 15;
		} else if(distanceFromPlayer > 150) {
			distanceFromPlayer = 150;
		}
	}
	
	private void calculatePitch() {
		if(Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
			if(pitch < 0) {
				pitch = 0;
			} else if(pitch > 60) {
				pitch = 60;
			}
		}
	}
	
	private void calculateAngleAroundPlayer() {
		if(Mouse.isButtonDown(1)) {
			float angleChanger = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChanger;
			if(angleAroundPlayer >= 360) {
				angleAroundPlayer -= 360;
			} else if(angleAroundPlayer <= 0) {
				angleAroundPlayer += 360;
			}
		}
	}
	
}
