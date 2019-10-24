package beauty;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

public class ElementsToPlace { 
    //lastcom
	public Random random;
	public int SizeOdds = 0;
	public int SizeOddsEnemies = 0;
	public Elements objElement; 
	public Elements objElement2; 
	
	//elements
	//private static final int ODDS_STRAIGHT = 0;
	
	private static final int ODDS_JUMP = 0;
    private static final int ODDS_HILL_STRAIGHT = 1;	
    private static final int ODDS_TUBES =2;
    private static final int ODDS_CANNONS = 3;     
    private static final int ODDS_TUBES_FLOWER=4;
	//decorate
    private static final int WOOD=5;
    private static final int SMALL_TUBE=6;
    private static final int ODDS_HILL_STRAIGHT_FLOAT=7;    
    private static final int BLOCK_ELEMENT=8;
    private static final int COINS=9; 
    private static final int BLOCK_BLUE=10;
    private static final int BLOCK_WOOD=11;
     
    //enemies
    private static final int ENEMY_RED_KOOPA = 12;
	private static final int ENEMY_GREEN_KOOPA = 13;
    private static final int ENEMY_GOOMBA = 14;
    private static final int ENEMY_SPIKY = 15;
    private static final int ENEMY_FLOWER = 16;

    private static final int ENEMY_ARMORED_TURTLE=17;
    private static final int ENEMY_JUMP_FLOWER=18;
    private static final int ENEMY_CANNON_BALL=19;
    private static final int ENEMY_CHOMP_FLOWER=20;
    
    
    
    /*static final int BLOCK_EMPTY
    private static final int BLOCK_POWERUP
    private static final int BLOCK_COIN
    private static final int GROUND
    private static final int ROCK
    private static final int COIN*/
    
	
	
    //new elements
    //private static final int ODDS_GAPS = 10;
	
	
    
	private int[] odds = new int[21];
	public ArrayList<Elements> finalList = new ArrayList<Elements>();
	public ArrayList<Elements> finalListNoOrder = new ArrayList<Elements>();
	public ArrayList<Elements> finalListOriginal = new ArrayList<Elements>();
	
