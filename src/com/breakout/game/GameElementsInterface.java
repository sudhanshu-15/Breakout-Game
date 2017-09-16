package com.breakout.game;

import java.awt.Rectangle;

public interface GameElementsInterface {

	public int getPosX();
	
	public int getPosY();
	
	public void setPosX(int posX);
	
	public void setPosY(int posY);
	
	public Rectangle createCollider(int x, int y, int width, int height);
	
	public void reset();
}
