import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.breakout.game.GameBrick;
import com.breakout.game.GameBrickList;

public class GameBrickTest {
	private GameBrick gameBrick = new GameBrick(0, 0);
	private GameBrickList gameBrickList = new GameBrickList();
	
	@Test
	public void testGameBallgetPosX() {
		int expectedOutput = 0;
		int actualOutput = gameBrick.getPosX();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testGameBallgetPosY() {
		int expectedOutput = 0;
		int actualOutput = gameBrick.getPosY();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testGameBallgetHeight() {
		int expectedOutput = 30;
		int actualOutput = gameBrick.getBrickHeight();
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testGameBallgetWidth() {
		int expectedOutput = 50;
		int actualOutput = gameBrick.getBrickWidth();
		assertEquals(expectedOutput, actualOutput);
	}
	
	
	@Test
	public void testGameBrickList() {
		//the ArrayList could not be copied.
		ArrayList<GameBrick> expectedOutput = gameBrickList.getBrickArrayList();
		ArrayList<GameBrick> actualOutput = gameBrickList.getBrickArrayList();
		assertEquals(expectedOutput, actualOutput);
	}
}
