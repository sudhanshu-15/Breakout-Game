package com.breakout.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GameBrick{
	public int brickArray[][];
	public int brickHeight;
	public int brickWidth;
	
	//Constructor which creates a Brick matrix
	public GameBrick(int rows, int columns){
		brickHeight = GameConstants.BRICK_HEIGHT;
		brickWidth = GameConstants.BRICK_WIDTH;
		brickArray = new int[rows][columns];
		
		for(int i = 0; i<brickArray.length;++i){
			for(int j = 0;j<brickArray[0].length;++j){
				brickArray[i][j] = 1;
			}
		}
	}
	
	public void draw(Graphics2D g){
		for(int i =0; i<brickArray.length;++i){
			for(int j = 0; j<brickArray[0].length;++j){
				if(brickArray[i][j]>0){
					g.setColor(Color.BLACK);
					g.fillRect(j*brickWidth+45, i*brickHeight+40, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.WHITE);
					g.drawRect(j*brickWidth+45, i*brickHeight+40, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int column){
		brickArray[row][column] = value;
	}
	
}
