package benweber;

import java.util.ArrayList;
import java.util.Random;

import dk.itu.mario.MarioInterface.Constraints;
import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.SpriteTemplate;
import dk.itu.mario.level.Level;

public class CustomizedLevel extends Level implements LevelInterface {

	 public   int ENEMIES = 0; //the number of enemies the level contains
	 public   int BLOCKS_EMPTY = 0; // the number of empty blocks
	 public   int BLOCKS_COINS = 0; // the number of coin blocks
	 public   int BLOCKS_POWER = 0; // the number of power blocks
	 public   int COINS = 0; //These are the coins in boxes that Mario collect

	 private double CHANCE_BLOCK_POWER_UP = 0.1;
	 private double CHANCE_BLOCK_COIN = 0.3;
	 private double CHANCE_BLOCK_ENEMY = 0.2;
	 private double CHANCE_WINGED = 0.5;
	 private double CHANCE_COIN = 0.2;
	 private double COIN_HEIGHT = 5;
	 private double CHANCE_PLATFORM = 0.1;
	 private double CHANCE_END_PLATFORM = 0.1;
	 private int PLATFORM_HEIGHT = 4;
	 private double CHANCE_ENEMY = 0.1;
	 private double CHANCE_PIPE = 0.1;
	 private int PIPE_MIN_HEIGHT = 2;
	 private double PIPE_HEIGHT = 3.0;
	 private int minX = 5;
	 private double CHANCE_HILL = 0.1;
	 private double CHANCE_END_HILL = 0.3;
	 private double CHANCE_HILL_ENEMY = 0.3;
	 private double HILL_HEIGHT = 4;
	 private int GAP_LENGTH = 5;
	 private double CHANGE_GAP = 0.1;
	 private double CHANGE_HILL_CHANGE = 0.1;
	 private double GAP_OFFSET = -5;
	 private double GAP_RANGE = 10;
	 private int GROUND_MAX_HEIGHT = 5;
	 
	 // controls the fun
	 Random rand = new Random();

	 // constraints
     int gapCount = 0;
     int turtleCount = 0;
     int coinBlockCount = 0;

    public CustomizedLevel(int width, int height, long seed, int difficulty,
                           int type, GamePlay playerMetrics) {
        super(width, height);

        ArrayList<Integer> ground = new ArrayList<Integer>();
		
		// used to place the ground
		int lastY = GROUND_MAX_HEIGHT + (int)(rand.nextDouble()*(height - 1 - GROUND_MAX_HEIGHT));
		int y = lastY;
		int nextY = y;
		boolean justChanged = false;
		int length = 0;
		int landHeight = height - 1;
				
		// place the ground
		for (int x=0; x<width; x++) {
			
			// need more ground
			if (length > GAP_LENGTH && y >= height) {
				nextY = landHeight;
				justChanged = true;
				length = 1;
			}
			// adjust ground level
			else if (x > minX && rand.nextDouble() < CHANGE_HILL_CHANGE && !justChanged) {
				nextY += (int)(GAP_OFFSET + GAP_RANGE*rand.nextDouble());
				nextY = Math.min(height - 2, nextY);
				nextY = Math.max(5, nextY);
				justChanged = true;
				length = 1;
			}
			// add a gap
			else if (x > minX && y < height &&  rand.nextDouble() < CHANGE_GAP && !justChanged && gapCount < Constraints.gaps) {
				landHeight = Math.min(height - 1, lastY);
				nextY = height;
				justChanged = true;
				length = 1;
				gapCount++;
			}
			else {
				length++;
				justChanged = false;
			}
			
			setGroundHeight(x, y, lastY, nextY);
			ground.add(y);
			
			lastY = y;			
			y = nextY;
		}
		
		// non colliding hills
		int x=0;		
		y = height;
		for (Integer h : ground) {
			if (y == height) {			
				if (x > 10 && rand.nextDouble() < CHANCE_HILL) {
					y  = (int)(HILL_HEIGHT + rand.nextDouble()*(h - HILL_HEIGHT));
					setBlock(x, y, this.HILL_TOP_LEFT);		
					
					for (int i=y + 1; i<h; i++) {
						setBlock(x, i, this.HILL_LEFT);		
					}

				}
			}
			else {
				// end if hitting a wall
				if (y >= h) {
					y = height;
				}
				else 
					if (rand.nextDouble() < CHANCE_END_HILL) {
					setBlock(x, y, this.HILL_TOP_RIGHT);		
					
					for (int i=y + 1; i<h; i++) {
						setBlock(x, i, this.HILL_RIGHT);		
					}
					
//					ground.set(x, y);
					y = height;
				}
				else {
					setBlock(x, y, this.HILL_TOP);		
					
					for (int i=y + 1; i<h; i++) {
						setBlock(x, i, this.HILL_FILL);		
					}
					
					if (rand.nextDouble() < CHANCE_HILL_ENEMY) {
						boolean winged = rand.nextDouble() < CHANCE_WINGED;
						int t = (int)(rand.nextDouble()*(SpriteTemplate.CHOMP_FLOWER + 1));
						
						// turtle constraint
						if (t==SpriteTemplate.GREEN_TURTLE || t==SpriteTemplate.RED_TURTLE) {				
							if (turtleCount < Constraints.turtels) {
								turtleCount++;
							}
							else {
								t = SpriteTemplate.GOOMPA;
							}
						}
												
						setSpriteTemplate(x, y - 1, new SpriteTemplate(t, winged));
					}
				}
			}
			
			x++;
		}
				
		// pipes
		lastY = 0;
		int lastlastY = 0;
		x=0;		
		int lastX = 0;
		for (Integer h : ground) {
			if (x > minX && rand.nextDouble() < CHANCE_PIPE) {
				if (h == lastY && lastlastY <= lastY && x > (lastX + 1)) {				
					height = PIPE_MIN_HEIGHT + (int)(Math.random()*PIPE_HEIGHT);
					placePipe(x - 1, h, height);
					lastX = x;
				}
			}
			
			lastlastY = lastY;
			lastY = h;
			x++;
		}
		
		// place enemies
		x=0;		
		for (Integer h : ground) {
			if (x > minX && rand.nextDouble() < CHANCE_ENEMY) {
				boolean winged = rand.nextDouble() < CHANCE_WINGED;
				int t = (int)(rand.nextDouble()*(SpriteTemplate.CHOMP_FLOWER + 1));

				// turtle constraint
				if (t==SpriteTemplate.GREEN_TURTLE || t==SpriteTemplate.RED_TURTLE) {				
					if (turtleCount < Constraints.turtels) {
						turtleCount++;
					}
					else {
						t = SpriteTemplate.GOOMPA;
					}
				}
				
				int tile = getBlock(x, h - 1);
				if (tile == 0) {
					setSpriteTemplate(x, h - 1, new SpriteTemplate(t, winged));
				}

			}
			
			x++;
		}
				
		// platforms
		x=0;		
		y = height;
		for (Integer h : ground) {
			int max = 0;
			
			// find the highest object
			for (max=0; max<h; max++) {
				int tile = getBlock(x, max);
				if (tile != 0) {
					break;
				}				
			}
			
			if (y == height) {		
				if (x > minX && rand.nextDouble() < CHANCE_PLATFORM) {
					y  = max - PLATFORM_HEIGHT; // (int)(-5*rand.nextDouble()*(h - 0));
					
					if (y >= 1  && h - max > 1) {
						placeBlock(x, y);
					}
					else {
						y = height;
					}
				}
			}
			else {
				// end if hitting a wall
				if (y >= (max + 1)) {
					y = height;
				}
				else if (rand.nextDouble() < CHANCE_END_PLATFORM) {
					placeBlock(x, y);
					y = height;
				}
				else {
					placeBlock(x, y);
				}
			}
			
			x++;
		}
				
		// coins
		x=0;		
		for (Integer h : ground) {
			if (x > 5 && rand.nextDouble() < CHANCE_COIN) {
				y = h - (int)(1 + Math.random()*COIN_HEIGHT);
				
				int tile = getBlock(x, y);
				if (tile == 0) {
					setBlock(x, y, this.COIN);		
				}
			}
			
			x++;
		}

		// place the exit
		this.xExit = width - 5;
	}
	
