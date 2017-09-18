

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.breakout.command.BallCommand;
import com.breakout.game.GameBall;

public class BallMoveTest {
	
	GameBall ball ;	
	@Before
	public void executedBeforeEach() {
    	ball = new GameBall(200, 400, 1, 1, Color.GREEN);
    	}
	
	@Test
	public void testPosX() {
		BallCommand bc = new BallCommand(ball);
		bc.execute();
	//	System.out.println(ball.getVelX());
	//	System.out.println(ball.getPosX());
		assertEquals(201, ball.getPosX());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testPosY() {
		BallCommand bc = new BallCommand(ball);
		bc.execute();
		assertEquals(401, ball.getPosY());
	}
}
