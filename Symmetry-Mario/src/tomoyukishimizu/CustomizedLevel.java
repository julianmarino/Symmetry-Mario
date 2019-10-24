
package tomoyukishimizu;

import java.io.*;
import java.util.Random;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.level.Level;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.engine.sprites.Enemy;



public class CustomizedLevel extends Level
{
    Random random;
    
    public static final byte GRASS_TOP = (byte)(1 + 8*16);

    public static final int MAX_DIFFICULTY = 4;
    
    public static final int ODDS_COIN = 0;
    public static final int ODDS_BLOCK = 1;
    public static final int ODDS_ENEMY = 2;
    public static final int ODDS_OTHER = 3;
    
    private int difficulty;
    private int odds[] = new int[5];
    
    private int type;
    private int gaps;
    private int turtles;
    private int coinBlocks;
    
    
    private GamePlay playerM;
    
    private byte buildMap[][];
    private SpriteTemplate buildSprite[][];
    private boolean isPartsReadError;

    
    
    /**
     */
    public CustomizedLevel(int width, int height, long seed, int difficulty, int type, GamePlay playerMetrics)
    {	
        super(width, height);
        this.playerM = playerMetrics;
        creat();
    }

    
    
    /**
     */
    public void creat()
    {
        this.type = 0;
        setParameters();

        random = new Random();

        int length = 0;
        
        length += buildStraight(0, getWidth(), true);
        while (length < getWidth() - 64) {
            length += buildZone(length, getWidth() - length);
        }
        int floor = height-1-random.nextInt(4);
        if( height > 15 ){ floor = (height-9) - random.nextInt(4); }
        xExit = length + 8;
        yExit = floor;
        for (int x = length; x < getWidth(); x++) {
            for(int y = 0; y < getHeight(); y++) {
                if( y >= floor ){ setBlock(x, y, GROUND); }
            }
        }

        if( (type==LevelInterface.TYPE_CASTLE) || (type == LevelInterface.TYPE_UNDERGROUND) ){
            int ceiling = 0;
            int run = 0;
            for(int x = 0; x < width; x++){
                if( run-- <= 0 && x > 4 ){
                    ceiling = random.nextInt(4);
                    run = random.nextInt(4) + 4;
                }
                for(int y = 0; y < height; y++){
                    if( (x > 4 && y <= ceiling) || (x < 1) ){
                        setBlock(x, y, GROUND);
                    }
                }
            }
        }

        fixLevelForCompitition();
        groundfy();
        fixWalls();
    }

    
    
    /**
     */
    private int buildZone(int startX, int maxLength)
    {
    	setPartsRandom();
    	if( isPartsReadError ){
    		isPartsReadError = false;
    		int t = random.nextInt(5);
    		if( t == 0 ){ return buildStraight(startX, maxLength, false); }
    		else if( t == 1 ){ return buildHillStraight(startX, maxLength); }
    		else if( t == 2 ){ return buildTubes(startX, maxLength); }
    		else if( t == 3 ){ return buildJump(startX, maxLength); }
    		else if( t == 4 ){ return buildCannons(startX, maxLength); }
    		return buildStraight(startX, maxLength, false);
    	}
    	
    	int ret_length = buildMap.length;
    	if( ret_length > maxLength ){ return 0; }
    	
    	for(int x=0; x<buildMap.length; x++){
    		for(int y=0; y<height; y++){
    			setBlock(x+startX, y, buildMap[x][y]);
    			if( buildSprite[x][y] != null ){ setSpriteTemplate(x+startX, y, buildSprite[x][y]); }
    		}
    	}
        
        return ret_length;
    }
    
    
    
