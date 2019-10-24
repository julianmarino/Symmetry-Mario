package beauty;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.level.Level;

public class LoadBeautyLevel extends Level{
	
	private int type;
	
	public LoadBeautyLevel(int width, int height)
    {
		super(width, height);  
    }
	public LoadBeautyLevel(int width, int height, long seed, int difficulty, int type, String nameFile)
    {
		
        this(width, height);
        LoadLevel(seed, difficulty, type,nameFile);        
        
    }
	public void LoadLevel(long seed, int difficulty, int type, String nameFile)
	{//Method from Lelis and reis
		retornaTela(this, "Screens/Telas/"+nameFile);
		fixWalls();
	}
	
    protected void retornaTela(Level level, String nomeTela){    	
    	//Method from Reis and Lelis
    	FileInputStream fis;
		try {
			fis = new FileInputStream(nomeTela);
			DataInputStream dis = new DataInputStream(fis);			
			level = Level.load(dis,level);
			
			//System.out.println("\nCARREGADO COM SUCESSO\n\tRetornando Tela");
			
			
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		System.out.println("\nFALHOU AO CARREGAR\n\tRetornando");
		
		
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
	
}
