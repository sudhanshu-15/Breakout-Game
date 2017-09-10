package com.breakout.command;

import com.breakout.game.GameTime;


import org.apache.log4j.Logger;

public class TimerCommand implements Command{
	private static Logger log = Logger.getLogger("timeLogger");
	private GameTime timeDisplay;
	private String initTime, text;
	private int runningTime;
	
	public TimerCommand(GameTime timeDisplay) {
		this.timeDisplay = timeDisplay;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//log.debug("in time execute");
		initTime = timeDisplay.getText();
		//log.debug(initTime);
		runningTime = timeDisplay.getTime();
		//log.debug(runningTime);
		timeDisplay.setTime(runningTime + 25);
		//log.debug(time);
		
		
		if (runningTime % 1000 == 0 ) {
			int currTime = runningTime/1000;
			int gameMin = currTime / 60;
			int gameSec = currTime % 60;
			String minText = (gameMin < 10 ? "0" : "") + gameMin;
			String secText = (gameSec < 10 ? "0" : "") + gameSec;
			text = minText + ":" + secText;
			log.debug(text);
			timeDisplay.setText(text);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		log.debug(initTime);
		
		timeDisplay.setText(initTime);
	}
}
