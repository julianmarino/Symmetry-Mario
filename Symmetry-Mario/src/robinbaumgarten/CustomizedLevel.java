package robinbaumgarten;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.Enemy;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;
import dk.itu.mario.scene.LevelScene;

public class CustomizedLevel  extends Level implements LevelInterface {

    public static final int TYPE_OVERGROUND = 0;
    public static final int TYPE_UNDERGROUND = 1;
    public static final int TYPE_CASTLE = 2;

    public static long lastSeed;
    public static final int LevelLengthMinThreshold = 50;

    private ArrayList<Integer> enemyPositions;
    private ArrayList<Integer> coinQuestionPositions;
    private ArrayList<Integer> coinBlockPositions;
    private int gapCount = 0;
    
    Random random;

    private int difficulty;
    private int type;

    
    private int[] easy = {1,3,5,6,15,19,20};
    private int[] medium = {2,4,7,8,9,11,16,17,18,21}; 
    private int[] hard = {10,12,13,14,22};

    private int totalPieces = easy.length + medium.length + hard.length;
    
    private static final int EASY = 0;
    private static final int MEDIUM = 1;
    private static final int HARD = 2;
    
    						  //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22
    private int[] gapsInTile = {0,0,2,0,0,1,0,1,7,0, 0, 3, 2, 0, 2, 3, 2, 1, 0, 0, 5, 0, 1};
    
    	
	public CustomizedLevel(float skill, float exploring)
	{
	   	super(Constraints.levelWidth, 15);
    	random = new Random();
    	//this.playerM = playerMetrics;
    	difficulty = 10; //(int) (skill * 100);
    	System.out.println("Chosen difficulty: "+difficulty);
    	//creat(seed, difficulty, type);
    	enemyPositions = new ArrayList<Integer>();
    	coinQuestionPositions  = new ArrayList<Integer>();
    	coinBlockPositions  = new ArrayList<Integer>();
    	createLevel(Constraints.levelWidth, 15, 0, 0, TYPE_OVERGROUND);
	}


	public LevelInterface generateLevel(GamePlay playerMetrics) {
		//LevelInterface level = new CustomizedLevel(320,15,new Random().nextLong(),1,1,playerMetrics);
		LevelInterface level = null;
		//dk.itu.mario.level.robinbaumgarten.MyLevel lvl = LevelBuilder.createLevel(Constraints.levelWidth, 15, 0, 0, TYPE_OVERGROUND);
		return level;
	}

    
    public void createLevel(int width, int height, long seed, int difficulty, int type)
    {
        //LevelBuilder levelGenerator = new LevelBuilder(width, height);
        ArrayList<Integer> al = new ArrayList<Integer>();
        int maxPieces = (width - 50) / 25;
        int remainingBlocks = width - 50 - maxPieces * 25;
        System.out.println("blocks: "+maxPieces+ " remaining units: "+remainingBlocks);
        
        al.add(0); 
        int previousPiece = 0;
        for (int i = 1; i < maxPieces; i++)
        {
        	previousPiece = getRandomPiece(selectNextPieceDifficulty(previousPiece), al);
        	al.add(previousPiece);
        }
        
        System.out.println("Creating Level ...");
        createLevel(seed, difficulty, type, al);
        
    }
    
    private int gapsSoFar(ArrayList<Integer> a)
    {
    	int g = 0;
    	for (int i : a)
    	{
    		g+= gapsInTile[i];
    	}
    	return g;
    }
    
    private int getRandomPiece(int difficulty, ArrayList<Integer> a)
    {
    	boolean found = false;
    	int gaps = gapsSoFar(a);
    	while(!found)
    	{
    		int level = random.nextInt(totalPieces)+1;
    		
    		boolean exists_already = false;
    		for(Integer b: a)
    			if ((int) b == level)
    				exists_already = true;
    		
    		if (getPieceDifficulty(level) == difficulty && (!exists_already || random.nextInt(100) < 5) && gaps + gapsInTile[level] <= Constraints.gaps)
    			return level;
    	}
    	return 1;
    }
    
    private int getPieceDifficulty(int piece)
    {
       	for (int i = 0; i < easy.length; i++)
    	{
    		if (easy[i] == piece)
    			return EASY;
    	}
       	for (int i = 0; i < medium.length; i++)
    	{
    		if (medium[i] == piece)
    			return MEDIUM;
    	}
       	for (int i = 0; i < hard.length; i++)
    	{
    		if (hard[i] == piece)
    			return HARD;
    	}
       	return MEDIUM;
    }
    
    private int selectNextPieceDifficulty(int previousPiece)
    {
    	int relativeDifficulty = difficulty;
    	if (getPieceDifficulty(previousPiece) == HARD)
    		relativeDifficulty /= 2;
    	
    	if (getPieceDifficulty(previousPiece) == EASY && difficulty > 40)
    		relativeDifficulty *= 1.2f;
    	
    	if (getPieceDifficulty(previousPiece) == MEDIUM && difficulty < 40)
    		relativeDifficulty *= 0.5f;

    	relativeDifficulty += random.nextInt(50)-25;
    	if (relativeDifficulty < 30)
    		return EASY;
    	if (relativeDifficulty < 70)
    		return MEDIUM;
    	else
    		return HARD;
    }

    
    
