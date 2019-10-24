package dk.itu.mario.level;

import java.awt.Point;
import java.io.*;
import java.util.ArrayList;

import Auxiliar.SpriteTemplatePrimitivo;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.engine.sprites.SpriteTemplate;


public class Level implements LevelInterface
{

    protected static final byte BLOCK_EMPTY	= (byte) (0 + 1 * 16);
    protected static final byte BLOCK_POWERUP	= (byte) (4 + 2 + 1 * 16);
    protected static final byte BLOCK_COIN	= (byte) (4 + 1 + 1 * 16);
    protected static final byte GROUND		= (byte) (1 + 9 * 16);
    protected static final byte ROCK			= (byte) (9 + 0 * 16);
    protected static final byte COIN			= (byte) (2 + 2 * 16);


    protected static final byte LEFT_GRASS_EDGE = (byte) (0+9*16);
    protected static final byte RIGHT_GRASS_EDGE = (byte) (2+9*16);
    protected static final byte RIGHT_UP_GRASS_EDGE = (byte) (2+8*16);
    protected static final byte LEFT_UP_GRASS_EDGE = (byte) (0+8*16);
    protected static final byte LEFT_POCKET_GRASS = (byte) (3+9*16);
    protected static final byte RIGHT_POCKET_GRASS = (byte) (3+8*16);

    protected static final byte HILL_FILL = (byte) (5 + 9 * 16);
    protected static final byte HILL_LEFT = (byte) (4 + 9 * 16);
    protected static final byte HILL_RIGHT = (byte) (6 + 9 * 16);
    protected static final byte HILL_TOP = (byte) (5 + 8 * 16);
    protected static final byte HILL_TOP_LEFT = (byte) (4 + 8 * 16);
    protected static final byte HILL_TOP_RIGHT = (byte) (6 + 8 * 16);

    protected static final byte HILL_TOP_LEFT_IN = (byte) (4 + 11 * 16);
    protected static final byte HILL_TOP_RIGHT_IN = (byte) (6 + 11 * 16);

    protected static final byte TUBE_TOP_LEFT = (byte) (10 + 0 * 16);
    protected static final byte TUBE_TOP_RIGHT = (byte) (11 + 0 * 16);

    protected static final byte TUBE_SIDE_LEFT = (byte) (10 + 1 * 16);
    protected static final byte TUBE_SIDE_RIGHT = (byte) (11 + 1 * 16);

    //The level's width and height
    protected int width;
    protected int height;

    //This map of WIDTH * HEIGHT that contains the level's design
    private byte[][] map;

    //This is a map of WIDTH * HEIGHT that contains the placement and type enemies
    private SpriteTemplate[][] spriteTemplates;

    //These are the place of the end of the level
    protected int xExit;
    protected int yExit;

    //Increased from Reis and Lelis
    private static final int FILE_HEADER = 0x271c4178;
    public SpriteTemplatePrimitivo spritePrimitivo[]; // Vetor para guardar todas as informações dos sprites adicionados
    public int tam_spritePrimitivo = 0; // Guarda o tamanho do template
    public ArrayList<Point>listaInicioFimTelas = new ArrayList<Point>();
    
    public byte[][] data;		 //Estava sendo utilizada na outra arquitetura
    public byte[][] observation; //Estava sendo utilizada na outra arquitetura
    
    public Level(){

    }

    public Level(int width, int height)
    {
        this.width = width;
        this.height = height;

        xExit = 10;
        yExit = 10;
        map = new byte[width][height];
        spriteTemplates = new SpriteTemplate[width][height];
        
        //from Reis and Lelis
        data = new byte[width][height];        
        observation = new byte[width][height];
        
        //***************************** Inicializando com o tamanho da largura da tela
        spritePrimitivo = new SpriteTemplatePrimitivo[width];
        //System.out.println("Realizado com sucesso!");
    }

    public static void loadBehaviors(DataInputStream dis) throws IOException
    {
        dis.readFully(Level.TILE_BEHAVIORS);
    }

    public static void saveBehaviors(DataOutputStream dos) throws IOException
    {
        dos.write(Level.TILE_BEHAVIORS);
    }

    public static Level load(DataInputStream dis, Level level) throws IOException
    {
    	//Method from Reis and Lelis
        long header = dis.readLong();
        if (header != Level.FILE_HEADER) throw new IOException("Bad level header");
        int version = dis.read() & 0xff;

        int width = dis.readShort() & 0xffff;
        int height = dis.readShort() & 0xffff;      
        
        //Level level = new Level(width, height);
        /*
         * Modifiquei o código aqui para salvar outras informações sobre o LEVEl
         * */
        level.xExit = dis.readInt();
        level.yExit = dis.readInt();
        
        level.map = new byte[width][height];
        level.data = new byte[width][height];
        for (int i = 0; i < width; i++)
        {
            dis.readFully(level.map[i]);
            dis.readFully(level.data[i]);
        }
        
        level.tam_spritePrimitivo = dis.readInt(); //Carregar o tamanho do vetor de sprites
        
        //System.out.println("Tamanho carregado: " + level.tam_spritePrimitivo);
        
        int x, y, tipo, alado;
      
        for (int i = 0; i < level.tam_spritePrimitivo; i++)
        {
        	x = dis.readInt();
        	y = dis.readInt();
            tipo = dis.readInt();
            alado = dis.readInt();
            /*System.out.println("\tx: " + x + " y: " + y + 
            		" type: " + tipo + " alado: " + alado); */
            
            level.spritePrimitivo[i] = new SpriteTemplatePrimitivo(x, y, tipo, alado == 1 ? true : false);
        }
        
        level.setSpritePrimitivo();
        
        return level;
    }
    
