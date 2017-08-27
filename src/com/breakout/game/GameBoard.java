package com.breakout.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener, KeyListener{

	private GameBrick brick;
	private GameBall ball;
	private GamePaddle paddle;
	private int delay = 8;
	private Timer timer;

	
	public GameBoard(GameBrick brick, GameBall ball, GamePaddle paddle) {
		this.brick = brick;
		this.ball = ball;
		this.paddle = paddle;		
		this.setSize(GameConstants.boardDimensions);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setFocusTraversalKeysEnabled(false);
		this.timer = new Timer(delay, this);
//		this.timer.start();
	}
	
	public void paint(Graphics g){
		super.paint(g);		
		ball.draw(g);
		paddle.draw(g);
		brick.draw((Graphics2D)g);
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
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			paddle.checkBounds(e.getKeyCode(), GameConstants.BOARD_WIDTH - 50 , 10);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			paddle.checkBounds(e.getKeyCode(), GameConstants.BOARD_WIDTH - 50 , 10);
		}
		
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
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
		repaint();
	}
}
