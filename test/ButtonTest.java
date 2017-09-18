import javax.swing.JButton;

import org.junit.Assert;
import org.junit.Test;

import com.breakout.game.GameBall;
import com.breakout.game.GameConstants;
import com.breakout.game.GamePaddle;
import com.breakout.game.GameButtonPanel;

public class ButtonTest {
	private GameButtonPanel gameButtonPanel;
/*	
	@Test
	public void testReset() {
		JButton resetButton = gameButtonPanel.getStartButton();
		resetButton.setText("RESTART");
		resetButton.doClick();
		GameBall ballTest = gameBoardTest.getBall();
		Assert.assertEquals("Ball Reset",GameConstants.BALL_POS_X, ballTest.getPosX());
		GamePaddle paddleTest = gameBoardTest.getPaddle();
		Assert.assertEquals("Paddle Reset",GameConstants.PADDLE_POS_X, paddleTest.getPosX());
	}
	
	@Test
	public void testUndo() {
		for (int i = 0 ; i < 3 ; i ++) {
			System.out.println(ballCmdUndoTest.get(i).getInitX());
		}
		JButton undoButton = gameBoardTest.getUndoButton();
		undoButton.setEnabled(true);
		undoButton.doClick();
		int lastBallXPos = ballCmdUndoTest.get(2).getInitX();
		Assert.assertEquals("Ball Undo", lastBallXPos,gameBoardTest.getBall().getPosX());
	}

*/
}
