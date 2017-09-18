import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.breakout.command.BallCommand;
import com.breakout.command.BrickCommand;
import com.breakout.command.PaddleCommand;
import com.breakout.command.TimerCommand;
import com.breakout.game.GameBall;
import com.breakout.game.GameBoard;
import com.breakout.game.GameBrick;
import com.breakout.game.GameConstants;
import com.breakout.game.GamePaddle;
import com.breakout.game.GameTime;

public class GameBoardTest {
	
	private GameBoard gameBoardTest;
	private GameBall ball;
	private GamePaddle paddle;
	private GameTime timeDisplay;
	private List<BallCommand> ballCmdUndoTest;
	private List<BrickCommand> brickCmdUndoTest;
	private List<PaddleCommand> paddleCmdUndoTest;
	private List<TimerCommand> timerCmdUndoTest;
	
	@Before
	public void setup(){
		ballCmdUndoTest = new ArrayList<BallCommand>();
		brickCmdUndoTest = new ArrayList<BrickCommand>();
		paddleCmdUndoTest = new ArrayList<PaddleCommand>();
		timerCmdUndoTest = new ArrayList<TimerCommand>();
		ball = new GameBall(GameConstants.BALL_POS_TEST_X, GameConstants.BALL_POS_Y, GameConstants.BALL_VEL_X, GameConstants.BALL_VEL_Y, GameConstants.BALL_COLOR);
		paddle = new GamePaddle(GameConstants.PADDLE_POS_X, GameConstants.PADDLE_POS_Y, GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT, GameConstants.PADDLE_COLOR);
//		brick = new GameBrick(GameConstants.BRICK_ROW, GameConstants.BRICK_COLUMN);
		timeDisplay = new GameTime();
		gameBoardTest = new GameBoard(ball, paddle, timeDisplay, null);
		for (int i = 0 ; i < 3 ; i++) {
			BallCommand ballCmd = new BallCommand(ball);
			ballCmd.execute();
			ballCmdUndoTest.add(ballCmd);
			
			PaddleCommand paddleCmd = new PaddleCommand(paddle);
			paddleCmd.execute();
			paddleCmdUndoTest.add(paddleCmd);
			
//			BrickCommand brickCmd = new BrickCommand(new GameBrick(null), false);
//			brickCmd.execute();
//			brickCmdUndoTest.add(brickCmd);
			
			TimerCommand timerCmd = new TimerCommand(timeDisplay);
			timerCmd.execute();
			timerCmdUndoTest.add(timerCmd);
		}
//		gameBoardTest.setBallcmdList(ballCmdUndoTest);
//		gameBoardTest.setBrickcmdList(brickCmdUndoTest);
//		gameBoardTest.setPaddlecmdList(paddleCmdUndoTest);
//		gameBoardTest.setTimercmdList(timerCmdUndoTest);
		gameBoardTest.draw();
	}

	@Test
	public void testReset() {
//		JButton resetButton = gameBoardTest.getStartButton();
//		resetButton.setText("RESTART");
//		resetButton.doClick();
//		GameBall ballTest = gameBoardTest.getBall();
//		Assert.assertEquals("Ball Reset",GameConstants.BALL_POS_X, ballTest.getPosX());
//		GamePaddle paddleTest = gameBoardTest.getPaddle();
//		Assert.assertEquals("Paddle Reset",GameConstants.PADDLE_POS_X, paddleTest.getPosX());
	}
	
	@Test
	public void testUndo() {
		for (int i = 0 ; i < 3 ; i ++) {
			System.out.println(ballCmdUndoTest.get(i).getInitX());
		}
//		JButton undoButton = gameBoardTest.getUndoButton();
//		undoButton.setEnabled(true);
//		undoButton.doClick();
//		int lastBallXPos = ballCmdUndoTest.get(2).getInitX();
//		Assert.assertEquals("Ball Undo", lastBallXPos,gameBoardTest.getBall().getPosX());
	}

}
