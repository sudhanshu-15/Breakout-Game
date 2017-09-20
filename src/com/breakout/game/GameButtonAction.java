package com.breakout.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private int layoutFlag = 1;
	private GameBrickList brickList;
	private GameTime timer;
	private boolean pauseFlag = false;
	
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
			startGame();
			break;
		case "Pause":
			buttonLog.info("Pause pressed");
			pauseGame();
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
			changeStep();
			System.out.println("switch");
			break;
			
		}

	}
	
	private void changeStep() {
		// TODO Auto-generated method stub
		System.out.println("f1");
		if(layoutFlag==1){
			System.out.println("f2");

			//BorderLayout
			layoutFlag = 0;
			gameButtonPanel.removeAll();
			gameButtonPanel.getSouthPanel();
			gameButtonPanel.getNorthPanel().removeAll();
			gameButtonPanel.getSouthPanel().removeAll();
			
			gameButtonPanel.setLayout(new BorderLayout());
			
//			gameButtonPanel.setBackground(Color.BLUE);
//			gameButtonPanel.getNorthPanel().setBackground(Color.BLACK);
//			gameButtonPanel.getSouthPanel().setBackground(Color.BLACK);
			
			gameButtonPanel.getNorthPanel().add(gameButtonPanel.getUndoButton());
			gameButtonPanel.getNorthPanel().add(gameButtonPanel.getLoadButton());
			gameButtonPanel.getNorthPanel().add(gameButtonPanel.getPauseButton());
			
//			gameButtonPanel.getSouthPanel().add(gameButtonPanel.getUndoButton());
//			gameButtonPanel.getSouthPanel().add(gameButtonPanel.getReplayButton());
			
			gameButtonPanel.add(gameButtonPanel.getSaveButton(),BorderLayout.LINE_START);
			gameButtonPanel.add(gameButtonPanel.getStartButton(),BorderLayout.PAGE_START);
			gameButtonPanel.add(gameButtonPanel.getNorthPanel(),BorderLayout.CENTER);
			gameButtonPanel.add(gameButtonPanel.getChangeButton(),BorderLayout.PAGE_END);
			gameButtonPanel.add(gameButtonPanel.getReplayButton(),BorderLayout.LINE_END);
//			gameButtonPanel.add(gameButtonPanel.getPauseButton(),BorderLayout.CENTER);
//			gameButtonPanel.add(gameButtonPanel.getUndoButton(),BorderLayout.CENTER);
//			gameFrame.setLayout(new BoxLayout(gameFrame.getContentPane(), BoxLayout.Y_AXIS));
//			gameFrame.add(gameButtonPanel);
//			if(gameBoard!=null){
//				gameFrame.add(gameBoard);
//			}
		}
		else{
			//gameFrame.setLayout(new BorderLayout());
			System.out.println("f2");

			layoutFlag=1;	
			gameButtonPanel.removeAll();
			gameButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//			gameButtonPanel.setBackground(Color.BLUE);

			getButtons();
			gameFrame.add(gameButtonPanel, BorderLayout.NORTH);

		}
		gameButtonPanel.revalidate();
		gameButtonPanel.repaint();

	}
	public void getButtons(){
		gameButtonPanel.add(gameButtonPanel.getStartButton());
		gameButtonPanel.add(gameButtonPanel.getPauseButton());
		gameButtonPanel.add(gameButtonPanel.getUndoButton());
		gameButtonPanel.add(gameButtonPanel.getReplayButton());
		gameButtonPanel.add(gameButtonPanel.getLoadButton());
		gameButtonPanel.add(gameButtonPanel.getSaveButton());
		gameButtonPanel.add(gameButtonPanel.getChangeButton());


	}

	public void startGame(){
		
		if(gameButtonPanel.getStartButton().getText() == "Start"){
		ball = new GameBall(GameConstants.BALL_POS_X, GameConstants.BALL_POS_Y, GameConstants.BALL_VEL_X, GameConstants.BALL_VEL_Y, GameConstants.BALL_COLOR);
		paddle = new GamePaddle(GameConstants.PADDLE_POS_X, GameConstants.PADDLE_POS_Y, GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT, GameConstants.PADDLE_COLOR);
		brickList = new GameBrickList();
		timer= new GameTime();
		gameBoard = new GameBoard(ball, paddle, timer, brickList);
		gameFrame.add(gameBoard,BorderLayout.NORTH);
		gameBoard.setFocusable(true);
		gameBoard.requestFocusInWindow();
		gameBoard.setBounds(1,100,GameConstants.BOARD_WIDTH,GameConstants.BOARD_HEIGHT-60);
		gameBoard.gameLoop();
		gameControl = gameBoard.getGameControl();
		gameControl.setPlay(true);
		undoCount = 0;
		gameButtonPanel.getUndoButton().setEnabled(false);
		gameButtonPanel.getReplayButton().setEnabled(false);
		gameButtonPanel.getSaveButton().setEnabled(false);
		gameButtonPanel.getLoadButton().setEnabled(false);
		gameButtonPanel.getPauseButton().setEnabled(true);
		gameButtonPanel.getStartButton().setText("ReStart");
		//gameButtonPanel.getStartButton().setEnabled(false);
		
		gameFrame.add(gameButtonPanel,BorderLayout.NORTH);
		gameBoard.draw();

		}
		else if(gameButtonPanel.getStartButton().getText() == "ReStart"){
			System.out.println("Reset");
			gameControl.restart();
			gameControl.removeElementsFromMacroList();
			gameControl.setPlay(true);
		}
	}
	
	public void pauseGame(){
		 if(pauseFlag == true){
			 pauseFlag = false;
		 }else{
			 pauseFlag = true;
		 }
		 if(pauseFlag){
			 gameControl.setPlay(false);
			 gameButtonPanel.getUndoButton().setEnabled(true);
			 gameButtonPanel.getReplayButton().setEnabled(true);
			 gameButtonPanel.getSaveButton().setEnabled(true);
			 gameButtonPanel.getLoadButton().setEnabled(true);
			 gameButtonPanel.getPauseButton().setText("Resume");
			 gameFrame.add(gameButtonPanel, BorderLayout.NORTH);
		 }
		 else {
			 gameControl.setPlay(true);
			 gameButtonPanel.getUndoButton().setEnabled(false);
			 gameButtonPanel.getReplayButton().setEnabled(false);
			 gameButtonPanel.getSaveButton().setEnabled(false);
			 gameButtonPanel.getLoadButton().setEnabled(false);
			 gameButtonPanel.getPauseButton().setText("Pause");
			 gameFrame.add(gameButtonPanel,BorderLayout.NORTH);
		 }
		/*
		if(pauseFlag){
			//gameButtonPanel.getPauseButton().setText("Resume");
			System.out.println("I pressed pause");
			gameControl.setPlay(false);
			gameButtonPanel.getUndoButton().setEnabled(true);
			gameButtonPanel.getReplayButton().setEnabled(true);
			gameButtonPanel.getSaveButton().setEnabled(true);
			gameButtonPanel.getLoadButton().setEnabled(true);
			//gameButtonPanel.getPauseButton().setText("Resume");
		}
		else{
			System.out.println("I pressed Resume");
			gameControl.setPlay(false);
			gameButtonPanel.getUndoButton().setEnabled(false);
			gameButtonPanel.getReplayButton().setEnabled(false);
			gameButtonPanel.getSaveButton().setEnabled(false);
			gameButtonPanel.getLoadButton().setEnabled(false);
			//gameButtonPanel.getPauseButton().setText("Pause");
		}
		*/
		
		
	}

	private void loadGame() {
		GameLoad gameLoad = new GameLoad();
		if(gameLoad.Deserialize() !=null){
		ArrayList<MacroCommand> loadArray = gameLoad.Deserialize();
//		System.out.println(loadArray.size());
		MacroCommand macroUndo = loadArray.get(loadArray.size() - 1);
		ball = macroUndo.ball;
		paddle = macroUndo.paddle;
		brickList = macroUndo.brickList;
		timer = macroUndo.timer;
		
		if(gameBoard != null){
			gameFrame.getContentPane().remove(gameBoard);
		}
		gameBoard = new GameBoard(ball, paddle, timer, brickList);
		gameBoard.setBounds(1,100,GameConstants.BOARD_WIDTH,GameConstants.BOARD_HEIGHT-60);
		gameControl = gameBoard.getGameControl();
		gameControl.setMacroCommandArray(loadArray);
		gameFrame.getContentPane().add(gameBoard,BorderLayout.CENTER);
		gameBoard.draw();
		gameBoard.gameLoop();
		gameControl.setPlay(false);
		gameButtonPanel.getPauseButton().setEnabled(true);
		gameButtonPanel.getPauseButton().setText("Resume");
		gameButtonPanel.getStartButton().setText("Restart");
		gameButtonPanel.getReplayButton().setEnabled(true);
		gameButtonPanel.getUndoButton().setEnabled(true);
//		gameFrame.add(gameButtonPanel);
		gameBoard.setFocusable(true);
		gameBoard.requestFocusInWindow();
		
		}
		
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
