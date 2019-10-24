package dk.itu.mario.scene;
import java.awt.GraphicsConfiguration;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;

import Metrics.Metrics;
import beauty.BeautyCustomizedLevel;
import beauty.LoadBeautyLevel;
import beauty.SingleScreen;
import dk.itu.mario.level.BgLevelGenerator;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.engine.sonar.FixedSoundSource;
import dk.itu.mario.engine.sprites.CoinAnim;
import dk.itu.mario.engine.sprites.FireFlower;
import dk.itu.mario.engine.sprites.Mario;
import dk.itu.mario.engine.sprites.Mushroom;
import dk.itu.mario.engine.sprites.Particle;
import dk.itu.mario.engine.sprites.Sprite;
import dk.itu.mario.engine.Art;
import dk.itu.mario.engine.BgRenderer;
import dk.itu.mario.engine.DataRecorder;
import dk.itu.mario.engine.LevelRenderer;
import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.level.CustomizedLevel;
import dk.itu.mario.level.Level;
import dk.itu.mario.level.RandomLevel;
import dk.itu.mario.level.generator.CustomizedLevelGenerator;
import dk.itu.mario.engine.Play;
import dk.itu.mario.res.ResourcesManager;

	public class LevelSceneTestLoad extends LevelScene{

			ArrayList<Double> switchPoints;
			private double thresshold; //how large the distance from point to mario should be before switching
			private int point = -1;
			private int []checkPoints;
			private boolean isCustom;
			private String nameFile;
			private int typeTask;

			public LevelSceneTestLoad(GraphicsConfiguration graphicsConfiguration,
					MarioComponent renderer, long seed, int levelDifficulty, int type,boolean isCustom,String nameFile, int typeTask){
				super(graphicsConfiguration,renderer,seed,levelDifficulty,type);
				this.isCustom = isCustom;
				this.nameFile=nameFile;
				this.typeTask=typeTask;
			
			}

			public void init() {
		        try
		        {
		            Level.loadBehaviors(new DataInputStream(ResourcesManager.class.getResourceAsStream("res/tiles.dat")));
		        }
		        catch (IOException e)
		        {
		            e.printStackTrace();
		            System.exit(0);
		        } 

		        if(level==null)
		        	if(isCustom){
		        		
		        		//CustomizedLevelGenerator clg = new CustomizedLevelGenerator();
		        		//beautyLevelGenerator clg = new beautyLevelGenerator();
		        		//GamePlay gp = new GamePlay();
		        		//gp = gp.read("player.txt");
		                	//currentLevel = (Level)clg.generateLevel(gp);
		    
		    			
		        		//********mthod for load and print beauty screens*******//
		        		//System.out.println("Hamlet"+typeTask);
		    			if(typeTask==3)
		    			{
		        		currentLevel = new LoadBeautyLevel(84, 15, levelSeed, levelDifficulty,levelType,nameFile);
		    			}
		        		//********mthod for load and apply metruc beauty screens*******//
		    			
		    			if(typeTask==4)
		    			{
		    			double maxMetricDiff=0;
		    			double minMetricDiff=10000000;
		    			int maxMetricIndex;
		    			int minMetricIndex;
		    			
		    			int maxScreensEvaluated=173;
		    			ArrayList metricsList=new ArrayList<SingleScreen>();
		    			for(int i=0;i<maxScreensEvaluated;i++)		    				
		    			{
		    				currentLevel = new LoadBeautyLevel(84, 15, levelSeed, levelDifficulty,levelType,"Tela"+i);
		    				Metrics objDifficultyMetric=new Metrics(20,15,currentLevel);		        		
		    				double metricValue=objDifficultyMetric.MetricsCalc(1, currentLevel); 
		    				SingleScreen objSingleScreen=new SingleScreen();
		    				objSingleScreen.setNameScreen("Tela"+i);		    			
		    				objSingleScreen.setValueMetric(metricValue);
		    				metricsList.add(objSingleScreen);
		        		
		    				if(metricValue<minMetricDiff)
		    				{
		    					minMetricDiff=metricValue;
		    					minMetricIndex=i;
		    				}
		    				if(metricValue>maxMetricDiff)
		    				{
		    					maxMetricDiff=metricValue;
		    					maxMetricIndex=i;
		    				}		    					    			
		    			}
		    			
		    			
		    			Iterator<SingleScreen> nombreIterator = metricsList.iterator();
		    	        while(nombreIterator.hasNext()){
		    	        	SingleScreen elemento = nombreIterator.next();
		    	        	//System.out.print(elemento.getNameScreen()+"("+elemento.getValueMetric()+" "+elemento.getValueMetricNormalized()+" )  / ");
		    	        }
		    	        
		    	        Normalization(metricsList, minMetricDiff, maxMetricDiff);
		    	        Hashtable hsMetrics = new Hashtable();
		    	        hsMetrics=CreateHashTableMetrics(hsMetrics, metricsList);
		    	        SaveHashTable(hsMetrics);
		    			}
		    	      //********End*******//
		              } 
			        	else 
		        		currentLevel = new RandomLevel(320, 15, levelSeed, levelDifficulty,levelType);

		        try {
					 level = currentLevel.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}

		        //level is always overground
		        Art.startMusic(1);

		        paused = false;
		        Sprite.spriteContext = this;
		        sprites.clear();

		        layer = new LevelRenderer(level, graphicsConfiguration, 320, 240);
		        for (int i = 0; i < 2; i++)
		        {
		            int scrollSpeed = 4 >> i;
		            int w = ((level.getWidth() * 16) - 320) / scrollSpeed + 320;
		            int h = ((level.getHeight() * 16) - 240) / scrollSpeed + 240;
		            Level bgLevel = BgLevelGenerator.createLevel(w / 32 + 1, h / 32 + 1, i == 0, levelType);
		            bgLayer[i] = new BgRenderer(bgLevel, graphicsConfiguration, 320, 240, scrollSpeed);
		        }

		        double oldX = 0;
		        if(mario!=null)
		        	oldX = mario.x;

		        mario = new Mario(this);
		        sprites.add(mario);
		        startTime = 1;

		        timeLeft = 200*15;

		        tick = 0;

		        /*
		         * SETS UP ALL OF THE CHECKPOINTS TO CHECK FOR SWITCHING
		         */
		        switchPoints = new ArrayList<Double>();

		        //first pick a random starting waypoint from among ten positions
		    	int squareSize = 16; //size of one square in pixels
		        int sections = 10;

		    	double startX = 32; //mario start position
		    	double endX = level.getxExit()*squareSize; //position of the end on the level
		    	if(!isCustom && recorder==null)
		    		recorder = new DataRecorder(this,(RandomLevel)level,keys);

		        gameStarted = false;
			}



			public void tick(){
				super.tick();

				if(recorder != null && !gameStarted){
					recorder.startLittleRecord();
					recorder.startTime();
					gameStarted = true;
				}
				if(recorder != null)
				recorder.tickRecord();
			}

			public void winActions(){
				if(recorder != null)
				recorder.fillGamePlayMetrics((RandomLevel)level);
				marioComponent.win();
			}

			public void deathActions(){
				if(Mario.lives <=0){//has no more lives
					if(recorder != null)
					recorder.fillGamePlayMetrics((RandomLevel)level);
					marioComponent.lose();
				}
				else // mario still has lives to play :)--> have a new beginning
					reset();
			}

			public void bump(int x, int y, boolean canBreakBricks)
		    {
		        byte block = level.getBlock(x, y);

		        if ((Level.TILE_BEHAVIORS[block & 0xff] & Level.BIT_BUMPABLE) > 0)
		        {
		            bumpInto(x, y - 1);
		            level.setBlock(x, y, (byte) 4);

		            if (((Level.TILE_BEHAVIORS[block & 0xff]) & Level.BIT_SPECIAL) > 0)
		            {
		                sound.play(Art.samples[Art.SAMPLE_ITEM_SPROUT], new FixedSoundSource(x * 16 + 8, y * 16 + 8), 1, 1, 1);
		                if (!Mario.large)
		                {
		                    addSprite(new Mushroom(this, x * 16 + 8, y * 16 + 8));
		                }
		                else
		                {
		                    addSprite(new FireFlower(this, x * 16 + 8, y * 16 + 8));
		                }

		                if(recorder != null){
		                	recorder.blockPowerDestroyRecord();
		                }
		            }
		            else
		            {
		            	//TODO should only record hidden coins (in boxes)
		            	if(recorder != null){
		            		recorder.blockCoinDestroyRecord();
		            	}

		                Mario.getCoin();
		                sound.play(Art.samples[Art.SAMPLE_GET_COIN], new FixedSoundSource(x * 16 + 8, y * 16 + 8), 1, 1, 1);
		                addSprite(new CoinAnim(x, y));
		            }
		        }

		        if ((Level.TILE_BEHAVIORS[block & 0xff] & Level.BIT_BREAKABLE) > 0)
		        {
		            bumpInto(x, y - 1);
		            if (canBreakBricks)
		            {
		            	if(recorder != null){
		            		recorder.blockEmptyDestroyRecord();
		            	}

		                sound.play(Art.samples[Art.SAMPLE_BREAK_BLOCK], new FixedSoundSource(x * 16 + 8, y * 16 + 8), 1, 1, 1);
		                level.setBlock(x, y, (byte) 0);
		                for (int xx = 0; xx < 2; xx++)
		                    for (int yy = 0; yy < 2; yy++)
		                        addSprite(new Particle(x * 16 + xx * 8 + 4, y * 16 + yy * 8 + 4, (xx * 2 - 1) * 4, (yy * 2 - 1) * 4 - 8));
		            }

		        }
		    }

			 public void bumpInto(int x, int y)
			    {
			        byte block = level.getBlock(x, y);
			        if (((Level.TILE_BEHAVIORS[block & 0xff]) & Level.BIT_PICKUPABLE) > 0)
			        {
			            Mario.getCoin();
			            sound.play(Art.samples[Art.SAMPLE_GET_COIN], new FixedSoundSource(x * 16 + 8, y * 16 + 8), 1, 1, 1);
			            level.setBlock(x, y, (byte) 0);
			            addSprite(new CoinAnim(x, y + 1));


			            //TODO no idea when this happens... maybe remove coin count
			            if(recorder != null)
			            	recorder.recordCoin();
			        }

			        for (Sprite sprite : sprites)
			        {
			            sprite.bumpCheck(x, y);
			        }
			    }

			private int randomNumber(int low, int high){
				return new Random(new Random().nextLong()).nextInt(high-low)+low;
			}

			private int toBlock(float n){
				return (int)(n/16);
			}

			private int toBlock(double n){
				return (int)(n/16);
			}

			private float toReal(int b){
				return b*16;
			}
			
			//method implemented to normalize the values of the metric
			public ArrayList Normalization(ArrayList metricsList,double minValueMetric, double maxValueMetric)
			{
				double normalizedValue;
				int capetus;
				int maxScale=7;
				int minScale=1;
				
				Iterator<SingleScreen> nombreIterator = metricsList.iterator();
    	        while(nombreIterator.hasNext()){
    	        	SingleScreen elemento = nombreIterator.next();
    	        	normalizedValue= (1+ (elemento.getValueMetric()-minValueMetric)*(maxScale-minScale)/(maxValueMetric-minValueMetric));
    	        	normalizedValue=round(normalizedValue);
    	        	elemento.setValueMetricNormalized((int)normalizedValue);   	    
    	        	//System.out.print(elemento.getNameScreen()+"("+elemento.getValueMetric()+" "+elemento.getValueMetricNormalized()+" )  / ");
    	        }
				
				return metricsList;
			}
			public int round(double d){
			    double dAbs = Math.abs(d);
			    int i = (int) dAbs;
			    double result = dAbs - (double) i;
			    if(result<0.5){
			        return d<0 ? -i : i;            
			    }else{
			        return d<0 ? -(i+1) : i+1;          
			    }
			}
			public Hashtable CreateHashTableMetrics(Hashtable hsMetrics,ArrayList metricsList)
			{
				Iterator<SingleScreen> nombreIterator = metricsList.iterator();
    	        while(nombreIterator.hasNext()){
    	        	SingleScreen elemento = nombreIterator.next();
    	        	int keyhsMetrics=elemento.getValueMetricNormalized();
    	        	if(hsMetrics.get(keyhsMetrics)!=null)
    	        	{
    	        		ArrayList ScreensOfMetricx =(ArrayList<SingleScreen>)(hsMetrics.get(keyhsMetrics));
    	        		ScreensOfMetricx.add(elemento);
    	        		hsMetrics.put(keyhsMetrics, ScreensOfMetricx);
    	        	}
    	        	else
    	        	{
    	        		ArrayList ScreensOfMetricx=new ArrayList<SingleScreen>();
    	        		ScreensOfMetricx.add(elemento);
    	        		hsMetrics.put(keyhsMetrics, ScreensOfMetricx);
    	        	}
    	        	//System.out.print(elemento.getNameScreen()+"("+elemento.getValueMetric()+" "+elemento.getValueMetricNormalized()+" )  / ");
    	        }
				//hsMetrics.put(key, value)
				return hsMetrics;
			}
			public void SaveHashTable(Hashtable hsTable)
			{
				 
			    try {
			    	FileOutputStream fos=new FileOutputStream("TabelaTelasMetric");
					ObjectOutputStream os = new ObjectOutputStream(fos);
					os.writeObject(hsTable);
					os.close();	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}


}
