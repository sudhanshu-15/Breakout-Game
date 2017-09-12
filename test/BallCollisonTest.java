

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Rectangle;
import org.junit.Before;
import org.junit.Test;

import com.breakout.game.GameBall;
import com.breakout.game.GameBrick;
import com.breakout.game.GamePaddle;
import com.breakout.command.BallCommand;

public class BallCollisonTest {

	
	GameBall ball;
	@Before 
	public void excutedbeforeeach() {
	}

	@Test
	public void test() {
		Boolean dead = true;
		GameBall ball = new GameBall(11,11,1,1, Color.BLACK);
		GameBrick brick = new GameBrick(12,12);
		BallCommand bc = new BallCommand(ball);
		
		Rectangle ballCollider = ball.createCollider(ball.getPosX(), ball.getPosY(), 20, 20);
		
		bc.execute();
		
		if(ballCollider.intersects(brick.getBrickCollider()))
		{ dead = false;}
		
		assertEquals(false, dead);

		
	}}
