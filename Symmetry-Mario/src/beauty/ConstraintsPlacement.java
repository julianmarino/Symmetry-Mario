package beauty;

import graphBuilder.BlockNode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import dk.itu.mario.level.Level;

public class ConstraintsPlacement {

	Level level;
	public ConstraintsPlacement(Level level)
	{
		this.level=level;
		//System.out.println("Height "+level.getHeight()+" Weight "+level.getWidth()+ " tamoffloor "+ level.getMap()[11][11]);
	}
	
	public boolean ConstraintsMinSpaceCoins(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        int xElemMax=xElement+(widthElement-1);
	        int yElemMax=floor-(heigthElement);
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
	        int xNextMax=x+(widthElementNext-1);
	        int yNextMax=y-(heigthElementNext);
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        
	        if(y-heigthElementNext<=floor && y>= floor-heigthElement )
	        {
	        	if(Math.abs(xNextMax-xElement)<2 || Math.abs(x-xElemMax)<2)
	        	return false;
	        }
	        
		}
		return true;
	}
	
	public boolean ConstraintsMinSpace(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        double widthElement=(double)element.getWidth();
	        double heigthElement=(double)element.getHeigth();
	        double xElemMax=(double)xElement+(widthElement-1);
	        double yElemMax=(double)floor-(heigthElement);
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
	        int xNextMax=x+(widthElementNext-1);
	        int yNextMax=y-(heigthElementNext);
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        if(element.getTypeElem()==objElemP.getOddsJump())
	        {
	        	heigthElement=heigthElement+1;
	        }	
	        
	        if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement )
	        {
	        	if(Math.abs(yNextMax-floor)<3 || Math.abs(y-yElemMax)<3)
	        	return false;
	        }
	        if(y-heigthElementNext<=floor && y>= floor-heigthElement )
	        {
	        	if(Math.abs(xNextMax-xElement)<2 || Math.abs(x-xElemMax)<2)
	        	return false;
	        }
	        
		}
		return true;
	}
	
	public boolean ConstraintsMinSpaceFloat(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP, int floorTileHeight)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        int xElemMax=xElement+(widthElement-1);
	        int yElemMax=floor-(heigthElement);
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
	        int xNextMax=x+(widthElementNext-1);
	        int yNextMax=y-(heigthElementNext);
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        
	        if(x+(widthElementNext-1)>=xElement-1 && x<xElement+widthElement+1 )
	        {
	        	if(Math.abs(yNextMax-floor)<3 || Math.abs(y-yElemMax)<3)
	        	return false;
	        }
	        if(y-heigthElementNext<=floor && y>= floor-heigthElement )
	        {
	        	if(Math.abs(xNextMax-xElement)<2 || Math.abs(x-xElemMax)<2)
	        	return false;
	        }
	        if(y>=floorTileHeight-2)
	        {
	        	return false;
	        }
		}
		return true;
	}
	
	public boolean ConstraintsMinSpaceHillFloat(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP, int floorTileHeight)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        int xElemMax=xElement+(widthElement-1);
	        int yElemMax=floor-(heigthElement);
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
	        int xNextMax=x+(widthElementNext-1);
	        int yNextMax=y-(heigthElementNext);
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        
	        if(x+(widthElementNext-1)>=xElement-1 && x<xElement+widthElement+1 )
	        {
	        	if(Math.abs(yNextMax-floor)<3 || Math.abs(y-yElemMax)<3)
	        	return false;
	        }
	        if(y-heigthElementNext<=floor && y>= floor-heigthElement )
	        {
	        	if(Math.abs(xNextMax-xElement)<2 || Math.abs(x-xElemMax)<2)
	        	return false;
	        }
	        if(y>=floorTileHeight-2)
	        {
	        	return false;
	        }
		}
		return true;
	}
	
	public boolean ConstraintsMinSpaceHills(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        int xElemMax=xElement+(widthElement-1);
	        int yElemMax=floor-(heigthElement);
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
	        int xNextMax=x+(widthElementNext-1);
	        int yNextMax=y-(heigthElementNext);
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        if(element.getTypeElem()!=objElemP.getOddsHillStraight())
	        {
	        	if(element.getTypeElem()==objElemP.getOddsJump())
		        {
		        	heigthElement=heigthElement+1;
		        }	
	        	if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement )
	        	{
	        		if(Math.abs(yNextMax-floor)<3 || Math.abs(y-yElemMax)<3)
	        			return false;
	        	}
	        	if(y-heigthElementNext<=floor && y>= floor-heigthElement )
	        	{
	        		if(Math.abs(xNextMax-xElement)<2 || Math.abs(x-xElemMax)<2)
	        			return false;
	        	}
	        }
	        
			}
		return true;
	}
	
	public boolean ConstraintsPosibleGoalJump(int x, int y)
	{
		return true;
	}
	
	public boolean ConstraintsFloor(int x, int y)
	{
		
		
		x=x+10;
		//System.out.println("x "+x+" y"+y+" "+level.getMap()[x][y]);
		if(y<level.getHeight()-1)
		{
		if (level.getMap()[x][y+1] == (byte) (2+8*16)) {// RIGHT_UP_GRASS_EDGE
			return true;
			
		}
		else if (level.getMap()[x][y+1] == (byte) (0+8*16)) {// LEFT_UP_GRASS_EDGE
			return true;
			
		}
		else if (level.getMap()[x][y+1] == (byte) (5 + 8 * 16)) {// HILL_TOP
			return true;
			
		}
		else if (level.getMap()[x][y+1] == (byte) (4 + 8 * 16)) {//HILL_TOP_LEFT
			return true;
			
		}
	    else if (level.getMap()[x][y+1] == (byte) (6 + 8 * 16)) {// HILL_TOP_RIGHT
	    	return true;
			
		}
		else if (level.getMap()[x][y+1] == (byte) (4 + 11 * 16)) {// HILL_TOP_LEFT_IN
			return true;
			
		}
		else if (level.getMap()[x][y+1] == (byte) (6 + 11 * 16)) {// HILL_TOP_RIGHT_IN
			return true;
		}
		else if (level.getMap()[x][y+1] == (byte) (1 + 8 * 16)) {// HILL_TOP_W
			return true;
		}
		}
		return false;
	}
	
	public boolean ConstraintsFloorFloatings(ArrayList states, int x, int y, ArrayList finalListElements)
	{
		
		x=x+10;
		//System.out.println("x "+x+" y"+y+" "+level.getMap()[x][y]);
		
		//Inside this if we are validatig the distance from the floor
		if(y<level.getHeight()-1)
		{
		for(int j=y+1;j<y+5;j++)
		{
			if(j<level.getHeight())
			{
			if (level.getMap()[x][j] == (byte) (2+8*16)) {// RIGHT_UP_GRASS_EDGE
			return true;
			
			}
			else if (level.getMap()[x][j] == (byte) (0+8*16)) {// LEFT_UP_GRASS_EDGE
			return true;
			
			}
			else if (level.getMap()[x][j] == (byte) (5 + 8 * 16)) {// HILL_TOP
			return true;
			
			}
			else if (level.getMap()[x][j] == (byte) (4 + 8 * 16)) {//HILL_TOP_LEFT
			return true;
			
			}
			else if (level.getMap()[x][j] == (byte) (6 + 8 * 16)) {// HILL_TOP_RIGHT
	    	return true;
			
			}
			else if (level.getMap()[x][j] == (byte) (4 + 11 * 16)) {// HILL_TOP_LEFT_IN
			return true;
			
			}
			else if (level.getMap()[x][j] == (byte) (6 + 11 * 16)) {// HILL_TOP_RIGHT_IN
			return true;
			}
			else if (level.getMap()[x][j] == (byte) (1 + 8 * 16)) {// HILL_TOP_W
			return true;
			}
			}
		}
		}
		x=x-10;
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        double widthElement=(double)element.getWidth();
	        double heigthElement=(double)element.getHeigth();
	        
	        
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
			
	        for(int k=x;k<x+widthElementNext;k++)
	        {
	        	//for(int i=xElement-2;i<=xElement+widthElement+2;i++)
				//{	
					if(k>=xElement-1 && k<=xElement+widthElement && y<(floor-heigthElement) && y>((floor-heigthElement)-5))
					{
						
						return true;
					}
				//}
	        }
		}
		
		return false;
	}
	
	public boolean ConstraintsOverlaid(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        
	        if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement && y-heigthElementNext<=floor && y>= floor-heigthElement )
	        {
	        	return false;
	        }
	        
		}
		return true;
	}
	
	public boolean ConstraintsOverlaidHills(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP)
	{
		//System.out.println("Na funcaoo? ");
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        if(element.getTypeElem()!=objElemP.getOddsHillStraight())
	        {
	        if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement && y-heigthElementNext<=floor && y>= floor-heigthElement )
	        {
	        	return false;
	        }
	        }
	        
		}
		return true;
	}	
	public boolean ConstraintsWidthMaxTile(ArrayList states, int x, int y, ArrayList finalListElements,int width)
	{
		
		Elements elementNext=(Elements)finalListElements.get(states.size());
        int widthElementNext=elementNext.getWidth();
        if((x+widthElementNext)<=width)
        {
        	return true;
        }
		return false;
	}
	
	public boolean ConstraintsFloorRelative(ArrayList states, int x, int y, ArrayList finalListElements, int floorTileHeight, ElementsToPlace objElemP)
	{
		
		//System.out.println("x "+x+" y"+y+" "+level.getMap()[x][y]);
		if(y<level.getHeight()-1)
		{
			if (y == floorTileHeight-1) {
				
				for(int e=0;e<states.size();e++)
				{
					
					Elements element=(Elements)finalListElements.get(e);
					BlockNode state=(BlockNode)states.get(e);
					
					int xElement = (int)state.getX();
			        int floor= (int)state.getY();
			        double widthElement=(double)element.getWidth();
			        double heigthElement=(double)element.getHeigth();
			        
			        
			        
			        /*System.out.print(" idElement"+state.getIdElement());
			        System.out.print(" xElement"+xElement);
			        System.out.print(" floor"+floor);
			        System.out.print(" widthElement"+widthElement);
			        System.out.print(" heigthElement"+heigthElement);*/
			        
			        Elements elementNext=(Elements)finalListElements.get(states.size());
			        int widthElementNext=elementNext.getWidth();
					
			        if(element.getTypeElem()==objElemP.getOddsJump())
			        {
			        	if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement)
			        	{
			        		return false;
			        	}
			        }
				}
				return true;
			}		
		}
		return false;
	}
	
	public boolean ConstraintsFloorGapsRelative(int x, int y)
	{
		
		if (y == level.getHeight()-1) {
				return true;			
			}		
		return false;
	}
	
	public boolean ConstraintsFloorFloatingsHillsRelative(ArrayList states, int x, int y, ArrayList finalListElements, int floorTileHeight, ElementsToPlace objElemP)
	{
		
		//System.out.println("x "+x+" y"+y+" "+level.getMap()[x][y]);
		
		//Inside this if we are validatig the distance from the floor
		Elements elementNextB=(Elements)finalListElements.get(states.size());
        int heightElementNext=elementNextB.getHeigth();
        
		if(y<level.getHeight()-1)
		{
		for(int j=y+3;j<y+(4-(heightElementNext-1));j++)
		{
			if(j<level.getHeight())
			{
				
				if (j == floorTileHeight) {
					return true;
					
				}		
				
			}
		}
		}
		
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        double widthElement=(double)element.getWidth();
	        double heigthElement=(double)element.getHeigth();
	        
	        
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
			
	        if(element.getTypeElem()!=objElemP.getCoins())
	        {
	        for(int k=x;k<x+widthElementNext;k++)
	        {
	        	//for(int i=xElement-2;i<=xElement+widthElement+2;i++)
				//{	
					if(k>=xElement-1 && k<=xElement+widthElement && y<(floor-heigthElement) && y>((floor-heigthElement)-(4-heightElementNext+1)))
					{
						/*if(x>xElement || (x+widthElementNext)<(xElement+widthElement))
						{
						return true;
						}*/
						if(x>xElement )
						{
						return true;
						}
					}
				//}
	        }
	        }
		}
		
		return false;
	}
	
	public boolean ConstraintsFloorFloatingsRelative(ArrayList states, int x, int y, ArrayList finalListElements, int floorTileHeight, ElementsToPlace objElemP)
	{
		
		//System.out.println("x "+x+" y"+y+" "+level.getMap()[x][y]);
		
		//Inside this if we are validatig the distance from the floor
		if(y<level.getHeight()-1)
		{
		for(int j=y+3;j<y+5;j++)
		{
			if(j<level.getHeight())
			{
				
				if (j == floorTileHeight) {
					return true;
					
				}		
				
			}
		}
		}
		
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        double widthElement=(double)element.getWidth();
	        double heigthElement=(double)element.getHeigth();
	        
	        
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
			
	        if(element.getTypeElem()!=objElemP.getCoins())
	        {
	        for(int k=x;k<x+widthElementNext;k++)
	        {
	        	//for(int i=xElement-2;i<=xElement+widthElement+2;i++)
				//{	
					if(k>=xElement-1 && k<=xElement+widthElement && y<(floor-heigthElement) && y>((floor-heigthElement)-5))
					{
						
						return true;
					}
				//}
	        }
	        }
		}
		
		return false;
	}
	
	public boolean ConstraintsFloorEnemiesRelative(ArrayList states, int x, int y, ArrayList finalListElements, int floorTileHeight, ElementsToPlace objElemP)
	{
		
		//System.out.println("x "+x+" y"+y+" "+level.getMap()[x][y]);
		if(y<level.getHeight()-1)
		{
			if (y == floorTileHeight-1) {
				
				for(int e=0;e<states.size();e++)
				{
					
					Elements element=(Elements)finalListElements.get(e);
					BlockNode state=(BlockNode)states.get(e);
					
					int xElement = (int)state.getX();
			        int floor= (int)state.getY();
			        double widthElement=(double)element.getWidth();
			        double heigthElement=(double)element.getHeigth();
			        
			        
			        
			        /*System.out.print(" idElement"+state.getIdElement());
			        System.out.print(" xElement"+xElement);
			        System.out.print(" floor"+floor);
			        System.out.print(" widthElement"+widthElement);
			        System.out.print(" heigthElement"+heigthElement);*/
			        
			        Elements elementNext=(Elements)finalListElements.get(states.size());
			        int widthElementNext=elementNext.getWidth();
					
			        if(element.getTypeElem()==objElemP.getOddsJump())
			        {
			        	if(x+(widthElementNext-1)>=xElement && x<xElement+widthElement)
			        	{
			        		return false;
			        	}
			        }
				}
				return true;
			}
			
			for(int e=0;e<states.size();e++)
			{
				
				Elements element=(Elements)finalListElements.get(e);
				BlockNode state=(BlockNode)states.get(e);
				
				int xElement = (int)state.getX();
		        int floor= (int)state.getY();
		        int widthElement=element.getWidth();
		        int heigthElement=element.getHeigth();
		        
		        
		        
		        /*System.out.print(" idElement"+state.getIdElement());
		        System.out.print(" xElement"+xElement);
		        System.out.print(" floor"+floor);
		        System.out.print(" widthElement"+widthElement);
		        System.out.print(" heigthElement"+heigthElement);*/
		        
		        Elements elementNext=(Elements)finalListElements.get(states.size());
		        int widthElementNext=elementNext.getWidth();
				
		        if(element.getTypeElem()!=objElemP.getCoins())
		        {
		        for(int k=x;k<x+widthElementNext;k++)
		        {
		        	//for(int i=xElement-2;i<=xElement+widthElement+2;i++)
					//{	
						if(k>=xElement && k<=xElement+widthElement-1 && y==(floor-heigthElement)-1)
						{
							
							return true;
						}
					//}
		        }
		        }
			}			
			
		}
		
		return false;
	}	
	
	public boolean ConstraintsWidthMaxTileRelative(ArrayList states, int x, int y, ArrayList finalListElements,int width, int maxRight)
	{
		
		
        if(((x+width-1)<=maxRight))
        {
        	return true;
        }
		return false;
	}
	
	public boolean ConstraintsHeightMaxTileRelative(ArrayList states, int x, int y, ArrayList finalListElements,int heigth, int maxRight)
	{
		
		
        if((y-heigth+1)>=2)
        {
        	return true;
        }
		return false;
	}	
	
	public boolean ConstraintsMinWidth(ArrayList states, int x, int y, ArrayList finalListElements,ElementsToPlace objElemP)
	{
		//System.out.println("Na funcaoo? ");
		int counterGapsNear=0;
		for(int e=0;e<states.size();e++)
		{
			
			Elements element=(Elements)finalListElements.get(e);
			BlockNode state=(BlockNode)states.get(e);
			
			int xElement = (int)state.getX();
	        int floor= (int)state.getY();
	        int widthElement=element.getWidth();
	        int heigthElement=element.getHeigth();
	        
	        /*System.out.print(" idElement"+state.getIdElement());
	        System.out.print(" xElement"+xElement);
	        System.out.print(" floor"+floor);
	        System.out.print(" widthElement"+widthElement);
	        System.out.print(" heigthElement"+heigthElement);*/
	        
	        Elements elementNext=(Elements)finalListElements.get(states.size());
	        int widthElementNext=elementNext.getWidth();
	        int heigthElementNext=elementNext.getHeigth();
			
			/*for(int i=xElement-(widthElementNext-1);i<xElement+widthElement;i++)
			{
				for(int j = floor; j >= floor-heigthElement; j--)
				{
					if(x==i && y==j)
					{
						return false;
					}
				}
			}*/
	        if(element.getTypeElem()==objElemP.getOddsJump())
	        {
	        	if(y== (floor-heigthElement)-1 )
	        	{
	        		if(x+(widthElementNext-1)==xElement-1 	||  x==xElement+widthElement)
	        			counterGapsNear++;
	        	}
	        }
		}
		if(counterGapsNear>1)
		{
			return false;
		}
		
		return true;
	}
	//public boolean StateRepeated
	
	public boolean StateRepeated(int typeElem,int widthElem,int heigthElem,int i,int j,TreeSet currentState,Hashtable hTable)
	{
		String newSubStringCurrentState=Integer.toString(typeElem)+"|"+Integer.toString(widthElem)+"|"+Integer.toString(heigthElem)+"|"+Integer.toString(i)+"|"+Integer.toString(j);		
		TreeSet copycurrentState=(TreeSet)currentState.clone();
		copycurrentState.add(newSubStringCurrentState);
		boolean exists=hTable.containsKey(copycurrentState);
		return exists;
	}
	public Level getLevel()
	{
		return level;
	}
}
