package com.breakout.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePaddleKey implements KeyListener {
	
	private GamePaddle paddle;
	
	public GamePaddleKey(GamePaddle paddle){
		this.paddle = paddle;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		paddle.setPaddleDirection(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
