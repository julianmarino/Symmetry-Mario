package robinbaumgarten;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.LevelFactory;

public class RobinLevelGenerator implements LevelGenerator
{

	private float skill;
	private float exploring;
	
	public LevelInterface generateLevel(GamePlay playerMetrics) 
	{
		analyseMetrics(playerMetrics);
		
		LevelInterface level = new CustomizedLevel(skill, exploring);
		LevelFactory.register("RobinLevel", level);

		return level;
	}

	private void analyseMetrics(GamePlay pm) 
	{
		float relativeTime = pm.totalTime/194.6363636f;
		float jumpsNumber = pm.jumpsNumber;
		float relativeJumps = pm.jumpsNumber/90.36363636f;
		float timesPressedRun = pm.timesPressedRun; 
		float timeSpentRunning = pm.timeSpentRunning;
		float avgRunDuration = 0;
		if (timesPressedRun > 0)
			avgRunDuration = (float) timeSpentRunning / (float) timesPressedRun;
		float timeRunningRight = pm.timeRunningRight;
		float timeRunningLeft = pm.timeRunningLeft;
		float percentRight = (float) timeRunningRight/ (float) pm.totalTime;
		float percentLeft = (float) timeRunningLeft/ (float) pm.totalTime;
		float percentStanding = (pm.totalTime - timeRunningRight - timeRunningLeft) / pm.totalTime;
		
		int totalBlocksDestroyed = pm.emptyBlocksDestroyed + pm.coinBlocksDestroyed + pm.powerBlocksDestroyed;
		int totalBlocks = pm.totalEmptyBlocks + pm.totalCoinBlocks + pm.totalpowerBlocks;
		
		float percentBlocksDestroyed = 0;
		if (totalBlocks > 0)
			percentBlocksDestroyed = (float) totalBlocksDestroyed/(float) totalBlocks;
		float kickedShells = pm.kickedShells;
		float percentFireKills = 0;
		if (pm.totalEnemies > 0)
			percentFireKills = (float) pm.enemyKillByFire / (float) pm.totalEnemies;
		
		float percentShellKills = 0;
		if (pm.totalEnemies > 0)
			percentShellKills = (float) pm.enemyKillByKickingShell / (float) pm.totalEnemies;
		
		int totalEnemyKills = pm.ArmoredTurtlesKilled + pm.CannonBallKilled + pm.GoombasKilled + pm.GreenTurtlesKilled + pm.JumpFlowersKilled + pm.RedTurtlesKilled;
		
		float percentStompKills = 0;
		if (pm.totalEnemies > 0)
			percentStompKills = (float) (totalEnemyKills - pm.enemyKillByFire - pm.enemyKillByKickingShell) / (float) pm.totalEnemies;
		
		float percentKills = 0;
		if (pm.totalEnemies > 0)
			percentKills = (float) totalEnemyKills / (float) pm.totalEnemies;

		float percentLittle = (float) pm.totalTimeLittleMode / (float) pm.totalTime;
		float percentLarge = (float) pm.totalTimeLargeMode / (float) pm.totalTime;
		float timesSwichingPower = pm.timesSwichingPower;
		
		float percentUselessJumps = 0;
		if (pm.jumpsNumber > 0)
			percentUselessJumps = (float) pm.aimlessJumps / (float) pm.jumpsNumber;
		
		float timesOfDeathByFallingIntoGap = (float) pm.timesOfDeathByFallingIntoGap;
		float deaths = pm.timesOfDeathByArmoredTurtle + pm.timesOfDeathByCannonBall + pm.timesOfDeathByChompFlower + (float) pm.timesOfDeathByFallingIntoGap 
			+ pm.timesOfDeathByGoomba + pm.timesOfDeathByGreenTurtle + pm.timesOfDeathByGreenTurtle + pm.timesOfDeathByJumpFlower + pm.timesOfDeathByRedTurtle;
		float percentCoins = (float) pm.coinsCollected / (float) (pm.totalCoins + pm.totalCoinBlocks);
		
		float percentSimpleKills = 0;
		if (totalEnemyKills > 0)
			percentSimpleKills = (float) pm.GoombasKilled / (float) totalEnemyKills;
		
		float percentMediumKills = 0;
		if (totalEnemyKills > 0)
			percentMediumKills = (float) (pm.RedTurtlesKilled + pm.GreenTurtlesKilled) / (float) totalEnemyKills;
		
		
		// calculate linear discriminant vectors
		/*
		float ld1 = relativeTime * 8.0811070f +
		jumpsNumber * 4.7412682f +
		relativeJumps * -6.7701231f +
		timesPressedRun * -0.3607579f +
		timeSpentRunning * -2.6949282f +
		avgRunDuration * 0.0584240f +
		timeRunningRight * -2.1866242f +
		timeRunningLeft * -3.2814920f +
		percentRight * -0.4432759f +
		percentLeft * 2.1738023f +
		percentStanding * -0.2708169f +
		percentBlocksDestroyed * -2.1434300f +
		kickedShells * -0.2300388f +
		percentFireKills * -0.7107540f +
		percentShellKills * 0.1072598f +
		percentStompKills * -0.6485834f +
		percentKills * -1.0637390f +
		percentLittle * -0.7916546f +
		percentLarge * -0.5687113f +
		timesSwichingPower * 1.4312810f +
		percentUselessJumps * -0.0448557f +
		timesOfDeathByFallingIntoGap * -0.4905894f +
		deaths * -0.0675869f +
		percentCoins * 0.5668009f +
		percentSimpleKills * 1.5963194f +
		percentMediumKills * 0.6174112f;
		
		float ld2 = relativeTime * 3.0199474f +
		jumpsNumber * 3.6374422f +
		relativeJumps * -4.4340822f +
		timesPressedRun * 0.6508829f +
		timeSpentRunning * 0.5975616f +
		avgRunDuration * -0.2109744f +
		timeRunningRight * -2.4817611f +
		timeRunningLeft * -1.9469593f +
		percentRight * 0.6766097f +
		percentLeft * -1.5374563f +
		percentStanding * -0.1370125f +
		percentBlocksDestroyed * 3.2122786f +
		kickedShells * 0.9943151f +
		percentFireKills * -0.0879530f +
		percentShellKills * -0.6190645f +
		percentStompKills * -0.1646502f +
		percentKills * -0.3737736f +
		percentLittle * -0.2912982f +
		percentLarge * 0.3348598f +
		timesSwichingPower * 0.2097495f +
		percentUselessJumps * 3.0678891f +
		timesOfDeathByFallingIntoGap * 0.1564391f +
		deaths * 0.0230661f +
		percentCoins * -1.2141340f +
		percentSimpleKills * 1.8535549f +
		percentMediumKills * 0.5301656f;
		
		float ld3 = relativeTime * 0.5157145f +
		jumpsNumber * 2.5186020f +
		relativeJumps * 0.4067964f +
		timesPressedRun * 1.5769037f +
		timeSpentRunning * 0.5339109f +
		avgRunDuration * -0.0062218f +
		timeRunningRight * -2.6210547f +
		timeRunningLeft * 3.8106016f +
		percentRight * -0.0710120f +
		percentLeft * -0.6637742f +
		percentStanding * 0.2694283f +
		percentBlocksDestroyed * -5.0283944f +
		kickedShells * 0.4588766f +
		percentFireKills * -0.8556660f +
		percentShellKills * -1.2054306f +
		percentStompKills * -0.2295259f +
		percentKills * -1.2676953f +
		percentLittle * -0.7917185f +
		percentLarge * -0.7016682f +
		timesSwichingPower * 0.4166469f +
		percentUselessJumps * -2.1493395f +
		timesOfDeathByFallingIntoGap * 0.4605534f +
		deaths * -0.5070359f +
		percentCoins * 0.7394285f +
		percentSimpleKills * -0.8921534f +
		percentMediumKills * -1.2439475f;
		*/
		
		float ld1 = relativeTime * 16.45132739f +
		jumpsNumber * 0.116448201f +
		relativeJumps * -14.24763371f +
		timesPressedRun * -0.022363972f +
		timeSpentRunning * -0.166641129f +
		avgRunDuration * 0.020359096f +
		timeRunningRight * -0.037926846f +
		timeRunningLeft * -0.153909494f +
		percentRight * -2.389709721f +
		percentLeft * 33.1248949f +
		percentBlocksDestroyed * -6.323731221f +
		kickedShells * -0.110677477f +
		percentFireKills * -2.950758115f +
		percentShellKills * 1.448217641f +
		percentStompKills * -2.628011111f +
		percentKills * -3.135655722f +
		percentLittle * -2.369160944f +
		percentLarge * -2.791544872f +
		timesSwichingPower * 0.649433152f +
		percentUselessJumps * -0.300389097f +
		timesOfDeathByFallingIntoGap * -1.264119086f +
		deaths * -0.05273058f +
		percentCoins * 1.139681081f +
		percentSimpleKills * 3.611320883f +
		percentMediumKills * 1.464896264f;
		
		float ld2 = relativeTime * 6.147937885f +
		jumpsNumber * 0.089337618f +
		relativeJumps * -9.331466923f +
		timesPressedRun * 0.040349291f +
		timeSpentRunning * 0.036950275f +
		avgRunDuration * -0.073518562f +
		timeRunningRight * -0.043045976f +
		timeRunningLeft * -0.091316852f +
		percentRight * 8.127796876f +
		percentLeft * -20.75366038f +
		percentBlocksDestroyed * 9.47714029f +
		kickedShells * 0.478390079f +
		percentFireKills * -0.365144497f +
		percentShellKills * -8.358585347f +
		percentStompKills * -0.667150125f +
		percentKills * -1.101797725f +
		percentLittle * -0.871759529f +
		percentLarge * 1.64367402f +
		timesSwichingPower * 0.095172263f +
		percentUselessJumps * 20.54498153f +
		timesOfDeathByFallingIntoGap * 0.40310224f +
		deaths * 0.017995953f +
		percentCoins * -2.44129046f +
		percentSimpleKills * 4.193259532f +
		percentMediumKills * 1.257893714f;
		
		float ld3 = relativeTime * -1.049879346f +
		jumpsNumber * -0.061858274f +
		relativeJumps * -0.856097698f +
		timesPressedRun * -0.097754833f +
		timeSpentRunning * -0.033014429f +
		avgRunDuration * 0.002168134f +
		timeRunningRight * 0.045462014f +
		timeRunningLeft * -0.178725948f +
		percentRight * 2.929156127f +
		percentLeft * 11.63477705f +
		percentBlocksDestroyed * 14.83520121f +
		kickedShells * -0.220777127f +
		percentFireKills * 3.552373129f +
		percentShellKills * 16.27567724f +
		percentStompKills * 0.930021774f +
		percentKills * 3.736871529f +
		percentLittle * 2.369352238f +
		percentLarge * 3.444169428f +
		timesSwichingPower * -0.18905047f +
		percentUselessJumps * 14.39365548f +
		timesOfDeathByFallingIntoGap * -1.186724333f +
		deaths * 0.395584079f +
		percentCoins * -1.486787907f +
		percentSimpleKills * 2.01830048f +
		percentMediumKills * 2.951443308f;
		
		// LD1 small: lots of jumps, fast. good player!
		// LD1 big: not many jumps, takes a long time. beginner
		
		// LD2 small: 
		// LD2 big: explorers

		
		// attempt to normalise skill [0-1];
		 skill = (-ld1 + 6)/12; 
		 exploring = (ld2 - 14)/10;
		skill = Math.min(skill, 1);
		System.out.println("LD: "+ld1+ " "+ld2+ " "+ld3+ " verdict: skill: "+skill+" explorer: "+exploring);
	}

}
