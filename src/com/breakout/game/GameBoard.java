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

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements KeyListener, Runnable, Observable {

	//public boolean play = false;
	private GameBrick brick;
	private GameBall ball;
	//private Rectangle ballRect;
	private GamePaddle paddle;
	//private Rectangle paddleRect;
	private GameTime timeDisplay;
	private int delay = 5;
	private Timer timer;
	private Thread game;
	private List<Observer> observers;
	
	private int runningTime;
	
	public GameBoard(GameBrick brick, GameBall ball, GamePaddle paddle, GameTime timeDisplay) {
		this.brick = brick;
		this.ball = ball;
		//ballRect = new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20);
		this.paddle = paddle;
		//paddleRect = new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
		this.timeDisplay = timeDisplay;
		this.setSize(GameConstants.BOARD_DIMENSIONS);
		this.setBackground(Color.WHITE);
		this.setBounds(1, 10, GameConstants.BOARD_WIDTH,GameConstants.BOARD_HEIGHT-10);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setFocusTraversalKeysEnabled(false);
		//this.timer = new Timer(delay, this);
		runningTime = 0;
		game = new Thread(this);
		game.start();
//		this.timer.start();
	}
	
	public void paint(Graphics g){
//		timer.start();
		super.paint(g);	
		this.add(timeDisplay);
		ball.draw(g);
		paddle.draw(g);
		brick.draw((Graphics2D)g);
		//timer.start();
		
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			paddle.checkBounds(e.getKeyCode(), GameConstants.BOARD_WIDTH - 60 , 5);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			paddle.checkBounds(e.getKeyCode(), GameConstants.BOARD_WIDTH - 60 , 5);
		}	
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!paddle.play){
				paddle.play = true;
				ball.setPosX(GameConstants.BALL_POS_X);
				ball.setPosY(GameConstants.BALL_POS_Y);
				ball.setVelX(GameConstants.BALL_VEL_X);
				ball.setVelY(GameConstants.BALL_VEL_Y);
				paddle.setPosX(GameConstants.PADDLE_POS_X);
				brick = new GameBrick(GameConstants.BRICK_ROW, GameConstants.BRICK_COLUMN);
				GameConstants.TOTAL_BRICKS = GameConstants.BRICK_ROW * GameConstants.BRICK_COLUMN;
				//repaint();
			}
		}
		//repaint();
	}
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
//		timer.start();
		ball.checkBounds(559,559);
		runningTime += 5;
		timeDisplay.updateText(runningTime);
		
		if(paddle.play){
			ball.setPosX(ball.getPosX()+ball.getVelX());
			ball.setPosY(ball.getPosY()+ ball.getVelY());
			
			Rectangle ballRect = new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20);
			Rectangle paddleRect = new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
			
			//manage ball and paddle interaction
			if(ballRect.intersects(paddleRect)){
				ball.setVelY(-(ball.getVelY()));
			}
			
			//manage ball and brick interaction
			GAME: for(int i =0;i< brick.brickArray.length;++i){
				for(int j = 0;j<brick.brickArray[0].length;++j){
					if(brick.brickArray[i][j]>0){
						int brickWidth = brick.brickWidth;
						int brickHeight = brick.brickHeight;
						int brickX = j*brickWidth+10;
						int brickY = i*brickHeight+40;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)){
							brick.setBrickValue(0, i, j);
							GameConstants.TOTAL_BRICKS--;
							
							if(ball.getPosX() + 19 <= brickRect.x || ball.getPosX() + 1 >= brickRect.x + brickRect.width){
								ball.setVelX(-(ball.getVelX()));
							}else{
								ball.setVelY(-(ball.getVelY()));
							}
							break GAME;
						}
					}
				}
			}
		}
		repaint();
	}
*/
	
	public void paddleCollision(Rectangle ballRect, Rectangle paddleRect) {
		if(ballRect.intersects(paddleRect)){
			ball.setVelY(-(ball.getVelY()));
		}
	}
	
	public void brickCollision(Rectangle ballRect) {
		for(int i =0;i< brick.brickArray.length;++i){
			for(int j = 0;j<brick.brickArray[0].length;++j){
				if(brick.brickArray[i][j]>0){
					int brickWidth = brick.brickWidth;
					int brickHeight = brick.brickHeight;
					int brickX = j*brickWidth+10;
					int brickY = i*brickHeight+40;
					
					Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
					Rectangle brickRect = rect;
					
					// hit bottom
					//if ((ball.getPosX() >= brickRect.getX()) && (ball.getPosX() <= brickRect.getX() + 1) && (ball.getPosY() == brickRect.getY() + brickRect.getHeight())) {
						//ball.setVelY(1);
					//}
					
					//if(ballRect.getY() == brickRect.y + brickHeight) {
						//ball.setVelY(1);
						//brick.setBrickValue(0, i, j);
					//}
					
					if(ballRect.intersects(brickRect)){
						brick.setBrickValue(0, i, j);
						GameConstants.TOTAL_BRICKS--;
						
						if(ball.getPosX() + 19 <= brickRect.x || ball.getPosX() + 1 >= brickRect.x + brickRect.width){
							ball.setVelX(-(ball.getVelX()));
						}
						else{
							ball.setVelY(-(ball.getVelY()));
						}
						
					}
				}
			}
		}
	}
 	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			observers = new ArrayList<Observer>();
			register(ball);
			register(timeDisplay);
			//int rT = 0;
			//rT += 20;
			int xBall = ball.getPosX();
			int yBall = ball.getPosY();
			Rectangle ballRect = new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20);
			Rectangle paddleRect = new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getWidth(), paddle.getHeight());
			//System.out.println(xBall + ":" + yBall);
			ball.checkBounds(559, 559);
			brickCollision(ballRect);
			paddleCollision(ballRect, paddleRect);
			//timeDisplay.updateText(rT);
			//System.out.println(rT);
			notifyObservers();
			
			repaint();
			
			try {
				//rT += 20;
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	@Override
	public void register(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}

	@Override
	public void unregister(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer o: observers) {
			o.update();
		}
	}
}
