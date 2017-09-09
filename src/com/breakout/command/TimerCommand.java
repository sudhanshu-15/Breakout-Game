package com.breakout.command;

import com.breakout.game.GameTime;

public class TimerCommand implements Command{
	private GameTime timeDisplay;
	private String initTime, text;
	private int runningTime;
	
	public TimerCommand(GameTime timeDisplay) {
		this.timeDisplay = timeDisplay;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//System.out.println("in time execute");
		initTime = timeDisplay.getText();
		//System.out.println(initTime);
		runningTime = timeDisplay.getTime();
		//System.out.println(runningTime);
		timeDisplay.setTime(runningTime + 10);
		//System.out.println(time);
		
		if (runningTime % 1000 == 0 ) {
			int currTime = runningTime/1000;
			int gameMin = currTime / 60;
			int gameSec = currTime % 60;
			String minText = (gameMin < 10 ? "0" : "") + gameMin;
			String secText = (gameSec < 10 ? "0" : "") + gameSec;
			text = minText + ":" + secText;
			//System.out.println(text);
			timeDisplay.setText(text);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		//System.out.println(initTime);
		timeDisplay.setText(initTime);
	}
}