	public ElementsToPlace(Random random, int floorTileHeight, Hashtable hsObjectsScreen,int height,int numElementsGlobalParamet)
	{ 
		this.random=random;
		//odds[ODDS_STRAIGHT] = 0;
        odds[ODDS_HILL_STRAIGHT] = 0;
        odds[ODDS_TUBES] = 0;
        odds[ODDS_JUMP] = 0;
        odds[ODDS_CANNONS] =0;
        
        odds[BLOCK_ELEMENT] = 0;
        odds[COINS] = 0;
        
      //******enemies**************************
        odds[ENEMY_RED_KOOPA] = 0;
        odds[ENEMY_GREEN_KOOPA] = 0;
        odds[ENEMY_GOOMBA] = 0;
        odds[ENEMY_SPIKY] = 0;
        odds[ENEMY_FLOWER] = 0;
        
        odds[ENEMY_ARMORED_TURTLE] = 0;
        odds[ENEMY_JUMP_FLOWER] = 0;
        odds[ENEMY_CANNON_BALL] = 0;
        odds[ENEMY_CHOMP_FLOWER] = 0;
        
        odds[ODDS_TUBES_FLOWER] = 0;
        odds[ODDS_HILL_STRAIGHT_FLOAT] = 0;
        
        odds[WOOD] = 0;
        odds[SMALL_TUBE] = 0;
        odds[BLOCK_BLUE] = 0;
        odds[BLOCK_WOOD] = 0;
        
        

        //generation of number of objects
        //System.out.println("o numero escolhido "+numElementsGlobalParamet);
        //int numberInScreen =5+random.nextInt(1);
        int numberInScreen =numElementsGlobalParamet;
        for(int i=0;i<numberInScreen;i++)
        {
            ArrayList listObjects=new ArrayList<SingleElement> ();
            int typeElementSelected=0;
            SingleElement item = new SingleElement(0,0,"0");
            
        	do
        	{
        	int typeElement =random.nextInt(16);
        	
        	if(typeElement<3)
        	{
        		if(hsObjectsScreen.containsKey("Mountain"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("Mountain");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=ODDS_HILL_STRAIGHT;
            	odds[ODDS_HILL_STRAIGHT] = odds[ODDS_HILL_STRAIGHT]+1;
        		}       		
        		
        	}
        	else if(typeElement<5)
        	{
        		if(hsObjectsScreen.containsKey("Tube"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("Tube");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=ODDS_TUBES;
            	odds[ODDS_TUBES] = odds[ODDS_TUBES]+1;
        		} 
        	}
        	else if(typeElement<6)
        	{
        		if(hsObjectsScreen.containsKey("Gap"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("Gap");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=ODDS_JUMP;
            	odds[ODDS_JUMP] = odds[ODDS_JUMP]+1;
        		} 
        	}
        	else if(typeElement<8)
        	{
        		//Random randoml=new Random();
        		if(hsObjectsScreen.containsKey("Canion") && (random.nextInt(3)==0))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("Canion");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=ODDS_CANNONS;
            	odds[ODDS_CANNONS] = odds[ODDS_CANNONS]+1;
        		} 
        	}
        	else if(typeElement<10)
        	{
        		if(hsObjectsScreen.containsKey("Block"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("Block");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=BLOCK_ELEMENT;
            	odds[BLOCK_ELEMENT] = odds[BLOCK_ELEMENT]+1;
        		} 
        	}
        	else if(typeElement<12)
        	{
        		if(hsObjectsScreen.containsKey("TubeFlower"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("TubeFlower");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=ODDS_TUBES_FLOWER;
            	odds[ODDS_TUBES_FLOWER] = odds[ODDS_TUBES_FLOWER]+1;
        		} 
        	}
        	else if(typeElement<14)
        	{
        		if(hsObjectsScreen.containsKey("MountainFloat"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("MountainFloat");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=ODDS_HILL_STRAIGHT_FLOAT;
            	odds[ODDS_HILL_STRAIGHT_FLOAT] = odds[ODDS_HILL_STRAIGHT_FLOAT]+1;
        		} 
        	}
        	else if(typeElement<15)
        	{
        		int typeSubElement =random.nextInt(1);
        		if(typeSubElement==0)
        		{	
        			if(hsObjectsScreen.containsKey("Wood"))
        			{
        				listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("Wood");
        				//Random randomGenerator=new Random();
        		
        				int index = random.nextInt(listObjects.size());
        				item = (SingleElement) listObjects.get(index);
        				typeElementSelected=WOOD;
        				odds[WOOD] = odds[WOOD]+1;
        			} 
        		}
        		else{
        			if(hsObjectsScreen.containsKey("TubeSmall"))
            		{
            		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("TubeSmall");
            		//Random randomGenerator=new Random();
            		
            		int index = random.nextInt(listObjects.size());
            		item = (SingleElement) listObjects.get(index);
                	typeElementSelected=SMALL_TUBE;
                	odds[SMALL_TUBE] = odds[SMALL_TUBE]+1;
            		} 
        		}
        	}
        	
        	else if(typeElement<16)
        	{
        		if(hsObjectsScreen.containsKey("BlockWood"))
        		{
        		listObjects=(ArrayList<SingleElement>) hsObjectsScreen.get("BlockWood");
        		//Random randomGenerator=new Random();
        		
        		int index = random.nextInt(listObjects.size());
        		item = (SingleElement) listObjects.get(index);
            	typeElementSelected=BLOCK_WOOD;
            	odds[BLOCK_WOOD] = odds[BLOCK_WOOD]+1;
        		} 
        	}

        	} while (listObjects.isEmpty());
        	
        	//System.out.println("finalmenteseleccionado "+item.getTypeElement()+" "+item.getHeigth()+" "+item.getWidth());
        	objElement = new Elements();
    		objElement.setIdElem(i);
    		objElement.setTypeElem(typeElementSelected);
        	if(typeElementSelected==ODDS_HILL_STRAIGHT_FLOAT)
        	{
        		objElement.setHeigth(1);
        		objElement.setWidth(4); 
        	}
        	else if(typeElementSelected==ODDS_JUMP)
        	{
        		objElement.setHeigth(GenerateHeight(objElement.getTypeElem(),floorTileHeight));
        		objElement.setWidth(item.getWidth()); 
        	}
        	else if(typeElementSelected==ODDS_HILL_STRAIGHT)
        	{
        		if( (floorTileHeight-item.getHeigth())>(height/3)+1 )
        		{
        			objElement.setHeigth(item.getHeigth());
            		objElement.setWidth(item.getWidth());
        		}
        		else
        		{	int sizeHill = 1+random.nextInt(3);
        			objElement.setHeigth(sizeHill);
            		objElement.setWidth(item.getWidth());
        		}
        		for(int j=0;j<finalList.size();j++)
        		{
        			if(finalList.get(j).getTypeElem()==ODDS_HILL_STRAIGHT)
        			{ 
        				if(finalList.get(j).getHeigth()==objElement.getHeigth())
        				{
        					objElement.setHeigth(objElement.getHeigth()-1);
        				}
        				else if(finalList.get(j).getHeigth()<objElement.getHeigth() )
        				{
        					int tempHeight=finalList.get(j).getHeigth();
        					finalList.get(j).setHeigth(objElement.getHeigth());
        					objElement.setHeigth(tempHeight);
        				}
        			}
        		}
        	}
        	else
        	{
        		objElement.setHeigth(item.getHeigth());
        		objElement.setWidth(item.getWidth()); 
        	}   		
    		
    		finalList.add(objElement);
    		//System.out.println("finalList1 "+finalList.get(i).getTypeElem() + " "+finalList.get(i).getHeigth() +" "+finalList.get(i).getWidth());
    		//finalListOriginal=new ArrayList<Elements>(finalList);
    		
        }
        SingleElement objSingleElement= new SingleElement(0, 0, "");
        finalList=objSingleElement.sortElementsbyType(finalList);
        finalListNoOrder=new ArrayList<Elements>();
        
        ArrayList subAListElements=new ArrayList<Elements>();
        for(int i=0;i<finalList.size();i++)
        {
        	objElement2=new Elements();
        	objElement2=objElement2.cloneElements(finalList.get(i), i);
        	finalListNoOrder.add(objElement2);
        	if((finalList.get(i)).getTypeElem()>ODDS_HILL_STRAIGHT)
        	{
        		subAListElements.add(finalList.get(i));
        	}
        }
        subAListElements=objSingleElement.sortElementsbyAreas(subAListElements);
        Collections.reverse(subAListElements);
        subAListElements=objSingleElement.sortElementsbyType(subAListElements);
        int counterPartial=0;
        for(int i=0;i<finalList.size();i++)
        {
        	if((finalList.get(i)).getTypeElem()>ODDS_HILL_STRAIGHT)
        	{        		
        		finalList.set(i, (Elements)subAListElements.get(counterPartial));
        		counterPartial=counterPartial+1;
        	}
        }
        
		for(int i=0;i<finalList.size();i++)
		{
			finalList.get(i).setIdElem(i);
			finalListNoOrder.get(i).setIdElem(i);
			//System.out.println("List Original" + finalListOriginal.get(i).getTypeElem()+ " "+finalListOriginal.get(i).getHeigth() +" "+finalListOriginal.get(i).getWidth());
			//System.out.println("List" + finalList.get(i).getTypeElem()+ " "+finalList.get(i).getHeigth() +" "+finalList.get(i).getWidth());
			//System.out.println("List No order areas" + finalListNoOrder.get(i).getTypeElem()+ " "+finalListNoOrder.get(i).getHeigth() +" "+finalListNoOrder.get(i).getWidth());
			//System.out.println("----");
		}
		//System.out.println("");
        
        /*for(int i=0;i<odds.length;i++)
        {
        	for(int j=0;j<odds[i];j++)
        	{
        		objElement = new Elements();
        		objElement.setIdElem(finalList.size());
        		objElement.setTypeElem(i);
        		objElement.setHeigth(GenerateHeight(objElement.getTypeElem(),floorTileHeight));
        		objElement.setWidth(GenerateWidth(objElement.getTypeElem())); 
        		finalList.add(objElement);
        	}
        }*/
        
	}
	
	public ElementsToPlace(Random random, int floorTileHeight,int num_ODDS_HILL_STRAIGHT,int num_ODDS_TUBES, int num_ODDS_JUMP,
			int num_ODDS_CANNONS, int num_BLOCK_ELEMENT, int num_COINS, int num_ENEMY_RED_KOOPA, int num_ENEMY_GREEN_KOOPA, int num_ENEMY_GOOMBA, 
			int num_ENEMY_SPIKY, int num_ENEMY_FLOWER, int num_ENEMY_ARMORED_TURTLE, int num_ENEMY_JUMP_FLOWER, int num_ENEMY_CANNON_BALL,
			int num_ENEMY_CHOMP_FLOWER,int num_ODDS_TUBES_FLOWER,int num_ODDS_HILL_STRAIGHT_FLOAT,int num_WOOD, int num_SMALL_TUBE, int num_BLOCK_BLUE, int num_BLOCK_WOOD)
	{ 
		this.random=random;
		//odds[ODDS_STRAIGHT] = 0;
        odds[ODDS_HILL_STRAIGHT] = num_ODDS_HILL_STRAIGHT;
        odds[ODDS_TUBES] = num_ODDS_TUBES;
        odds[ODDS_JUMP] = num_ODDS_JUMP;
        odds[ODDS_CANNONS] =num_ODDS_CANNONS;
        
        odds[BLOCK_ELEMENT] = num_BLOCK_ELEMENT;
        odds[COINS] = num_COINS;
        
      //******enemies**************************
        odds[ENEMY_RED_KOOPA] = num_ENEMY_RED_KOOPA;
        odds[ENEMY_GREEN_KOOPA] = num_ENEMY_GREEN_KOOPA;
        odds[ENEMY_GOOMBA] = num_ENEMY_GOOMBA;
        odds[ENEMY_SPIKY] = num_ENEMY_SPIKY;
        odds[ENEMY_FLOWER] = num_ENEMY_FLOWER;
        
        odds[ENEMY_ARMORED_TURTLE] = num_ENEMY_ARMORED_TURTLE;
        odds[ENEMY_JUMP_FLOWER] = num_ENEMY_JUMP_FLOWER;
        odds[ENEMY_CANNON_BALL] = num_ENEMY_CANNON_BALL;
        odds[ENEMY_CHOMP_FLOWER] = num_ENEMY_CHOMP_FLOWER;
        
        odds[ODDS_TUBES_FLOWER] = num_ODDS_TUBES_FLOWER;
        odds[ODDS_HILL_STRAIGHT_FLOAT] = num_ODDS_HILL_STRAIGHT_FLOAT;
        
        odds[WOOD] = num_WOOD;
        odds[SMALL_TUBE] = num_SMALL_TUBE;
        odds[BLOCK_BLUE] = num_BLOCK_BLUE;
        odds[BLOCK_WOOD] = num_BLOCK_WOOD;
        
        
        for(int i=0;i<odds.length;i++)
        {
        	for(int j=0;j<odds[i];j++)
        	{
        		objElement = new Elements();
        		objElement.setIdElem(finalList.size());
        		objElement.setTypeElem(i);
        		objElement.setHeigth(GenerateHeight(objElement.getTypeElem(),floorTileHeight));
        		objElement.setWidth(GenerateWidth(objElement.getTypeElem())); 
        		finalList.add(objElement);
        	}
        }
        
	}
	public int[] getOdds()
	{
		return odds;
		
	}
	public int getNumberObjects()
	{
		for(int i=0;i<odds.length;i++)
		{
			if(odds[i]>0)
			{
				SizeOdds=SizeOdds+odds[i];
			}
			
		}
		return SizeOdds;
	}
	
	public int getNumberObjectsEnemies()
	{
		for(int i=0;i<odds.length;i++)
		{
			if(odds[i]>0)
			{
				if(i==ENEMY_RED_KOOPA || i==ENEMY_GREEN_KOOPA || i==ENEMY_GOOMBA || i==ENEMY_SPIKY || i==ENEMY_FLOWER || i==ENEMY_ARMORED_TURTLE || i==ENEMY_JUMP_FLOWER || i==ENEMY_CANNON_BALL || i==ENEMY_CHOMP_FLOWER)
				SizeOddsEnemies=SizeOddsEnemies+odds[i];
			}
			
		}
		return SizeOddsEnemies;
	}
	
	public int GenerateHeight(int type,int floorTileHeight)
	{
		if(type==ODDS_TUBES)
		{
			//Size used by NLG
			//return random.nextInt(2) + 1;
			return 1;
		}
		else if(type==ODDS_CANNONS)
		{
			//size used by NLG
			//return random.nextInt(4);
			return 1;
		}
		else if(type==BLOCK_ELEMENT)
		{
			//size used by NLG
			return 0;
		}
		else if(type==COINS)
		{
			//return random.nextInt(4);
			return 3;
		}
		else if(type==ODDS_JUMP)
		{
			return floorTileHeight;
		}
		else if(type==ODDS_HILL_STRAIGHT)
		{
			//return falta determinar exactitud
			return 4;
		}
		//******enemies**************************
		else if(type==ENEMY_RED_KOOPA || type==ENEMY_GREEN_KOOPA || type==ENEMY_GOOMBA || type==ENEMY_SPIKY || type==ENEMY_FLOWER || type==ENEMY_ARMORED_TURTLE || type==ENEMY_JUMP_FLOWER || type==ENEMY_CANNON_BALL || type==ENEMY_CHOMP_FLOWER)
		{
			return 1;
		}
		else if(type==ODDS_TUBES_FLOWER)
		{
			//Size used by NLG
			//return random.nextInt(2) + 1;
			return 1;
		}
		else if(type==ODDS_HILL_STRAIGHT_FLOAT)
		{
			//return falta determinar exactitud
			return 1;
		}
		//news
		else if(type==WOOD )
		{
			//return falta determinar exactitud
			return 2;
		}
		else if(type==SMALL_TUBE)
		{
			//return falta determinar exactitud
			return 2;
		}
		else if(type==BLOCK_BLUE )
		{
			//return falta determinar exactitud
			return 0;
		}
		else if(type==BLOCK_WOOD )
		{
			//return falta determinar exactitud
			return 0;
		}
		return 0;
		
	}
	
	public int GenerateWidth(int type)
	{
		
		if(type==ODDS_TUBES)
		{
			//Size used by NLG
			return 2;
		}
		else if(type==ODDS_CANNONS)
		{
			//size used by NLG
			return 1;
		}
		else if(type==BLOCK_ELEMENT)
		{
			//size used by NLG
			//return random.nextInt(4)+1;
			return 6;
		}
		else if(type==COINS)
		{
			//size used by NLG
			//return random.nextInt(4)+1;
			return 4;
		}
		else if(type==ODDS_JUMP)
		{
			//return random.nextInt(3)+2;
			return 3;
		}
		else if(type==ODDS_HILL_STRAIGHT)
		{
			//return falta determinar exactitud
			return 6;
		}
		else if(type==ENEMY_RED_KOOPA || type==ENEMY_GREEN_KOOPA || type==ENEMY_GOOMBA || type==ENEMY_SPIKY || type==ENEMY_FLOWER || type==ENEMY_ARMORED_TURTLE || type==ENEMY_JUMP_FLOWER || type==ENEMY_CANNON_BALL || type==ENEMY_CHOMP_FLOWER)
		{
			return 1;
		}
		else if(type==ODDS_TUBES_FLOWER)
		{
			//Size used by NLG
			//return random.nextInt(2) + 1;
			return 2;
		}
		else if(type==ODDS_HILL_STRAIGHT_FLOAT)
		{
			//return falta determinar exactitud
			return 5;
		}
		else if(type==WOOD )
		{
			//return falta determinar exactitud
			return 1;
		}
		else if(type==SMALL_TUBE)
		{
			//return falta determinar exactitud
			return 1;
		}
		else if(type==BLOCK_BLUE )
		{
			//return falta determinar exactitud
			return 4;
		}
		else if(type==BLOCK_WOOD )
		{
			//return falta determinar exactitud
			return 4;
		}
		return 0;
	}
	 
	public void setFinalList(ArrayList finalListNoOrder)
	{
		finalList= finalListNoOrder;
	}
	
	public ArrayList getFinalList()
	{
		return finalList;
	}
	
	public ArrayList getFinalListNoOrder()
	{
		return finalListNoOrder;
	}
	
    public static final int getOddsTubes() {
		return ODDS_TUBES;
	}
	public static final int getOddsJump() {
		return ODDS_JUMP;
	}
	public static final int getOddsCannons() {
		return ODDS_CANNONS;
	}
	public static final int getOddsHillStraight() {
		return ODDS_HILL_STRAIGHT;
	}
	public static final int  getBlockElement() {
		return BLOCK_ELEMENT;
	}
	public static final int  getCoins() {
		return COINS;
	}

	//******enemies**************************
	public static final int getEnemyRedKoopa() {
		return ENEMY_RED_KOOPA;
	}
	public static final int getEnemyGreenKoopa() {
		return ENEMY_GREEN_KOOPA;
	}
	public static final int getEnemyGoomba() {
		return ENEMY_GOOMBA;
	}
	public static final int getEnemySpiky() {
		return ENEMY_SPIKY;
	}
	public static final int getEnemyFlower() {
		return ENEMY_FLOWER;
	}	
    public static int getEnemyArmoredTurtle() {
		return ENEMY_ARMORED_TURTLE;
	}
	public static int getEnemyJumpFlower() {
		return ENEMY_JUMP_FLOWER;
	}
	public static int getEnemyCannonBall() {
		return ENEMY_CANNON_BALL;
	}
	public static int getEnemyChompFlower() {
		return ENEMY_CHOMP_FLOWER;
	}
	public static int getTubesFlower() {
		return ODDS_TUBES_FLOWER;
	}
	public static int getOddsHillStraightFloat() {
		return ODDS_HILL_STRAIGHT_FLOAT;
	}
	public static int getOddsTubesFlower() {
		return ODDS_TUBES_FLOWER;
	}
	public static int getWood() {
		return WOOD;
	}
	public static int getSmallTube() {
		return SMALL_TUBE;
	}
	public static int getBlockBlue() {
		return BLOCK_BLUE;
	}
	public static int getBlockWood() {
		return BLOCK_WOOD;
	}
	public Random getRandom() {
		return random;
	}
	public int getSizeOdds() {
		return SizeOdds;
	}
	public int getSizeOddsEnemies() {
		return SizeOddsEnemies;
	}
	public Elements getObjElement() {
		return objElement;
	}
}
