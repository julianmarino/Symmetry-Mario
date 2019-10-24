
package tomoyukishimizu;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.LevelFactory;

public class TomoyLevelGenerator implements LevelGenerator{

	public LevelInterface generateLevel(GamePlay playerMetrics) {
		LevelInterface level = new CustomizedLevel(320, 15, 0, 0, 0, playerMetrics);
		LevelFactory.register("TomoyLevel", level);

		return level;
	}
}



/* end of file */