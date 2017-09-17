package com.breakout.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class GamePaddle implements GameElementsInterface,Serializable{
	
	private static final long serialVersionUID = 9L;
	
	private int posX;
	private int posY;
	private int width;
	private int height;
	private Color paddleColor;
	private int paddleDirection;
	
	
	//Constuctor for paddle : needs postion, dimensions, color
	public GamePaddle(int posX, int posY, int width, int height, Color paddleColor) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.paddleColor = paddleColor;
		this.setPaddleDirection(1000);
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
	
	
	public int getPaddleDirection() {
		return paddleDirection;
	}

	public void setPaddleDirection(int paddleDirection) {
		this.paddleDirection = paddleDirection;
	}

	public void draw(Graphics g){
		g.setColor(paddleColor);				
		g.fillRect(posX, posY, width, height);
	}
	
	public Rectangle createCollider(int x, int y, int width, int height){
		return new Rectangle(x, y, width, height);
	}
	
	public void reset(){
		this.setPosX(GameConstants.PADDLE_POS_X);
	}
	
}
