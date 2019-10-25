package beauty;

import graphBuilder.BlockNode;
import graphBuilder.Branch;
import graphBuilder.GraphBuilder;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import javax.annotation.processing.RoundEnvironment;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.engine.sprites.Enemy;
import dk.itu.mario.engine.LevelFactory;
import dk.itu.mario.level.Level;
import dk.itu.mario.level.RandomLevel;

import java.util.Random;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import Auxiliar.CopiaArquivos;
import Auxiliar.InformacoesTelas;
import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.engine.sprites.Enemy;


public class BeautyCustomizedLevel extends Level{

		//Array have the kind of eleements of a tile
		private int[] odds;
		ElementsToPlace objElem;
		private int difficulty;
	    private int type;
		private int gaps;
		Random random;
		public static long lastSeed;
		private ArrayList<BlockNode> Beststates = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates2 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates3a = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates3 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates4 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates5 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates6 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates7 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> Beststates8 = new ArrayList<BlockNode>();
		private ArrayList<BlockNode> BestGlobalstates = new ArrayList<BlockNode>();
		private int maxScreens=100;
		
		private int floorTileHeight=0;
		private static final int initialStraight = 3;
		private static final int finalStraight = 64;
		private static int mediumStraight;
		boolean powerUp;
		
		private ArrayList bestBranches;
	    
		TreeSet<String> currentState = new TreeSet<String>();
		Hashtable hTable = new Hashtable();
		
		private double wParamether;
	
		public BeautyCustomizedLevel(int width, int height)
	    {
			super(width, height); 
	    }
		//Constructor to topk screens
		public BeautyCustomizedLevel(int width, int height, long seed, int difficulty, int type,int num_ODDS_HILL_STRAIGHT,int num_ODDS_TUBES, int num_ODDS_JUMP,
				int num_ODDS_CANNONS, int num_BLOCK_ELEMENT, int num_COINS, int num_ENEMY_RED_KOOPA, int num_ENEMY_GREEN_KOOPA, int num_ENEMY_GOOMBA, 
				int num_ENEMY_SPIKY, int num_ENEMY_FLOWER, int num_ENEMY_ARMORED_TURTLE, int num_ENEMY_JUMP_FLOWER, int num_ENEMY_CANNON_BALL,
				int num_ENEMY_CHOMP_FLOWER, int num_ODDS_TUBES_FLOWER, int num_ODDS_HILL_STRAIGHT_FLOAT, int num_WOOD, int num_SMALL_TUBE, int num_BLOCK_BLUE, int num_BLOCK_WOOD)
	    {
			
	        this(width, height);
	        creat(seed, difficulty, type,num_ODDS_HILL_STRAIGHT,num_ODDS_TUBES, num_ODDS_JUMP,
	    			num_ODDS_CANNONS, num_BLOCK_ELEMENT, num_COINS, num_ENEMY_RED_KOOPA, num_ENEMY_GREEN_KOOPA, num_ENEMY_GOOMBA, 
	    			num_ENEMY_SPIKY, num_ENEMY_FLOWER, num_ENEMY_ARMORED_TURTLE, num_ENEMY_JUMP_FLOWER, num_ENEMY_CANNON_BALL,
	    			num_ENEMY_CHOMP_FLOWER,num_ODDS_TUBES_FLOWER,num_ODDS_HILL_STRAIGHT_FLOAT,num_WOOD,num_SMALL_TUBE,num_BLOCK_BLUE,num_BLOCK_WOOD);
	        
	        
	    }
		//Constructor to many single screens
		public BeautyCustomizedLevel(int width, int height, long seed, int difficulty, int type,int count,int num_ODDS_HILL_STRAIGHT,int num_ODDS_TUBES, int num_ODDS_JUMP,
				int num_ODDS_CANNONS, int num_BLOCK_ELEMENT, int num_COINS, int num_ENEMY_RED_KOOPA, int num_ENEMY_GREEN_KOOPA, int num_ENEMY_GOOMBA, 
				int num_ENEMY_SPIKY, int num_ENEMY_FLOWER, int num_ENEMY_ARMORED_TURTLE, int num_ENEMY_JUMP_FLOWER, int num_ENEMY_CANNON_BALL,
				int num_ENEMY_CHOMP_FLOWER,int num_ODDS_TUBES_FLOWER,int num_ODDS_HILL_STRAIGHT_FLOAT,int num_WOOD,int num_SMALL_TUBE,int num_BLOCK_BLUE,int num_BLOCK_WOOD)
	    {
			
	        this(width, height);
	        creatManySearches(seed, difficulty, type,num_ODDS_HILL_STRAIGHT,num_ODDS_TUBES, num_ODDS_JUMP,
	    			num_ODDS_CANNONS, num_BLOCK_ELEMENT, num_COINS, num_ENEMY_RED_KOOPA, num_ENEMY_GREEN_KOOPA, num_ENEMY_GOOMBA, 
	    			num_ENEMY_SPIKY, num_ENEMY_FLOWER, num_ENEMY_ARMORED_TURTLE, num_ENEMY_JUMP_FLOWER, num_ENEMY_CANNON_BALL,
	    			num_ENEMY_CHOMP_FLOWER,count,num_ODDS_TUBES_FLOWER,num_ODDS_HILL_STRAIGHT_FLOAT,num_WOOD,num_SMALL_TUBE,num_BLOCK_BLUE,num_BLOCK_WOOD);
	        
	        
	    }
		
		//Constructor to many single screens receiving objects as parameters
		public BeautyCustomizedLevel(int width, int height, long seed, int difficulty, int type,int count, Hashtable hsObjectsScreen, int typeSymmetry,int[] odds,ElementsToPlace objElem, double wParamether, int methodSearch)
	    {
			
	        this(width, height);
	        this.objElem=objElem;
	        this.odds=odds;
	        this.wParamether=wParamether;
	        creatManySearchesObjectsNLG(seed, difficulty, type,count,hsObjectsScreen,typeSymmetry,methodSearch);
	        //System.out.println("constructorcorrect");
	        
	    }
		