    /**
     */
    private void setPartsRandom()
    {
    	int dif = difficulty;
    	int t = random.nextInt(100);
    	if( t < 20 ){ dif--; }
    	else if( t < 40 ){ dif++; }
    	if( dif > MAX_DIFFICULTY ){ dif = MAX_DIFFICULTY; }
    	if( dif < 0 ){ dif = 0; }
    	
    	//TODO
    	//If necessary
    	//Please modify the path of directory named partsByGA
    	String dirPath = "./partsByGA/" + dif + "/" + getPreferenceString();
    	System.out.println(dirPath);
    	File dir = new File(dirPath);
    	String fileNames[] = dir.list(getFileExtensionFilter(".prt2"));
    	
    	try{
    		if( fileNames.length == 0 ){
        		dirPath = "./partsByGA/" + dif + "/";
            	dir = new File(dirPath);
    			System.out.println("------------"+dirPath);

            	fileNames = dir.list(getFileExtensionFilter(".prt2"));
        	}
    		
    		String fileName = fileNames[ random.nextInt(fileNames.length) ];
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dirPath+fileName));
			/*int difficulty = */in.readInt();
			int prtWidth = in.readInt();
			int prtHeight = in.readInt();
			
			buildMap = new byte [prtWidth][15];
			for(int x=0; x<prtWidth; x++){
				for(int y=(height-prtHeight); y<height; y++){
					buildMap[x][y] = in.readByte();
				}
			}
			
			buildSprite = new SpriteTemplate [prtWidth][15];
			for(int x=0; x<prtWidth; x++){
				for(int y=(height-prtHeight); y<height; y++){
					int type = in.readInt();
					boolean winged = in.readBoolean();
					if( type != -1 ){ buildSprite[x][y]= new SpriteTemplate(type, winged); }
					//buildSprite[x][y] = (SpriteTemplate)in.readObject();
				}
			}
			in.close();
			