    private void createLevel(long seed, int difficulty, int type, ArrayList<Integer> sequence)
    {
        random = new Random();
    	int currentPos = 0;
    	for (int piece : sequence)
    	{
    		
        	try
    		{
    			//MyLevel leveltmp = MyLevel.load(new DataInputStream(LevelScene.class.getResourceAsStream("resources/test"+piece+".lvl")));

        		System.out.println("Loading part "+piece);
        		MyLevel leveltmp = MyLevel.load(new DataInputStream(MyLevel.class.getResourceAsStream("test"+piece+".lvl")));
    			if (leveltmp.width + currentPos <= this.width)
            	{
            		for(int w = 0; w < leveltmp.width; w++)
            		{
            			for (int h = 0; h < leveltmp.height; h++)
            			{
            				// enemy special code
            				if (leveltmp.map[w][h] == (byte) (4 + 2 * 16))
            				{
            					enemyPositions.add(w+currentPos);
            					enemyPositions.add(h);
            					//addEnemyLine(w+currentPos, w+currentPos+2, h);
            				}
            				else
            				{
            					setBlock(w+currentPos, h, leveltmp.map[w][h]);                 				
            					
            					if (leveltmp.map[w][h] == (byte) (1 + 1 * 16))
            					{
            						coinBlockPositions.add(w+currentPos);
            						coinBlockPositions.add(h);
            					}
            					
            					if (leveltmp.map[w][h] >= (byte) (4+ 1 * 16) && leveltmp.map[w][h] <= (byte) (7 + 1 * 16)
            							&& leveltmp.map[w][h] != (byte) (6+1 * 16) )
                				{
                					coinQuestionPositions.add(w+currentPos);
                					coinQuestionPositions.add(h);
                				}
            					
            					if (leveltmp.map[w][h] == (byte) 10 && random.nextInt(130) < difficulty)
                				{
                	                setSpriteTemplate(w+currentPos, h,
                                            new SpriteTemplate(Enemy.ENEMY_FLOWER, false));
                				}

            					//System.out.println("setting block "+(w+currentPos)+" "+h+" to "+leveltmp.map[w][h]);
            				}
            			}
            		}
            		currentPos += leveltmp.width;
            	}
    		} 
        	catch (IOException e)
    		{
    			e.printStackTrace();
    		}
        	


    	}  
    	
    	// pad end
    	int gapsEnd = 0;
    	for (int x = currentPos; x < width; x++)
    	{
    		if (gapsSoFar(sequence) + gapsEnd < Constraints.gaps && x%2 == 1 && x > currentPos + 2)
    		{    		
    			gapsEnd++;
    		}
    		else
    		{
        		setBlock(x, 14, (byte) (1 + 9 * 16));
        		setBlock(x, 13, (byte) (1 + 9 * 16));
        		setBlock(x, 12, (byte) (1 + 8 * 16));    			
    		}
    	}
    	
    	fixCoinBlocks();
    	fixEnemies();
    	
    	this.xExit = currentPos;
    	this.yExit = 12;
    }
    
    private void fixCoinBlocks()
    {
    	int coinsRequired = Constraints.coinBlocks;
    	int totalCoins = coinQuestionPositions.size()/2 + coinBlockPositions.size()/2;
    	
    	for (int t = 0; t < coinsRequired; t++)
    	{
    		if (coinQuestionPositions.size() > 0)
    		{
        		int randomCoin = random.nextInt(coinQuestionPositions.size()/2);
	            setBlock(coinQuestionPositions.get(randomCoin * 2), coinQuestionPositions.get(randomCoin * 2 + 1), (byte)(4 + 1 * 16));
	            totalCoins--;
	            coinQuestionPositions.remove(randomCoin * 2);
	            coinQuestionPositions.remove(randomCoin * 2);
    		}
    		else
    		{

        		int randomCoin = random.nextInt(coinBlockPositions.size()/2);
        		setBlock(coinBlockPositions.get(randomCoin * 2), coinBlockPositions.get(randomCoin * 2 + 1), (byte)(1 + 1 * 16));
	            totalCoins--;
	            coinBlockPositions.remove(randomCoin * 2);
	            coinBlockPositions.remove(randomCoin * 2);
    		}
            
            if (totalCoins == 0)
            	break;            	
    	}
    	
    	// remaining blocks can't have coins.
    	for (int i = 0; i < coinQuestionPositions.size(); i+=2)
    	{
            setBlock(coinQuestionPositions.get(i), coinQuestionPositions.get(i + 1), (byte)(0 + 1 * 16));
    	}
    	for (int i = 0; i < coinBlockPositions.size(); i+=2)
    	{
            setBlock(coinBlockPositions.get(i), coinBlockPositions.get(i + 1), (byte)(0 + 1 * 16));
    	}
    	
   	
    }
    
    private void fixEnemies()
    {
    	int totalTurtles = 0;
    	int turtlesRequired = Constraints.turtels;
    	int totalEnemies = enemyPositions.size()/2;
    	
    	for (int t = 0; t < turtlesRequired; t++)
    	{
    		int randomEnemy = random.nextInt(totalEnemies);
    		boolean winged = false;
    		if (difficulty > 25)
    			winged = random.nextInt(200) < difficulty;
            setSpriteTemplate(enemyPositions.get(randomEnemy * 2), enemyPositions.get(randomEnemy * 2 + 1), new SpriteTemplate(random.nextInt(2), winged));
            totalEnemies--;
            enemyPositions.remove(randomEnemy * 2);
            enemyPositions.remove(randomEnemy * 2);
            if (totalEnemies == 0)
            	break;            	
    	}
 
    	// remaining enemies: goombas or spikeys
    	for (int t = 0; t < totalEnemies; t++)
    	{
    		boolean winged = false;
    		if (difficulty > 25)
    			winged = random.nextInt(200) < difficulty;
    		int type = Enemy.ENEMY_GOOMBA;
    		if (difficulty > 40 && random.nextInt(200) < difficulty)
    			type = Enemy.ENEMY_SPIKY;    			
            setSpriteTemplate(enemyPositions.get(t * 2), enemyPositions.get(t * 2 + 1), new SpriteTemplate(type, winged));

    	}	
    }	
}