    public void setSpritePrimitivo()
    {//Method from Reis and Lelis
    	for (int i = 0; i < tam_spritePrimitivo; i++)
        {
    		spriteTemplates[spritePrimitivo[i].getX()][spritePrimitivo[i].getY()] = 
    				new SpriteTemplate(spritePrimitivo[i].getType(), spritePrimitivo[i].getWinged() == 1 ? true : false);
        }
    }
    
    public void save(DataOutputStream dos) throws IOException
    {//method increased from Reis and Lelis
    	//System.out.println("ayayayayyyyyyyyy");
        dos.writeLong(Level.FILE_HEADER);
        dos.write((byte) 0);

        dos.writeShort((short) width);
        dos.writeShort((short) height);

        dos.writeInt(xExit);
        dos.writeInt(yExit);
        //System.out.println("comadre");
        int Willian; // Apenas para marcar o ponto onde estou editando
        
        for (int i = 0; i < width; i++)
        {
            dos.write(map[i]);
            dos.write(data[i]);
            
        }
        
        dos.writeInt(tam_spritePrimitivo);
        
        //System.out.println("Tamanho salvo: " + tam_spritePrimitivo);
 

        for (int i = 0; i < tam_spritePrimitivo; i++)
        {
        	dos.writeInt(spritePrimitivo[i].getX());
            dos.writeInt(spritePrimitivo[i].getY());
            dos.writeInt(spritePrimitivo[i].getType());
            dos.writeInt(spritePrimitivo[i].getWinged());
            
            //System.out.println("\tx: " + spritePrimitivo[i].getX() + " y: " + spritePrimitivo[i].getY() + 
            	//	" type: " + spritePrimitivo[i].getType() + " alado: " + spritePrimitivo[i].getWinged());
            
        }
        
    }
    /**
     *Clone the level data so that we can load it when Mario die
     */
    public Level clone() throws CloneNotSupportedException {

        Level clone=new Level(width, height);

        clone.map = new byte[width][height];
    	clone.spriteTemplates = new SpriteTemplate[width][height];
    	clone.xExit = xExit;
    	clone.yExit = yExit;

    	for (int i = 0; i < map.length; i++)
    		for (int j = 0; j < map[i].length; j++) {
    			clone.map[i][j]= map[i][j];
    			clone.spriteTemplates[i][j] = spriteTemplates[i][j];
    	}

        return clone;

      }

    public void tick(){    }

    public byte getBlockCapped(int x, int y)
    {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x >= width) x = width - 1;
        if (y >= height) y = height - 1;
        return map[x][y];
    }

    public byte getBlock(int x, int y)
    {
        if (x < 0) x = 0;
        if (y < 0) return 0;
        if (x >= width) x = width - 1;
        if (y >= height) y = height - 1;
        return map[x][y];
    }

    public void setBlock(int x, int y, byte b)
    {
        if (x < 0) return;
        if (y < 0) return;
        if (x >= width) return;
        if (y >= height) return;
        map[x][y] = b;
    }

    public boolean isBlocking(int x, int y, float xa, float ya)
    {
        byte block = getBlock(x, y);

        boolean blocking = ((TILE_BEHAVIORS[block & 0xff]) & BIT_BLOCK_ALL) > 0;
        blocking |= (ya > 0) && ((TILE_BEHAVIORS[block & 0xff]) & BIT_BLOCK_UPPER) > 0;
        blocking |= (ya < 0) && ((TILE_BEHAVIORS[block & 0xff]) & BIT_BLOCK_LOWER) > 0;

        return blocking;
    }

    public SpriteTemplate getSpriteTemplate(int x, int y)
    {
        if (x < 0) return null;
        if (y < 0) return null;
        if (x >= width) return null;
        if (y >= height) return null;
        return spriteTemplates[x][y];
    }

    public void setSpriteTemplate(int x, int y, SpriteTemplate spriteTemplate)
    {
        if (x < 0) return;
        if (y < 0) return;
        if (x >= width) return;
        if (y >= height) return;
        spriteTemplates[x][y] = spriteTemplate;
        
        //Increasing from Lelis and Reis
        spritePrimitivo[tam_spritePrimitivo] = new SpriteTemplatePrimitivo(x, y, spriteTemplate.getType(), spriteTemplate.getWinged());
        tam_spritePrimitivo++;       
        
    }
    
    public void setSpriteTemplateOriginal(int x, int y, SpriteTemplate spriteTemplate)
    {
        if (x < 0) return;
        if (y < 0) return;
        if (x >= width) return;
        if (y >= height) return;
        spriteTemplates[x][y] = spriteTemplate;    
        
    }

    public SpriteTemplate[][] getSpriteTemplate(){
    	return this.spriteTemplates;
    }

    public void resetSpriteTemplate(){
    	for (int i = 0; i < spriteTemplates.length; i++) {
			for (int j = 0; j < spriteTemplates[i].length; j++) {

				SpriteTemplate st = spriteTemplates[i][j];
				if(st != null)
					st.isDead = false;
			}
		}
    }


    public void print(byte[][] array){
    	for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				//System.out.print(array[i][j]);
			}
			//System.out.println();
		}
    }
	public byte[][] getMap() {
		return map;
	}
	public SpriteTemplate[][] getSpriteTemplates() {
		return spriteTemplates;
	}
	public int getxExit() {
		// TODO Auto-generated method stub
		return xExit;
	}
	public int getyExit() {
		// TODO Auto-generated method stub
		return yExit;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}


	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}


}
