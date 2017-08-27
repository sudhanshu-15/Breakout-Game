package com.breakout.game;

import java.awt.Color;
import java.awt.Dimension;

/*
 * Dimension related constants are defined in this class.
 * 
 */
public class GameConstants {
	
	//Frame Constants
	protected static final int BOARD_HEIGHT = 600;
	protected static final int BOARD_WIDTH = 600;
	protected static final Dimension boardDimensions= new Dimension(BOARD_HEIGHT, BOARD_WIDTH);	

	//Play start
	public static boolean play = false;
	
	//Brick constants
	public static int brickHeight = 40;
	public static int brickWidth = 80;
	public static int brickRow = 3;
	public static int brickColumn = 6;
	
	//Ball Constants
	public static int ballPosX = 120; 	//X-coordinate position of ball
	public static int ballPosY = 100;	//Y-coordinate position of ball
	public static int ballVelX = -1;	//Velocity on X-axis
	public static int ballVelY = -2;	//Velocity on Y-axis
	public static Color ballColor = Color.RED;  //Ball Color
	
	//Paddle Constants
	public static int paddlePosX = 300;
	public static int paddlePosY = 500;
	public static int paddleWidth = 50;
	public static int paddleHeight = 10;
	public static Color paddleColor = Color.GREEN;
	
}
