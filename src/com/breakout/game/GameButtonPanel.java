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
	private JPanel northPanel;
	private JPanel southPanel;
	private GameControl gameControl;
	private JFrame gameFrame;
	
	
	

	public GameButtonPanel(JFrame gameFrame){
		setLayout(new FlowLayout());
		southPanel = new JPanel();
		northPanel = new JPanel();
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
		
		undoButton.setEnabled(false);
		replayButton.setEnabled(false);
		saveButton.setEnabled(false);
		pauseButton.setEnabled(false);
		
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
		northPanel.setSize(GameConstants.BUTTON_NPANEL_DIMENSIONS);
		southPanel.setSize(GameConstants.BUTTON_SPANEL_DIMENSIONS);
		this.setBounds(0,0,60,GameConstants.BOARD_WIDTH);
		northPanel.setBounds(0,0,20,GameConstants.BOARD_WIDTH);
		southPanel.setBounds(0,40,20,GameConstants.BOARD_WIDTH);

	}


	public JButton getChangeButton() {
		return changeButton;
	}


	public void setChangeButton(JButton changeButton) {
		this.changeButton = changeButton;
	}


	public JButton getStartButton() {
		return startButton;
	}


	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}


	public JButton getPauseButton() {
		return pauseButton;
	}


	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}


	public JButton getUndoButton() {
		return undoButton;
	}


	public void setUndoButton(JButton undoButton) {
		this.undoButton = undoButton;
	}


	public JButton getReplayButton() {
		return replayButton;
	}


	public void setReplayButton(JButton replayButton) {
		this.replayButton = replayButton;
	}


	public JButton getSaveButton() {
		return saveButton;
	}


	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}


	public JButton getLoadButton() {
		return loadButton;
	}


	public void setLoadButton(JButton loadButton) {
		this.loadButton = loadButton;
	}
	public JPanel getNorthPanel() {
		return northPanel;
	}


	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}


	public JPanel getSouthPanel() {
		return southPanel;
	}


	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	
}
