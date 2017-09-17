package com.breakout.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.breakout.command.MacroCommand;

public class GameButtonAction implements ActionListener {

	private static Logger buttonLog = Logger.getLogger("buttonLogger");

	private GameBoard gameBoard;
	private GameControl gameControl;
	private int undoCount;
	private Iterator<MacroCommand> macroCommandIterator;
	private GameButtonPanel gameButtonPanel;
	private JFrame gameFrame;
	private GameBall ball;
	private GamePaddle paddle;
	private GameBrickList brickList;
	private GameTime timer;

	public GameButtonAction(JFrame gameFrame, GameButtonPanel gameButtonPanel){
		this.gameFrame = gameFrame;
		this.gameButtonPanel = gameButtonPanel;
		this.undoCount = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub


		String action = e.getActionCommand();

		switch(action){
		case "Start":
			buttonLog.info("Start pressed");
			ball = new GameBall(GameConstants.BALL_POS_X, GameConstants.BALL_POS_Y, GameConstants.BALL_VEL_X, GameConstants.BALL_VEL_Y, GameConstants.BALL_COLOR);
			paddle = new GamePaddle(GameConstants.PADDLE_POS_X, GameConstants.PADDLE_POS_Y, GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT, GameConstants.PADDLE_COLOR);
			brickList = new GameBrickList();
			timer= new GameTime();
			gameBoard = new GameBoard(ball, paddle, timer, brickList);
			gameFrame.add(gameBoard);
			gameBoard.draw();
			gameBoard.setFocusable(true);
			gameBoard.requestFocusInWindow();
			gameBoard.gameLoop();
			gameControl = gameBoard.getGameControl();
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
			saveGame();
			break;
		case "Load":
				buttonLog.info("Load pressed");
				loadGame();
			break;
		case "Change":
			buttonLog.info("Change layout");

			break;
		}

	}

	private void loadGame() {
		GameLoad gameLoad = new GameLoad();
		ArrayList<MacroCommand> loadArray = gameLoad.Deserialize();
//		System.out.println(loadArray.size());
		MacroCommand macroUndo = loadArray.get(loadArray.size() - 1);
		ball = macroUndo.ball;
		paddle = macroUndo.paddle;
		brickList = macroUndo.brickList;
		timer = macroUndo.timer;
		gameBoard = new GameBoard(ball, paddle, timer, brickList);
		gameControl = gameBoard.getGameControl();
		gameControl.setMacroCommandArray(loadArray);
		gameFrame.add(gameBoard);
		gameBoard.draw();
		gameBoard.gameLoop();
		gameControl.setPlay(true);
		
//	    macroUndo.undo();
//	    gameBoard.draw();
	}

	private void saveGame() {
		// TODO Auto-generated method stub
		GameSave gameSave = new GameSave(this.gameControl.getMacroCommandArray());
		if(gameSave.Serialize()) 
		{
			JOptionPane.showMessageDialog(null,
					"Save Successful",
					"Save",
					JOptionPane.INFORMATION_MESSAGE);

		}
		else 
		{

			JOptionPane.showMessageDialog(null,
					"Error Saving File",
					"Save",
					JOptionPane.ERROR_MESSAGE);
		}


	}

	public void undoStep(){
		int i = gameControl.getMacroCommandArray().size() - undoCount -1;
		buttonLog.info(i);
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
