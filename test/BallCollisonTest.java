

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.breakout.game.GameBall;
import com.breakout.game.GameBrick;
import com.breakout.game.GamePaddle;
import com.breakout.command.BallCommand;
import com.breakout.command.MacroCommand;

public class BallCollisonTest {

	Boolean dead = true;
	GameBall ball = new GameBall(11,11,1,1, Color.BLACK);
	GameBrick brick = new GameBrick(12,12);
	BallCommand bc = new BallCommand(ball);
	
	@Before 
	public void excutedbeforeeach() {
	}

	@Test
	public void testBallCollision() {

		
		Rectangle ballCollider = ball.createCollider(ball.getPosX(), ball.getPosY(), 20, 20);
		
		bc.execute();
		
		if(ballCollider.intersects(brick.getBrickCollider()))
		{ dead = false;}
		
		assertEquals(false, dead);
	}
	@Test
	public void testGameBallgetPosX() {
		int expectedOutput = 11;
		int actualOutput = ball.getPosX();
		assertEquals(expectedOutput, actualOutput);
	}
		
	@Test
	public void testGameBallgetPosY() {
		int expectedOutput = 11;
		int actualOutput = ball.getPosY();
		assertEquals(expectedOutput, actualOutput);
		}
	
	@Test
	public void testGameBallgetVelX() {
		int expectedOutput = 1;
		int actualOutput = ball.getVelX();
		assertEquals(expectedOutput, actualOutput);
		}
	
	@Test
	public void testGameBallgetVelY() {
		int expectedOutput = 1;
		int actualOutput = ball.getVelY();
		assertEquals(expectedOutput, actualOutput);
		}
}
