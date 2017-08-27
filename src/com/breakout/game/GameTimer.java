package com.breakout.game;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameTimer extends JPanel implements Observer{

	private int gameMin = 0;
	private int gameSec = 0;
	private int runningTime = 0;
	private JTextField gameTimeText;
	
	public GameTimer() {
		// TODO Auto-generated constructor stub
		//TextField for time display.
		gameTimeText = new JTextField(5);
		gameTimeText.setEditable(false);
		gameTimeText.setFont(GameConstants.timerFont);
		gameTimeText.setText("00:00");
		gameTimeText.setHorizontalAlignment(JTextField.CENTER);
		
		//Panel to add TextField
		JPanel gameTimeDisplay = new JPanel();
		JLabel gameTimeLabel = new JLabel("Timer: ");
		gameTimeLabel.setForeground(Color.WHITE);
		gameTimeDisplay.add(gameTimeLabel);
		gameTimeDisplay.add(gameTimeText);
		gameTimeDisplay.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(1, 1));
		this.add(gameTimeDisplay);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (runningTime % 1000 == 0 ) {
			int currTime = runningTime/1000;
			gameMin = currTime / 60;
			gameSec = currTime % 60;
			String minText = (gameMin < 10 ? "0" : "") + gameMin;
			String secText = (gameSec < 10 ? "0" : "") + gameSec;
			gameTimeText.setText(minText + ":" + secText);
			
		}
	}

}
