package com.breakout.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.breakout.command.BallCommand;
import com.breakout.command.TimerCommand;
import com.breakout.command.BrickCommand;
import com.breakout.command.PaddleCommand;
import org.apache.log4j.Logger;

public class GameBoard extends JPanel implements ActionListener, KeyListener{

	//public boolean play = false;
	private static Logger startlog = Logger.getLogger("startLogger");
	private static Logger pauselog = Logger.getLogger("pauseLogger");
	private static Logger undolog = Logger.getLogger("undoLogger");
	private static Logger replaylog = Logger.getLogger("replayLogger");
	private GameBrick brick;
	private GameBall ball;
	private GamePaddle paddle;
	private GameTime timeDisplay;
	private int delay = 30;
	private Timer timer;
	private JButton startButton;
	private JButton pauseButton;
	private JButton undoButton;
	private JButton replayButton;
	private List<BallCommand> ballcmdList;
	private List<TimerCommand> timercmdList;
	private List<BrickCommand> brickcmdList;
	private List<PaddleCommand> paddlecmdList;
	private List<GameBrick> brickList;
	private int undoCount;
	private static Iterator<BallCommand> replayBallIterator;
	private static Iterator<BrickCommand> replayBrickIterator;
	private static Iterator<PaddleCommand> replayPaddleIterator;
	private static Iterator<TimerCommand> replayTimerIterator;
	
	
	private int runningTime;
	private static PaddleCommand paddleMoveKey;
	private static boolean keyPress;
	
	public GameBoard(GameBall ball, GamePaddle paddle, GameTime timeDisplay) {
		this.ball = ball;
		this.paddle = paddle;
		this.timeDisplay = timeDisplay;
		this.setSize(GameConstants.BOARD_DIMENSIONS);
		this.setBackground(Color.WHITE);
		this.setBounds(1, 10, GameConstants.BOARD_WIDTH,GameConstants.BOARD_HEIGHT-10);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setFocusTraversalKeysEnabled(false);
		this.timer = new Timer(delay, this);
		runningTime = 0;
		ballcmdList = new ArrayList<BallCommand>();
		timercmdList = new ArrayList<TimerCommand>();
		brickcmdList = new ArrayList<BrickCommand>();
		paddlecmdList = new ArrayList<PaddleCommand>();
		brickList = new ArrayList<GameBrick>();
		this.spawnBricks();
		this.keyPress = false;
		ActionClass actionEvent = new ActionClass();
		startButton = new JButton("START");
		startButton.setFocusable(false);
		startButton.addActionListener(actionEvent);
		startButton.setActionCommand("Start");
		this.add(startButton);
		pauseButton = new JButton("PAUSE");
		pauseButton.setFocusable(false);
		pauseButton.setEnabled(false);
		pauseButton.addActionListener(actionEvent);
		pauseButton.setActionCommand("Pause");
		this.add(pauseButton);
		undoButton = new JButton("UNDO");
		undoButton.setFocusable(false);
		undoButton.setEnabled(false);
		undoButton.addActionListener(actionEvent);
		undoButton.setActionCommand("Undo");
		this.add(undoButton);
		replayButton = new JButton("REPLAY");
		replayButton.setFocusable(false);
		replayButton.setEnabled(false);
		replayButton.addActionListener(actionEvent);
		replayButton.setActionCommand("Replay");
		this.add(replayButton);
		undoCount = 1;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawLine(0, GameConstants.BUTTON_BOTTOM_X_VALUE, this.getWidth(), GameConstants.BUTTON_BOTTOM_X_VALUE);
		this.add(timeDisplay);
		ball.draw(g);
		paddle.draw(g);
		
		for (GameBrick brick: brickList){
			if(!brick.isDead()){
				brick.draw(g);
			}
		}
		
		timer.start();
	}	
	
