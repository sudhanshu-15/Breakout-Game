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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.breakout.command.BallCommand;
import com.breakout.command.TimerCommand;
import com.breakout.command.BrickCommand;

public class GameBoard extends JPanel implements ActionListener, KeyListener{

	//public boolean play = false;
	private GameBrick brick;
	private GameBall ball;
	private GamePaddle paddle;
	private GameTime timeDisplay;
	private int delay = 5;
	private Timer timer;
	private JButton startButton;
	private JButton pauseButton;
	private JButton undoButton;
	private JButton replayButton;
	private List<BallCommand> ballcmdList;
	private List<TimerCommand> timercmdList;
	private List<GameBrick> brickList;
	
	private int runningTime;
	
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
//		this.timer.start();
		ballcmdList = new ArrayList<BallCommand>();
		timercmdList = new ArrayList<TimerCommand>();
		brickList = new ArrayList<GameBrick>();
		this.spawnBricks();
		startButton = new JButton("START");
		startButton.setFocusable(false);
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("startButton Pressed: "+paddle.play);
				paddle.play = !paddle.play;
				if (paddle.play && startButton.getText().equals("START")) {
					startButton.setText("RESTART");
					timer.start();
					pauseButton.setEnabled(true);
				}
				
				else {
					pauseButton.setEnabled(false);
					pauseButton.setText("PAUSE");
					startButton.setText("START");
					paddle.play = false;
					timer.stop();
					ball.setPosX(GameConstants.BALL_POS_X);
					ball.setPosY(GameConstants.BALL_POS_Y);
					ball.setVelX(GameConstants.BALL_VEL_X);
					ball.setVelY(GameConstants.BALL_VEL_Y);
					paddle.setPosX(GameConstants.PADDLE_POS_X);
					//brick = new GameBrick(GameConstants.BRICK_ROW, GameConstants.BRICK_COLUMN);
					GameConstants.TOTAL_BRICKS = GameConstants.BRICK_ROW * GameConstants.BRICK_COLUMN;
					runningTime = 0;
					timeDisplay.updateText(runningTime);
					timeDisplay.setTime(runningTime);
					repaint();
				}
			}
			
		});
		this.add(startButton);
		pauseButton = new JButton("PAUSE");
		pauseButton.setFocusable(false);
		pauseButton.setEnabled(false);
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				paddle.play = !paddle.play;
				if (!paddle.play) {
					timer.stop();
					pauseButton.setText("UNPAUSE");
				}
				else {
					timer.start();
					pauseButton.setText("PAUSE");
				}
			}
			
		});
		this.add(pauseButton);
		undoButton = new JButton("UNDO");
		this.add(undoButton);
		replayButton = new JButton("REPLAY");
		this.add(replayButton);
	}
	
	public void paint(Graphics g){
//		timer.start();
		super.paint(g);	
		this.add(timeDisplay);
		ball.draw(g);
		paddle.draw(g);
		
		for (GameBrick brick: brickList){
			if(!brick.isDead()){
				brick.draw(g);
			}
		}
		
		timer.start();
		
		if(GameConstants.TOTAL_BRICKS <= 0){
			paddle.play = false;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Congratualtions", 200, 300);
			
			//Restart Button
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Play Again", 170, 340);	
			runningTime = 0;
			timeDisplay.updateText(runningTime);
		}
		
		if(ball.getPosY() > GameConstants.BOARD_HEIGHT){
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Game Over", 200, 300);
			
			//Restart Button
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 170, 340);
			paddle.play = false;
			runningTime = 0;
			timeDisplay.updateText(runningTime);
			
		}
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			paddle.checkBounds(e.getKeyCode(), GameConstants.BOARD_WIDTH - 60 , 5);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			paddle.checkBounds(e.getKeyCode(), GameConstants.BOARD_WIDTH - 60 , 5);
		}	
		repaint();
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
			
//			ball.setPosX(ball.getPosX()+ball.getVelX());
//			ball.setPosY(ball.getPosY()+ ball.getVelY());
			
			Rectangle ballCollider = ball.createCollider(ball.getPosX(), ball.getPosY(), 20, 20);
			Rectangle paddleRect = new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
			
			//manage ball and paddle interaction
			if(ballCollider.intersects(paddleRect)){
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
			}else{
				BrickCommand brickCmd = new BrickCommand(deadBrick, false);
				brickCmd.execute();
				
			}
			
		}
		repaint();
	}
	
	public void spawnBricks(){
		for (int x = 1; x < 11; x++){
			for (int y = 2; y < 6; y++){
				brickList.add(new GameBrick(x * GameConstants.BRICK_WIDTH, y * GameConstants.BRICK_HEIGHT));
			}
		}
	}
}