			System.out.println(dirPath);
		}
		catch(Exception e){
			System.out.println("read error");
			isPartsReadError = true;
		}
    }
    
    
    
    /**
     */
    private String getPreferenceString()
    {
    	int preference = ODDS_OTHER;
    	int t = random.nextInt(100);
    	
    	int start = 0;
        int end = 0;
        for(int index = 0; index < odds.length; index++){
        	end = (start + odds[index]);
        	if( (t>=start) && (t<end) ){
        		preference = index;
        		break;
        	}
        	start = end;
        }
        
        if( preference == ODDS_OTHER ){ return ""; }
        else if( preference == ODDS_COIN ){ return "Coin/"; }
        else if( preference == ODDS_BLOCK ){ return "Block/"; }
        else if( preference == ODDS_ENEMY ){ return "Enemy/"; }
        return "";
    }
    
    
    
    
    /**
     */
    public static FilenameFilter getFileExtensionFilter(String extension) {  
    	final String _extension = extension;  
    	return new FilenameFilter(){  
    		public boolean accept(File file, String name) {  
    			boolean ret = name.endsWith(_extension);   
    			return ret;  
    		}  
    	};  
    }
    
    
    
    /**
     */
    private void fixWalls()
    {
        boolean[][] blockMap = new boolean[width + 1][height + 1];

        for(int x = 0; x < width+1; x++){
            for(int y = 0; y < height+1; y++){
                int blocks = 0;
                for(int xx = x-1; xx < x+1; xx++){
                    for(int yy = y-1; yy < y+1; yy++){
                        if( getBlockCapped(xx, yy) == GROUND ){
                            blocks++;
                        }
                    }
                }
                blockMap[x][y] = blocks == 4;
            }
        }
        blockify(blockMap, width+1, height+1);
    }

    
    
    /**
     */
    private void blockify(boolean[][] blocks, int width, int height)
    {
        int to = 0;
        if( type == LevelInterface.TYPE_CASTLE ){
            to = 4 * 2;
        }
        else if( type == LevelInterface.TYPE_UNDERGROUND ){
            to = 4 * 3;
        }

        boolean[][] b = new boolean[2][2];

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
            	
            	//
                for(int xx = x; xx <= x+1; xx++){
                    for(int yy = y; yy <= y + 1; yy++){
                        int _xx = xx;
                        int _yy = yy;
                        if( _xx < 0 ){ _xx = 0; }
                        if( _yy < 0 ){ _yy = 0; }
                        if( _xx > width-1 ){ _xx = width-1; }
                        if( _yy > height-1 ){ _yy = height-1; }
                        b[xx - x][yy - y] = blocks[_xx][_yy];
                    }
                }

                if (b[0][0] == b[1][0] && b[0][1] == b[1][1]) {
                    if (b[0][0] == b[0][1]) {
                        if (b[0][0]) {
                            setBlock(x, y, (byte) (1 + 9 * 16 + to));
                        }
                        else {
                            // KEEP OLD BLOCK!
                        }
                    }
                    else{
                        if (b[0][0]) {
                            //down grass top?
                            setBlock(x, y, (byte) (1 + 10 * 16 + to));
                        }
                        else {
                            //up grass top
                            setBlock(x, y, (byte) (1 + 8 * 16 + to));
                        }
                    }
                }
                else if (b[0][0] == b[0][1] && b[1][0] == b[1][1]) {
                    if (b[0][0]) {
                        //right grass top
                        setBlock(x, y, (byte) (2 + 9 * 16 + to));
                    }
                    else {
                        //left grass top
                        setBlock(x, y, (byte) (0 + 9 * 16 + to));
                    }
                } else if (b[0][0] == b[1][1] && b[0][1] == b[1][0]) {
                    setBlock(x, y, (byte) (1 + 9 * 16 + to));
                } else if (b[0][0] == b[1][0]) {
                    if (b[0][0]) {
                        if (b[0][1]) {
                            setBlock(x, y, (byte) (3 + 10 * 16 + to));
                        } else {
                            setBlock(x, y, (byte) (3 + 11 * 16 + to));
                        }
                    } else {
                        if (b[0][1]) {
                            //right up grass top
                            setBlock(x, y, (byte) (2 + 8 * 16 + to));
                        } else {
                            //left up grass top
                            setBlock(x, y, (byte) (0 + 8 * 16 + to));
                        }
                    }
                } else if (b[0][1] == b[1][1]) {
                    if (b[0][1]) {
                        if (b[0][0]) {
                            //left pocket grass
                            setBlock(x, y, (byte) (3 + 9 * 16 + to));
                        } else {
                            //right pocket grass
                            setBlock(x, y, (byte) (3 + 8 * 16 + to));
                        }
                    } else {
                        if (b[0][0]) {
                            setBlock(x, y, (byte) (2 + 10 * 16 + to));
                        } else {
                            setBlock(x, y, (byte) (0 + 10 * 16 + to));
                        }
                    }
                } else {
                    setBlock(x, y, (byte) (0 + 1 * 16 + to));
                }
            }
        }
    }
    
    
    
    /**
     */
    private void groundfy()
    {
    	for(int x=0; x<width; x++){
    		for(int y=0; y<height; y++){
    			if( isSoil(getBlock(x, y)) ){ setBlock(x, y, GROUND); }
    		}
    	}
    }
    
    
    
    /**
	 */
    public static boolean isSoil(byte chipID)
	{
		if( chipID == LEFT_GRASS_EDGE ){ return true; }
		if( chipID == RIGHT_GRASS_EDGE ){ return true; };
		if( chipID == RIGHT_UP_GRASS_EDGE ){ return true; }
		if( chipID == LEFT_UP_GRASS_EDGE ){ return true; }
		if( chipID == LEFT_POCKET_GRASS ){ return true; }
		if( chipID == RIGHT_POCKET_GRASS ){ return true; }
		if( chipID == GRASS_TOP ){ return true; }
		return false;
	}
    
    
    
    /**
     * @author tomoyuki
     */
    private void setParameters()
    {
    	difficulty = calcDifficulty(playerM);
    	odds[ODDS_COIN] = checkCoinCollecter(playerM);
        odds[ODDS_BLOCK] = checkBlockDestroyer(playerM);
        odds[ODDS_ENEMY] = checkEnemyKiller(playerM);
        odds[ODDS_OTHER] = (100 - odds[ODDS_COIN] - odds[ODDS_BLOCK] - odds[ODDS_ENEMY]);
        
        System.out.println("difficulty=" + difficulty);
        System.out.println("ODDS_COIN=" + odds[ODDS_COIN]);
        System.out.println("ODDS_BLOCK=" + odds[ODDS_BLOCK]);
        System.out.println("ODDS_ENEMY=" + odds[ODDS_ENEMY]);
        System.out.println("ODDS_OTHER=" + odds[ODDS_OTHER]);
        System.out.println("SUM=" + (odds[ODDS_COIN]+odds[ODDS_BLOCK]+odds[ODDS_ENEMY]+odds[ODDS_OTHER]));
    }
    
    
    
    /**
     * @author tomoyuki
     */
    private int calcDifficulty(GamePlay gp)
    {
    	int ret_difficulty = 0;
    	
    	final int RUNNING_THREADHOLT_UP = 50;
    	final double STOMP_THREADHOLD_UP = (double)(gp.totalEnemies) * 0.7;
    	
    	int totalTimesOfDeath = (int)(gp.timesOfDeathByArmoredTurtle + gp.timesOfDeathByCannonBall + gp.timesOfDeathByChompFlower
    							+ gp.timesOfDeathByFallingIntoGap + gp.timesOfDeathByGoomba + gp.timesOfDeathByGreenTurtle
    							+ gp.timesOfDeathByJumpFlower + gp.timesOfDeathByRedTurtle);
    	
    	double timeSpentRunning = ( (double)gp.timeSpentRunning / (double)(totalTimesOfDeath+1) );
    	
    	int totalEnemyKillByStomp = (int)(gp.jumpsNumber - gp.aimlessJumps - gp.emptyBlocksDestroyed - gp.coinBlocksDestroyed - gp.powerBlocksDestroyed);
    	
    	ret_difficulty = (MAX_DIFFICULTY - totalTimesOfDeath);//1~4
    	if( timeSpentRunning >= RUNNING_THREADHOLT_UP ){ ret_difficulty++; }
    	if( totalEnemyKillByStomp >= STOMP_THREADHOLD_UP ){ ret_difficulty++; }
    	
    	ret_difficulty += judgeDiffcultuFromModeTime(gp);
    	if( gp.timesSwichingPower > 3 ){ ret_difficulty--; }
    	
    	if( ret_difficulty > MAX_DIFFICULTY ){ ret_difficulty = MAX_DIFFICULTY; }
    	if( ret_difficulty < 0 ){ ret_difficulty = 0; }
    	return ret_difficulty;
    }
    
    
    
    /**
     */
    private int judgeDiffcultuFromModeTime(GamePlay gp)
    {
    	if( gp.totalTimeLittleMode > gp.totalTimeLargeMode ){
    		if( gp.totalTimeLittleMode > gp.totalTimeFireMode ){ return -1; }
    		else{ return 1; }
    	}
    	else if( gp.totalTimeLargeMode > gp.totalTimeFireMode ){ return 0; }
    	else{ return 1; }
    	//return 0;
    }
    
    
    
    /**
     * @author tomoyuki
     */
    private int checkCoinCollecter(GamePlay gp)
    {
    	if( gp.totalCoins == 0 ){ return 0; }
    	
    	double retVal = ( (double)(gp.coinsCollected) / (double)(gp.totalCoins) );
    	if( retVal > 1.0 ){ retVal = 1.0; }
    	retVal *= (100.0/4.0);
    	
    	return (int)retVal;
    }
    
    
    
    /**
     * @author tomoyuki
     */
    private int checkEnemyKiller(GamePlay gp)
    {
    	if( gp.totalEnemies == 0 ){ return 0; }
    	
    	int totalEnemyKilled = (gp.ArmoredTurtlesKilled + gp.CannonBallKilled + gp.ChompFlowersKilled + gp.GoombasKilled
    							+ gp.GreenTurtlesKilled + gp.JumpFlowersKilled + gp.RedTurtlesKilled);
    	double retVal = ( (double)(totalEnemyKilled) / (double)(gp.totalEnemies) );
    	if( retVal > 1.0 ){ retVal = 1.0; }
    	retVal *= (100.0/4.0);
    	
    	return (int)retVal;
    }
    
    
    
    /**
     */
    private int checkBlockDestroyer(GamePlay gp)
    {
    	int totalBlocks = (gp.totalEmptyBlocks + gp.totalCoinBlocks + gp.totalpowerBlocks);
    	if( totalBlocks == 0 ){ return 0; }

    	int totalBlocksDestroyed = (gp.emptyBlocksDestroyed + gp.coinBlocksDestroyed + gp.powerBlocksDestroyed);
    	
    	double retVal = ( (double)(totalBlocksDestroyed) / (double)(totalBlocks) );
    	if( retVal > 1.0 ){ retVal = 1.0; }
    	retVal *= (100.0/4.0);
    	
    	return (int)retVal;
    }    
    
    
    
    /**
     */
    private void fixLevelForCompitition()
    {
    	gaps = checkGapNumber();
        turtles = checkTurtleNumber();
        coinBlocks = checkCoinBlockNumber();
        
        fixGapNumber( gaps - Constraints.gaps );
        fixTurtleNumber( turtles - Constraints.turtels );
        fixCoinBlockNumber( coinBlocks - Constraints.coinBlocks );
    }
    
    
    
    /**
     */
    private int checkTurtleNumber()
    {
    	int ret_turtles = 0;
    	for(int i=0; i<getSpriteTemplate().length; i++){
			SpriteTemplate[] st = (SpriteTemplate[])getSpriteTemplate()[i];
			for(int j = 0; j<st.length; j++) {
				if( st[j] != null ){
					int type = ((SpriteTemplate)st[j]).type;
					if( type==SpriteTemplate.RED_TURTLE || type== SpriteTemplate.GREEN_TURTLE || type==SpriteTemplate.ARMORED_TURTLE){
						ret_turtles++;
					}
				}
			}
    	}
    	return ret_turtles;
    }
    
    
    
    /**
     */
    private int checkGapNumber()
    {
    	int ret_gaps = 0;
    	for(int x=0; x<width; x++){
			if( getBlock(x, height-1) == 0 ){
				ret_gaps++;
				while( x<width && getBlock(x, height-1)==0 ){ x++; }
			}
		}
    	return ret_gaps;
    }
    
    
    /**
     */
    private int checkCoinBlockNumber()
    {
    	int ret_coinBlocks = 0;
    	for(int x=0; x<width; x++){
			for(int y=0; y<height; y++) {
				byte chipID = getBlock(x, y);
				if( (TILE_BEHAVIORS[chipID & 0xff] & BIT_BUMPABLE) > 0 ){
					if( !(((TILE_BEHAVIORS[chipID & 0xff]) & BIT_SPECIAL) > 0) ){
						ret_coinBlocks++;
					}
				}
			}
    	}
    	return ret_coinBlocks;
    }
    
    
    
    /**
     */
    private void fixTurtleNumber(int fixNum)
    {
    	if( fixNum <= 0 ){ return; }
    	
    	for(int x=0; x<width; x++){
    		for(int y=0; y<height; y++) {
    			SpriteTemplate st = getSpriteTemplate(x, y);
    			if( st != null ){
    				int type = st.type;
    				if( type==SpriteTemplate.RED_TURTLE || type== SpriteTemplate.GREEN_TURTLE || type==SpriteTemplate.ARMORED_TURTLE){
    					setSpriteTemplate(x, y, new SpriteTemplate(SpriteTemplate.GOOMPA, false));
    					fixNum--;
    					if( fixNum == 0 ){ return; }
    				}
    			}
    		}
    	}
    }
    
    
    
    /**
     */
    private void fixCoinBlockNumber(int fixNum)
    {
    	if( fixNum <= 0 ){ return; }
    	
    	for(int x=0; x<width; x++){
			for(int y=0; y<height; y++) {
				byte chipID = getBlock(x, y);
				if( (TILE_BEHAVIORS[chipID & 0xff] & BIT_BUMPABLE) > 0 ){
					if( !(((TILE_BEHAVIORS[chipID & 0xff]) & BIT_SPECIAL) > 0) ){
						setBlock(x, y, BLOCK_EMPTY);
						fixNum--;
						if( fixNum == 0 ){ return; }
					}
				}
			}
    	}
    }
    
    
    
    /**
     */
    private void fixGapNumber(int fixNum)
    {
    	if( fixNum <= 0 ){ return; }
    	
    	for(int x=0; x<width; x++){
			if( getBlock(x, height-1) == 0 ){
				while( x<width && getBlock(x, height-1)==0 ){
					setBlock(x, height-1, GROUND);
					x++;
				}
				fixNum--;
				if( fixNum == 0 ){ return; }
			}
		}
    }
    
    
    
    /**
     */
    /*public void saveStageInfo(String filename)
    {
    	int stageWidth = getWidth();
    	int stageHeight = getHeight();
    	
    	byte[][] map = getMap();
    	
    	 try {
 			FileOutputStream fos = new FileOutputStream(filename);
 			ObjectOutputStream out = new ObjectOutputStream(fos);
 			
 			out.writeInt(stageWidth);
 			out.writeInt(stageHeight);
 			//�}�b�v
 			for(int x=0; x<stageWidth; x++){
 				for(int y=0; y<stageHeight; y++){
 					out.writeByte(map[x][y]);
 				}
 			}
 			//SpriteTemplates
 			for(int x=0; x<stageWidth; x++){
 				for(int y=0; y<stageHeight; y++){
 					out.writeObject(getSpriteTemplate(x, y));
 				}
 			}
 			out.writeInt(this.getxExit());
 			out.writeInt(this.getyExit());
 			//out.writeInt(super.ENEMIES);
 			//out.writeInt(super.BLOCKS_EMPTY);
 			//out.writeInt(super.BLOCKS_COINS);
 			//out.writeInt(super.BLOCKS_POWER);
 			//out.writeInt(super.COINS);
 			
 			out.close();
 		}
    	catch(FileNotFoundException e){
    		e.printStackTrace();
 		}
    	catch(IOException e){
 			e.printStackTrace();
 		}
    }*/
    
    
    
    /**
     */
    /*public void loadStageInfo(String filename)
    {
    	try{
    		FileInputStream fis = new FileInputStream(filename);
    		ObjectInputStream in = new ObjectInputStream(fis);
				
    		int stageWidth = in.readInt();
    		int stageHeight = in.readInt();
    		for(int x=0; x<stageWidth; x++){
    			for(int y=0; y<stageHeight; y++){
    				setBlock(x, y, in.readByte());
    			}
    		}
    		//SpriteTemplates
    		for(int x=0; x<stageWidth; x++){
    			for(int y=0; y<stageHeight; y++){
    				setSpriteTemplate(x, y, (SpriteTemplate)in.readObject());
    			}
    		}
    		this.xExit = in.readInt();
    		this.yExit = in.readInt();
    		//super.ENEMIES = in.readInt();
    		//super.BLOCKS_EMPTY = in.readInt();
    		//super.BLOCKS_COINS = in.readInt();
    		//super.BLOCKS_POWER = in.readInt();
    		//super.COINS = in.readInt();
    		
    		in.close();
    	}
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}
    }*/
    
    
    
    //--------------------------------------------------------------------------------
    private int buildJump(int xo, int maxLength)
    {
        int js = random.nextInt(4) + 2;
        int jl = random.nextInt(2) + 2;
        int length = js * 2 + jl;

        boolean hasStairs = random.nextInt(3) == 0;

        int floor = height - 1 - random.nextInt(4);
        for(int x = xo; x < xo + length; x++){
            if(x < xo + js || x > xo + length - js - 1){
                for (int y = 0; y < height; y++){
                    if(y >= floor){
                        setBlock(x, y, GROUND);
                    }
                    else if(hasStairs){
                        if(x < xo + js){
                            if(y >= floor - (x - xo) + 1){
                                setBlock(x, y, ROCK);
                            }
                        }
                        else{
                            if(y >= floor - ((xo + length) - x) + 2){
                                setBlock(x, y, ROCK);
                            }
                        }
                    }
                }
            }
        }

        return length;
    }
    private int buildCannons(int xo, int maxLength)
    {
        int length = random.nextInt(10) + 2;
        if (length > maxLength) length = maxLength;

        int floor = height - 1 - random.nextInt(4);
        int xCannon = xo + 1 + random.nextInt(4);
        for(int x = xo; x < xo + length; x++){
            if( x > xCannon ){
                xCannon += 2 + random.nextInt(4);
            }
            if(xCannon == xo + length - 1){ xCannon += 10; }
            int cannonHeight = floor - random.nextInt(4) - 1;

            for(int y = 0; y < height; y++){
                if(y >= floor){
                    setBlock(x, y, GROUND);
                }
                else{
                    if(x == xCannon && y >= cannonHeight){
                        if(y == cannonHeight){
                            setBlock(x, y, (byte)(14 + 0*16));
                        }
                        else if(y == cannonHeight + 1){
                            setBlock(x, y, (byte)(14 + 1*16));
                        }
                        else{
                            setBlock(x, y, (byte)(14 + 2*16));
                        }
                    }
                }
            }
        }

        return length;
    }
    private int buildHillStraight(int xo, int maxLength)
    {
        int length = random.nextInt(10) + 10;
        if( length > maxLength ){ length = maxLength; }

        int floor = height - 1 - random.nextInt(4);
        for(int x = xo; x < xo + length; x++){
            for(int y = 0; y < height; y++){
                if( y >= floor ){
                    setBlock(x, y, GROUND);
                }
            }
        }

        addEnemyLine(xo + 1, xo + length - 1, floor - 1);

        int h = floor;

        boolean keepGoing = true;

        boolean[] occupied = new boolean[length];
        while( keepGoing ){
            h = h - 2 - random.nextInt(3);

            if( h <= 0 ){
                keepGoing = false;
            }
            else{
                int l = random.nextInt(5) + 3;
                 
                int xxo = random.nextInt(length - l - 2) + xo + 1;

                if (occupied[xxo - xo] || occupied[xxo - xo + l] || occupied[xxo - xo - 1] || occupied[xxo - xo + l + 1]){
                    keepGoing = false;
                }
                else{
                    occupied[xxo - xo] = true;
                    occupied[xxo - xo + l] = true;
                    addEnemyLine(xxo, xxo + l, h - 1);
                    if( random.nextInt(4) == 0 ){
                        decorate(xxo - 1, xxo + l + 1, h);
                        keepGoing = false;
                    }
                    for(int x = xxo; x < xxo + l; x++){
                        for(int y = h; y < floor; y++){
                            int xx = 5;
                            if(x == xxo){ xx = 4; }
                            if(x == xxo + l - 1){ xx = 6; }
                            int yy = 9;
                            if( y == h ){ yy = 8; }

                            if( getBlock(x, y) == 0 ){
                                setBlock(x, y, (byte) (xx + yy * 16));
                            }
                            else{
                                if (getBlock(x, y) == HILL_TOP_LEFT){ setBlock(x, y, HILL_TOP_LEFT_IN); }
                                if (getBlock(x, y) == HILL_TOP_RIGHT){ setBlock(x, y, HILL_TOP_RIGHT_IN); }
                            }
                        }
                    }
                }
            }
        }

        return length;
    }
    private void addEnemyLine(int x0, int x1, int y)
    {
        for(int x = x0; x < x1; x++){
            if( random.nextInt(35) < difficulty + 1 ){
                int type = random.nextInt(4);

                if( difficulty < 1 ){
                    type = Enemy.ENEMY_GOOMBA;
                }
                else if( difficulty < 3 ){
                    type = random.nextInt(3);
                }

                setSpriteTemplate(x, y, new SpriteTemplate(type, random.nextInt(35) < difficulty));
            }
        }
    }
    private int buildTubes(int xo, int maxLength)
    {
        int length = random.nextInt(10) + 5;
        if( length > maxLength ){ length = maxLength; }

        int floor = height - 1 - random.nextInt(4);
        int xTube = xo + 1 + random.nextInt(4);
        int tubeHeight = floor - random.nextInt(2) - 2;
        for(int x = xo; x < xo + length; x++){
            if(x > xTube + 1){
                xTube += 3 + random.nextInt(4);
                tubeHeight = floor - random.nextInt(2) - 2;
            }
            if(xTube >= xo + length - 2){ xTube += 10; }

            if(x == xTube && random.nextInt(11) < difficulty + 1){
                setSpriteTemplate(x, tubeHeight, new SpriteTemplate(Enemy.ENEMY_FLOWER, false));
            }

            for(int y = 0; y < height; y++){
                if  (y >= floor){
                    setBlock(x, y,GROUND);
                }
                else{
                    if((x == xTube || x == xTube + 1) && y >= tubeHeight){
                        int xPic = 10 + x - xTube;

                        if( y == tubeHeight ){
                            setBlock(x, y, (byte) (xPic + 0 * 16));
                        }
                        else{
                            setBlock(x, y, (byte) (xPic + 1 * 16));
                        }
                    }
                }
            }
        }

        return length;
    }
    private int buildStraight(int xo, int maxLength, boolean safe)
    {
        int length = random.nextInt(10) + 2;

        if( safe ){ length = 10 + random.nextInt(5); }

        if( length > maxLength ){ length = maxLength; }

        int floor = height - 1 - random.nextInt(4);
        if( safe ){ floor = height - 3; }
        //runs from the specified x position to the length of the segment
        for(int x = xo; x < xo + length; x++){
            for(int y = 0; y < height; y++){
                if ( y >= floor ){
                    setBlock(x, y, GROUND);
                }
            }
        }

        if( !safe ){
            if( length > 5 ){
                decorate(xo, xo + length, floor);
            }
        }

        return length;
    }
    private void decorate(int xStart, int xLength, int floor)
    {
        if( floor < 1 ){ return; }
        
        boolean rocks = true;

        addEnemyLine(xStart + 1, xLength - 1, floor - 1);

        int s = random.nextInt(4);
        int e = random.nextInt(4);

        if(floor - 2 > 0){
            if((xLength - 1 - e) - (xStart + 1 + s) > 1){
                for(int x = xStart + 1 + s; x < xLength - 1 - e; x++){
                    setBlock(x, floor-2, COIN);
                }
            }
        }

        s = random.nextInt(4);
        e = random.nextInt(4);
        
        if( floor-4 > 0 ){
            if((xLength - 1 - e) - (xStart + 1 + s) > 2){
                for(int x = xStart + 1 + s; x < xLength - 1 - e; x++){
                    if (rocks){
                        if (x != xStart + 1 && x != xLength - 2 && random.nextInt(3) == 0){
                            if( random.nextInt(4) == 0 ){
                                setBlock(x, floor-4, BLOCK_POWERUP);
                            }
                            else{
                                setBlock(x, floor-4, BLOCK_COIN);
                            }
                        }
                        else if( random.nextInt(4) == 0 ){
                            if( random.nextInt(4) == 0 ){
                                setBlock(x, floor-4, (byte)(2 + 1*16));
                            }
                            else{
                                setBlock(x, floor-4, (byte)(1 + 1*16));
                            }
                        }
                        else{
                            setBlock(x, floor-4, BLOCK_EMPTY);
                        }
                    }
                }
            }
        }
    }
    
    
}



/* end of file */