	public void draw(){
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (paddle.play) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT){
				paddleMoveKey = new PaddleCommand(paddle, e.getKeyCode());
				paddleMoveKey.execute();
				keyPress = true;
			}
		}
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
//		timer.start();
		ball.checkBounds(this.getWidth() - 20, this.getHeight()-20);
		
		runningTime += 5;
		//timeDisplay.updateText(runningTime);
		
		if(paddle.play){
			
			BallCommand ballCmd = new BallCommand(ball);
			TimerCommand timerCmd = new TimerCommand(timeDisplay);
			
			ballCmd.execute();
			ballcmdList.add(ballCmd);
			
			timerCmd.execute();
			timercmdList.add(timerCmd);
			
			Rectangle ballCollider = ball.createCollider(ball.getPosX(), ball.getPosY(), 20, 20);
			Rectangle paddleCollider = paddle.createCollider(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
			
			//manage ball and paddle interaction
			if(ballCollider.intersects(paddleCollider)){
				ball.setVelY(-(ball.getVelY()));
			}
			
			//manage ball and brick interaction
//			GAME: for(int i =0;i< brick.brickArray.length;++i){
//				for(int j = 0;j<brick.brickArray[0].length;++j){
//					if(brick.brickArray[i][j]>0){
//						int brickWidth = brick.brickWidth;
//						int brickHeight = brick.brickHeight;
//						int brickX = j*brickWidth+10;
//						int brickY = i*brickHeight+40;
//						
//						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
//						Rectangle brickRect = rect;
//						
//						if(ballRect.intersects(brickRect)){
//							brick.setBrickValue(0, i, j);
//							GameConstants.TOTAL_BRICKS--;
//							
//							if(ball.getPosX() + 19 <= brickRect.x || ball.getPosX() + 1 >= brickRect.x + brickRect.width){
//								ball.setVelX(-(ball.getVelX()));
//							}else{
//								ball.setVelY(-(ball.getVelY()));
//							}
//							break GAME;
//						}
//					}
//				}
//			}
			
			GameBrick deadBrick = brickList.get(0);
			Boolean hit = false;
			
			for(GameBrick brick : brickList){
				if(ballCollider.intersects(brick.getBrickCollider()) && !brick.isDead()){
					hit = true;
					deadBrick = brick;
					ball.setVelY(-ball.getVelY());
				}
			}
			
			if(hit){
				BrickCommand brickCmd = new BrickCommand(deadBrick, true);
				brickCmd.execute();
				brickcmdList.add(brickCmd);
			}else{
				BrickCommand brickCmd = new BrickCommand(deadBrick, false);
				brickCmd.execute();
				brickcmdList.add(brickCmd);
				
			}
			
			if(keyPress){
				paddlecmdList.add(paddleMoveKey);
			}else{
				PaddleCommand paddleMove = new PaddleCommand(paddle, 1000);
				paddleMove.execute();
				paddlecmdList.add(paddleMove);
			}
			
			if (allDead()) {
				JOptionPane.showMessageDialog(null, "Congradulations!!! Press Restart to play again.");
			}
			
			if (gameOver()) {
				JOptionPane.showMessageDialog(null, "Game Over! :( Press Restart to play again.");
			}
		}
		repaint();
	}
	
	public void spawnBricks(){
		for (int x = 1; x <= GameConstants.BRICK_COLUMN; x++){
			for (int y = 2; y <= GameConstants.BRICK_ROW+2; y++){
				brickList.add(new GameBrick(x * GameConstants.BRICK_WIDTH, y * GameConstants.BRICK_HEIGHT));
			}
		}
	}
	
	public GameBall getBall() {
		return ball;
	}

	public void setBall(GameBall ball) {
		this.ball = ball;
	}

	public GamePaddle getPaddle() {
		return paddle;
	}

	public void setPaddle(GamePaddle paddle) {
		this.paddle = paddle;
	}

	public List<BallCommand> getBallcmdList() {
		return ballcmdList;
	}

	public void setBallcmdList(List<BallCommand> ballcmdList) {
		this.ballcmdList = ballcmdList;
	}

	public List<TimerCommand> getTimercmdList() {
		return timercmdList;
	}

	public void setTimercmdList(List<TimerCommand> timercmdList) {
		this.timercmdList = timercmdList;
	}

	public List<BrickCommand> getBrickcmdList() {
		return brickcmdList;
	}

	public void setBrickcmdList(List<BrickCommand> brickcmdList) {
		this.brickcmdList = brickcmdList;
	}

	public List<PaddleCommand> getPaddlecmdList() {
		return paddlecmdList;
	}

	public void setPaddlecmdList(List<PaddleCommand> paddlecmdList) {
		this.paddlecmdList = paddlecmdList;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getUndoButton() {
		return undoButton;
	}

	public void setUndoButton(JButton undoButton) {
		this.undoButton = undoButton;
	}

	public boolean allDead() {
		int deadCount = 0;
		for (GameBrick brick : brickList) {
			if (brick.isDead()) {
				deadCount++;
			}
		}
		if (deadCount == GameConstants.TOTAL_BRICKS) {
			paddle.play = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean gameOver() {
		if (ball.getPosY() > GameConstants.BOARD_HEIGHT) {
			ball.setPosY(GameConstants.BALL_POS_Y);
			paddle.play = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	private class ActionClass implements ActionListener{
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			
			switch(action){
			case "Start":
				startlog.info("Start");
				startPress();
				break;
			case "Pause":
				pauselog.info("Pause");
				pausePress();
				break;
			case "Undo":
				undolog.info("Undo");
				undoPress();
				break;
			case "Replay":
				replaylog.info("Replay");
				replayPress();
				break;
			}
			
		}
		
		private void startPress(){
			startlog.info("Start pressed");
			paddle.play = !paddle.play;
			if (paddle.play && startButton.getText().equals("START")) {
				startButton.setText("RESTART");
				timer.start();
				pauseButton.setEnabled(true);
			}else{
				startlog.info("Reset presses");
				pauseButton.setEnabled(false);
				pauseButton.setText("PAUSE");
				startButton.setText("START");
				paddle.play = false;
				ballcmdList = new ArrayList<BallCommand>();
				timercmdList = new ArrayList<TimerCommand>();
				brickcmdList = new ArrayList<BrickCommand>();
				paddlecmdList = new ArrayList<PaddleCommand>();
				resetPress();
			}
			
		}

		private void resetPress(){
			timer.stop();
			ball.setPosX(GameConstants.BALL_POS_X);
			ball.setPosY(GameConstants.BALL_POS_Y);
			ball.setVelX(GameConstants.BALL_VEL_X);
			ball.setVelY(GameConstants.BALL_VEL_Y);
			paddle.setPosX(GameConstants.PADDLE_POS_X);
			runningTime = 0;
			timeDisplay.updateText(runningTime);
			timeDisplay.setTime(runningTime);
			for(GameBrick brick : brickList){
				brick.setDead(false);
			}
			repaint();
		}
		
		private void pausePress(){
			paddle.play = !paddle.play;
			if (!paddle.play) {
				timer.stop();
				pauseButton.setText("UNPAUSE");
				undoButton.setEnabled(true);
				replayButton.setEnabled(true);
			}else{
				timer.start();
				pauseButton.setText("PAUSE");
				undoButton.setEnabled(false);
				replayButton.setEnabled(false);
			}
		}
		
		private void undoPress(){
			undolog.info(ballcmdList.size());
			
			undolog.info(brickcmdList.size());
			
			undolog.info(paddlecmdList.size());
			
			undolog.info(timercmdList.size());
			
			int position = ballcmdList.size() - undoCount;
			if (position >= 0){
				BallCommand undoBall = ballcmdList.get(position);
				BrickCommand undoBrick = brickcmdList.get(position);
				PaddleCommand undoPaddle = paddlecmdList.get(position);
				TimerCommand undoTimer = timercmdList.get(position);
				undoBall.undo();
				undoBrick.undo();
				undoPaddle.undo();
				undoTimer.undo();
				undoCount += 1;
			}else{
				undoButton.setEnabled(false);
			}
		}
		
		private void replayPress(){
			resetPress();
			replayBallIterator = ballcmdList.iterator();
			replayBrickIterator = brickcmdList.iterator();
			replayPaddleIterator = paddlecmdList.iterator();
			replayTimerIterator = timercmdList.iterator();
			replaylog.info("Replay Pressed");
			new Thread(){
				public void run(){
					while(replayBallIterator.hasNext() && replayBrickIterator.hasNext() && replayPaddleIterator.hasNext() && replayTimerIterator.hasNext()){
						BallCommand replayBall = replayBallIterator.next();
						BrickCommand replayBrick = replayBrickIterator.next();
						PaddleCommand replayPaddle = replayPaddleIterator.next();
						TimerCommand replayTimer = replayTimerIterator.next();
						
						replayBall.undo();
						replayBrick.execute();
						replayPaddle.undo();
						replayTimer.undo();
						try {
							SwingUtilities.invokeAndWait(new Runnable(){

								@Override
								public void run() {
									// TODO Auto-generated method stub
									repaint();
									try {
										java.lang.Thread.sleep(30, 0);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										replaylog.error("Interrupted exception", e);
									}
								}
								
							});
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							replaylog.error("Invocation exception", e);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							replaylog.error("Interrupted exception",e);
						}
					}
				}
			}.start();
			
		}
		
	}
}
