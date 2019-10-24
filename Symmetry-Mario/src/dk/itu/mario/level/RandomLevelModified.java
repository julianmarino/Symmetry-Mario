package dk.itu.mario.level;

import java.util.ArrayList;
import java.util.Random;

import beauty.SingleElement;
//import br.ufv.willian.auxiliares.VariaveisGlobais;
import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.engine.sprites.Enemy;


public class RandomLevelModified extends Level{
	//Store information about the level
	 public   int ENEMIES = 0; //the number of enemies the level contains
	 public   int BLOCKS_EMPTY = 0; // the number of empty blocks
	 public   int BLOCKS_COINS = 0; // the number of coin blocks
	 public   int BLOCKS_POWER = 0; // the number of power blocks
	 public   int COINS = 0; //These are the coins in boxes that Mario collect
	 
	 public static final int TYPE_OVERGROUND = 0;
	 public static final int TYPE_UNDERGROUND = 1;
	 public static final int TYPE_CASTLE = 2;

 
	private static Random levelSeedRandom = new Random();
	    public static long lastSeed;

	    Random random;

	    private static final int ODDS_STRAIGHT = 0;
	    private static final int ODDS_HILL_STRAIGHT = 1;
	    private static final int ODDS_TUBES = 2;
	    private static final int ODDS_JUMP = 3;
	    private static final int ODDS_CANNONS = 4;
	    
	    private int[] odds = new int[5];
	    
	    private int totalOdds;
	    
	    private int difficulty;
	    private int type;
		private int gaps;
		private ArrayList objectsOfSpecificType; 
		
		public RandomLevelModified(int width, int height)
	    {
			super(width, height);
	    }


		public RandomLevelModified(int width, int height, long seed, int difficulty, int type,ArrayList<SingleElement> objectsOfSpecificType)
	    {
			
	        this(width, height);
	        this.objectsOfSpecificType=objectsOfSpecificType;
	        creat(seed, difficulty, type);
	    }

