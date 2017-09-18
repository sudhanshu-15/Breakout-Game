package com.breakout.game;

import javax.swing.JLabel;

public class GameTime extends JLabel{

	private String text;
	private int time;
	
	
	//Constructor for game time display, initializes to 00:00
	public GameTime(){
		text = "00:00";
		this.setText(text);
	}
	
	public void updateText(int runningTime){
		
		if (runningTime % 1000 == 0 ) {
			int currTime = runningTime/1000;
			int gameMin = currTime / 60;
			int gameSec = currTime % 60;
			String minText = (gameMin < 10 ? "0" : "") + gameMin;
			String secText = (gameSec < 10 ? "0" : "") + gameSec;
			text = minText + ":" + secText;
			this.setText(text);
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void reset(){
		this.setText("00:00");
		this.setTime(0);
	}
}
