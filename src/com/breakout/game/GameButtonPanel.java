package com.breakout.game;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameButtonPanel extends JPanel{
	
	private JButton startButton;
	private JButton pauseButton;
	private JButton undoButton;
	private JButton replayButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton changeButton;
	private GameButtonAction buttonAction;
	private GameBoard board;
	private GameControl gameControl;
	private JFrame gameFrame;
	
	
	public GameButtonPanel(JFrame gameFrame){
		setLayout(new FlowLayout());
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		undoButton = new JButton("Undo");
		replayButton = new JButton("Replay");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		changeButton = new JButton("Change");
		
		startButton.setFocusable(false);
		pauseButton.setFocusable(false);
		undoButton.setFocusable(false);
		replayButton.setFocusable(false);
		saveButton.setFocusable(false);
		loadButton.setFocusable(false);
		changeButton.setFocusable(false);
		
		this.add(startButton);
		this.add(pauseButton);
		this.add(undoButton);
		this.add(replayButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(changeButton);
		
//		this.board = board;
//		this.gameControl = board.getGameControl();
		this.gameFrame = gameFrame;
		
		buttonAction = new GameButtonAction(gameFrame, this);
		
		startButton.addActionListener(buttonAction);
		pauseButton.addActionListener(buttonAction);
		undoButton.addActionListener(buttonAction);
		replayButton.addActionListener(buttonAction);
		saveButton.addActionListener(buttonAction);
		loadButton.addActionListener(buttonAction);
		changeButton.addActionListener(buttonAction);
		
		startButton.setActionCommand("Start");
		pauseButton.setActionCommand("Pause");
		undoButton.setActionCommand("Undo");
		replayButton.setActionCommand("Replay");
		saveButton.setActionCommand("Save");
		loadButton.setActionCommand("Load");
		changeButton.setActionCommand("Change");
		
		this.setSize(GameConstants.BUTTON_PANEL_DIMENSIONS);
		
	}
	
}