	    public void creat(long seed, int difficulty, int type)
	    {
	    	this.type = type;
	        this.difficulty = difficulty;
	        odds[ODDS_STRAIGHT] = 20;
	        odds[ODDS_HILL_STRAIGHT] = 10;
	        odds[ODDS_TUBES] = 2 + 1 * difficulty;
	        odds[ODDS_JUMP] = 2 * difficulty;
	        odds[ODDS_CANNONS] = -10 + 5 * difficulty;

	        if (type != RandomLevel.TYPE_OVERGROUND)
	        {
	            odds[ODDS_HILL_STRAIGHT] = 0;
	        }

	        for (int i = 0; i < odds.length; i++)
	        {
	            if (odds[i] < 0) odds[i] = 0;
	            totalOdds += odds[i];
	            odds[i] = totalOdds - odds[i];
	        }

	        lastSeed = seed;
	        random = new Random(seed);

	        int length = 0;
	        
	        length += buildStraight(0, getWidth(), true);
	        
	        //level.setBlock(length, 8, (byte) (4 + 2 + 1 * 16)); //Block PowerUp
	        
	        while (length < getWidth() - 64)   
	        {
	            length += buildZone(length, getWidth() - length);
	        }

	        int floor = height - 1 - random.nextInt(4);

	        //level.xExit= length + 8; //A saida � colocada a 8 pixels do ultimo obstaculo = 9
	        //level.yExit = floor;
	        //level.setBlock(length, floor - 4, (byte) (4 + 2 + 1 * 16)); //Block PowerUp
	        xExit = length + 8;
	        yExit = floor;
	        
	        
	        

	        for (int x = length; x < getWidth(); x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, (byte) (1 + 9 * 16)); //GROUND (Bloco marron)
	                }
	            }
	        }

	        if (type == RandomLevelModified.TYPE_CASTLE || type == RandomLevelModified.TYPE_UNDERGROUND)
	        {
	            int ceiling = 0;
	            int run = 0;
	            for (int x = 0; x < getWidth(); x++)
	            {
	                if (run-- <= 0 && x > 4)
	                {
	                    ceiling = random.nextInt(4);
	                    run = random.nextInt(4) + 4;
	                }
	                for (int y = 0; y < getHeight(); y++)
	                {
	                    if ((x > 4 && y <= ceiling) || x < 1)
	                    {
	                        setBlock(x, y, (byte) (1 + 9 * 16)); //GROUND (Bloco marron)
	                    }
	                }
	            }
	        }

	        fixWalls();

	    }

	    private int buildZone(int x, int maxLength)
	    {
	    	//System.out.println("Entrou no buildZone");
	    	
	        int t = random.nextInt(totalOdds);
	        int type = 0;
	        for (int i = 0; i < odds.length; i++)
	        {
	            if (odds[i] <= t)
	            {
	                type = i;
	            }
	        }
	                
	        type = random.nextInt(6);
	        //type = ODDS_STRAIGHT;
	        //System.out.print("type = " + type + " ");
	        
	        switch (type)
	        {
	            case ODDS_STRAIGHT:
	                return buildStraight(x, maxLength, false);           	
	                //return 0;
	            case ODDS_HILL_STRAIGHT:
	                return buildHillStraight(x, maxLength);
	            case ODDS_TUBES:   //Tubos
	                return buildTubes(x, maxLength);
	            case ODDS_JUMP:   //Buracos
	                return buildJump(x, maxLength);
	            case ODDS_CANNONS:    //Canhoes
	            	return buildCannons(x, maxLength);


	            case 5:
	                return buildHillStraight(x, maxLength);
	        }
	        
	                
	        return 0;
	    }

	    private int buildJump(int xo, int maxLength)
	    {	    	
	    	boolean trava = false;
	    	int js = random.nextInt(4) + 2;
	        int jl = random.nextInt(2) + 2;
	        int length = js * 2 + jl;

	        boolean hasStairs = random.nextInt(3) == 0;
	        //hasStairs = false;

	        int floor = height - 1 - random.nextInt(4);
	        if(hasStairs)
	        	floor =  height - 1 - random.nextInt(2);
	        
	        for (int x = xo; x < xo + length; x++)
	        {
	            if (x < xo + js || x > xo + length - js - 1)
	            {
	                for (int y = 0; y < height; y++)
	                {
	                    if (y >= floor)
	                    {
	                        setBlock(x, y, (byte) (1 + 9 * 16)); //GROUND (Bloco marron)
	                    }
	                    else if (hasStairs)
	                    {
	                        if (x < xo + js)
	                        {
	                            if (y >= floor - (x - xo) + 1)
	                            {
	                                setBlock(x, y, (byte) (9 + 0 * 16));
	                            }
	                        }
	                        else
	                        {
	                            if (y >= floor - ((xo + length) - x) + 2)
	                            {
	                                setBlock(x, y, (byte) (9 + 0 * 16));
	                            }
	                        }
	                    }
	                }         
	                
	            }
	            trava = true;
	        }
	        
	        //System.out.println("\nPlataforma em buildJump");
	        
	        int chao;
	        if(hasStairs){        	
	        	chao = floor - js + 1;
	        }
	        else
	        	chao = floor;
	        
	        //System.out.println("js: " + js + " floor: " + floor + " Passado: " + chao);
	        
	        //VariaveisGlobais.tag_jump = true;
	        
	        buildPlatform(xo, xo + length, chao);
	        
	        //if(trava)cont_buracos++;
	        //System.out.println("Entrou no buildJump: " + length);
	        //return 10;
	        additionElementToList("Gap", 0,jl);
	        return length;
	    }

	    private int buildCannons(int xo, int maxLength)
	    {
	        //int length = random.nextInt(10) + 2;
	    	int length = 3 + random.nextInt(3);
	        if (length > maxLength) length = maxLength;

	        int floor = height - 1 - random.nextInt(4);
	        //int xCannon = xo + 1 + random.nextInt(4);
	        int xCannon = xo + 1;
	        
	        for (int x = xo; x < xo + length; x++)
	        {
	            if (x > xCannon)
	            {
	                xCannon += 2 + random.nextInt(4);
	                
	            }
	            if (xCannon == xo + length - 1) xCannon += 10;
	            int cannonHeight = floor - random.nextInt(4) - 1;

	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, (byte) (1 + 9 * 16));     //GROUND (Bloco marron)               
	                }
	                else
	                {
	                    if (x == xCannon && y >= cannonHeight)
	                    {
	                        if (y == cannonHeight)
	                        {
	                            //VariaveisGlobais.tag_cannos = true;
	                        	additionElementToList("Canion", floor-cannonHeight,1);
	                        	setBlock(x, y, (byte) (14 + 0 * 16)); //Canhao
	                            //cont_canhoes++;
	                        }
	                        else if (y == cannonHeight + 1)
	                        {
	                        	setBlock(x, y, (byte) (14 + 1 * 16)); //Conector base-canhao
	                        }
	                        else
	                        {
	                            setBlock(x, y, (byte) (14 + 2 * 16)); //Base do canhao
	                        }
	                    }
	                }
	            }
	            
	            
	        }

	        //System.out.println("\nPlataforma em buildCannons");
	        if(length > 3)
	        	buildPlatform(xo + 1, xo + length, floor - 2);
	        
	        
	        return length;
	    }

	    private int buildHillStraight(int xo, int maxLength)
	    {
	        int length = random.nextInt(10) + 10;
	        //length = 15;
	        if (length > maxLength) length = maxLength;

	        int floor = height - 1 - random.nextInt(4);
	        for (int x = xo; x < xo + length; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, (byte) (1 + 9 * 16)); //GROUND (Bloco marron)
	                }
	            }
	        }

	        addEnemyLine(xo + 1, xo + length - 1, floor - 1);

	        int h = floor;
	        int min_h = h;

	        boolean keepGoing = true;
	        //keepGoing = false;

	        boolean[] occupied = new boolean[length];
	        while (keepGoing)
	        {
	            h = h - 2 - random.nextInt(3);
	            if(min_h > h)
	            	min_h = h;

	            if (h <= 0)
	            {
	                keepGoing = false;
	            }
	            else
	            {
	                int l = random.nextInt(5) + 3;
	                int xxo = random.nextInt(length - l - 2) + xo + 1;

	                if (occupied[xxo - xo] || occupied[xxo - xo + l] || occupied[xxo - xo - 1] || occupied[xxo - xo + l + 1])
	                {
	                    keepGoing = false;
	                }
	                else
	                {
	                    occupied[xxo - xo] = true;
	                    occupied[xxo - xo + l] = true;
	                    addEnemyLine(xxo, xxo + l, h - 1);
	                    //if (random.nextInt(4) == 0)
	                    if (random.nextInt(3) == 0)
	                    {
	                        decorate(xxo - 1, xxo + l + 1, h); 
	                        keepGoing = false;
	                        min_h -= 2;
	                    }
	                    for (int x = xxo; x < xxo + l; x++)
	                    {
	                        for (int y = h; y < floor; y++)
	                        {
	                            int xx = 5;
	                            if (x == xxo) xx = 4;
	                            if (x == xxo + l - 1) xx = 6;
	                            int yy = 9;
	                            if (y == h) yy = 8;

	                            if (getBlock(x, y) == 0)
	                            {
	                                setBlock(x, y, (byte) (xx + yy * 16));
	                            }
	                            else
	                            {
	                                if (getBlock(x, y) == (byte) (4 + 8 * 16)) {
	                                	setBlock(x, y, (byte) (4 + 11 * 16));
	                                }
	                                if (getBlock(x, y) == (byte) (6 + 8 * 16)) {
	                                	setBlock(x, y, (byte) (6 + 11 * 16));
	                                }
	                            }
	                            /*
	                            if(
	                            		level.getBlock(x, y) == (byte) (4 + 8 * 16)
	                            		|| level.getBlock(x, y) == (byte) (5 + 8 * 16)
	                            		|| level.getBlock(x, y) == (byte) (6 + 8 * 16)
	                              ){
	                            	if(min_h > y)
	                            		min_h = y;                            	
	                            } 
	                            */
	                            
	                        }
	                    }
	                    additionElementToList("Mountain", floor-h,l);
	                }
	            }
	        }

	        //level.setBlock(xo + 1, min_h, (byte) (11 + 0 * 16));//Entrada direita do Cano
			//level.setBlock(xo , min_h, (byte) (10 + 0 * 16 ));//Entrada esquerda do Cano
	        
	        //System.out.println("\nPlataforma em buildHillStraight");
	        //System.out.println("floor: "+ floor + " min_h: " + min_h);
	        /*
	        if(floor - min_h > 5){
	        	min_h = min_h + 2;
	        	System.out.println("floor - min_h > 5. Retornando: " + min_h);
	        }
	        else{
	        	min_h = floor;
	        	System.out.println("floor - min_h <= 5. Retornando: " + min_h);
	        }
	        */
	        
	        
	        
	        
	        if(length > 6){
	        	buildPlatform(xo + 2, xo + length - 2, min_h + 2);
	        }
	        else        	
	        	buildPlatform(xo, xo + length, min_h + 2); 
	        
	        //Setando que foi colocado montanhas na tela
	        //VariaveisGlobais.tag_hill_straigth = true;
	        
	        if(random.nextInt(3) == 0){
		        int x = random.nextInt(length);
		        int y = random.nextInt(4);
		        int quant = random.nextInt(3);
		        if(x + quant > length)
		        	quant = length - x;
		        //System.out.println(quant);
		        byte block;     
		           
		        if(random.nextBoolean())
		        {
		        	block = (byte) 28; //bloco azul-rocheado
		        	
		        }
		        else
		        {
		        	block = (byte) 12; //bloco de madeira   
		        }
		        for(int i = 0; i < quant; i++){
		        	if(getBlock(x + xo + i, min_h - y) == 0 ||
		        			getBlock(x + xo + i, min_h - y - 1) == 0 ||
		        			getBlock(x + xo + i, min_h - y + 1) == 0)
		        		setBlock(x + xo + i, min_h - y, block);
		        }
		        if(block==(byte) 28 && quant>0)
		        {
		        additionElementToList("BlockBlue", 1,quant);
		        }
		        else if(block==(byte) 28 && quant>0)
		        {
		        	additionElementToList("BlockWood", 1,quant);	
		        }
	        }
	        
	        return length;
	    }
 
	    private void addEnemyLine(int x0, int x1, int y)
	    {
	        for (int x = x0; x < x1; x++)
	        {
	            if (random.nextInt(35) < difficulty + 1)
	            {
	                int type = random.nextInt(4);

	                if (difficulty < 1)
	                {
	                    type = Enemy.ENEMY_GOOMBA;
	                }
	                else if (difficulty < 3)
	                {
	                    type = random.nextInt(3);
	                }

	                setSpriteTemplate(x, y, new SpriteTemplate(type, random.nextInt(35) < difficulty));
	                ENEMIES++;
	            }
	        }
	    }

	    private int buildTubes(int xo, int maxLength)
	    {
	    	boolean enemy=false;
	        int length = random.nextInt(10) + 5;
	        if (length > maxLength) length = maxLength;

	        int floor = height - 1 - random.nextInt(4);
	        int xTube = xo + 1 + random.nextInt(4);
	        int tubeHeight = floor - random.nextInt(2) - 2;
	        for (int x = xo; x < xo + length; x++)
	        {
	            if (x > xTube + 1)
	            {
	                xTube += 3 + random.nextInt(4);
	                tubeHeight = floor - random.nextInt(2) - 2;
	            }
	            if (xTube >= xo + length - 2) xTube += 10;

	            if (x == xTube && random.nextInt(11) < difficulty + 1)
	            {
	                setSpriteTemplate(x, tubeHeight, new SpriteTemplate(Enemy.ENEMY_FLOWER, false));
	                ENEMIES++;
	                enemy=true;
	            }

	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, (byte) (1 + 9 * 16)); //GROUND (Bloco marron)
	                }
	                else
	                {
	                    if ((x == xTube || x == xTube + 1) && y >= tubeHeight)
	                    {
	                        int xPic = 10 + x - xTube;
	                        if (y == tubeHeight)
	                        {
	                            setBlock(x, y, (byte) (xPic + 0 * 16));
	                        }
	                        else
	                        {
	                            setBlock(x, y, (byte) (xPic + 1 * 16));
	                        }
	                        
	                        if(x == xTube && y == tubeHeight)
	                        {
	                        	if(enemy==false)
	                        	{
	                        		additionElementToList("Tube", floor-tubeHeight,2);
	                        	
	                        	}
	                        	else
	                        	{
	                        		additionElementToList("TubeFlower", floor-tubeHeight,2);
	                        		enemy=false;
	                        	}
	                        }
	                    }
	                }
	            }
	        }
	        
	        
	        //VariaveisGlobais.tag_tubes = true;
	        
	        //System.out.println("\nPlataforma em buildTubes");
	        buildPlatform(xo, xo + length, floor);
	        
	        //System.out.println("Entrou no buildTubes: " + length);
	        return length;
	        //return 18;
	    }

	    private int buildStraight(int xo, int maxLength, boolean safe)
	    {
	        int length = random.nextInt(10) + 3;
	        
	        //if (safe) length = 10 + random.nextInt(5); // O inicio da fase e no minimo 10
	        if (safe) length = 10; //Eu coloquei este valor fixo. Usei 13, para descontar 12 e deixar 1 para evitar poss�veis bugs.
	        
	        if (length > maxLength) length = maxLength;

	        int floor = height - 1 - random.nextInt(4);
	        //int floor = height - 3;
	        
	        for (int x = xo; x < xo + length; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, (byte) (1 + 9 * 16));  //GROUND (Bloco marron)
	                }
	            }
	        }
	        
	        //System.out.println("\nPlataforma em buildStraight");
	        if(safe){ 
	        	 
	        	/*
	        	int num_block = 0;
	        	if(random.nextInt(2) == 0){
	        		
	        		num_block = random.nextInt(3) + 1;
	        		
	        		int aux = random.nextInt(3);
		    		byte block = 0;
		    		switch (aux) {
					case 0:
						block = (byte) (0 + 1 * 16); //Bloco vazio
						break;
					case 1:
						block = (byte) (1 + 1 * 16); //Bloco amarelo com Moeda
						break;
					case 2:
						block = (byte) (4 + 1 + 1 * 16); //Block_Coin
						break;
					
					}    			
		            
		    		aux = random.nextInt(3) + 3;
		    		int inicio = 3 + random.nextInt(4);
	    			for(int x = inicio; x < inicio + num_block; x++){
	    				//System.out.println("floor: " + floor + " aux: " + aux);
	    				level.setBlock(x, floor - aux , block);
	    				if(block == (byte) (1 + 1 * 16) || block == (4 + 1 + 1 * 16))
	    					cont_coin++;
	    			}
	    			
	    			num_block = random.nextInt(3) + 1;
	    			inicio = 5 + random.nextInt(3);
	    			for(int x = inicio; x < inicio + num_block; x++)
	    				level.setBlock(x, floor - (aux + 4) , block);
	        		
	        	} 
	        	
	        	*/
	        	          	
	        	 
	        	setBlock(2, floor-2, (byte) 67);
	    		setBlock(2, floor-1, (byte) 83);
	    		setBlock(3, floor-2, (byte) 68);
	    		setBlock(3, floor-1, (byte) 84);
	        	
	        }
	        else{
	        	
	        	buildPlatform(xo, xo + length, floor);

	            if (length > 5)
	            {
	                decorate(xo, xo + length, floor);
	            }
	            
	            //Coloca o mini cano e a cerca de madeira
	            if(random.nextInt(4) == 0){            	
	            	int x = xo + random.nextInt(length - 1) + 1;
	            	int y = floor - random.nextInt(3);
	            	if(getBlock(x, y - 1) == 0 && getBlock(x, y - 2) == 0 && getBlock(x, y - 3) == 0){
	            		if(random.nextBoolean()){
	            			setBlock(x, y - 3, (byte) 24); //caninho cima
	            			setBlock(x, y - 2, (byte) 40); //caninho meio
	            			setBlock(x, y - 1, (byte) 56); //caninho baixo
	            			additionElementToList("TubeSmall", 3,1);
	            		}else{
	            			setBlock(x, y - 3, (byte) 25); //Madeira cima
	            			setBlock(x, y - 2, (byte) 41);//Madeira meio
	            			setBlock(x, y - 1, (byte) 57); //Madeira baixo
	            			additionElementToList("Wood", 3,1);
	            		}
	            	}
	            }
	            
	            //VariaveisGlobais.tag_straigth = true;
	        }
	        
	        
	        //System.out.println("Entrou no buildStraight: " + length);
	        return length;
	        //return 19;
	    }

	    private void decorate(int xStart, int xLength, int floor)
	    {
	    	//if its at the very top, just return
	        if (floor < 1)
	        	return;

	        //        boolean coins = random.nextInt(3) == 0;
	        boolean rocks = true;

	        //add an enemy line above the box
	        addEnemyLine(xStart + 1, xLength - 1, floor - 1);

	        int s = random.nextInt(4);
	        int e = random.nextInt(4);

	        if (floor - 2 > 0){
	            if ((xLength - 1 - e) - (xStart + 1 + s) > 1){
	                for(int x = xStart + 1 + s; x < xLength - 1 - e; x++){
	                    setBlock(x, floor - 2, COIN);
	                    COINS++;
	                }
	            }
	        }

	        s = random.nextInt(4);
	        e = random.nextInt(4);
	        
	        //this fills the set of blocks and the hidden objects inside them
	        if (floor - 4 > 0)
	        {
	            if ((xLength - 1 - e) - (xStart + 1 + s) > 2)
	            {
	                for (int x = xStart + 1 + s; x < xLength - 1 - e; x++)
	                {
	                    if (rocks)
	                    {
	                        if (x != xStart + 1 && x != xLength - 2 && random.nextInt(3) == 0)
	                        {
	                            if (random.nextInt(4) == 0)
	                            {
	                                setBlock(x, floor - 4, BLOCK_POWERUP);
	                                BLOCKS_POWER++;
	                            }
	                            else
	                            {	//the fills a block with a hidden coin
	                                setBlock(x, floor - 4, BLOCK_COIN);
	                                BLOCKS_COINS++;
	                            }
	                        }
	                        else if (random.nextInt(4) == 0)
	                        {
	                            if (random.nextInt(4) == 0)
	                            {
	                                setBlock(x, floor - 4, (byte) (2 + 1 * 16));
	                            }
	                            else
	                            {
	                                setBlock(x, floor - 4, (byte) (1 + 1 * 16));
	                            }
	                        }
	                        else
	                        {
	                            setBlock(x, floor - 4, BLOCK_EMPTY);
	                            BLOCKS_EMPTY++;
	                        }
	                    }
	                }
	                additionElementToList("Block", 1,(xLength - 1 - e)-(xStart + 1 + s));
	            }
	            
	        }
	    }

	    private void fixWalls()
	    {
	        boolean[][] blockMap = new boolean[width + 1][height + 1];

	        for (int x = 0; x < width + 1; x++)
	        {
	            for (int y = 0; y < height + 1; y++)
	            {
	                int blocks = 0;
	                for (int xx = x - 1; xx < x + 1; xx++)
	                {
	                    for (int yy = y - 1; yy < y + 1; yy++)
	                    {
	                        if (getBlockCapped(xx, yy) == GROUND){
	                        	blocks++;
	                        }
	                    }
	                }
	                blockMap[x][y] = blocks == 4;
	            }
	        }
	        blockify(this, blockMap, width + 1, height + 1);
	    }

	    private void blockify(Level level, boolean[][] blocks, int width, int height){
	        int to = 0;
	        if (type == LevelInterface.TYPE_CASTLE)
	        {
	            to = 4 * 2;
	        }
	        else if (type == LevelInterface.TYPE_UNDERGROUND)
	        {
	            to = 4 * 3;
	        }

	        boolean[][] b = new boolean[2][2];

	        for (int x = 0; x < width; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                for (int xx = x; xx <= x + 1; xx++)
	                {
	                    for (int yy = y; yy <= y + 1; yy++)
	                    {
	                        int _xx = xx;
	                        int _yy = yy;
	                        if (_xx < 0) _xx = 0;
	                        if (_yy < 0) _yy = 0;
	                        if (_xx > width - 1) _xx = width - 1;
	                        if (_yy > height - 1) _yy = height - 1;
	                        b[xx - x][yy - y] = blocks[_xx][_yy];
	                    }
	                }

	                if (b[0][0] == b[1][0] && b[0][1] == b[1][1])
	                {
	                    if (b[0][0] == b[0][1])
	                    {
	                        if (b[0][0])
	                        {
	                            level.setBlock(x, y, (byte) (1 + 9 * 16 + to));
	                        }
	                        else
	                        {
	                            // KEEP OLD BLOCK!
	                        }
	                    }
	                    else
	                    {
	                        if (b[0][0])
	                        {
	                        	//down grass top?
	                            level.setBlock(x, y, (byte) (1 + 10 * 16 + to));
	                        }
	                        else
	                        {
	                        	//up grass top
	                            level.setBlock(x, y, (byte) (1 + 8 * 16 + to));
	                        }
	                    }
	                }
	                else if (b[0][0] == b[0][1] && b[1][0] == b[1][1])
	                {
	                    if (b[0][0])
	                    {
	                    	//right grass top
	                        level.setBlock(x, y, (byte) (2 + 9 * 16 + to));
	                    }
	                    else
	                    {
	                    	//left grass top
	                        level.setBlock(x, y, (byte) (0 + 9 * 16 + to));
	                    }
	                }
	                else if (b[0][0] == b[1][1] && b[0][1] == b[1][0])
	                {
	                    level.setBlock(x, y, (byte) (1 + 9 * 16 + to));
	                }
	                else if (b[0][0] == b[1][0])
	                {
	                    if (b[0][0])
	                    {
	                        if (b[0][1])
	                        {
	                            level.setBlock(x, y, (byte) (3 + 10 * 16 + to));
	                        }
	                        else
	                        {
	                            level.setBlock(x, y, (byte) (3 + 11 * 16 + to));
	                        }
	                    }
	                    else
	                    {
	                        if (b[0][1])
	                        {
	                        	//right up grass top
	                            level.setBlock(x, y, (byte) (2 + 8 * 16 + to));
	                        }
	                        else
	                        {
	                        	//left up grass top
	                            level.setBlock(x, y, (byte) (0 + 8 * 16 + to));
	                        }
	                    }
	                }
	                else if (b[0][1] == b[1][1])
	                {
	                    if (b[0][1])
	                    {
	                        if (b[0][0])
	                        {
	                        	//left pocket grass
	                            level.setBlock(x, y, (byte) (3 + 9 * 16 + to));
	                        }
	                        else
	                        {
	                        	//right pocket grass
	                            level.setBlock(x, y, (byte) (3 + 8 * 16 + to));
	                        }
	                    }
	                    else
	                    {
	                        if (b[0][0])
	                        {
	                            level.setBlock(x, y, (byte) (2 + 10 * 16 + to));
	                        }
	                        else
	                        {
	                            level.setBlock(x, y, (byte) (0 + 10 * 16 + to));
	                        }
	                    }
	                }
	                else
	                {
	                    level.setBlock(x, y, (byte) (0 + 1 * 16 + to));
	                }
	            }
	        }
	    }
	    
	    public RandomLevel clone() throws CloneNotSupportedException {

	    	RandomLevel clone=new RandomLevel(width, height);

	    	clone.xExit = xExit;
	    	clone.yExit = yExit;
	    	byte[][] map = getMap();
	    	SpriteTemplate[][] st = getSpriteTemplate();
	    	
	    	for (int i = 0; i < map.length; i++)
	    		for (int j = 0; j < map[i].length; j++) {
	    			clone.setBlock(i, j, map[i][j]);
	    			clone.setSpriteTemplateOriginal(i, j, st[i][j]);
	    	}
	    	clone.BLOCKS_COINS = BLOCKS_COINS;
	    	clone.BLOCKS_EMPTY = BLOCKS_EMPTY;
	    	clone.BLOCKS_POWER = BLOCKS_POWER;
	    	clone.ENEMIES = ENEMIES;
	    	clone.COINS = COINS;
	    	
	        return clone;

	      }
	    
	    public void additionElementToList(String type,int heigth, int width)
	    {
	    	SingleElement objSingleElement=new SingleElement(heigth-1,width,type);
	    	//System.out.println("SIngleElement "+objSingleElement.getHeigth()+" "+objSingleElement.getWidth()+" "+objSingleElement.getTypeElement());
			objectsOfSpecificType.add(objSingleElement);
	    }

	    public void buildPlatform(int x0, int x1, int floor){
	    	
	    	if(random.nextBoolean())
	    	//if(x0 >= 0)
	    		return;
	    	
	    	//Largura m�nima da plataforma tem de ser de 3: 1 para bloco superior e 1 para bloco inferior e um para o meio
	    	int length = x1 - x0; //Comprimento da plataforma

	    	//Random rand = new Random();
	    	int x_aux_plataforma = 0;
	    	if(length > 3){
		    	if(random.nextInt(4) != 0){
		    		x_aux_plataforma = random.nextInt(length - 3);
		    		if(x_aux_plataforma > 3)
		    			x_aux_plataforma = 3;
		    		x0 += x_aux_plataforma;
		    	}else{
		    		x0++;
		    	}
		    	length = x1 - x0;	    	
	    	}
	    	
	    	if(length > 3){
		    	if(random.nextInt(4) != 0){
		    		x_aux_plataforma = random.nextInt(length - 3);
		    		if(x_aux_plataforma > 3)
		    			x_aux_plataforma = 3;
		    		x1 -= x_aux_plataforma;
		    	}
		    	else
		    		x1--;
		    	length = x1 - x0;	    	
	    	}	
	    	
	    	
	    	/**
	    	 * M�ximo que a parte de baixo da plataforma pode chegar
	    	 */
	    	int max_plataforma = floor - 6; // 7: Espa�o de 4 acima do chao + 1 para poss�veis blocos + 1 acima dos blocos
	    	
	    	//if(x0 == 0) x0 = 3;
	    	if(length < 3){
	    		
	    		if(max_plataforma >= 2){
	    			
	    			int y = random.nextInt(max_plataforma - 1);
	    			max_plataforma = y + 2;
	    			
	    			int type_block = random.nextInt(4);
		    		byte block = 0;
		    		switch (type_block) {
					case 0:
						block = (byte) (0 + 1 * 16); //Bloco vazio
						break;
					case 1:
						block = (byte) (1 + 1 * 16); //Bloco amarelo com Moeda
						break;
					case 2:
						block = (byte) 28;  //bloco azul-rocheado
						break;
					case 3:
						block = (byte) 12; //bloco de madeira
						break;
					
					} 
		    		   		
		            		
	    			for(int x = x0; x < x1; x++){
	    				setBlock(x, max_plataforma , block);
	    				//if(block == (byte) (1 + 1 * 16))
	    					//cont_coin++;
	    			}
	    			if(type_block==0)
	    			{
	    				additionElementToList("Block", 1,x1-x0);
	    			}
	    			else if(type_block==1)
	    			{
	    				additionElementToList("Block", 1,x1-x0);
	    			}
	    			else if(type_block==2)
	    			{
	    				additionElementToList("BlockBlue", 1,x1-x0);
	    			}
	    			else if(type_block==3)
	    			{
	    				additionElementToList("BlockWood", 1,x1-x0);
	    			}
	    			
	    			//VariaveisGlobais.tag_platform = true;
	    			
	    		}
	    		//System.out.println("Largura insuficiente para plataforma.");
	    		return;
	    		
	    	}
	    	
	    	if(max_plataforma < 5) { //Coordenada em y. 
	    		if(max_plataforma >= 2){ 
	    			//System.out.println("Contruindo plataforma de blocos. max_plataforma retornado: " + max_plataforma );
	    			if(length > 6){
	    				x0 += 2;
	    				x1 -= 2;
	    			}
	    			
	    			int y = random.nextInt(max_plataforma - 1);
	    			max_plataforma = 2 + y;
	    			
	    			int type_block = random.nextInt(4);
		    		byte block = 0;
		    		switch (type_block) {
					case 0:
						block = (byte) (0 + 1 * 16); //Bloco vazio
						break;
					case 1:
						block = (byte) (1 + 1 * 16); //Bloco amarelo com Moeda
						break;
					case 2:
						block = (byte) 28;  //bloco azul-rocheado
						break;
					case 3:
						block = (byte) 12; //bloco de madeira
						break;
					
					}    			
		            		
	    			for(int x = x0; x < x1; x++){
	    				setBlock(x, max_plataforma , block);
	    				//if(block == (byte) (1 + 1 * 16))
	    					//cont_coin++;
	    			}
	    			if(type_block==0)
	    			{
	    				additionElementToList("Block", 1,x1-x0);
	    			}
	    			else if(type_block==1)
	    			{
	    				additionElementToList("Block", 1,x1-x0);
	    			}
	    			else if(type_block==2)
	    			{
	    				additionElementToList("BlockBlue", 1,x1-x0);
	    			}
	    			else if(type_block==3)
	    			{
	    				additionElementToList("BlockWood", 1,x1-x0);
	    			}
	    			
	    			//VariaveisGlobais.tag_platform = true;
	    		}
	    		
	    		return;
	    	}    	
	    	
	    	    	
	    	int begin_platform = 3; 
	    	begin_platform -= random.nextInt(2);
	    	int aux = max_plataforma - 4;
	    	begin_platform += random.nextInt(aux);
	    	
	    	if(begin_platform > max_plataforma - 3)
	    		begin_platform = max_plataforma - 3;
	    	    	
	    	int widthPlatform = random.nextInt(max_plataforma - begin_platform);
	    	if(widthPlatform < 3)
	    		widthPlatform = 3;    	    	
	    	
	    	
	    	//System.out.println("Contruindo uma plataforma entre " + x0 +" e  "+ x1 + " com comprimento " + length +
	    		//	". Onde o y_MAX � " + max_plataforma +  " de lagura " + widthPlatform + " a partir de " + begin_platform);
	    	
	    	if(random.nextInt(4) == 3){
				int y = random.nextInt(widthPlatform) + begin_platform;
				int type_block = random.nextInt(4);
	    		byte block = 0;
	    		switch (type_block) {
				case 0:
					block = (byte) (0 + 1 * 16); //Bloco vazio
					break;
				case 1:
					block = (byte) (1 + 1 * 16); //Bloco amarelo com Moeda
					break;
				case 2:
					block = (byte) 28;  //bloco azul-rocheado
					break;
				case 3:
					block = (byte) 12; //bloco de madeira
					break;
				
				}    			
	    		for (int x = x0; x < x1; x++)
	            {
	                setBlock(x, y, (byte) block); 
	                //if(block == (byte) (1 + 1 * 16))
	                	//cont_coin++;
	            }
    			if(type_block==0)
    			{
    				additionElementToList("Block", 1,x1-x0);
    			}
    			else if(type_block==1)
    			{
    				additionElementToList("Block", 1,x1-x0);
    			}
    			else if(type_block==2)
    			{
    				additionElementToList("BlockBlue", 1,x1-x0);
    			}
    			else if(type_block==3)
    			{
    				additionElementToList("BlockWood", 1,x1-x0);
    			}
	            
			}else{
				for (int x = x0; x < x1; x++)
	        	{
	    			for (int y = begin_platform; y < begin_platform + widthPlatform; y++)
		        	{
		            	setBlock(x, y, (byte) (1 + 9 * 16));  //GROUND (Bloco marron)
		            	//level.setBlock(x, y , (byte)(4 + 1 + 1 * 16));
		        	}

	        	}
				additionElementToList("MountainFloat", widthPlatform,x1-x0);
	    	}
	    	//level.setBlock(x1, begin_platform , (byte)(4 + 1 + 1 * 16));
	    	//level.setBlock(x1 + 1, begin_platform, (byte) (11 + 0 * 16 ));//Entrada direita do Cano
	    	//level.setBlock(x1 + 1, begin_platform + widthPlatform, (byte) (11 + 0 * 16));//Entrada direita do Cano
			//level.setBlock(x0 - 1, begin_platform, (byte) (10 + 0 * 16 ));//Entrada esquerda do Cano
			//level.setBlock(x0 - 1, begin_platform + widthPlatform, (byte) (10 + 0 * 16));//Entrada esquerda do Cano
	    	
	    	//VariaveisGlobais.tag_platform = true;
	    	
	    	if(x1 - x0 > 4)
	            decorate(x0, x1, begin_platform - 1);        
	    	
	    }
	    public ArrayList getObjectsOfSpecificType()
	    {
	    	return objectsOfSpecificType;
	    }

}
