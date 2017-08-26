package com.breakout.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class Brick{
	public int brick2D[][];
	public int brickHeight;
	public int brickWidth;
	
	public Brick(int rows, int columns){
		brickHeight = 40;
		brickWidth = 80;
		brick2D = new int[rows][columns];
		
		for(int i = 0; i<brick2D.length;++i){
			for(int j = 0;j<brick2D[0].length;++j){
				brick2D[i][j] = 1;
			}
		}
	}
	
	public void drawBrick(Graphics2D g){
		for(int i =0; i<brick2D.length;++i){
			for(int j = 0; j<brick2D[0].length;++j){
				if(brick2D[i][j]>0){
					g.setColor(Color.WHITE);
					g.fillRect(j*brickWidth+60, i*brickHeight+40, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j*brickWidth+60, i*brickHeight+40, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int column){
		brick2D[row][column] = value;
	}
}
