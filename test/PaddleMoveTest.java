
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import com.breakout.command.PaddleCommand;
import com.breakout.game.GamePaddle;

public class PaddleMoveTest {
	GamePaddle paddle;
	
	int Paddlenew=255;
	
	@Before
	public void executedBeforeEach() {
    	paddle = new GamePaddle(225, 450, 40, 15, Color.GREEN);
    	}
	
	@Test
	public void testforrightmove() {
		int direction = KeyEvent.VK_RIGHT;
		PaddleCommand p = new PaddleCommand(paddle, direction);
		p.execute();
		System.out.println(paddle.getPosX());
		Paddlenew = paddle.getPosX();
		assertEquals(255, Paddlenew);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testforleftmove() {
		int direction = KeyEvent.VK_LEFT;
		PaddleCommand p = new PaddleCommand(paddle, direction);
		p.execute();
		System.out.println(paddle.getPosX());
		Paddlenew = paddle.getPosX();
		assertEquals(195, Paddlenew);
		//fail("Not yet implemented");
	}
	
	
}
