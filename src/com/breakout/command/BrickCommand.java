package com.breakout.command;

import com.breakout.game.GameBrick;

public class BrickCommand implements Command {
	
	private GameBrick brick;
	private boolean hit;
	
	public BrickCommand(GameBrick brick, boolean hit){
		this.brick = brick;
		this.hit = hit;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(hit){
			brick.setDead(true);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		brick.setDead(false);
	}

}
