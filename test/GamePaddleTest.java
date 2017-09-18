import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import com.breakout.command.PaddleCommand;
import com.breakout.game.GamePaddle;

public class GamePaddleTest {

	private GamePaddle gamePaddle = new GamePaddle(0, 0, 0, 0, Color.GREEN);
	GamePaddle paddle;
	int Paddlenew=255;
	
	@Before
	public void executedBeforeEach() {
    	paddle = new GamePaddle(225, 450, 40, 15, Color.GREEN);
    	}
	
	@Test
	public void testGamePaddlePosX() {
		int expectedOutput = 0;
		int actualOutput = gamePaddle.getPosX();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testGamePaddlePosY() {
		int expectedOutput = 0;
		int actualOutput = gamePaddle.getPosY();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testGamePaddleWidth() {
		int expectedOutput = 0;
		int actualOutput = gamePaddle.getWidth();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testGamePaddleHeight() {
		int expectedOutput = 0;
		int actualOutput = gamePaddle.getHeight();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testforrightmove() {
		int direction = KeyEvent.VK_RIGHT;
		PaddleCommand p = new PaddleCommand(paddle);
		p.execute();
		System.out.println(paddle.getPosX());
		Paddlenew = paddle.getPosX();
		assertEquals(225, Paddlenew);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testforleftmove() {
		int direction = KeyEvent.VK_LEFT;
		PaddleCommand p = new PaddleCommand(paddle);
		p.execute();
		System.out.println(paddle.getPosX());
		Paddlenew = paddle.getPosX();
		assertEquals(225, Paddlenew);
		//fail("Not yet implemented");
	}
}
