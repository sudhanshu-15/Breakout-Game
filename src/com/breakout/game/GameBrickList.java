package com.breakout.game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class GameBrickList implements Serializable {
	
	private static final long serialVersionUID = 8L;
	
	private ArrayList<GameBrick> brickArrayList;
	
	public GameBrickList(){
		brickArrayList = new ArrayList<GameBrick>();
		for(int x = 1; x <= GameConstants.BRICK_COLUMN; x++){
			for(int y = 2; y <= GameConstants.BRICK_ROW+2; y++){
				brickArrayList.add(new GameBrick(x * GameConstants.BRICK_WIDTH, y * GameConstants.BRICK_HEIGHT));
			}
		}
	}
	
	public void draw(Graphics g){
		for(GameBrick gameBrick : brickArrayList){
			if(!gameBrick.isDead()){
				gameBrick.draw(g);
			}
		}
	}
	
	public void reset(){
		for(GameBrick gameBrick : brickArrayList){
			gameBrick.setDead(false);
		}
	}

	public ArrayList<GameBrick> getBrickArrayList() {
		return brickArrayList;
	}

	public void setBrickArrayList(ArrayList<GameBrick> brickArrayList) {
		this.brickArrayList = brickArrayList;
	}
	
	
}