		public void generateElements(Random random, int floorTileHeight, int num_ODDS_HILL_STRAIGHT,int num_ODDS_TUBES, int num_ODDS_JUMP,
				int num_ODDS_CANNONS, int num_BLOCK_ELEMENT, int num_COINS, int num_ENEMY_RED_KOOPA, int num_ENEMY_GREEN_KOOPA, int num_ENEMY_GOOMBA, 
				int num_ENEMY_SPIKY, int num_ENEMY_FLOWER, int num_ENEMY_ARMORED_TURTLE, int num_ENEMY_JUMP_FLOWER, int num_ENEMY_CANNON_BALL,
				int num_ENEMY_CHOMP_FLOWER, int num_ODDS_TUBES_FLOWER,int num_ODDS_HILL_STRAIGHT_FLOAT,int num_WOOD, int num_SMALL_TUBE, int num_BLOCK_BLUE, int num_BLOCK_WOOD)
		{
			objElem=new ElementsToPlace(random,floorTileHeight,num_ODDS_HILL_STRAIGHT,num_ODDS_TUBES, num_ODDS_JUMP,
					num_ODDS_CANNONS, num_BLOCK_ELEMENT,num_COINS, num_ENEMY_RED_KOOPA, num_ENEMY_GREEN_KOOPA, num_ENEMY_GOOMBA, 
					num_ENEMY_SPIKY, num_ENEMY_FLOWER, num_ENEMY_ARMORED_TURTLE, num_ENEMY_JUMP_FLOWER, num_ENEMY_CANNON_BALL,
					num_ENEMY_CHOMP_FLOWER,num_ODDS_TUBES_FLOWER,num_ODDS_HILL_STRAIGHT_FLOAT,num_WOOD,num_SMALL_TUBE,num_BLOCK_BLUE,num_BLOCK_WOOD);
			odds=objElem.getOdds();
		}
		
		//receiving objects from NLG
		public void generateElementsNLG(Random random, int floorTileHeight,Hashtable hsObjectsScreen)
		{
			//System.out.println("iscalled");
			objElem=new ElementsToPlace(random,floorTileHeight,hsObjectsScreen,height,1);
			odds=objElem.getOdds();
		}

