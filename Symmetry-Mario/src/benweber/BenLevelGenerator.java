package benweber;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.LevelFactory;

public class BenLevelGenerator implements LevelGenerator{

	public LevelInterface generateLevel(GamePlay playerMetrics) {
		LevelInterface level = new CustomizedLevel(320, 15, 0, 0, 0, playerMetrics);
		LevelFactory.register("BenLevel", level);
		return level;
	}
}
