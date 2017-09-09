package com.breakout.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class GameBrick{
	
	private int posX;
	private int posY;
	private int brickHeight;
	private int brickWidth;
	private boolean dead;
	private Color brickColor;
	private Rectangle brickCollider;
	
	//Constructor which creates a Brick matrix
	public GameBrick(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		brickHeight = GameConstants.BRICK_HEIGHT;
		brickWidth = GameConstants.BRICK_WIDTH;
		this.dead = false;
		this.brickCollider = this.createCollider(posX, posY, brickWidth, brickHeight);
		
		Random random = new Random();
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		this.brickColor = new Color(red, green, blue);
		
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Rectangle getBrickCollider() {
		return brickCollider;
	}

	public void draw(Graphics g){
		g.setColor(brickColor);
		g.fillRect(posX, posY, brickWidth, brickHeight);
	}
	
	public Rectangle createCollider(int posX, int posY, int width, int height){
		return new Rectangle(posX, posY, width, height);
	}
}
