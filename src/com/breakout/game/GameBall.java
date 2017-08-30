package com.breakout.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

public class GameBall implements Observer, GameElementsInterface{
	
	//Ball class for the game breakout
	
	private int posX; 	//X-coordinate position of ball
	private int posY;	//Y-coordinate position of ball
	private int velX;	//Velocity on X-axis
	private int velY;	//Velocity on Y-axis
	private Color ballColor;  //Ball Color
	
	public GameBall(int posX, int posY,int velX, int velY, Color ballColor ){
		this.posX = posX;
		this.posY = posY;
		
		this.velX = velX;
		this.velY = velY;
		this.ballColor = ballColor;
		
	}
	
	public GameBall(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		
		this.velX = -1;
		this.velY = -2;
		this.ballColor = Color.RED;
	}
	
	@Override
	public int getPosX() {
		return posX;
	}
	
	@Override
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	@Override
	public int getPosY() {
		return posY;
	}

	@Override
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
		
		g.setColor(ballColor);				// Fixed Color variable for setting ballColor
		g.fillOval(posX, posY, 20, 20); 		//TO-DO: Add import for constants and add dimensions		
	}
	
	public void checkBounds(int boundaryX, int boundaryY){		// Function to check for boundary of the game window
		if (posX < 0 || posX > boundaryX){						//Changes velocity to negative if boundary in reached
			velX = -velX;
		}
		/*
		if (posX == boundaryX) {
			velX = -velX;
		}
		if (posX == 0) {
			velX = velX;
		}
		*/
		if (posY < 0){						//TO-DO: Add logic for game-over when ball goes beyond max Y
			velY = -velY;
		}
//		if(posY > boundaryY){
//			GamePaddle.play = false;
//		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//setPosX(getPosX() + getVelX());
		//setPosY(getPosY() + getVelY());
		posX += getVelX();
		posY += getVelY();
	}
}