	public void placeBlock(int x, int y) {

		// choose block type
		if (rand.nextDouble() < CHANCE_BLOCK_POWER_UP) {
			setBlock(x, y, this.BLOCK_POWERUP);		
		}
		else if (rand.nextDouble() < CHANCE_BLOCK_COIN && coinBlockCount < Constraints.coinBlocks) {
			setBlock(x, y, this.BLOCK_COIN);					
			coinBlockCount++;
		}
		else {
			setBlock(x, y, this.BLOCK_EMPTY);		
		}		
		
		// place enemies
		if (rand.nextDouble() < CHANCE_BLOCK_ENEMY) {
			boolean winged = rand.nextDouble() < CHANCE_WINGED;
			int t = (int)(rand.nextDouble()*(SpriteTemplate.CHOMP_FLOWER + 1));
						
			// turtle constraint
			if (t==SpriteTemplate.GREEN_TURTLE || t==SpriteTemplate.RED_TURTLE) {				
				if (turtleCount < Constraints.turtels) {
					turtleCount++;
				}
				else {
					t = SpriteTemplate.GOOMPA;
				}
			}
			
			setSpriteTemplate(x, y - 1, new SpriteTemplate(t, winged));
		}
	}

	public void placePipe(int x, int y, int height) {
		for (int i=1; i<height; i++) {
			setBlock(x, y - i, this.TUBE_SIDE_LEFT);				
			setBlock(x + 1, y - i, this.TUBE_SIDE_RIGHT);							
		}
		
		setBlock(x, y - height, this.TUBE_TOP_LEFT);				
		setBlock(x + 1, y - height, this.TUBE_TOP_RIGHT);				
	}
	
	public void setGroundHeight(int x, int y, int lastY, int nextY) {
		for (int i=y + 1; i<height; i++) {
			setBlock(x, i, this.HILL_FILL);		
		}

		if (y < lastY) {			
			setBlock(x, y, this.LEFT_UP_GRASS_EDGE);					
			
			for (int i=y + 1; i<lastY; i++) {
				setBlock(x, i, this.LEFT_GRASS_EDGE);		
			}
			
			setBlock(x, lastY, this.RIGHT_POCKET_GRASS);					
		}
		else if (y < nextY) {			
			setBlock(x, y, this.RIGHT_UP_GRASS_EDGE);					
			
			for (int i=y + 1; i<nextY; i++) {
				setBlock(x, i, this.RIGHT_GRASS_EDGE);		
			}
			
			setBlock(x, nextY, this.LEFT_POCKET_GRASS);					
		}
		else {
			setBlock(x, y, this.HILL_TOP);		
		}		
		
		// place the exit
		if (x == (width - 5)) {
			this.yExit = y;
		}
	}	
}
