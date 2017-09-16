package com.breakout.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

public class GameButtonAction implements ActionListener {
	
	private static Logger buttonLog = Logger.getLogger("buttonLogger");

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String action = e.getActionCommand();
		
		switch(action){
		case "Start":
			buttonLog.info("Start pressed");
			break;
		case "Pause":
			buttonLog.info("Pause pressed");
			break;
		case "Undo":
			buttonLog.info("Undo pressed");
			break;
		case "Replay":
			buttonLog.info("Replay pressed");
			break;
		case "Save":
			buttonLog.info("Save pressed");
			break;
		case "Load":
			buttonLog.info("Load pressed");
			break;
		case "Change":
			buttonLog.info("Change layout");
			break;
		}

	}

}
