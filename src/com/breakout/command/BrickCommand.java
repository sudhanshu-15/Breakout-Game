package com.breakout.command;

import java.io.Serializable;
import java.util.ArrayList;

import com.breakout.game.GameBrick;
import com.breakout.game.GameBrickList;

public class BrickCommand implements Command ,Serializable{
	
	private static final long serialVersionUID = 3L;
	
	private GameBrickList brickList;
	private ArrayList<GameBrick> brickArrayList;
	private ArrayList<Integer> deadBrickList;
	
	public BrickCommand(GameBrickList brickList, ArrayList<Integer> deadBrickList){
		this.brickList = brickList;
		this.brickArrayList = brickList.getBrickArrayList();
		this.deadBrickList = deadBrickList;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(Integer i : deadBrickList){
			GameBrick brick = brickArrayList.get(i);
			brick.setDead(true);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for(Integer i : deadBrickList){
			GameBrick brick = brickArrayList.get(i);
			brick.setDead(!brick.isDead());
		}
	}

}