		public void creatManySearchesObjectsNLG(long seed, int difficulty, int type,int count, Hashtable hsObjectsScreen, int typeSymmetry,int methodSearch)
	    {
			mediumStraight=width-initialStraight-finalStraight;
			lastSeed = seed;
	        random = new Random(seed);						
			this.type = type;
	        this.difficulty = difficulty;
	        
	      //create the start location
	        int length = 0;
	        length += buildStraight(0, initialStraight, true);
	        
	      //set the end piece
	        int floor = height - 1 - random.nextInt(4);

	        xExit = initialStraight+mediumStraight + 8;
	        yExit = floor;

	        // fills the end piece
	        for (int x = (initialStraight+mediumStraight); x < width; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, GROUND);
	                }
	            }
	        }
	        //creating the floor of rest of the tile
	        length +=buildStraight(initialStraight, mediumStraight, false);
	        //generateElementsNLG(random,height-floorTileHeight-1,hsObjectsScreen);
	        //piece with mountain
	        //length +=buildHillStraight(length, 10);
	        
	        //Placing the elements on the tile
	        //********to generate 1 screen***********
	        //fixWalls();
	        CreatingBeautyContent(count,typeSymmetry,methodSearch);
	      //********to generate k screens***********
	        //CreatingBeautyContentTopK();
	        
	        
	      
	        //Fixing walls
	        
	        
	    }
		
		public void creatManySearches(long seed, int difficulty, int type, int num_ODDS_HILL_STRAIGHT,int num_ODDS_TUBES, int num_ODDS_JUMP,
				int num_ODDS_CANNONS, int num_BLOCK_ELEMENT, int num_COINS, int num_ENEMY_RED_KOOPA, int num_ENEMY_GREEN_KOOPA, int num_ENEMY_GOOMBA, 
				int num_ENEMY_SPIKY, int num_ENEMY_FLOWER, int num_ENEMY_ARMORED_TURTLE, int num_ENEMY_JUMP_FLOWER, int num_ENEMY_CANNON_BALL,
				int num_ENEMY_CHOMP_FLOWER,int count, int num_ODDS_TUBES_FLOWER,int num_ODDS_HILL_STRAIGHT_FLOAT,int num_WOOD, int num_SMALL_TUBE, int num_BLOCK_BLUE, int num_BLOCK_WOOD)
	    {
			mediumStraight=width-initialStraight-finalStraight;
			lastSeed = seed;
	        random = new Random(seed);						
			this.type = type;
	        this.difficulty = difficulty;
	        
	      //create the start location
	        int length = 0;
	        length += buildStraight(0, initialStraight, true);
	        
	      //set the end piece
	        int floor = height - 1 - random.nextInt(4);

	        xExit = initialStraight+mediumStraight + 8;
	        yExit = floor;

	        // fills the end piece
	        for (int x = (initialStraight+mediumStraight); x < width; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, GROUND);
	                }
	            }
	        }
	        //creating the floor of rest of the tile
	        length +=buildStraight(initialStraight, mediumStraight, false);
	        generateElements(random,height-floorTileHeight-1,num_ODDS_HILL_STRAIGHT,num_ODDS_TUBES, num_ODDS_JUMP,
	    			num_ODDS_CANNONS, num_BLOCK_ELEMENT, num_COINS, num_ENEMY_RED_KOOPA, num_ENEMY_GREEN_KOOPA, num_ENEMY_GOOMBA, 
	    			num_ENEMY_SPIKY, num_ENEMY_FLOWER, num_ENEMY_ARMORED_TURTLE, num_ENEMY_JUMP_FLOWER, num_ENEMY_CANNON_BALL,
	    			num_ENEMY_CHOMP_FLOWER,num_ODDS_TUBES_FLOWER,num_ODDS_HILL_STRAIGHT_FLOAT,num_WOOD,num_SMALL_TUBE,num_BLOCK_BLUE,num_BLOCK_WOOD);
	        //piece with mountain
	        //length +=buildHillStraight(length, 10);
	        
	        //Placing the elements on the tile
	        //********to generate 1 screen***********
	        //fixWalls();
	        CreatingBeautyContent(count,1,1);
	      //********to generate k screens***********
	        //CreatingBeautyContentTopK();
	        
	        
	      
	        //Fixing walls
	        
	        
	    }		
		
		public void creat(long seed, int difficulty, int type,int num_ODDS_HILL_STRAIGHT,int num_ODDS_TUBES, int num_ODDS_JUMP,
				int num_ODDS_CANNONS, int num_BLOCK_ELEMENT, int num_COINS, int num_ENEMY_RED_KOOPA, int num_ENEMY_GREEN_KOOPA, int num_ENEMY_GOOMBA, 
				int num_ENEMY_SPIKY, int num_ENEMY_FLOWER, int num_ENEMY_ARMORED_TURTLE, int num_ENEMY_JUMP_FLOWER, int num_ENEMY_CANNON_BALL,
				int num_ENEMY_CHOMP_FLOWER,int num_ODDS_TUBES_FLOWER,int num_ODDS_HILL_STRAIGHT_FLOAT, int num_WOOD, int num_SMALL_TUBE, int num_BLOCK_BLUE, int num_BLOCK_WOOD)
	    {
			mediumStraight=width-initialStraight-finalStraight;
			lastSeed = seed;
	        random = new Random(seed);						
			this.type = type;
	        this.difficulty = difficulty;
	        
	      //create the start location
	        int length = 0;
	        length += buildStraight(0, initialStraight, true);
	        
	      //set the end piece
	        int floor = height - 1 - random.nextInt(4);

	        xExit = initialStraight+mediumStraight + 8;
	        yExit = floor;

	        // fills the end piece
	        for (int x = (initialStraight+mediumStraight); x < width; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, GROUND);
	                }
	            }
	        }
	        //creating the floor of rest of the tile
	        length +=buildStraight(initialStraight, mediumStraight, false);
	        generateElements(random,height-floorTileHeight-1,num_ODDS_HILL_STRAIGHT,num_ODDS_TUBES, num_ODDS_JUMP,
	    			num_ODDS_CANNONS, num_BLOCK_ELEMENT, num_COINS, num_ENEMY_RED_KOOPA, num_ENEMY_GREEN_KOOPA, num_ENEMY_GOOMBA, 
	    			num_ENEMY_SPIKY, num_ENEMY_FLOWER, num_ENEMY_ARMORED_TURTLE, num_ENEMY_JUMP_FLOWER, num_ENEMY_CANNON_BALL,
	    			num_ENEMY_CHOMP_FLOWER, num_ODDS_TUBES_FLOWER, num_ODDS_HILL_STRAIGHT_FLOAT,num_WOOD,num_SMALL_TUBE,num_BLOCK_BLUE,num_BLOCK_WOOD);
	        //piece with mountain
	        //length +=buildHillStraight(length, 10);
	        
	        //Placing the elements on the tile
	        //********to generate 1 screen***********
	        //fixWalls();
	        //CreatingBeautyContent();
	      //********to generate k screens***********
	        CreatingBeautyContentTopK();
	        
	        
	      
	        //Fixing walls
	        
	        
	    }

		private void CreatingBeautyContent(int count,int typeSymmetry, int methodSearch) { 
			// TODO Auto-generated method stub

	        
	        //from here is mine
			
			//creating CosntraintsPlacement object
			ConstraintsPlacement objConstraints= new ConstraintsPlacement(this);
			//Creating array with states
			ArrayList<BlockNode> states = new ArrayList<BlockNode>();
			//Building the graph in a deph way
	    	GraphBuilder objGrapB= new GraphBuilder(1);
	    	GraphBuilder objGrapB2= new GraphBuilder(1);
	    	GraphBuilder objGrapB3= new GraphBuilder(1);
	    	GraphBuilder objGrapB3a= new GraphBuilder(1);
	    	GraphBuilder objGrapB4= new GraphBuilder(1);
	    	GraphBuilder objGrapB5= new GraphBuilder(1);
	    	GraphBuilder objGrapB6= new GraphBuilder(1);
	    	GraphBuilder objGrapB7= new GraphBuilder(1);
	    	GraphBuilder objGrapB8= new GraphBuilder(1);
	    	
	    	objGrapB.setWparamether(wParamether);
	    	objGrapB2.setWparamether(wParamether);
	    	objGrapB3.setWparamether(wParamether);
	    	objGrapB3a.setWparamether(wParamether);
	    	objGrapB4.setWparamether(wParamether);
	    	objGrapB5.setWparamether(wParamether);
	    	objGrapB6.setWparamether(wParamether);
	    	objGrapB7.setWparamether(wParamether);
	    	objGrapB8.setWparamether(wParamether);
	    	
	    	int numElements=objElem.getNumberObjects();
	    	int numEnemies=objElem.getNumberObjectsEnemies();
	    	int globalControlSearch=0;
	    	
	    	double time6=0;
	    	double time7=0;
	    	double time3=0;
	    	double time4=0;
	    	double time5=0;
	    	double time8=0;
	    	
	    	double startTime=0;
	    	double stopTime=0;
	    	
	    	double sum3=0;
	    	double sum4=0;
	    	double sum5=0;
	    	double sum6=0;
	    	double sum7=0;
	    	double sum8=0;

	    	
	    	//Beststates=objGrapB.basicDepthSearch(mediumStraight,height,numElements,numElements,states,objConstraints, objElem.getFinalList(),objElem);
	    	//Beststates=objGrapB.relativePositionDepthSearch(mediumStraight,height,numElements-numEnemies,numElements-numEnemies,states,objConstraints, objElem.getFinalList(),objElem,-mediumStraight+2,mediumStraight-2,floorTileHeight,0,0,numEnemies,random,globalControlSearch);
	    	//Beststates=objGrapB.relativeTransPositionDepthSearch(mediumStraight,height,numElements,numElements,states,objConstraints, objElem.getFinalList(),objElem,-mediumStraight+1,mediumStraight-1,floorTileHeight,0,0,currentState,hTable);
	    	//Beststates=objGrapB.DepthSearchCenterFrame(mediumStraight,height,numElements-numEnemies,numElements-numEnemies,states,objConstraints, objElem.getFinalList(),objElem,1,mediumStraight-2,floorTileHeight,0,0,numEnemies,random,globalControlSearch);
	    	//Beststates=objGrapB.DepthSearchPruningAlt(mediumStraight,height,numElements-numEnemies,numElements-numEnemies,states,objConstraints, objElem.getFinalList(),objElem,1,mediumStraight-2,floorTileHeight,0,0,numEnemies,random,globalControlSearch);
	    	
	       

	        
	        //3.3) Brute-force search
	        //objElem.setFinalList(objElem.getFinalListNoOrder());
	    	for(int i=0;i<1;i++)
	    	{
	    		startTime = System.currentTimeMillis();
	    		Beststates3=objGrapB3.DepthSearchCenterFrameNoPruningNoRegionsNoObjects(mediumStraight,height,numElements-numEnemies,numElements-numEnemies,states,objConstraints, objElem.getFinalList(),objElem,0,mediumStraight-2,floorTileHeight,0,0,numEnemies,random,globalControlSearch,8,typeSymmetry);
	    		stopTime = System.currentTimeMillis();
	    		time3 = stopTime - startTime;
//	    		round(time3,2);
//	    		double nodes3=round((double)objGrapB3.getCounterIDs(),2);
//	    		System.out.println(objGrapB3.bestSymmetryV+" "+time3+" "+((objGrapB3.getCounterIDs())));
	    		sum3=sum3+time3;
	    	}
	    	time3=sum3/1;
	    	time3=round(time3,2);
	        //System.out.println("Time Brute-force search "+elapsedTime);
	    	
	        
	        
	        //3.7) B&B+heuristic
	        //objElem.setFinalList(objElem.getFinalListNoOrder());
	    	for(int i=0;i<1;i++)
	    	{
	    		startTime = System.currentTimeMillis();
	    		Beststates7=objGrapB7.depthSearchBBHeuristic(mediumStraight,height,numElements-numEnemies,numElements-numEnemies,states,objConstraints, objElem.getFinalList(),objElem,0,mediumStraight-2,floorTileHeight,0,0,numEnemies,random,globalControlSearch,8,typeSymmetry);
	    		stopTime = System.currentTimeMillis();
	    		time7 = stopTime - startTime;
//	    		round(time7,2);
//	    		double nodes7=round((double)objGrapB3.getCounterIDs(),2);
//	    		System.out.println(objGrapB6.bestSymmetryV+" "+time6+" "+((objGrapB6.getCounterIDs())));
	    		sum7=sum7+time7;
	    	}
	    	time7=sum7/1;
	    	time7=round(time7,2);
	        //System.out.println("Time B&B+heuristic + region ordering "+elapsedTime);
	    	
	    	
	    	
//	    	if(objGrapB3.bestSymmetryV<objGrapB5.bestSymmetryV)
//	    	{
//	    		double bestSYmmetry=objGrapB3.bestSymmetryV;
//	    		//System.out.println("bestSym "+bestSYmmetry);
//	    		BestGlobalstates=Beststates3;
//	    		
//	    	}
//	    	else
//	    	{
//	    		double bestSYmmetry=objGrapB5.bestSymmetryV;
//	    		//System.out.println("bestSym "+bestSYmmetry);
//	    		BestGlobalstates=Beststates5;
//	    	}
	    	
	    	BestGlobalstates=Beststates7;
	        
	        
	    	//**System.out.println("Simetry 0-> Brute-force search order "+objGrapB3a.bestSymmetryV);	
	    	//System.out.println("Simetry 1-> Brute-force search "+objGrapB3.bestSymmetryV);	    	
	    	//System.out.println("Simetry 2-> B&B+oldheuristic "+objGrapB4.bestSymmetryV);
	    	//**System.out.println("Simetry 3-> B&B+heuristic + object ordering "+objGrapB2.bestSymmetryV);	    	
	    	//System.out.println("Simetry 4-> B&B+ region + leviheuristic"+objGrapB5.bestSymmetryV);
	    	//**System.out.println("Simetry 5-> B&B+heuristic + region ordering + object ordering "+objGrapB.bestSymmetryV);
	    	//System.out.println("Simetry 6-> B&B+heuristic + leviheuristic "+objGrapB6.bestSymmetryV);
	    	//System.out.println("Simetry 7-> B&B+oldoldheuristic "+objGrapB7.bestSymmetryV);
	    	
	    	//**System.out.println( "States 0 "+objGrapB3a.getCounterIDs() );
	    	//System.out.println( "States 1 "+objGrapB3.getCounterIDs() );
	    	//System.out.println( "States 2 "+objGrapB4.getCounterIDs() );
	    	//**System.out.println( "States 3 "+objGrapB2.getCounterIDs() );	    		    	
	    	//System.out.println( "States 4 "+objGrapB5.getCounterIDs() );
	    	//**System.out.println( "States 5 "+objGrapB.getCounterIDs() );
	    	//System.out.println( "States 6 "+objGrapB6.getCounterIDs() );
	    	//System.out.println( "States 7 "+objGrapB7.getCounterIDs() );
	    	
	    	double TimeRate7=time3/time7;
	    	double TimeRate6=time3/time6;
	    	double TimeRate5=time3/time5;
	    	
	    	TimeRate7=round(TimeRate7,2);
	    	TimeRate6=round(TimeRate6,2);
	    	TimeRate5=round(TimeRate5,2);
	    	
	    	double NodesRate7=(double)objGrapB3.getCounterIDs()/(double)objGrapB7.getCounterIDs();
	    	double NodesRate6=(double)objGrapB3.getCounterIDs()/(double)objGrapB6.getCounterIDs();
	    	double NodesRate5=(double)objGrapB3.getCounterIDs()/(double)objGrapB5.getCounterIDs();
	    	
	    	NodesRate7=round(NodesRate7,2);
	    	NodesRate6=round(NodesRate6,2);
	    	NodesRate5=round(NodesRate5,2);

	    	
	    	

	        //imprimiendo los estados visitados
	        /*
	        Iterator<BlockNode> nombreIterator = states.iterator();
	        while(nombreIterator.hasNext()){
	        	BlockNode elemento = nombreIterator.next();
	        	System.out.print(elemento.getID()+" / ");
	        }*/
	        
	        //here we are painting as the best branch founded
	        
	        //System.out.println("nene "+BestGlobalstates.size());
	        Iterator<BlockNode> nombreIterator = BestGlobalstates.iterator();
	        while(nombreIterator.hasNext()){
	        	BlockNode elemento = nombreIterator.next();
	        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" ) - "+elemento.getType()+" "+elemento.getIdElement()+ " / ");
	        }
	        
	        
	        //Here we will put the elements on the tile
	        try {
	        Level levelScreen=PaintElements(BestGlobalstates,this);
	        Screen objScreen=new Screen();
			objScreen.SaveScreen(levelScreen,odds,objElem);
    		InformacoesTelas info = new InformacoesTelas();
	        CopiaArquivos copiador = new CopiaArquivos();
	        
	        info = info.carregaInfoTela("infoTela");
			info.setOutrasVariaveis(0, 0); // Salva outras informacoes da Tela
			info.salvaInfoTela("infoTela", info);					
			copiador.copy("" + count, "Screens/");
			
	        }  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		private void CreatingBeautyContentTopK() { 
			// TODO Auto-generated method stub

	        
	        //from here is mine
			
			//creating CosntraintsPlacement object
			ConstraintsPlacement objConstraints= new ConstraintsPlacement(this);
			//Creating array with states
			ArrayList<BlockNode> states = new ArrayList<BlockNode>();
			//Building the graph in a deph way
	    	GraphBuilder objGrapB= new GraphBuilder(1);
	    	int numElements=objElem.getNumberObjects();
	    	int numEnemies=objElem.getNumberObjectsEnemies();
	    	int globalControlSearch=0;
	    	//Beststates=objGrapB.basicDepthSearch(mediumStraight,height,numElements,numElements,states,objConstraints, objElem.getFinalList(),objElem);
	    	bestBranches=objGrapB.relativePositionDepthSearchTopK(mediumStraight,height,numElements-numEnemies,numElements-numEnemies,states,objConstraints, objElem.getFinalList(),objElem,-mediumStraight+1,mediumStraight-1,floorTileHeight,0,0,maxScreens,numEnemies,random,globalControlSearch);
	    	//Beststates=objGrapB.relativeTransPositionDepthSearch(mediumStraight,height,numElements,numElements,states,objConstraints, objElem.getFinalList(),objElem,-mediumStraight+1,mediumStraight-1,floorTileHeight,0,0,currentState,hTable);
	       
	    	Branch objBranch=new Branch();
	    	//bestBranches=objBranch.sortBranches(bestBranches);
	    	
	    	//System.out.print( "CounterStates"+objGrapB.getCounterIDs() );
	        
	        //imprimiendo los estados visitados
	        /*
	        Iterator<BlockNode> nombreIterator = states.iterator();
	        while(nombreIterator.hasNext()){
	        	BlockNode elemento = nombreIterator.next();
	        	System.out.print(elemento.getID()+" / ");
	        }*/
	        
	        //here we are painting as the best branches foundeded
	        
	        Iterator<Branch> nombreIterator = bestBranches.iterator();
	        while(nombreIterator.hasNext()){
	        	Branch elemento = nombreIterator.next();
	        	//System.out.print(elemento.getHeuristicValue()+ " / ");
	        }
	        
	        
	        //Here we will put the elements on the tile
	    	//Just here to implement the n screens!
	        for(int i=0;i<maxScreens;i++)
	        {//block from Lelis and Reis (>class GeradorDeFasses)
/*	        	try {
	        		Level levelScreen=PaintElements(((Branch)bestBranches.get(i)).getStates(),this.clone());					
					Screen objScreen=new Screen();
					objScreen.SaveScreen(levelScreen,odds,objElem);
	        		InformacoesTelas info = new InformacoesTelas();
			        CopiaArquivos copiador = new CopiaArquivos();
			        
			        info = info.carregaInfoTela("infoTela");
					info.setOutrasVariaveis(0, 0); // Salva outras informacoes da Tela
					info.salvaInfoTela("infoTela", info);					
					copiador.copy("" + i, "Screens/");
					
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	        }
	        
			
		}
		
		private Level PaintElements(ArrayList Beststates, Level mLevel)
		{
			powerUp=false;  
			Iterator<BlockNode> itBests = Beststates.iterator();
	        while(itBests.hasNext()){
	        	BlockNode elemento = itBests.next();
	        	int idElementToPlace=elemento.getType();
	        	if(idElementToPlace==objElem.getOddsTubes())
	        	{ 
	        		buildTubes(elemento,mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getOddsCannons())
	        	{
	        		buildCannons(elemento,mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getBlockElement())
	        	{
	        		buildBlocksElement(elemento,mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getCoins())
	        	{
	        		buildCoins(elemento, mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getOddsJump())
	        	{
	        		buildJumps(elemento, mLevel,Beststates,objElem.getFinalList());
	        	}
	        	else if(idElementToPlace==objElem.getOddsHillStraight())
	        	{
	        		buildHills(elemento, mLevel,Beststates,objElem.getFinalList());
	        	}
	        	else if(idElementToPlace==objElem.getEnemyRedKoopa() || idElementToPlace==objElem.getEnemyGreenKoopa() || idElementToPlace==objElem.getEnemyGoomba() || idElementToPlace==objElem.getEnemySpiky() || idElementToPlace==objElem.getEnemyFlower() || idElementToPlace==objElem.getEnemyArmoredTurtle() || idElementToPlace==objElem.getEnemyJumpFlower() || idElementToPlace==objElem.getEnemyCannonBall() || idElementToPlace==objElem.getEnemyChompFlower())
	        	{
	        		buildEnemies(elemento, mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getTubesFlower())
	        	{
	        		buildTubesFlower(elemento, mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getOddsHillStraightFloat())
	        	{
	        		buildHillsFloat(elemento, mLevel,Beststates,objElem.getFinalList());
	        	}
	        	else if(idElementToPlace==objElem.getSmallTube())
	        	{
	        		buildSmallTube(elemento, mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getWood())
	        	{
	        		buildWood(elemento, mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getBlockBlue())
	        	{
	        		buildBlockBlue(elemento, mLevel);
	        	}
	        	else if(idElementToPlace==objElem.getBlockWood())
	        	{
	        		buildBlockWood(elemento, mLevel,Beststates,objElem.getFinalList());
	        	}
	        }
			
			return mLevel;
		}
		
		private int buildStraight(int xo, int maxLength, boolean safe)
	    {
	        int length = maxLength;

	  

	        //int floor = (height - (1 + random.nextInt(4)));
	        int floor = 13;

	        //runs from the specified x position to the length of the segment
	        for (int x = xo; x < xo + length; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor) 
	                {
	                    setBlock(x, y, GROUND); 
	                }
	            }
	        }

	        if (!safe)
	        {
	            if (length > 5)
	            {
	                //decorate(xo, xo + length, floor);
	            }
	        }
	        floorTileHeight=floor;
	        return length;
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
	    
	    /*private int buildHillStraight(int xo, int maxLength)
	    {
	        int length = maxLength;
	        if (length > maxLength) length = maxLength;

	        int floor = height - 1 - random.nextInt(4);
	        for (int x = xo; x < xo + length; x++)
	        {
	            for (int y = 0; y < height; y++)
	            {
	                if (y >= floor)
	                {
	                    setBlock(x, y, GROUND);
	                }
	            }
	        }
	        
	        addEnemyLine(xo + 1, xo + length - 1, floor - 1);

	        int h = floor;

	        boolean keepGoing = true;

	        boolean[] occupied = new boolean[length];
	        while (keepGoing)
	        {
	            h = h - 2 - random.nextInt(3);

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
	                    //addEnemyLine(xxo, xxo + l, h - 1);
	                    if (random.nextInt(4) == 0)
	                    {
	                        //decorate(xxo - 1, xxo + l + 1, h);
	                        keepGoing = false;
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
	                                if (getBlock(x, y) == HILL_TOP_LEFT) setBlock(x, y, HILL_TOP_LEFT_IN);
	                                if (getBlock(x, y) == HILL_TOP_RIGHT) setBlock(x, y, HILL_TOP_RIGHT_IN);
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        return length;
	    }*/
	    private Level buildEnemies(BlockNode elemento,Level mLevel)
	    {
	    	int xEnemy = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        int wings =random.nextInt(10);
	        boolean typeWings=false;
	        if(wings>6)
	        {
	        	typeWings=true;
	        }
	        if(element.getTypeElem()==objElem.getEnemyRedKoopa())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(0, typeWings));
	        }
	        else if(element.getTypeElem()==objElem.getEnemyGreenKoopa())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(1, typeWings));	
	        }
	        else if(element.getTypeElem()==objElem.getEnemyGoomba())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(2, typeWings));	
	        }
	        else if(element.getTypeElem()==objElem.getEnemyArmoredTurtle() || element.getTypeElem()==objElem.getEnemySpiky())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(3, typeWings));	
	        }
	        else if(element.getTypeElem()==objElem.getEnemyJumpFlower())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(4, typeWings));	
	        }
	        else if(element.getTypeElem()==objElem.getEnemyCannonBall())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(5, typeWings));	
	        }
	        else if(element.getTypeElem()==objElem.getEnemyChompFlower() || element.getTypeElem()==objElem.getEnemyFlower())
	        {
	        	mLevel.setSpriteTemplate(xEnemy, floor, new SpriteTemplate(6, typeWings));	
	        }
	    	return mLevel;
	    }
	    
	    private Level buildHillsFloat(BlockNode elemento,Level mLevel,ArrayList states,ArrayList finalListElements)
	    {
	    	int xCoin = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        for (int y = floor; y >= floor-heigthElement; y--)
            {
	        	for (int x = xCoin; x < xCoin+widthElement; x++)
	        		{
	        		mLevel.setBlock(x, y, GROUND);
	        		
	        		}
            }
	        //boolean enemyAddedBefore=addEnemyLineMountains(xCoin + 1, xCoin + widthElement - 1, floor-heigthElement - 1);
	        //decorateMountainsFloat(xCoin , xCoin + widthElement , floor-heigthElement - 1,enemyAddedBefore,finalListElements,states);
	        return mLevel;
	    }
	    
	    private Level buildHills(BlockNode elemento,Level mLevel,ArrayList states,ArrayList finalListElements)
	    {
	    	int xJump = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        int idElement=element.getIdElem();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        boolean OverLoadHill=false;
	        byte byteZero=0;
	        
			for(int e=0;e<states.size();e++)
			{
				
				Elements elementR=(Elements)finalListElements.get(e);
				BlockNode stateR=(BlockNode)states.get(e);
				
				int xElementR = (int)(initialStraight+stateR.getX());
		        int floorR= (int)stateR.getY();
		        int widthElementR=elementR.getWidth();
		        int heigthElementR=elementR.getHeigth();
		        int typeR=elementR.getTypeElem();
		        int idElementR=elementR.getIdElem();
		        
		        if(typeR==objElem.getOddsHillStraight() && idElementR<idElement)
		        {
		        if(xJump+(widthElement-1)>=xElementR && xJump<xElementR+widthElementR )
		        {
		        	OverLoadHill=true;
		        }
		        }
		        
			}
	        
	        for (int y = floor; y >= floor-heigthElement; y--)
            {
	        	for (int x = xJump; x < xJump+widthElement; x++)
	        		{
	        		
	        		if(y==floor-heigthElement)
	        		{
	        			if(x==xJump)
	        			{
	        				mLevel.setBlock(x, y,byteZero );
	        				if(OverLoadHill==false)
	        					mLevel.setBlock(x, y, HILL_TOP_LEFT);
	        				else
	        					mLevel.setBlock(x, y, HILL_TOP_LEFT_IN);
	        			}
	        			else if(x==xJump+widthElement-1)
	        			{
	        				mLevel.setBlock(x, y,byteZero );
	        				if(OverLoadHill==false)
	        					mLevel.setBlock(x, y, HILL_TOP_RIGHT);
	        				else
	        					mLevel.setBlock(x, y, HILL_TOP_RIGHT_IN);
	        			}
	        			else
	        			{
	        				mLevel.setBlock(x, y,byteZero );
	        				mLevel.setBlock(x, y, HILL_TOP);
	        			}
	        			
	        		}
	        		else
	        		{
	        			if(x==xJump)
	        			{
	        				mLevel.setBlock(x, y,byteZero );
	        				mLevel.setBlock(x, y, HILL_LEFT);
	        			}
	        			else if(x==xJump+widthElement-1)
	        			{
	        				mLevel.setBlock(x, y,byteZero );
	        				mLevel.setBlock(x, y, HILL_RIGHT);
	        			}
	        			else
	        			{
	        				mLevel.setBlock(x, y,byteZero );
	        				mLevel.setBlock(x, y, HILL_FILL);
	        			}
	        		}
	        		}
            }
	        //boolean enemyAddedBefore=addEnemyLineMountains(xJump + 1, xJump + widthElement - 1, floor-heigthElement - 1);
	        //decorateMountains(xJump , xJump + widthElement , floor-heigthElement - 1, finalListElements, states,enemyAddedBefore);
	    	return mLevel;
	    }
	    
	    private Level buildJumps(BlockNode elemento,Level mLevel,ArrayList states,ArrayList finalListElements)
	    {
	    	int typeStairs=1;
	    	//System.out.println("xJump "+elemento.getX()+" "+elemento.getY());
	    	int xJump = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        for(int e=0;e<states.size();e++)
			{
				
	        	Elements elementR=(Elements)finalListElements.get(e);
				BlockNode stateR=(BlockNode)states.get(e);
				
				int xElementR = (int)(initialStraight+stateR.getX());
		        int floorR= (int)stateR.getY();
		        int widthElementR=elementR.getWidth();
		        int heigthElementR=elementR.getHeigth();
		        int typeR=elementR.getTypeElem();
		        int idElementR=elementR.getIdElem();
		        
		        int widthElementNext=widthElement+6;
		        int heigthElementNext=3;
		        int x=xJump-3;
		        int y=floor-heigthElement-1;
		        
		        if(x+(widthElementNext-1)>=xElementR && x<xElementR+widthElementR && y-heigthElementNext<=floorR && y>= floorR-heigthElementR )
		        {
		        	typeStairs=0;
		        }
		        
			}
	        
	        byte byteZero=0;
	        for (int y = floor; y >= floor-heigthElement; y--)
            {
	        	for (int x = xJump; x < xJump+widthElement; x++)
	        		{
	        		mLevel.setBlock(x, y, byteZero);
	        		
	        		}
            }
	        if(typeStairs==1)
	        {
	        	for(int x=xJump-2;x<xJump;x++)
	        	{
	        		if(x==xJump-2)
	        		{
	        		setBlock(x, floor-heigthElement-1, ROCK);
	        		
	        		}
	        		else
	        		{
	        		setBlock(x, floor-heigthElement-1, ROCK);
	        		setBlock(x, floor-heigthElement-2, ROCK);
	        		}
	        	}
	        	for(int x=xJump+widthElement;x<xJump+widthElement+2;x++)
	        	{
	        		if(x==xJump+widthElement)
	        		{
	        		setBlock(x, floor-heigthElement-1, ROCK);
	        		setBlock(x, floor-heigthElement-2, ROCK);
	        		}
	        		else
	        		{
	        		setBlock(x, floor-heigthElement-1, ROCK);
	        		
	        		} 
	        	}
	        }
	        
	        
	    	return mLevel;
	    }
	    
	    private Level buildCoins(BlockNode elemento,Level mLevel)
	    {
	    	int xCoin = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        for (int y = floor; y >= floor-heigthElement; y--)
            {
	        	for (int x = xCoin; x < xCoin+widthElement; x++)
	        		{
	        		mLevel.setBlock(x, y, COIN);
	        		
	        		}
            }
	        
	    	return mLevel;
	    }
	    
	    private Level buildCannons(BlockNode elemento, Level mLevel)
	    {
	        
	        int xCannon = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	      	        

	            for (int y = floor; y >= floor-heigthElement; y--)
	            {	               	            		                   
	               	if (y == floor-heigthElement)
	                   	{
	               		mLevel.setBlock(xCannon, y, (byte) (14 + 0 * 16));
	                    }
	                 else if (y == (floor-heigthElement) + 1)
	                    {
	                	
	                	 mLevel.setBlock(xCannon, y, (byte) (14 + 1 * 16));
	                    }
	                 else
	                    {
	                	
	                	 mLevel.setBlock(xCannon, y, (byte) (14 + 2 * 16));
	                    }	                    
	                
	            }
	            return mLevel;
        
	    }
	    
	    private Level buildTubesFlower(BlockNode elemento,Level mLevel)
	    {
	    	int xTube = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xTube"+xTube);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        for (int y = floor; y >= floor-heigthElement; y--)
            {
	        	for (int x=0;x<=1;x++)
	        	{
	        		int xPic = 10 + x;
	        		if (y == floor-heigthElement)
                   		{
               			//tube top
	        			
	        			mLevel.setBlock(xTube+x, y, (byte) (xPic + 0 * 16));
                   		}
	        		else
                    	{
	        			//tube side
	        			mLevel.setBlock(xTube+x, y, (byte) (xPic + 1 * 16));
                    	}	                    
	        		}
            	}
	        setSpriteTemplate(xTube, floor-heigthElement+1, new SpriteTemplate(Enemy.ENEMY_FLOWER, false));
	        return mLevel;
	    	}
	    
	    private Level buildTubes(BlockNode elemento,Level mLevel)
	    {
	    	int xTube = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xTube"+xTube);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        for (int y = floor; y >= floor-heigthElement; y--)
            {
	        	for (int x=0;x<=1;x++)
	        	{
	        		int xPic = 10 + x;
	        		if (y == floor-heigthElement)
                   		{
               			//tube top
	        			
	        			mLevel.setBlock(xTube+x, y, (byte) (xPic + 0 * 16));
                   		}
	        		else
                    	{
	        			//tube side
	        			mLevel.setBlock(xTube+x, y, (byte) (xPic + 1 * 16));
                    	}	                    
	        		}
            	}
	        return mLevel;
	    	}
	    private Level buildBlocksElement(BlockNode elemento, Level mLevel)
	    {
	        
	        int xBlock = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	      	  

	           for (int x = xBlock; x < xBlock+widthElement; x++)
	            {
	        	    int typeBlock =random.nextInt(3);
	               	if (typeBlock==0)
	                   	{
	               			int typeBlockPower =random.nextInt(5);
	               			if(typeBlockPower==0 && powerUp==false)
	               			{
	               				mLevel.setBlock(x, floor, BLOCK_POWERUP);
	               				powerUp=true;
	               			}
	               			else if (typeBlockPower==1)
		                    {
		                	
	               				mLevel.setBlock(x, floor, BLOCK_COIN);
		                    }
	               			else if (typeBlockPower==2 && powerUp==false)
		                    {		                	
	               				mLevel.setBlock(x, floor, (byte) (2 + 1 * 16));
	               				powerUp=true;
		                    }
	               			else if (typeBlockPower==3)
		                 	{
			                	
	               				mLevel.setBlock(x, floor, (byte) (1 + 1 * 16));
		                    }
	               			else if (typeBlockPower==4)
		                 	{
			                	
	               				mLevel.setBlock(x, floor, (byte) (1 + 1 * 16));
		                    }
	               			else
	               			{
	               				mLevel.setBlock(x, floor, (byte) (0 + 1 * 16));	
	               			}
	                   	}	                 	                    
	                 
	                 else
	                 	{
		                	
	                	 	mLevel.setBlock(x, floor, (byte) (0 + 1 * 16));
	                    }
	                 
	            }
	           
	           //addEnemyLine(xBlock + 1, xBlock + widthElement - 1, floor-heigthElement - 1);
	           
	        
	        /*if ((xLength - 1 - e) - (xStart + 1 + s) > 2)
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
            }*/
	           //addEnemyLineBlocks(xBlock + 1, xBlock + widthElement - 1, floor-heigthElement - 1);
	           return mLevel;
	    }
	    
	    private Level buildBlockBlue(BlockNode elemento, Level mLevel)
	    {
	        
	        int xBlock = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	      	        

	           for (int x = xBlock; x < xBlock+widthElement; x++)
	            {
	        	   mLevel.setBlock(x, floor, (byte) 28);
	            }
	        //decorate(xBlock , xBlock + widthElement , floor-heigthElement - 1);
	           return mLevel;
	    }
	    private Level buildBlockWood(BlockNode elemento, Level mLevel,  ArrayList states,ArrayList finalListElements)
	    {
	        
	        int xBlock = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	      	        

	           for (int x = xBlock; x < xBlock+widthElement; x++)
	            {
	        	   mLevel.setBlock(x, floor, (byte) 12);
	            }
        
	           //decorate(xBlock , xBlock + widthElement , floor-heigthElement - 1,finalListElements, states);
	           return mLevel;
	    }
	    private Level buildWood(BlockNode elemento, Level mLevel)
	    {
	        
	        int xBlock = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	      	        

	           for (int x = xBlock; x < xBlock+widthElement; x++)
	            {	        	   
	        	   mLevel.setBlock(x, (floor-heigthElement), (byte) 25); //Madeira cima
       			   mLevel.setBlock(x,(floor-heigthElement)+1, (byte) 41);//Madeira meio
       			   mLevel.setBlock(x, (floor-heigthElement)+2, (byte) 57); //Madeira baixo	               
	            }
        
	           return mLevel;
	    }
	    
	    private Level buildSmallTube(BlockNode elemento, Level mLevel)
	    {
	        
	        int xBlock = (int)(initialStraight+elemento.getX());
	        int floor= (int)elemento.getY();
	        Elements element=(Elements)objElem.getFinalList().get(elemento.getIdElement());
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        /*System.out.print("\n xCannon"+xCannon);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	      	        

	           for (int x = xBlock; x < xBlock+widthElement; x++)
	            {	        	   
	        	   mLevel.setBlock(x, (floor-heigthElement), (byte) 24); //caninho cima
       			   mLevel.setBlock(x,(floor-heigthElement)+1, (byte) 40); //caninho meio
       			   mLevel.setBlock(x, (floor-heigthElement)+2, (byte) 56); //caninho baixo	               
	            }
        
	           return mLevel;
	    }
	    
	    private boolean addEnemyLine(int x0, int x1, int y)
	    {
	    	boolean enemyAdded=false;
	    	//System.out.println("difficulty "+difficulty);
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
	               enemyAdded=true;
	            }
	        }
	        return enemyAdded;
	    }
	    private boolean addEnemyLineMountains(int x0, int x1, int y)
	    {
	    	boolean enemyAddedBefore=false;
	    	//System.out.println("difficulty "+difficulty);
	        for (int x = x0; x < x1; x++)
	        {
	            if (random.nextInt(20) < difficulty + 1)
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
	                enemyAddedBefore=true;
	            }
	        }
	        return enemyAddedBefore;
	    }
	    
	    private void addEnemyLineBlocks(int x0, int x1, int y)
	    {
	    	//System.out.println("difficulty "+difficulty);
	        for (int x = x0; x < x1; x++)
	        {
	            if (random.nextInt(15) < difficulty + 1)
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
	               
	            }
	        }
	    }

	    private void decorateMountainsFloat(int xStart, int xLength, int floor, boolean enemyAddedBefore, ArrayList finalListElements, ArrayList states)
	    {
	    	
	    	//if its at the very top, just return
	        if (floor < 1)
	        	return;

	        //        boolean coins = random.nextInt(3) == 0;
	        boolean rocks = true;

	        //add an enemy line above the box
	        Random r = new Random();
	        boolean enemyAdded=addEnemyLine(xStart + 1, xLength - 1, floor - 1);

	        int s = 1;
	        int e = 1;

	        if(enemyAdded==false && enemyAddedBefore==false)
	        {
	        if (floor - 2 > 0){
	            if ((xLength - e) - (xStart  + s) > 0){
	                for(int x = xStart + s; x < xLength- e; x++){
	                	boolean flag=true;
	                	for(int i=0;i<states.size();i++)
	                	{
	                		Elements element=(Elements)finalListElements.get(i);
	            			BlockNode state=(BlockNode)states.get(i);
	            			
	            			int xElement = (int)(initialStraight+state.getX());
	            	        int floorC= (int)state.getY()+1;
	            	        int widthElement=element.getWidth();
	            	        int heigthElement=element.getHeigth()+2;
	            	        
	            	        int widthElementNext=1;
	            	        int heigthElementNext=0;
	            	        
	            	        if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement && (floor-1)-heigthElementNext<=floorC && (floor-1)>= floorC-heigthElement )
	            	        {
	            	        	flag=false;
	            	        	break;
	            	        }
	            	       	            	        		            	        
	            	    } 
	                	if(flag==true)
	                	{
	                		setBlock(x, floor - 1, COIN);
	                	}
	                    
	                    //COINS++;
	                }
	            }
	        }
	        }

	        s = random.nextInt(4);
	        e = random.nextInt(4);
	        
	        //this fills the set of blocks and the hidden objects inside them
	        /*
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
	                                //BLOCKS_POWER++;
	                            }
	                            else
	                            {	//the fills a block with a hidden coin
	                                setBlock(x, floor - 4, BLOCK_COIN);
	                                //BLOCKS_COINS++;
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
	                            //BLOCKS_EMPTY++;
	                        }
	                    }
	                }
	                //additionElementToList("Block", 1,(xLength - 1 - e)-(xStart + 1 + s));
	            }
	            
	        }*/
	    }	    
	    
	    private void decorateMountains(int xStart, int xLength, int floor, ArrayList finalListElements, ArrayList states,boolean enemyAddedBefore)
	    {
	    	
	    	//if its at the very top, just return
	        if (floor < 1)
	        	return;

	        //        boolean coins = random.nextInt(3) == 0;
	        boolean rocks = true;

	        //add an enemy line above the box
	        Random r = new Random();
	        boolean enemyAdded=addEnemyLine(xStart + 1, xLength - 1, floor - 1);

	        int s = 1;
	        int e = 1;

	        if(enemyAdded==false && enemyAddedBefore==false)
	        {
	        if (floor - 2 > 0){
	            if ((xLength - e) - (xStart  + s) > 0){
	                for(int x = xStart + s; x < xLength- e; x++){
	                	boolean flag=true;
	                	for(int i=0;i<states.size();i++)
	                	{
	                		Elements element=(Elements)finalListElements.get(i);
	            			BlockNode state=(BlockNode)states.get(i);
	            			
	            			int xElement = (int)(initialStraight+state.getX());
	            	        int floorC= (int)state.getY()+1;
	            	        int widthElement=element.getWidth();
	            	        int heigthElement=element.getHeigth()+2;
	            	        
	            	        int widthElementNext=1;
	            	        int heigthElementNext=0;
	            	        
	            	        if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement && (floor-1)-heigthElementNext<=floorC && (floor-1)>= floorC-heigthElement )
	            	        {
	            	        	flag=false;
	            	        	break;
	            	        }
	            	       	            	        		            	        
	            	    } 
	                	if(flag==true)
	                	{
	                		setBlock(x, floor - 1, COIN);
	                	}
	                    
	                    //COINS++;
	                }
	            }
	        }
	        }

	        s = random.nextInt(4);
	        e = random.nextInt(4);
	        
	        //this fills the set of blocks and the hidden objects inside them
	        /*
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
	                                //BLOCKS_POWER++;
	                            }
	                            else
	                            {	//the fills a block with a hidden coin
	                                setBlock(x, floor - 4, BLOCK_COIN);
	                                //BLOCKS_COINS++;
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
	                            //BLOCKS_EMPTY++;
	                        }
	                    }
	                }
	                //additionElementToList("Block", 1,(xLength - 1 - e)-(xStart + 1 + s));
	            }
	            
	        }*/
	    }
	    private void decorate(int xStart, int xLength, int floor, ArrayList finalListElements, ArrayList states)
	    {
	    	
	    	//if its at the very top, just return
	        if (floor < 1)
	        	return;

	        //        boolean coins = random.nextInt(3) == 0;
	        boolean rocks = true;

	        //add an enemy line above the box
	        Random r = new Random();
	        boolean enemyAdded=addEnemyLine(xStart + 1, xLength - 1, floor - 1);

	        int s = 1;
	        int e = 1;

	        if(enemyAdded==false)
	        {
	        if (floor - 2 > 0){
	        	if ((xLength - e) - (xStart  + s) > 0){
	        		for(int x = xStart + s; x < xLength- e; x++){
	                	boolean flag=true;
	                	for(int i=0;i<states.size();i++)
	                	{
	                		Elements element=(Elements)finalListElements.get(i);
	            			BlockNode state=(BlockNode)states.get(i);
	            			
	            			int xElement = (int)(initialStraight+state.getX());
	            	        int floorC= (int)state.getY()+1;
	            	        int widthElement=element.getWidth();
	            	        int heigthElement=element.getHeigth()+2;
	            	        
	            	        int widthElementNext=1;
	            	        int heigthElementNext=0;
	            	        
	            	        if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement && (floor-1)-heigthElementNext<=floorC && (floor-1)>= floorC-heigthElement )
	            	        {
	            	        	flag=false;
	            	        	break;
	            	        }
	            	       	            	        		            	        
	            	    } 
	                	if(flag==true)
	                	{
	                		setBlock(x, floor - 1, COIN);
	                	}
	                    
	                    //COINS++;
	                }
	            }
	        }
	        }

	        s = random.nextInt(4);
	        e = random.nextInt(4);
	        
	        //this fills the set of blocks and the hidden objects inside them
	        /*
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
	                                //BLOCKS_POWER++;
	                            }
	                            else
	                            {	//the fills a block with a hidden coin
	                                setBlock(x, floor - 4, BLOCK_COIN);
	                                //BLOCKS_COINS++;
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
	                            //BLOCKS_EMPTY++;
	                        }
	                    }
	                }
	                //additionElementToList("Block", 1,(xLength - 1 - e)-(xStart + 1 + s));
	            }
	            
	        }*/
	    }
	    public static double round(double value, int places) {
	        if (places < 0) throw new IllegalArgumentException();

	        long factor = (long) Math.pow(10, places);
	        value = value * factor;
	        long tmp = Math.round(value);
	        return (double) tmp / factor;
	    }
}
