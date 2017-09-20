package com.breakout.game;

import java.awt.Color;
import java.awt.Dimension;

/*
 * Dimension related constants are defined in this class.
 * 
 */
public class GameConstants {
	
	//Frame Constants
	protected static final int FRAME_HEIGHT = 750;
	protected static final int FRAME_WIDTH = 620;
	protected static final int BOARD_HEIGHT = 700;
	protected static final int BOARD_WIDTH = 620;
	protected static final Dimension BOARD_DIMENSIONS= new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	
	//Button Panel Constants
	protected static int BUTTON_PANEL_HEIGHT = 60;
	protected static int BUTTON_PANEL_WIDTH = 620;
	protected static final Dimension BUTTON_PANEL_DIMENSIONS= new Dimension(BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);

	protected static int BUTTON_NPANEL_HEIGHT = 40;
	protected static int BUTTON_NPANEL_WIDTH = 400;
	protected static final Dimension BUTTON_NPANEL_DIMENSIONS= new Dimension(BUTTON_NPANEL_WIDTH, BUTTON_NPANEL_HEIGHT);

	protected static int BUTTON_SPANEL_HEIGHT = 20;
	protected static int BUTTON_SPANEL_WIDTH = 60;
	protected static final Dimension BUTTON_SPANEL_DIMENSIONS= new Dimension(BUTTON_SPANEL_WIDTH, BUTTON_SPANEL_HEIGHT);

	//Play start
	public static boolean PLAY = false;
	
	//Brick constants
	public static int BRICK_HEIGHT = 30;
	public static int BRICK_WIDTH = 50;
	public static int BRICK_ROW = 3;
	public static int BRICK_COLUMN = 10;
	public static int TOTAL_BRICKS = BRICK_ROW * BRICK_COLUMN;
	
	//Ball Constants
	public static int BALL_POS_X = 120; 	//X-coordinate position of ball
	public static int BALL_POS_Y = 200;	//Y-coordinate position of ball
	public static int BALL_VEL_X = -5;	//Velocity on X-axis -1
	public static int BALL_VEL_Y = -5;	//Velocity on Y-axis -2
	public static Color BALL_COLOR = Color.RED;  //Ball Color
	public static int BALL_POS_TEST_X = 200;
	
	//Paddle Constants
	public static int PADDLE_POS_X = 300;
	public static int PADDLE_POS_Y = 500;
	public static int PADDLE_WIDTH = 50;
	public static int PADDLE_HEIGHT = 10;
	public static Color PADDLE_COLOR = Color.GREEN;
	
	public static int PADDLE_MOVE = 30;
	
	public static int PADDLE_RIGHT_BOUND = BOARD_WIDTH - 60;
	public static int PADDLE_LEFT_BOUND = 5;
	
	//button Constants
	public static int BUTTON_BOTTOM_X_VALUE = 31;
	
}
