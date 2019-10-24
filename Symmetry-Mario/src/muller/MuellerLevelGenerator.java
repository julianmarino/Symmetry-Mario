package muller;

import java.util.Random;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.LevelFactory;
import dk.itu.mario.level.CustomizedLevel;
import dk.itu.mario.level.Level;

public class MuellerLevelGenerator implements LevelGenerator{

	public LevelInterface generateLevel(GamePlay playerMetrics) {
		//LevelInterface level = new CustomizedLevel(320,15,new Random().nextLong(),1,1,playerMetrics);
		LevelInterface level = new MartinMuellerLevel(320,15,new Random().nextLong(),1,0,playerMetrics);
		LevelFactory.register("MuellerLevel", level);
		return level;
	}

}
