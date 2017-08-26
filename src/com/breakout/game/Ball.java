package com.breakout.game;

import java.awt.Color;
import java.awt.Graphics;

public class Ball implements GameElementInterface{
	
	//Ball class for the game
	
	private int posX; 	//X-coordinate position of ball
	private int posY;	//Y-coordinate position of ball
	private int velX;	//Velocity on X-axis
	private int velY;	//Velocity on Y-axis
	
	public Ball(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		
		velX = 0;
		velY = 0;
		
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.ORANGE);				//TO-DO: Add variable for color
		g.fillOval(posX, posY, 20, 20); 		//TO-DO: Add import for constants and add dimensions
	}
}
