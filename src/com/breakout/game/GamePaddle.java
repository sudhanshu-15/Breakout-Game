package com.breakout.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class GamePaddle implements GameElementsInterface{
	
	private int posX;
	private int posY;
	private int width;
	private int height;
	private Color paddleColor;
	public boolean play = false;
	
	
	//Constuctor for paddle : needs postion, dimensions, color
	public GamePaddle(int posX, int posY, int width, int height, Color paddleColor) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.paddleColor = paddleColor;
	}
	
	//Constuctor with only position
	public GamePaddle(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		this.width = 40;
		this.height = 15;
		this.paddleColor = Color.GREEN;
	}

	@Override
	public int getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return posY;
	}

	@Override
	public void setPosX(int posX) {
		// TODO Auto-generated method stub
		this.posX = posX;
	}

	@Override
	public void setPosY(int posY) {
		// TODO Auto-generated method stub
		this.posY = posY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(Graphics g){
		g.setColor(paddleColor);				
		g.fillRect(posX, posY, width, height);
	}
	
	public Rectangle createCollider(int x, int y, int width, int height){
		return new Rectangle(x, y, width, height);
	}
	
//	//Function checks for the direction key pressed and also takes in the parameters for
//	//Max and Min boundary
//	//If the paddle reaches Max/Min boundary it sticks to the boundary 
//	public void checkBounds(int direction, int boundaryMax, int boundaryMin){
//		if (direction == KeyEvent.VK_RIGHT) {
//			if (posX >= boundaryMax) {
//				posX = boundaryMax;
//			}else {
//				moveRight();
//			}
//		}else if (direction == KeyEvent.VK_LEFT) {
//			if (posX <= boundaryMin) {
//				posX = boundaryMin;
//			}else {
//				moveLeft();
//			}
//		}
//	}
//	
//	//Function to move paddle to right
//	public void moveRight() {
//		play = true;
//		posX += 30;
//	}
//	
//	//Function to move paddle to left
//	public void moveLeft() {
//		play = true;
//		posX -= 30;
//	}	
	
}
