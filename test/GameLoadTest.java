import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.breakout.command.MacroCommand;
import com.breakout.game.GameControl;
import com.breakout.game.GameLoad;

public class GameLoadTest {
	
	GameLoad gameLoad;
	GameControl gameControl;
	@Before
	public void executedBeforeEach() {
	gameLoad = new GameLoad();
	}
	
	@Test
	public void testGameSave() {
		ArrayList<MacroCommand> expectedOutput = null;
		ArrayList<MacroCommand> actualOutput = gameLoad.Deserialize();
		assertEquals(expectedOutput, actualOutput);
	}
}