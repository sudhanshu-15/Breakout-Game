package com.breakout.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.breakout.command.MacroCommand;

public class GameButtonAction implements ActionListener {
	
	private static Logger buttonLog = Logger.getLogger("buttonLogger");
	
	private GameBoard gameBoard;
	private GameControl gameControl;
	private int undoCount;
	private Iterator<MacroCommand> macroCommandIterator;
	
	public GameButtonAction(GameBoard gameBoard, GameControl gameControl){
		this.gameBoard = gameBoard;
		this.gameControl = gameControl;
		this.undoCount = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		String action = e.getActionCommand();
		
		switch(action){
		case "Start":
			buttonLog.info("Start pressed");
			gameControl.setPlay(true);
			undoCount = 0;
			break;
		case "Pause":
			buttonLog.info("Pause pressed");
			gameControl.setPlay(false);
			break;
		case "Undo":
			buttonLog.info("Undo pressed");
			undoStep();
			break;
		case "Replay":
			buttonLog.info("Replay pressed");
			replayStep();
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
	
	public void undoStep(){
		int i = gameControl.getMacroCommandArray().size() - undoCount -1;
		if (i >= 0){
			MacroCommand macroUndo = gameControl.getMacroCommandArray().get(i);
			macroUndo.undo();
			undoCount++;
		}
	}
	
	public void replayStep(){
		macroCommandIterator = gameControl.getMacroCommandArray().iterator();
		gameControl.reset();
		gameBoard.draw();
		
		new Thread(){
			public void run(){
				while(macroCommandIterator.hasNext()){
					
					try {
						SwingUtilities.invokeAndWait(new Runnable(){
							MacroCommand macroReplay = macroCommandIterator.next();
							@Override
							public void run() {
								// TODO Auto-generated method stub
								macroReplay.undo();
								try {
									java.lang.Thread.sleep(30);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						});
					} catch (InvocationTargetException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}
