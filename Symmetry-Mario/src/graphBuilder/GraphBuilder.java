/* ==========================================
 * JGraphT : a free Java graph-theory library 
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/* -----------------
 * HelloJGraphT.java
 * -----------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh
 * Contributor(s):   -
 *
 * $Id$
 *
 * Changes
 * -------
 * 27-Jul-2003 : Initial revision (BN);
 *
 */
package graphBuilder;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import beauty.ConstraintsPlacement;
import beauty.Elements;
import beauty.ElementsToPlace;
import beauty.Utilities;
import dk.itu.mario.level.Level;


/**
 * A simple introduction to using JGraphT.
 *
 * @author Barak Naveh
 * @since Jul 27, 2003
 */
public class GraphBuilder
{

	private long counterIDs;
	private int counterBranches;
	private ArrayList<BlockNode> Beststates;
	private ArrayList<BlockNode> Worststates;
	private Branch BeststatesObj;
	private Branch WorststatesObj;
	private ArrayList bestBranches;
	private double xCenterMassGeneral=8;
	private double yCenterMassGeneral=6.5;
	private double xCenterMassCoins;
	private double yCenterMassCoins;
	private double symmetryV;
	public double bestSymmetryV=9999999;
	private double worstSymmetryV=0;
	private double bestAverageX=0;
	private double globalCenterXMass=8.0;
	private double partialSymmetry;
	private double [] partialHeight=new double [4];
	private double [] partialWidth=new double [4];
	private double [] partialXSummatory=new double[4];
	private double [] partialYSummatory=new double[4];
	private double [] partialASummatory=new double[4];
	private boolean firstBranchPercorred=false;
	private ArrayList<Double> bestXs;
	private ArrayList<Double> bestYs;
	
	private ArrayList<Double> XsQuadrant;
	private ArrayList<Double> YsQuadrant;
	
	double [] distributionsWidth;
	double [] distributionsHeight;
	//private ArrayList <double[]> gul;
	//private ArrayList <double[]> gur;
	//private ArrayList <double[]> gll;
	//private ArrayList <double[]> glr;
	private double [] gulAG;
	private double [] gurAG;
	private double [] gllAG;
	private double [] glrAG;
	
	private double [] gulAC;
	private double [] gurAC;
	private double [] gllAC;
	private double [] glrAC;
	
	
	private int localMaxObjLeft;
	private int localMaxObjRight;
	private int localMaxRight;
	private int localMaxLeft;
	
	

	
	private TreeSet localcurrentState;
	//Iterator<Integer> finalListIterator;
	private double wParamether;
	
    public GraphBuilder(long counterIDs)
    {
    	bestBranches = new ArrayList<Branch>();
    	this.counterIDs=counterIDs;
    	//finalListIterator = finalList.iterator();
    } // ensure non-instantiability.

    



    /**
     * Creates a toy directed graph based on URL objects that represents link
     * structure.
     *
     * @return a graph based on URL objects.
     */
    /*private static DirectedGraph<URL, DefaultEdge> createHrefGraph()
    {
        DirectedGraph<URL, DefaultEdge> g =
            new DefaultDirectedGraph<URL, DefaultEdge>(DefaultEdge.class);

        try {
            URL amazon = new URL("http://www.amazon.com");
            URL yahoo = new URL("http://www.yahoo.com");
            URL ebay = new URL("http://www.ebay.com");

            // add the vertices
            g.addVertex(amazon);
            g.addVertex(yahoo);
            g.addVertex(ebay);

            // add edges to create linking structure
            g.addEdge(yahoo, amazon);
            g.addEdge(yahoo, ebay);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return g;
    }*/

    /**
     * Craete a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    /*private static UndirectedGraph<String, DefaultEdge> createStringGraph()
    {
        UndirectedGraph<String, DefaultEdge> g =
            new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }*/
 
    
    /**
     * Craete a toy graph based on BlockNode objects.
     *
     * @return a graph based on BlockNode objects.
     */
   /* private static UndirectedGraph<BlockNode, DefaultEdge> createBlockNodeGraph()
    {
        UndirectedGraph<BlockNode, DefaultEdge> g =
            new SimpleGraph<BlockNode, DefaultEdge>(DefaultEdge.class);

        BlockNode objBlockNode1=new BlockNode(1,3);
        BlockNode objBlockNode2=new BlockNode(2,3);
        BlockNode objBlockNode3=new BlockNode(3,3);
        BlockNode objBlockNode4=new BlockNode(4,3);
        BlockNode objBlockNode5=new BlockNode(5,3);
        BlockNode objBlockNode6=new BlockNode(6,3);
        BlockNode objBlockNode7=new BlockNode(7,3);
        BlockNode objBlockNode8=new BlockNode(8,3);
        BlockNode objBlockNode9=new BlockNode(9,3);
        BlockNode objBlockNode10=new BlockNode(10,3);
        BlockNode objBlockNode16=new BlockNode(16,3);

        // add the vertices
        g.addVertex(objBlockNode1);
        g.addVertex(objBlockNode2);
        g.addVertex(objBlockNode3);
        g.addVertex(objBlockNode4);
        g.addVertex(objBlockNode5);
        g.addVertex(objBlockNode6);
        g.addVertex(objBlockNode7);
        g.addVertex(objBlockNode8);
        g.addVertex(objBlockNode9);
        g.addVertex(objBlockNode10);
        g.addVertex(objBlockNode16);

        // add edges to create a circuit
        g.addEdge(objBlockNode1, objBlockNode2);
        g.addEdge(objBlockNode1, objBlockNode3);
        g.addEdge(objBlockNode1, objBlockNode4);
        g.addEdge(objBlockNode2, objBlockNode5);
        g.addEdge(objBlockNode2, objBlockNode6);
        g.addEdge(objBlockNode3, objBlockNode8);
        g.addEdge(objBlockNode4, objBlockNode9);
        g.addEdge(objBlockNode4, objBlockNode10);
        g.addEdge(objBlockNode5, objBlockNode7);
        g.addEdge(objBlockNode5, objBlockNode16);

        GraphIterator<BlockNode, DefaultEdge> iterator = 
                new DepthFirstIterator<BlockNode, DefaultEdge>(g);
        while (iterator.hasNext()) {
            System.out.println( iterator.next().getX() );
           
        }
        return g;
    }*/
    
    public ArrayList  relativeTransPositionDepthSearch(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, TreeSet currentState, Hashtable hTable)
    {   
    	
    	if(countElements==countElementsFinal)
    	{ 
    		countElements--;
    		counterIDs=counterIDs+1;
    		Elements objElem= (Elements)finalList.get(0);
        	int idElem=objElem.getIdElem();
        	int typeElem=objElem.getTypeElem();
        	int widthElem=objElem.getWidth();
        	int heigthElem=objElem.getHeigth();
    		BlockNode objBlockNode2=new BlockNode(0,floorTileHeight-1,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		currentState=UpdateStringState(typeElem,widthElem,heigthElem,0,floorTileHeight-1,currentState);
    		hTable=UpdateTranspositionTable(hTable,currentState);
    		maxLeft=maxLeft+objElem.getWidth()-1;
    		maxObjRight=maxObjRight+objElem.getWidth()-1;
    	}
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	int widthElem=objElem.getWidth();
    	int heigthElem=objElem.getHeigth();
  
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 for(int j=0;j<height;j++)
    	 {  
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes())
    		 {    			
    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 else if(typeElem==objElemP.getBlockElement())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		     	
    		 
    		 if(objConstraints.StateRepeated(typeElem,widthElem,heigthElem,i,j,currentState,hTable)==true)
    		 {
    			 continue;
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		localcurrentState=UpdateStringState(typeElem,widthElem,heigthElem,i,j,currentState);
    		
    		if(counterIDs<10000)
    		{
    			    		
    			hTable=UpdateTranspositionTable(hTable,localcurrentState);
    		}
    		
    		if(countElements>0)
    		{
    			localMaxObjLeft=maxObjLeft;
    			localMaxObjRight=maxObjRight;
    			localMaxRight=maxRight;
    			localMaxLeft=maxLeft;
    			if(i<maxObjLeft)
    			{
    				int tempMaxObjLeft=maxObjLeft;
    				localMaxObjLeft=i;
    				localMaxRight=maxRight+(localMaxObjLeft-tempMaxObjLeft);
    			}
    			else if(i+objElem.getWidth()-1>maxObjRight)
    			{
    				int tempMaxObjRight=maxObjRight;
    				localMaxObjRight=i+objElem.getWidth()-1;
    				localMaxLeft=maxLeft+(localMaxObjRight-tempMaxObjRight);
    				//maxLeft=maxLeft+objElem.getWidth()-1;
    			}
    			
    			
    			relativeTransPositionDepthSearch(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,localMaxLeft,localMaxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,localcurrentState,hTable);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			validateBestBranch(states,objElemP,height,floorTileHeight,localMaxObjLeft);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	return Beststates;
    } 
    
    public boolean  validationSymmetryFuture(   int countElements, int countElementsFinal,ArrayList statesCopy, ArrayList finalList, ElementsToPlace objElemP,  Random random, double partialSymmetry,int floorTileHeight,int height)
    { 
    	
    	long counterIDsCopy=counterIDs;
    	countElements=countElements-1;
    	//Collections.sort(bestXs,Collections.reverseOrder());
    	//Collections.sort(bestYs,Collections.reverseOrder());
    	
        //System.out.println("bestSymmetry "+bestSymmetryV);
        int sizebestXs=bestXs.size();
        int sizebestYs=bestYs.size();
        
        int indexCounterX=0;
        int indexCounterY=0;
        
        double totalAreaWidth=0;
        double totalAreaHeight=0;
        
        for(int i=countElementsFinal-countElements-1;i<countElementsFinal;i++)
        {
        	Elements objElem= (Elements)finalList.get(i);
        	totalAreaWidth=totalAreaWidth+objElem.getWidth();
        	totalAreaHeight=totalAreaHeight+(objElem.getHeigth()+1);
        }
        
    	Utilities objUtilities=new Utilities();
    	distributionsHeight=objUtilities.DistributionsQuadrants(totalAreaHeight, partialHeight[0], partialHeight[1], partialHeight[2], partialHeight[3]);
    	distributionsWidth=objUtilities.DistributionsQuadrants(totalAreaWidth, partialWidth[0], partialWidth[1], partialWidth[2], partialWidth[3]);

        
        for(int i=0;i<4;i++)
        {   
        	if(distributionsWidth[i]!=0 || distributionsHeight[i]!=0)
        	{
        		double firstX=objUtilities.bestXYSummatory(partialXSummatory);
        	
        		double firstY=objUtilities.bestXYSummatory(partialYSummatory);
        	
        		/*if(indexCounterX<=sizebestXs-1)
        		{
        			firstX=bestXs.get(indexCounterX);
        		}
        		//indexCounterX=indexCounterX+1;
        	       	       	
        		if(indexCounterY<=sizebestYs-1)
        		{
        			firstY=bestYs.get(indexCounterY);
        		}*/
        	       	
        		//indexCounterY=indexCounterY+1;
        	
        		counterIDsCopy=counterIDsCopy+1;
        		double xMedium;
        		double yMedium;
        		double xPosition;
        		double yPosition;
        	
        		if(i==0 || i==2)
        		{
        			xMedium=globalCenterXMass-firstX;
        			xPosition=(xMedium-(distributionsWidth[i]/2));
        		}
        		else
        		{
        			xMedium=globalCenterXMass+firstX;
        			xPosition=(xMedium-(distributionsWidth[i]/2));
        			
        		}
        		
        		if(i==0 || i==1)
        		{
        			yMedium=yCenterMassGeneral-firstY;
        			yPosition=(yMedium+(distributionsHeight[i]/2));
        		}
        		else
        		{
        			yMedium=yCenterMassGeneral+firstY;
        			yPosition=(yMedium+(distributionsHeight[i]/2));
        			
        		}
        		
        		/*if(xPosition<0)
        		{
        			xPosition=xPosition+1;
        		}
        		if(yPosition<0)
        		{
        			yPosition=yPosition+1;
        		}*/
        	        	
        		BlockNode objBlockNode2=new BlockNode(xPosition,yPosition,counterIDsCopy,-1,1);
        		statesCopy.add(objBlockNode2);
        	}
    
        	}
        partialSymmetry=partialSymmetry(statesCopy,objElemP,height,floorTileHeight,localMaxObjLeft,true);
        
        
        
        if(partialSymmetry>bestSymmetryV)
        {
        	
        	return true;
        }
        else
        {
        	return false;
        }
    } 

    public boolean  validationPruningMAll(   int countElements, int countElementsFinal,ArrayList states, ArrayList finalList, ElementsToPlace objElemP,  Random random, double partialSymmetry, int floorTileHeight, int ruleThirds)
    { 
    	double tamBottomFromCenter=floorTileHeight-yCenterMassGeneral;
    	double tamTopFromCenter=yCenterMassGeneral-ruleThirds;
    	double maxTamFromCenter=0;
    	if(tamBottomFromCenter>tamTopFromCenter)
    	{
    		maxTamFromCenter=tamBottomFromCenter;
    	}
    	else
    	{
    		maxTamFromCenter=tamTopFromCenter;
    	}
    	
    	countElements=countElements-1;
    	//Collections.sort(bestXs,Collections.reverseOrder());
    	//Collections.sort(bestYs,Collections.reverseOrder());
    	
        //System.out.println("bestSymmetry "+bestSymmetryV);
        //int sizebestXs=bestXs.size();
        //int sizebestYs=bestYs.size();
        
        //int indexCounterX=0;
        //int indexCounterY=0;
        
        Arrays.sort(partialXSummatory);
        Arrays.sort(partialYSummatory);
        
        double firstX;
    	double secondX;
    	double thirdX;
    	
    	double firstY;
    	double secondY;
    	double thirdY;
        
        for(int i=countElementsFinal-countElements-1;i<countElementsFinal;i++)
        {   
        	firstX=0;
        	secondX=0;
        	thirdX=0;
        	firstY=0;
        	secondY=0;
        	thirdY=0;
        	
        	Elements objElem= (Elements)finalList.get(i);
        	double areaElement=(objElem.getHeigth()+1)*objElem.getWidth();
        	//int counterElements=0;
        	
        	if(partialXSummatory[3]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		firstX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		firstX=partialXSummatory[3];
        	}
        	
        	if(partialXSummatory[2]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		secondX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		secondX=partialXSummatory[2];
        	}
        	if(partialXSummatory[1]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		thirdX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		thirdX=partialXSummatory[1];
        	}
        	
        	if(partialYSummatory[3]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		firstY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		firstY=partialYSummatory[3];
        	}
        	
        	if(partialYSummatory[2]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		secondY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		secondY=partialYSummatory[2];
        	}
        	if(partialYSummatory[1]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		thirdY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		thirdY=partialYSummatory[1];
        	}
        	
        	//partialSymmetry=partialSymmetry-((3*areaElement)+(3*globalCenterXMass)+(3*yCenterMassGeneral));
        	partialSymmetry=partialSymmetry-((3*areaElement)+firstX+secondX+thirdX+firstY+secondY+thirdY);
        }
        //System.out.println("partialSymmetry "+partialSymmetry);
        if(partialSymmetry>bestSymmetryV)
        {
        	
        	return true;
        }
        else
        {
        	return false;
        }
    }
    
    public boolean  validationPruningMVertical(   int countElements, int countElementsFinal,ArrayList states, ArrayList finalList, ElementsToPlace objElemP,  Random random, double partialSymmetry, int floorTileHeight, int ruleThirds)
    { 
    	double tamBottomFromCenter=floorTileHeight-yCenterMassGeneral;
    	double tamTopFromCenter=yCenterMassGeneral-ruleThirds;
    	double maxTamFromCenter=0;
    	if(tamBottomFromCenter>tamTopFromCenter)
    	{
    		maxTamFromCenter=tamBottomFromCenter;
    	}
    	else
    	{
    		maxTamFromCenter=tamTopFromCenter;
    	}
    	
    	countElements=countElements-1;
    	//Collections.sort(bestXs,Collections.reverseOrder());
    	//Collections.sort(bestYs,Collections.reverseOrder());
    	
        //System.out.println("bestSymmetry "+bestSymmetryV);
        //int sizebestXs=bestXs.size();
        //int sizebestYs=bestYs.size();
        
        //int indexCounterX=0;
        //int indexCounterY=0;
        
        Arrays.sort(partialXSummatory);
        Arrays.sort(partialYSummatory);
        
        double firstX;
    	double secondX;
    	double thirdX;
    	
    	double firstY;
    	double secondY;
    	double thirdY;
        
        for(int i=countElementsFinal-countElements-1;i<countElementsFinal;i++)
        {   
        	firstX=0;
        	secondX=0;
        	thirdX=0;
        	firstY=0;
        	secondY=0;
        	thirdY=0;
        	
        	Elements objElem= (Elements)finalList.get(i);
        	double areaElement=(objElem.getHeigth()+1)*objElem.getWidth();
        	//int counterElements=0;
        	
        	if(partialXSummatory[3]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		firstX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		firstX=partialXSummatory[3];
        	}
        	
        	if(partialXSummatory[2]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		secondX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		secondX=partialXSummatory[2];
        	}
        	if(partialXSummatory[1]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		thirdX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		thirdX=partialXSummatory[1];
        	}
        	
        	if(partialYSummatory[3]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		firstY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		firstY=partialYSummatory[3];
        	}
        	
        	if(partialYSummatory[2]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		secondY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		secondY=partialYSummatory[2];
        	}
        	if(partialYSummatory[1]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		thirdY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		thirdY=partialYSummatory[1];
        	}
        	
        	//partialSymmetry=partialSymmetry-((3*areaElement)+(3*globalCenterXMass)+(3*yCenterMassGeneral));
        	partialSymmetry=partialSymmetry-((areaElement)+firstX+firstY);
        }
        //System.out.println("partialSymmetry "+partialSymmetry);
        if(partialSymmetry>bestSymmetryV)
        {
        	
        	return true;
        }
        else
        {
        	return false;
        }
    }
    
    
    
    
    public boolean  validationPruningAllHeuristic(   int countElements, int countElementsFinal,ArrayList states, ArrayList finalList, ElementsToPlace objElemP,  Random random, double partialSymmetry, int floorTileHeight, int ruleThirds)
    { 
    	double tamBottomFromCenter=floorTileHeight-yCenterMassGeneral;
    	double tamTopFromCenter=yCenterMassGeneral-ruleThirds;
    	double maxTamFromCenter=0;
    	if(tamBottomFromCenter>tamTopFromCenter)
    	{
    		maxTamFromCenter=tamBottomFromCenter;
    	}
    	else
    	{
    		maxTamFromCenter=tamTopFromCenter;
    	}
    	
    	countElements=countElements-1;
    	//Collections.sort(bestXs,Collections.reverseOrder());
    	//Collections.sort(bestYs,Collections.reverseOrder());
    	
        //System.out.println("bestSymmetry "+bestSymmetryV);
        //int sizebestXs=bestXs.size();
        //int sizebestYs=bestYs.size();
        
        //int indexCounterX=0;
        //int indexCounterY=0;
        
        Arrays.sort(partialXSummatory);
        Arrays.sort(partialYSummatory);
        
        double firstX;
    	double secondX;
    	double thirdX;
    	
    	double firstY;
    	double secondY;
    	double thirdY;
        
        for(int i=countElementsFinal-countElements-1;i<countElementsFinal;i++)
        {   
        	firstX=0;
        	secondX=0;
        	thirdX=0;
        	firstY=0;
        	secondY=0;
        	thirdY=0;
        	
        	Elements objElem= (Elements)finalList.get(i);
        	double areaElement=(objElem.getHeigth()+1)*objElem.getWidth();
        	//int counterElements=0;
        	
        	if(partialXSummatory[3]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		firstX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		firstX=partialXSummatory[3];
        	}
        	
        	if(partialXSummatory[2]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		secondX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		secondX=partialXSummatory[2];
        	}
        	if(partialXSummatory[1]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		thirdX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		thirdX=partialXSummatory[1];
        	}
        	
        	if(partialYSummatory[3]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		firstY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		firstY=partialYSummatory[3];
        	}
        	
        	if(partialYSummatory[2]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		secondY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		secondY=partialYSummatory[2];
        	}
        	if(partialYSummatory[1]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		thirdY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		thirdY=partialYSummatory[1];
        	}
        	
        	partialSymmetry=partialSymmetry-((3*areaElement)+(4*globalCenterXMass)+(4*yCenterMassGeneral));
        	partialSymmetry=partialSymmetry;
        	//partialSymmetry=partialSymmetry-((3*areaElement)+firstX+secondX+thirdX+firstY+secondY+thirdY);
        }
        //System.out.println("partialSymmetry "+partialSymmetry);
        partialSymmetry=partialSymmetry*wParamether;
        if(partialSymmetry>bestSymmetryV)
        {
        	
        	return true;
        }
        else
        {
        	return false;
        }
    }
    public boolean validationPruningVerticalHeuristic(   int countElements, int countElementsFinal,ArrayList states, ArrayList finalList, ElementsToPlace objElemP,  Random random, double partialSymmetry, int floorTileHeight, int ruleThirds)
    { 
    	double tamBottomFromCenter=floorTileHeight-yCenterMassGeneral;
    	double tamTopFromCenter=yCenterMassGeneral-ruleThirds;
    	double maxTamFromCenter=0;
    	if(tamBottomFromCenter>tamTopFromCenter)
    	{
    		maxTamFromCenter=tamBottomFromCenter;
    	}
    	else
    	{
    		maxTamFromCenter=tamTopFromCenter;
    	}
    	
    	countElements=countElements-1;
    	//Collections.sort(bestXs,Collections.reverseOrder());
    	//Collections.sort(bestYs,Collections.reverseOrder());
    	
        //System.out.println("bestSymmetry "+bestSymmetryV);
        //int sizebestXs=bestXs.size();
        //int sizebestYs=bestYs.size();
        
        //int indexCounterX=0;
        //int indexCounterY=0;
        
        Arrays.sort(partialXSummatory);
        Arrays.sort(partialYSummatory);
        
        double firstX;
    	double secondX;
    	double thirdX;
    	
    	double firstY;
    	double secondY;
    	double thirdY;
        
        for(int i=countElementsFinal-countElements-1;i<countElementsFinal;i++)
        {   
        	firstX=0;
        	secondX=0;
        	thirdX=0;
        	firstY=0;
        	secondY=0;
        	thirdY=0;
        	
        	Elements objElem= (Elements)finalList.get(i);
        	double areaElement=(objElem.getHeigth()+1)*objElem.getWidth();
        	//int counterElements=0;
        	
        	if(partialXSummatory[3]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		firstX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		firstX=partialXSummatory[3];
        	}
        	
        	if(partialXSummatory[2]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		secondX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		secondX=partialXSummatory[2];
        	}
        	if(partialXSummatory[1]>(globalCenterXMass)-(objElem.getWidth()/2))
        	{
        		thirdX=(globalCenterXMass)-(objElem.getWidth()/2);     		
        	}
        	else
        	{
        		thirdX=partialXSummatory[1];
        	}
        	
        	if(partialYSummatory[3]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		firstY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		firstY=partialYSummatory[3];
        	}
        	
        	if(partialYSummatory[2]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		secondY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		secondY=partialYSummatory[2];
        	}
        	if(partialYSummatory[1]>(maxTamFromCenter)-((objElem.getHeigth()+1)/2))
        	{
        		thirdY=(maxTamFromCenter)-((objElem.getHeigth()+1)/2);     		
        	}
        	else
        	{
        		thirdY=partialYSummatory[1];
        	}
        	
        	partialSymmetry=partialSymmetry-((areaElement)+(2*globalCenterXMass)+(2*yCenterMassGeneral));
        	partialSymmetry=partialSymmetry;
        	//partialSymmetry=partialSymmetry-((3*areaElement)+firstX+secondX+thirdX+firstY+secondY+thirdY);
        }
        //System.out.println("partialSymmetry "+partialSymmetry);
        partialSymmetry=partialSymmetry*wParamether;
        if(partialSymmetry>bestSymmetryV)
        {
        	
        	return true;
        }
        else
        {
        	return false;
        }
    }
 
	public ArrayList  DepthSearchPruningAlt(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch,int typeSymmetry)
    {    
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	int quadrant1X=0;
    	int quadrant2X=0;
    	
    	int quadrant1Y=0;
    	int quadrant2Y=0;
    	if(countElements!=countElementsFinal-1)
    	{
    		
    		for(int i=0;i<XsQuadrant.size();i++)
    		{
    			
    			if(XsQuadrant.get(i)<globalCenterXMass)
    			{
    				quadrant1X++;
    			}
    			else
    			{
    				quadrant2X++;
    			}
    		}
    		
    		for(int i=0;i<bestYs.size();i++)
    		{
    			if(YsQuadrant.get(i)<yCenterMassGeneral)
    			{
    				quadrant1Y++;
    			}
    			else
    			{
    				quadrant2Y++;
    			}
    		}
    		
    	}
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
       	 int indexN=i;
       	 for(int j=(height/3);j<height;j++)
       	 {       
       		 int indeyN=j;
       		 
       		 if(quadrant1X>quadrant2X)
       		 {
       			indexN= (maxRight-i);
       		 }
       		 
       		 if(quadrant1Y>quadrant2Y)
       		 {
       			 indeyN=(height-j-1)+(height/3);
       		 }
       		 if(objElem.getIdElem()==0)
       		 {   			     			
       			 if(indexN>globalCenterXMass)
       			 {
       				 
       				 continue;
       			 }
       		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
    		partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
    		boolean validationSymmetryFuture=false;
    		
    		ArrayList<BlockNode> statesCopy= new ArrayList<BlockNode>();
    		statesCopy=(ArrayList<BlockNode>)states.clone();
    		
    		if(firstBranchPercorred==true)
    		{
    			if(validationSymmetryFuture(countElements, countElementsFinal, statesCopy,finalList, objElemP, random,partialSymmetry,floorTileHeight,height)==true)
    			{
    				//System.out.println("cambiaso2");
    				validationSymmetryFuture=true;
    			}
    		} 
    		    		    		    		
    		if(countElements>0 && validationSymmetryFuture==false)
    		{    			
    			DepthSearchPruningAlt(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,typeSymmetry);
    		}
    		else{
    			if(countElements==0)
    			{
    			firstBranchPercorred=true;
    			//System.out.println("aca se debe calcular la formula");
    			//System.out.println("chimol");
    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
    			//System.out.println("chimol ");
    			}
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}
    	return Beststates;
    }
    
	//3.1) B&B+heuristic + region ordering + object ordering!
	public ArrayList  DepthSearchCenterFramePruningAll(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch, double centerXGlobal,int typeSymmetry)
    {    
		globalCenterXMass=centerXGlobal;
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem();
    	int widthElemm=objElem.getWidth();
    	
    	int quadrant1X=0;
    	int quadrant2X=0;
    	
    	int quadrant1Y=0;
    	int quadrant2Y=0;
    	
    	int minor=0;
    	double minorValue=10000000;
    	
    	if(countElements!=countElementsFinal-1)
    	{
    		
    		
    		for(int i=0;i<partialASummatory.length;i++)
    		{
    			
    			if(partialASummatory[i]<minorValue)
    			{
    				
    				minorValue=partialASummatory[i];
    				minor=i;
    			}
    		}
    		
    		/*
    		for(int i=0;i<XsQuadrant.size();i++)
    		{
    			
    			if(XsQuadrant.get(i)<globalCenterXMass)
    			{
    				quadrant1X++;
    			}
    			else
    			{
    				quadrant2X++;
    			}
    		}*/
    		/*for(int i=0;i<bestYs.size();i++)
    		{
    			if(YsQuadrant.get(i)<yCenterMassGeneral)
    			{
    				quadrant1Y++;
    			}
    			else
    			{
    				quadrant2Y++;
    			}
    		}*/
    		
    	}
    	
    	int ruleThirds=(height/3);
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 int indexN=i;
    	 for(int j=ruleThirds;j<height;j++)
    	 {       
    		 int indeyN=j;
    		 
    		 if(minor==0)
    		 {
       		 	if(objElem.getIdElem()==0)
       		 	{   			     			
       		 		if(indexN>globalCenterXMass)
       		 		{
       				 
       		 			continue;
       		 		}
       		 	}
    		 }
    		 
    		 if(minor==1)
    		 {
    			indexN= (maxRight-i+1); 
    			
       		 	if(objElem.getIdElem()==0)
       		 	{   			     			
       		 		if((indexN+widthElemm)<globalCenterXMass)
       		 		{
       				 
       		 			continue;
       		 		}
       		 	}
    		 }
    		 else if(minor==2)
    		 {
    			 indeyN=(height-j-1)+ruleThirds; 
    			 
        		 if(objElem.getIdElem()==0)
           		 {   			     			
           		 	if(indexN>globalCenterXMass)
           		 	{
           				 
           		 		continue;
           		 	}
           		 }
    		 }
    		 else if(minor==3)
    		 {
    			 indexN= (maxRight-i+1);
    			 indeyN=(height-j-1)+ruleThirds;
    			 
        		 if(objElem.getIdElem()==0)
           		 {   			     			
        		 	if((indexN+widthElemm)<globalCenterXMass)
           		 	{
           				 
           		 		continue;
           		 	}
           		 }
    		 }
    		 
    		 
    		 /*if(quadrant1X>quadrant2X)
    		 {
    			indexN= (maxRight-i+1);
    		 }
    		 
    		 if(quadrant1Y>quadrant2Y)
    		 {
    			 indeyN=(height-j-1)+ruleThirds;
    		 }*/

    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
    		//partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
    		partialSymmetry=partialSymmetryNewFormula(states,objElemP,height,floorTileHeight,localMaxObjLeft,false,typeSymmetry);
    		boolean validationPruningM=false;
    		if(firstBranchPercorred==true)
    		{
    			if(validationPruningMAll(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true && typeSymmetry==2)
    			{
    				//System.out.println("cambiaso");
    				validationPruningM=true;
    			}
    		}
    		if(countElements>0 && validationPruningM==false)
    		{    		    			
    			DepthSearchCenterFramePruningAll(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,centerXGlobal,typeSymmetry);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			if(countElements==0)
    			{
    			firstBranchPercorred=true;
    			//System.out.println("chunter");
    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
    			}
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}
    	/*System.out.println("puttyconexao "+bestSymmetryV);
    	Iterator<BlockNode> itSymmetry1 = Beststates.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			int widthElement=element.getWidth();
			int heigthElement=element.getHeigth()+1;
			//System.out.println("xInitial "+xInitial+" yInitial "+yInitial+" widthElement"+widthElement+" heigthElement "+heigthElement);
		}
		if(bestSymmetryV==3)
		{
			//System.out.println("Summeutru ");
		}*/
    	return Beststates;
    }
	
	//3.2) B&B+heuristic + object ordering---
	public ArrayList  DepthSearchCenterFramePruningOrderObjects(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch, double centerXGlobal,int typeSymmetry)
    {    
		globalCenterXMass=centerXGlobal;
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	int quadrant1X=0;
    	int quadrant2X=0;
    	
    	int quadrant1Y=0;
    	int quadrant2Y=0;
    	
    	int minor=0;
    	double minorValue=10000000;
    	
    	if(countElements!=countElementsFinal-1)
    	{
    		
    		
    		/*for(int i=0;i<partialASummatory.length;i++)
    		{
    			
    			if(partialASummatory[i]<minorValue)
    			{
    				
    				minorValue=partialASummatory[i];
    				minor=i;
    			}
    		}*/
    		
    		/*
    		for(int i=0;i<XsQuadrant.size();i++)
    		{
    			
    			if(XsQuadrant.get(i)<globalCenterXMass)
    			{
    				quadrant1X++;
    			}
    			else
    			{
    				quadrant2X++;
    			}
    		}*/
    		/*for(int i=0;i<bestYs.size();i++)
    		{
    			if(YsQuadrant.get(i)<yCenterMassGeneral)
    			{
    				quadrant1Y++;
    			}
    			else
    			{
    				quadrant2Y++;
    			}
    		}*/
    		
    	}
    	
    	int ruleThirds=(height/3);
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 int indexN=i;
    	 for(int j=ruleThirds;j<height;j++)
    	 {       
    		 int indeyN=j;
    		 
    		 /*if(minor==1)
    		 {
    			indexN= (maxRight-i+1); 
    		 }
    		 else if(minor==2)
    		 {
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }
    		 else if(minor==3)
    		 {
    			 indexN= (maxRight-i+1);
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }*/
    		 
    		 /*if(quadrant1X>quadrant2X)
    		 {
    			indexN= (maxRight-i+1);
    		 }
    		 
    		 if(quadrant1Y>quadrant2Y)
    		 {
    			 indeyN=(height-j-1)+ruleThirds;
    		 }*/
    		 if(objElem.getIdElem()==0)
    		 {   			     			
    			 if(indexN>globalCenterXMass)
    			 {
    				 
    				 continue;
    			 }
    		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
    		//partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
    		partialSymmetry=partialSymmetryNewFormula(states,objElemP,height,floorTileHeight,localMaxObjLeft,false,typeSymmetry);
    		boolean validationPruningM=false;
    		if(firstBranchPercorred==true)
    		{
    			if(validationPruningMAll(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true && typeSymmetry==2)
    			{
    				//System.out.println("cambiaso");
    				validationPruningM=true;
    			}
    		}
    		if(countElements>0 && validationPruningM==false)
    		{    		    			
    			DepthSearchCenterFramePruningOrderObjects(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,centerXGlobal,typeSymmetry);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			if(countElements==0)
    			{
    			firstBranchPercorred=true;
    			//System.out.println("chunter");
    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
    			}
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}
    	/*System.out.println("puttyconexao "+bestSymmetryV);
    	Iterator<BlockNode> itSymmetry1 = Beststates.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			int widthElement=element.getWidth();
			int heigthElement=element.getHeigth()+1;
			//System.out.println("xInitial "+xInitial+" yInitial "+yInitial+" widthElement"+widthElement+" heigthElement "+heigthElement);
		}
		if(bestSymmetryV==3)
		{
			//System.out.println("Summeutru ");
		}*/
    	return Beststates;
    }
	
	//3.3) Brute-force search
	public ArrayList  DepthSearchCenterFrameNoPruningNoRegionsNoObjects(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch, double centerXGlobal,int typeSymmetry)
    {    
		globalCenterXMass=centerXGlobal;
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	int quadrant1X=0;
    	int quadrant2X=0;
    	
    	int quadrant1Y=0;
    	int quadrant2Y=0;
    	
    	int minor=0;
    	double minorValue=10000000;
    	
    	if(countElements!=countElementsFinal-1)
    	{
    		
    		
    		/*for(int i=0;i<partialASummatory.length;i++)
    		{
    			
    			if(partialASummatory[i]<minorValue)
    			{
    				
    				minorValue=partialASummatory[i];
    				minor=i;
    			}
    		}*/
    		
    		/*
    		for(int i=0;i<XsQuadrant.size();i++)
    		{
    			
    			if(XsQuadrant.get(i)<globalCenterXMass)
    			{
    				quadrant1X++;
    			}
    			else
    			{
    				quadrant2X++;
    			}
    		}*/
    		/*for(int i=0;i<bestYs.size();i++)
    		{
    			if(YsQuadrant.get(i)<yCenterMassGeneral)
    			{
    				quadrant1Y++;
    			}
    			else
    			{
    				quadrant2Y++;
    			}
    		}*/
    		
    	}
    	
    	//int ruleThirds=(height/3);
    	int ruleThirds=0;
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 int indexN=i;
    	 for(int j=ruleThirds;j<height;j++)
    	 {       
    		 int indeyN=j;
    		 
    		 /*if(minor==1)
    		 {
    			indexN= (maxRight-i+1); 
    		 }
    		 else if(minor==2)
    		 {
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }
    		 else if(minor==3)
    		 {
    			 indexN= (maxRight-i+1);
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }*/
    		 
    		 /*if(quadrant1X>quadrant2X)
    		 {
    			indexN= (maxRight-i+1);
    		 }
    		 
    		 if(quadrant1Y>quadrant2Y)
    		 {
    			 indeyN=(height-j-1)+ruleThirds;
    		 }*/
//    		 if(objElem.getIdElem()==0)
//    		 {   			     			
//    			 if(indexN>globalCenterXMass)
//    			 {
//    				 
//    				 continue;
//    			 }
//    		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
    		//partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
    		//partialSymmetry=partialSymmetryNewFormula(states,objElemP,height,floorTileHeight,localMaxObjLeft,false,typeSymmetry);
    		boolean validationPruningM=false;
    		/*if(firstBranchPercorred==true)
    		{
    			if(validationPruningMAll(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true && typeSymmetry==2)
    			{
    				//System.out.println("cambiaso");
    				validationPruningM=true;
    			}
    		}*/
    		if(countElements>0 && validationPruningM==false)
    		{    		    			
    			DepthSearchCenterFrameNoPruningNoRegionsNoObjects(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,centerXGlobal,typeSymmetry);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			if(countElements==0)
    			{
    			firstBranchPercorred=true;
    			//System.out.println("chunter");
    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
    			}
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	
    	//putting enemies
    	/*if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}*/
    	/*System.out.println("puttyconexao "+bestSymmetryV);
    	Iterator<BlockNode> itSymmetry1 = Beststates.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			int widthElement=element.getWidth();
			int heigthElement=element.getHeigth()+1;
			//System.out.println("xInitial "+xInitial+" yInitial "+yInitial+" widthElement"+widthElement+" heigthElement "+heigthElement);
		}
		if(bestSymmetryV==3)
		{
			//System.out.println("Summeutru ");
		}*/
    	return Beststates;
    }
	
	//3.4) B&B+heuristic + oldHeuristic ---
	public ArrayList  DepthSearchCenterFramePruning(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch, double centerXGlobal,int typeSymmetry)
    {    
		globalCenterXMass=centerXGlobal;
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	int quadrant1X=0;
    	int quadrant2X=0;
    	
    	int quadrant1Y=0;
    	int quadrant2Y=0;
    	
    	int minor=0;
    	double minorValue=10000000;
    	
    	if(countElements!=countElementsFinal-1)
    	{
    		
    		
    		/*for(int i=0;i<partialASummatory.length;i++)
    		{
    			
    			if(partialASummatory[i]<minorValue)
    			{
    				
    				minorValue=partialASummatory[i];
    				minor=i;
    			}
    		}*/
    		
    		/*
    		for(int i=0;i<XsQuadrant.size();i++)
    		{
    			
    			if(XsQuadrant.get(i)<globalCenterXMass)
    			{
    				quadrant1X++;
    			}
    			else
    			{
    				quadrant2X++;
    			}
    		}*/
    		/*for(int i=0;i<bestYs.size();i++)
    		{
    			if(YsQuadrant.get(i)<yCenterMassGeneral)
    			{
    				quadrant1Y++;
    			}
    			else
    			{
    				quadrant2Y++;
    			}
    		}*/
    		
    	}
    	
    	int ruleThirds=(height/3);
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 int indexN=i;
    	 for(int j=ruleThirds;j<height;j++)
    	 {       
    		 int indeyN=j;
    		 
    		/* if(minor==1)
    		 {
    			indexN= (maxRight-i+1); 
    		 }
    		 else if(minor==2)
    		 {
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }
    		 else if(minor==3)
    		 {
    			 indexN= (maxRight-i+1);
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }*/
    		 
    		 /*if(quadrant1X>quadrant2X)
    		 {
    			indexN= (maxRight-i+1);
    		 }
    		 
    		 if(quadrant1Y>quadrant2Y)
    		 {
    			 indeyN=(height-j-1)+ruleThirds;
    		 }*/
    		 if(objElem.getIdElem()==0)
    		 {   			     			
    			 if(indexN>globalCenterXMass)
    			 {
    				 
    				 continue;
    			 }
    		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
    		//partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
    		partialSymmetry=partialSymmetryNewFormula(states,objElemP,height,floorTileHeight,localMaxObjLeft,false,typeSymmetry);
    		boolean validationPruningM=false;
    		if(firstBranchPercorred==true)
    		{
    			if(typeSymmetry==2)
    			{
    				if(validationPruningMAll(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true)
    				{
    					//System.out.println("cambiaso");
    					validationPruningM=true;
    				}
    			}
    			else if(typeSymmetry==1)
    			{
    				if(validationPruningMVertical(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true)
    				{
    					//System.out.println("cambiaso");
    					validationPruningM=true;
    				}
    			}
    		}
    		if(countElements>0 && validationPruningM==false)
    		{    		    			
    			DepthSearchCenterFramePruning(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,centerXGlobal,typeSymmetry);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			if(countElements==0)
    			{
    			firstBranchPercorred=true;
    			//System.out.println("chunter");
    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
    			}
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	
    	//putting enemies
    	/*if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}*/
    	/*System.out.println("puttyconexao "+bestSymmetryV);
    	Iterator<BlockNode> itSymmetry1 = Beststates.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			int widthElement=element.getWidth();
			int heigthElement=element.getHeigth()+1;
			//System.out.println("xInitial "+xInitial+" yInitial "+yInitial+" widthElement"+widthElement+" heigthElement "+heigthElement);
		}
		if(bestSymmetryV==3)
		{
			//System.out.println("Summeutru ");
		}*/
    	return Beststates;
    }  
	
	//3.7) B&B+oldold heuristic + oldoldHeuristic ---
	public ArrayList  depthSearchBBHeuristic(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch, double centerXGlobal,int typeSymmetry)
    {    
		globalCenterXMass=centerXGlobal;
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	int quadrant1X=0;
    	int quadrant2X=0;
    	
    	int quadrant1Y=0;
    	int quadrant2Y=0;
    	
    	int minor=0;
    	double minorValue=10000000;
    	
    	if(countElements!=countElementsFinal-1)
    	{
    		
    		
    		/*for(int i=0;i<partialASummatory.length;i++)
    		{
    			
    			if(partialASummatory[i]<minorValue)
    			{
    				
    				minorValue=partialASummatory[i];
    				minor=i;
    			}
    		}*/
    		
    		/*
    		for(int i=0;i<XsQuadrant.size();i++)
    		{
    			
    			if(XsQuadrant.get(i)<globalCenterXMass)
    			{
    				quadrant1X++;
    			}
    			else
    			{
    				quadrant2X++;
    			}
    		}*/
    		/*for(int i=0;i<bestYs.size();i++)
    		{
    			if(YsQuadrant.get(i)<yCenterMassGeneral)
    			{
    				quadrant1Y++;
    			}
    			else
    			{
    				quadrant2Y++;
    			}
    		}*/
    		
    	}
    	
    	//int ruleThirds=(height/3);
    	int ruleThirds=0;
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 int indexN=i;
    	 for(int j=ruleThirds;j<height;j++)
    	 {       
    		 int indeyN=j;
    		 
    		/* if(minor==1)
    		 {
    			indexN= (maxRight-i+1); 
    		 }
    		 else if(minor==2)
    		 {
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }
    		 else if(minor==3)
    		 {
    			 indexN= (maxRight-i+1);
    			 indeyN=(height-j-1)+ruleThirds; 
    		 }*/
    		 
    		 /*if(quadrant1X>quadrant2X)
    		 {
    			indexN= (maxRight-i+1);
    		 }
    		 
    		 if(quadrant1Y>quadrant2Y)
    		 {
    			 indeyN=(height-j-1)+ruleThirds;
    		 }*/
//    		 if(objElem.getIdElem()==0)
//    		 {   			     			
//    			 if(indexN>globalCenterXMass)
//    			 {
//    				 
//    				 continue;
//    			 }
//    		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
    		//partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
    		partialSymmetry=partialSymmetryNewFormula(states,objElemP,height,floorTileHeight,localMaxObjLeft,false,typeSymmetry);
    		boolean validationPruningM=false;
    		if(firstBranchPercorred==true)
    		{
    			if(typeSymmetry==2)
    			{
    				if(validationPruningAllHeuristic(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true)
    				{
    					//System.out.println("cambiaso");
    					validationPruningM=true;
    				}
    			}
    			else if(typeSymmetry==1)
    			{
    				if(validationPruningVerticalHeuristic(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true)
    				{
    					//System.out.println("cambiaso");
    					validationPruningM=true;
    				}
    			}
    		}
    		if(countElements>0 && validationPruningM==false)
    		{    		    			
    			depthSearchBBHeuristic(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,centerXGlobal,typeSymmetry);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			if(countElements==0)
    			{
    			firstBranchPercorred=true;
    			//System.out.println("chunter");
    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
    			}
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	
    	//putting enemies
    	/*if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}*/
    	/*System.out.println("puttyconexao "+bestSymmetryV);
    	Iterator<BlockNode> itSymmetry1 = Beststates.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			int widthElement=element.getWidth();
			int heigthElement=element.getHeigth()+1;
			//System.out.println("xInitial "+xInitial+" yInitial "+yInitial+" widthElement"+widthElement+" heigthElement "+heigthElement);
		}
		if(bestSymmetryV==3)
		{
			//System.out.println("Summeutru ");
		}*/
    	return Beststates;
    }   
	
	
	public ArrayList  DepthSearchCenterFrame(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch,double centerXGlobal, int typeSymmetry)
    {    
    	
	    
			globalCenterXMass=centerXGlobal;
	    	countElements--;
	    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
	    	int idElem=objElem.getIdElem();
	    	int typeElem=objElem.getTypeElem(); 
	    	
	    	int quadrant1X=0;
	    	int quadrant2X=0;
	    	
	    	int quadrant1Y=0;
	    	int quadrant2Y=0;
	    	if(countElements!=countElementsFinal-1)
	    	{
	    		
	    		for(int i=0;i<XsQuadrant.size();i++)
	    		{
	    			
	    			if(XsQuadrant.get(i)<globalCenterXMass)
	    			{
	    				quadrant1X++;
	    			}
	    			else
	    			{
	    				quadrant2X++;
	    			}
	    		}
	    		
	    		for(int i=0;i<bestYs.size();i++)
	    		{
	    			if(YsQuadrant.get(i)<yCenterMassGeneral)
	    			{
	    				quadrant1Y++;
	    			}
	    			else
	    			{
	    				quadrant2Y++;
	    			}
	    		}
	    		
	    	}
	    	
	    	int ruleThirds=(height/3);
	    	
	    	for(int i=maxLeft;i<=maxRight;i++)
	    	{
	    	 int indexN=i;
	    	 for(int j=ruleThirds;j<height;j++)
	    	 {       
	    		 int indeyN=j;
	    		 
	    		 if(quadrant1X>quadrant2X)
	    		 {
	    			indexN= (maxRight-i+1);
	    		 }
	    		 
	    		 if(quadrant1Y>quadrant2Y)
	    		 {
	    			 indeyN=(height-j-1)+ruleThirds;
	    		 }
	    		 if(objElem.getIdElem()==0)
	    		 {   			     			
	    			 if(indexN>globalCenterXMass)
	    			 {
	    				 
	    				 continue;
	    			 }
	    		 }
	    		 
	    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
	    		 {    			
	    			if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
	     		 	{
	     			 	
	    				//System.out.println("falseadoo");
	     			 	continue;
	     		 	}
	    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, indexN, indeyN, finalList,objElemP)==false)
	      			{
	      				continue;
	      			}
	    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
	    			{ 
	    				
	    				continue;
	    			}
	    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
	    			{ 
	    				
	    				continue;
	    			}
	    			else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
	    			{ 
	    				
	    				continue;
	    			}
	    			else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
	     		 	{
	     			 	
	    				//System.out.println("falseadoo");
	     			 	continue;
	     		 	}
	    		 }
	    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
	    		 {
	    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
	        		 	{
	        			 	
	       				//System.out.println("falseadoo");
	        			 	continue;
	        		 	}
	     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
	      			{ 
	      				
	      				continue;
	      			}
	     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
	     			{ 
	     				
	     				continue;
	     			}
	     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
	      			{ 
	      				
	      				continue;
	      			}
	     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false)
	       		 	{
	       			 	
	      				//System.out.println("falseadoo");
	       			 	continue;
	       		 	}
	     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
	     			 {
	     				 continue;
	     			 }*/   			 
	    		 }
	    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
	    		 {
	    			 if(objConstraints.ConstraintsOverlaid(states, indexN, indeyN, finalList,objElemP)==false)
	       		 	{
	       			 	
	      				//System.out.println("falseadoo");
	       			 	continue;
	       		 	}
	    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
	     			{ 
	     				
	     				continue;
	     			}
	    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
	    			{ 
	    				
	    				continue;
	    			}
	    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
	     			{ 
	     				
	     				continue;
	     			}
	    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, indexN, indeyN, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
	      		 	{
	      			 	
	     				//System.out.println("falseadoo");
	      			 	continue;
	      		 	}
	    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, indexN, indeyN, finalList,objElemP)==false && typeElem==objElemP.getCoins())
	        		 	{
	        			 	
	       				//System.out.println("falseadoo");
	        			 	continue;
	        		 	}
	    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
	    			 {
	    				 continue;
	    			 }*/
	    		 }
	    		 else if(typeElem==objElemP.getOddsJump())
	    		 {
	    			
	    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
	     			{ 
	     				
	     				continue;
	     			}
	    			 else if(objConstraints.ConstraintsFloorGapsRelative(indexN, indeyN)==false)
	     			{ 
	     				
	     				continue;
	     			}
	    			 else if(objConstraints.ConstraintsMinSpace(states, indexN, indeyN, finalList,objElemP)==false)
	      		 	{
	      			 	
	     				//System.out.println("falseadoo");
	      			 	continue;
	      		 	}
	    			
	    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
	    			 {
	    				 continue;
	    			 }*/
	    		 }
	    		 else if(typeElem==objElemP.getOddsHillStraight())
	    		 {
	    			 if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList, objElemP)==false)
	      		 	{      			 	
	     				//System.out.println("falseadoo");
	      			 	continue;
	      		 	}
	    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, indexN, indeyN, finalList, objElem.getWidth(),maxRight)==false)
	      			{ 
	      				
	      				continue;
	      			}
	    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, indexN, indeyN, finalList, objElem.getHeigth(),maxRight)==false)
	     			{ 
	     				
	     				continue;
	     			}
	    			 else if(objConstraints.ConstraintsFloorRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
	     			{ 
	     				
	     				continue;
	     			}
	    			 else if(objConstraints.ConstraintsMinSpaceHills(states, indexN, indeyN, finalList,objElemP)==false)
	      		 	{
	      			 	
	     				//System.out.println("falseadoo");
	      			 	continue;
	      		 	}
	    		 }
	    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
	    		 {
	     			if(objConstraints.ConstraintsOverlaidHills(states, indexN, indeyN, finalList,objElemP)==false)
	     		 	{
	     			 	
	    				//System.out.println("falseadoo");
	     			 	continue;
	     		 	}
	    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, indexN, indeyN, finalList,floorTileHeight,objElemP)==false)
	    			{ 
	    				
	    				continue;
	    			}
	    		 }
	    		 
	    		counterIDs=counterIDs+1;
	    		BlockNode objBlockNode2=new BlockNode(indexN,indeyN,counterIDs,typeElem,idElem);
	    		states.add(objBlockNode2);
	    		
	    		//partialSymmetry=partialSymmetry(states,objElemP,height,floorTileHeight,localMaxObjLeft,false);
	    		partialSymmetry=partialSymmetryNewFormula(states,objElemP,height,floorTileHeight,localMaxObjLeft,false,typeSymmetry);
	    		boolean validationPruningM=false;
	    		if(firstBranchPercorred==true)
	    		{
	    			if(validationPruningMAll(countElements, countElementsFinal, states,finalList, objElemP, random,partialSymmetry,floorTileHeight,ruleThirds)==true)
	    			{
	    				//System.out.println("cambiaso");
	    				validationPruningM=true;
	    			}
	    		}
	    		if(countElements>0 && validationPruningM==false)
	    		{    		    			
	    	    	DepthSearchCenterFramePruning(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,maxLeft,maxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1,centerXGlobal,typeSymmetry);
	    		}
	    		else{
	    			//System.out.println("aca se debe calcular la formula");
	    			if(countElements==0)
	    			{
	    			firstBranchPercorred=true;
	    			//System.out.println("chunter");
	    			validateBestBranchDepthSearchCenterFrame(states,objElemP,height,floorTileHeight,localMaxObjLeft,typeSymmetry);
	    			}
	    		}
	    		//System.out.println("Aqui deberia eliminar del array");
	    		states.remove(states.size() - 1);
	    		 
	    	 }
	    	}
	    	
	    	//putting enemies
	    	if(globalControlSearch==0)
	    	{
	    	for(int i=0;i<numEnemies;i++)
	    	{
	    		countElements--;
	        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
	        	int idElemEnem=objElemEnem.getIdElem();
	        	int typeElemEnem=objElemEnem.getTypeElem(); 
	        	
	        	counterIDs=counterIDs+1;
	    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
	    		Beststates.add(objBlockNode2);
	    	}
	    	}
	    	/*System.out.println("puttyconexao "+bestSymmetryV);
	    	Iterator<BlockNode> itSymmetry1 = Beststates.iterator();
			while(itSymmetry1.hasNext()){
				BlockNode elemento = itSymmetry1.next();
				Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

				double xInitial = elemento.getX();
				double yInitial= elemento.getY();

				int widthElement=element.getWidth();
				int heigthElement=element.getHeigth()+1;
				//System.out.println("xInitial "+xInitial+" yInitial "+yInitial+" widthElement"+widthElement+" heigthElement "+heigthElement);
			}
			if(bestSymmetryV==3)
			{
				//System.out.println("Summeutru ");
			}*/
	    	return Beststates;
    }

	public ArrayList  relativePositionDepthSearch(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight, int numEnemies, Random random, int globalControlSearch)
    {    
    	if(countElements==countElementsFinal)
    	{
    		countElements--;
    		counterIDs=counterIDs+1;
    		Elements objElem= (Elements)finalList.get(0);
        	int idElem=objElem.getIdElem();
        	int typeElem=objElem.getTypeElem();
        	if(typeElem==objElemP.getOddsJump())
        	{
        		BlockNode objBlockNode2=new BlockNode(0,height-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}
        	else
        	{
        		BlockNode objBlockNode2=new BlockNode(0,floorTileHeight-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}    		
    		maxLeft=maxLeft+objElem.getWidth()-1;
    		maxObjRight=maxObjRight+objElem.getWidth()-1;
    	}
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 for(int j=(height/3);j<height;j++)
    	 {     		 
    		 if(objElem.getIdElem()==1)
    		 {
    			 
    			 double widthZeroA=(double)(((Elements)finalList.get(0)).getWidth());
    			 double widthZero= widthZeroA/2;
    			
    			 if(widthZero!=(int)widthZero)
    			 {
    				 widthZero=widthZero+0.5; 
    			 }
    			
    			 if(i>widthZero-1)
    			 {
    				 
    				 continue;
    			 }
    		 }
    		 
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, i, j, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, i, j, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, i, j, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, i, j, finalList,objElemP)==false && typeElem==objElemP.getCoins())
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(i, j)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		
			localMaxObjLeft=maxObjLeft;
			localMaxObjRight=maxObjRight;
			localMaxRight=maxRight;
			localMaxLeft=maxLeft;
			if(i<maxObjLeft)
			{
				int tempMaxObjLeft=maxObjLeft;
				localMaxObjLeft=i;
				localMaxRight=maxRight+(localMaxObjLeft-tempMaxObjLeft);
			}
			else if(i+objElem.getWidth()-1>maxObjRight)
			{
				int tempMaxObjRight=maxObjRight;
				localMaxObjRight=i+objElem.getWidth()-1;
				localMaxLeft=maxLeft+(localMaxObjRight-tempMaxObjRight);
				//maxLeft=maxLeft+objElem.getWidth()-1;
			}
    		if(countElements>0)
    		{    			
    			relativePositionDepthSearch(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,localMaxLeft,localMaxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,numEnemies,random,globalControlSearch+1);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			validateBestBranch(states,objElemP,height,floorTileHeight,localMaxObjLeft);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		Beststates.add(objBlockNode2);
    	}
    	}
    	return Beststates;
    }    
    
	public ArrayList  relativePositionDepthSearchTopK(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int maxLeft, int maxRight,int floorTileHeight, int maxObjLeft, int maxObjRight,int maxScreens, int numEnemies,Random random, int globalControlSearch)
    {     
    	
    	if(countElements==countElementsFinal)
    	{
    		countElements--;
    		counterIDs=counterIDs+1;
    		Elements objElem= (Elements)finalList.get(0);
        	int idElem=objElem.getIdElem();
        	int typeElem=objElem.getTypeElem();
    		
        	if(typeElem==objElemP.getOddsJump())
        	{
        		BlockNode objBlockNode2=new BlockNode(0,height-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}
        	else
        	{
        		BlockNode objBlockNode2=new BlockNode(0,floorTileHeight-1,counterIDs,typeElem,idElem);
        		states.add(objBlockNode2);
        	}  
    		
    		maxLeft=maxLeft+objElem.getWidth()-1;
    		maxObjRight=maxObjRight+objElem.getWidth()-1;
    	}
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem(); 
    	
    	
    	
    	for(int i=maxLeft;i<=maxRight;i++)
    	{
    	 for(int j=(height/3);j<height;j++)
    	 {  
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes() || typeElem==objElemP.getTubesFlower())
    		 {    			
    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objElem.getWidth()==1 && objConstraints.ConstraintsMinWidth(states, i, j, finalList,objElemP)==false)
      			{
      				continue;
      			}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    		 }
    		 else if( typeElem==objElemP.getOddsHillStraightFloat())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
        		 	{
        			 	
       				//System.out.println("falseadoo");
        			 	continue;
        		 	}
     			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
     			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
     			 else if(objConstraints.ConstraintsFloorFloatingsHillsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
      			{ 
      				
      				continue;
      			}
     			 else if(objConstraints.ConstraintsMinSpaceHillFloat(states, i, j, finalList,objElemP,floorTileHeight)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
     			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
     			 {
     				 continue;
     			 }*/   			 
    		 }
    		 else if(typeElem==objElemP.getBlockElement() || typeElem==objElemP.getCoins()|| typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood() )
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
    			{ 
    				
    				continue;
    			}
    			 else if(objConstraints.ConstraintsFloorFloatingsRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceFloat(states, i, j, finalList,objElemP,floorTileHeight)==false && (typeElem==objElemP.getBlockElement() || typeElem==objElemP.getBlockBlue() || typeElem==objElemP.getBlockWood() || typeElem==objElemP.getSmallTube() || typeElem==objElemP.getWood()))
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsMinSpaceCoins(states, i, j, finalList,objElemP)==false && typeElem==objElemP.getCoins())
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 else if(typeElem==objElemP.getOddsJump())
    		 {
    			if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorGapsRelative(i, j)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpace(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 } 
    		 else if(typeElem==objElemP.getOddsHillStraight())
    		 {
    			 if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList, objElemP)==false)
      		 	{      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTileRelative(states, i, j, finalList, objElem.getWidth(),maxRight)==false)
      			{ 
      				
      				continue;
      			}
    			 else if(objConstraints.ConstraintsHeightMaxTileRelative(states, i, j, finalList, objElem.getHeigth(),maxRight)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsMinSpaceHills(states, i, j, finalList,objElemP)==false)
      		 	{
      			 	
     				//System.out.println("falseadoo");
      			 	continue;
      		 	}
    		 }
    		 else if(typeElem==objElemP.getEnemyRedKoopa() || typeElem==objElemP.getEnemyGreenKoopa() || typeElem==objElemP.getEnemyGoomba() || typeElem==objElemP.getEnemySpiky() || typeElem==objElemP.getEnemyFlower() || typeElem==objElemP.getEnemyArmoredTurtle() || typeElem==objElemP.getEnemyJumpFlower() || typeElem==objElemP.getEnemyCannonBall() || typeElem==objElemP.getEnemyChompFlower())
    		 {
     			if(objConstraints.ConstraintsOverlaidHills(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsFloorEnemiesRelative(states, i, j, finalList,floorTileHeight,objElemP)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    
			localMaxObjLeft=maxObjLeft;
			localMaxObjRight=maxObjRight;
			localMaxRight=maxRight;
			localMaxLeft=maxLeft;
			if(i<maxObjLeft)
			{
				int tempMaxObjLeft=maxObjLeft;
				localMaxObjLeft=i;
				localMaxRight=maxRight+(localMaxObjLeft-tempMaxObjLeft);
			}
			else if(i+objElem.getWidth()-1>maxObjRight)
			{
				int tempMaxObjRight=maxObjRight;
				localMaxObjRight=i+objElem.getWidth()-1;
				localMaxLeft=maxLeft+(localMaxObjRight-tempMaxObjRight);
				//maxLeft=maxLeft+objElem.getWidth()-1;
			}    
    		
    		if(countElements>0)
    		{    			
    			relativePositionDepthSearchTopK(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,localMaxLeft,localMaxRight,floorTileHeight,localMaxObjLeft,localMaxObjRight,maxScreens,numEnemies,random,globalControlSearch+1);
    		}
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			counterBranches=counterBranches+1;
    			validateBestBranchTopK(states,objElemP,height,counterBranches,maxScreens,localMaxObjLeft,floorTileHeight);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	//putting enemies
    	if(globalControlSearch==0)
    	{
    	for(int i=0;i<numEnemies;i++)
    	{
    		countElements--;
        	Elements objElemEnem= (Elements)finalList.get(countElementsFinal+i);
        	int idElemEnem=objElemEnem.getIdElem();
        	int typeElemEnem=objElemEnem.getTypeElem(); 
        	
        	counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(RandomCoordenateGenerator(random,0,width-1),RandomCoordenateGenerator(random,0,floorTileHeight),counterIDs,typeElemEnem,idElemEnem);
    		//states.add(objBlockNode2);

    		Iterator<Branch> nombreIterator = bestBranches.iterator();
            while(nombreIterator.hasNext()){
            	Branch elemento = nombreIterator.next();
            	elemento.getStates().add(objBlockNode2);
            }
    	}
    	}
    	return bestBranches;
    }	
	
    public ArrayList  basicDepthSearch(int width,int height,   int countElements, int countElementsFinal,ArrayList states, ConstraintsPlacement objConstraints, ArrayList finalList, ElementsToPlace objElemP,int floorTileHeight)
    {    
    	
    	countElements--;
    	Elements objElem= (Elements)finalList.get(countElementsFinal-countElements-1);
    	int idElem=objElem.getIdElem();
    	int typeElem=objElem.getTypeElem();
    	for(int i=0;i<width;i++)
    	{
    	 for(int j=0;j<height;j++)
    	 {  
    		 if(typeElem==objElemP.getOddsCannons() || typeElem==objElemP.getOddsTubes())
    		 {    			
    			
    			if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
     		 	{
     			 	
    				//System.out.println("falseadoo");
     			 	continue;
     		 	}
    			else if(objConstraints.ConstraintsWidthMaxTile(states, i, j, finalList, width)==false)
    			{ 
    				
    				continue;
    			}
    			else if(objConstraints.ConstraintsFloor(i,j)==false)
    			{ 
    				
    				continue;
    			}
    		 }
    		 else if(typeElem==objElemP.getBlockElement())
    		 {
    			 if(objConstraints.ConstraintsOverlaid(states, i, j, finalList,objElemP)==false)
       		 	{
       			 	
      				//System.out.println("falseadoo");
       			 	continue;
       		 	}
    			 else if(objConstraints.ConstraintsWidthMaxTile(states, i, j, finalList, width)==false)
     			{ 
     				
     				continue;
     			}
    			 else if(objConstraints.ConstraintsFloorFloatings(states, i, j, finalList)==false)
     			{ 
     				
     				continue;
     			}
    			
    			 /*if(objConstraints.ConstraintsPosibleGoalJump(i, j)==false)
    			 {
    				 continue;
    			 }*/
    		 }
    		 
    		counterIDs=counterIDs+1;
    		BlockNode objBlockNode2=new BlockNode(i,j,counterIDs,typeElem,idElem);
    		states.add(objBlockNode2);
    		if(countElements>0)
    			basicDepthSearch(width,height, countElements,countElementsFinal,states,objConstraints,finalList,objElemP,floorTileHeight);
    		else{
    			//System.out.println("aca se debe calcular la formula");
    			validateBestBranch(states,objElemP,height,floorTileHeight,localMaxObjLeft);
    			 
    		}
    		//System.out.println("Aqui deberia eliminar del array");
    		states.remove(states.size() - 1);
    		 
    	 }
    	}
    	return Beststates;
    }
 
    private Double partialSymmetry(ArrayList states, ElementsToPlace objElemP,int height,int floor,int maxObjLeft,boolean futureSymmetry) {
		// TODO Auto-generated method stub
    	
    	
    	//impresion de array actual
    	
    	Iterator<BlockNode> nombreIterator = states.iterator();
        while(nombreIterator.hasNext()){
        	BlockNode elemento = nombreIterator.next();
        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
        }
        
        //here we will calculate the center of mass
        //centerOfMassDepthSearchCenterFrame(states,objElemP,height,floor);
        symmetryV=symettry1Areas(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        //double DistanceX=distanceBetweenX(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
    	
        return symmetryV;
    			
	}
    
    private Double partialSymmetryNewFormula(ArrayList states, ElementsToPlace objElemP,int height,int floor,int maxObjLeft,boolean futureSymmetry,int typeSymmetry) {
		// TODO Auto-generated method stub
    	
    	
    	//impresion de array actual
    	
    	Iterator<BlockNode> nombreIterator = states.iterator();
        while(nombreIterator.hasNext()){
        	BlockNode elemento = nombreIterator.next();
        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
        }
        
        //here we will calculate the center of mass
        //centerOfMassDepthSearchCenterFrame(states,objElemP,height,floor);
        //symmetryV=symettry1Areas(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        //double DistanceX=distanceBetweenX(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
    	
        if(typeSymmetry==1)
        {
        	symmetryV=verticalSymmetry(states, xCenterMassGeneral, yCenterMassGeneral,objElemP );        
        }
        else
        {
        	symmetryV=allSymetry(states, xCenterMassGeneral, yCenterMassGeneral,objElemP ); 	
        }
        
        return symmetryV;
    			
	} 
    
    private void validateBestBranchDepthSearchCenterFrame(ArrayList states, ElementsToPlace objElemP,int height,int floor,int maxObjLeft, int typeSymmetry) {
		// TODO Auto-generated method stub
    	
    	//impresion de array actual
    	
//    	Iterator<BlockNode> nombreIterator = states.iterator();
//        while(nombreIterator.hasNext()){
//        	BlockNode elemento = nombreIterator.next();
//        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
//        }
        
        //here we will calculate the center of mass
        //centerOfMassDepthSearchCenterFrame(states,objElemP,height,floor);
       // symmetryV=symettry1Areas(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        if(typeSymmetry==1)
        {
        	symmetryV=verticalSymmetry(states, xCenterMassGeneral, yCenterMassGeneral,objElemP );        
        }
        else
        {
        	symmetryV=allSymetry(states, xCenterMassGeneral, yCenterMassGeneral,objElemP ); 	
        }
        double DistanceX=distanceBetweenX(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        
        if(symmetryV<bestSymmetryV)
        {
        		bestSymmetryV=symmetryV;
            	Beststates= new ArrayList<BlockNode>(states);
            	//Beststates=FormattingElementsSingle(Beststates, maxObjLeft);
            	bestAverageX=DistanceX;
        	
        }
        else if(symmetryV==bestSymmetryV )
        {
        	   if(DistanceX>bestAverageX)
        	   {
        		bestSymmetryV=symmetryV;
        		Beststates= new ArrayList<BlockNode>(states);
        		//Beststates=FormattingElementsSingle(Beststates, maxObjLeft);
        		bestAverageX=DistanceX;
        	   }
        }
        
    			
	}    
    
    private void validateBestBranch(ArrayList states, ElementsToPlace objElemP,int height,int floor,int maxObjLeft) {
		// TODO Auto-generated method stub
    	
    	//impresion de array actual
    	
    	Iterator<BlockNode> nombreIterator = states.iterator();
        while(nombreIterator.hasNext()){
        	BlockNode elemento = nombreIterator.next();
        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
        }
        
        //here we will calculate the center of mass
        //centerOfMass(states,objElemP,height,floor);
        symmetryV=symettry1Areas(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        double DistanceX=distanceBetweenX(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        
        if(symmetryV<bestSymmetryV)
        {
        		bestSymmetryV=symmetryV;
            	Beststates= new ArrayList<BlockNode>(states);
            	Beststates=FormattingElementsSingle(Beststates, maxObjLeft);
            	bestAverageX=DistanceX;
        	
        }
        else if(symmetryV==bestSymmetryV )
        {
        	   if(DistanceX>bestAverageX)
        	   {
        		bestSymmetryV=symmetryV;
        		Beststates= new ArrayList<BlockNode>(states);
        		Beststates=FormattingElementsSingle(Beststates, maxObjLeft);
        		bestAverageX=DistanceX;
        	   }
        }
    	
        
    			
	}
    
    private void validateBestBranchTopK(ArrayList states, ElementsToPlace objElemP,int height,int counterBranches, int maxScreens, int maxObjLeft,int floor) {
		// TODO Auto-generated method stub
    	
    	
    	
    	//impresion de array actual
    	
    	Iterator<BlockNode> nombreIterator = states.iterator();
        while(nombreIterator.hasNext()){
        	BlockNode elemento = nombreIterator.next();
        	//System.out.print(elemento.getID()+"("+elemento.getX()+" "+elemento.getY()+" )  / ");
        }
        
        //here we will calculate the center of mass
        //centerOfMass(states,objElemP,height,floor);
        symmetryV=symettry1Areas(states, objElemP, xCenterMassGeneral, yCenterMassGeneral,xCenterMassCoins, yCenterMassCoins);
        
      //creating object Branch
    	Branch objBranch=new Branch(symmetryV,new ArrayList<BlockNode>(states));
        
        
        
    	if(counterBranches<=maxScreens)
        {
        	if(symmetryV>worstSymmetryV)
            {
            	worstSymmetryV=symmetryV;
            	WorststatesObj=objBranch;
            }
        	objBranch=FormattingElementsTopK(objBranch, maxObjLeft);
        	bestBranches.add(objBranch);
        	bestBranches=objBranch.sortBranches(bestBranches);
        }
        else
        {
        	
        	if(objBranch.getHeuristicValue()<WorststatesObj.getHeuristicValue())
        	{
        		bestBranches.remove(WorststatesObj);
        		objBranch=FormattingElementsTopK(objBranch, maxObjLeft);
        		bestBranches.add(objBranch);
        		bestBranches=objBranch.sortBranches(bestBranches);
        		WorststatesObj=(Branch)bestBranches.get(bestBranches.size()-1);
        	}
        }
    			
	}    
    
	public double distanceBetweenX(ArrayList states,ElementsToPlace objElemP, double xCenterMassGeneral, double yCenterMassGeneral, double xCenterMassCoins, double yCenterMassCoins)
	{
		double widthElement=0;
		double heigthElement=0;
		
		double widthElementN=0;
		double heigthElementN=0;

		double averageXDistance=0;
		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElement=(double)element.getWidth();
	        heigthElement=(double)element.getHeigth()+1;
	        
	        int xMid=(int)((xInitial+widthElement)/2);
	        Iterator<BlockNode> itPlaces = states.iterator();
	        while(itPlaces.hasNext()){
	        	
	        	BlockNode elementoN = itPlaces.next();
				Elements elementN=(Elements)objElemP.getFinalList().get(elementoN.getIdElement());
				
				double xInitialN = elementoN.getX();
		        double yInitialN= elementoN.getY();
		        widthElementN=(double)elementN.getWidth();
		        heigthElementN=(double)elementN.getHeigth()+1;
		        int xMidN=(int)((xInitialN+widthElementN)/2);
	        	averageXDistance=averageXDistance+Math.abs(xMid-xMidN);
	        
	        }
	        
			}

		return averageXDistance;
	}
	
	public void centerOfMassDepthSearchCenterFrame(ArrayList states,ElementsToPlace objElemP,double height,int floor)
	{
		//System.out.println("llamada1");
		boolean flagPivotFloating=false;
		double summatoryAreasXG=0;
		double summatoryAreasYG=0;
		double summatoryAreasG=0;
		double widthElementG=0;
		double heigthElementG=0;
		double areaG;
		double xG,yG;
		
		double summatoryAreasXC=0;
		double summatoryAreasYC=0;
		double summatoryAreasC=0;
		double widthElementC=0;
		double heigthElementC=0;
		double areaC;
		double xC,yC;
		
		//Center of mass of all objects
		Iterator<BlockNode> itCenterMass = states.iterator();
		while(itCenterMass.hasNext()){
			BlockNode elemento = itCenterMass.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElementG=element.getWidth();
	        heigthElementG=element.getHeigth()+1;
	        
	        areaG=widthElementG*heigthElementG;
	        xG=xInitial+(widthElementG/2);
	        yG=yInitial-(heigthElementG/2);
	        //System.out.println(elemento.getIdElement()+"x "+xG+"y "+yG);
	        
	        summatoryAreasG=summatoryAreasG+areaG;
	        summatoryAreasXG=summatoryAreasXG+(areaG*(xG));
	        summatoryAreasYG=summatoryAreasYG+(areaG*(yG));
	        
	        if(element.getIdElem()==0 && (element.getTypeElem()==objElemP.getBlockElement() || element.getTypeElem()==objElemP.getOddsHillStraightFloat()))
	        {
	        	flagPivotFloating=true;
	        }
	        
			}
	        
	        		        
		}
		
		xCenterMassGeneral=summatoryAreasXG/summatoryAreasG;
		xCenterMassGeneral=globalCenterXMass;
        yCenterMassGeneral=summatoryAreasYG/summatoryAreasG;
        
        //xCenterMassGeneral=8.5;
        yCenterMassGeneral=7.5;
        if(flagPivotFloating==true)
        {
        	
        	yCenterMassGeneral=7.5;
        }
        //System.out.println("xCenterMassGC "+xCenterMassGeneral);
        //System.out.println("yCenterMassGC "+yCenterMassGeneral);
		
		//center of mass of Coins
		Iterator<BlockNode> itCenterMass2 = states.iterator();
		while(itCenterMass2.hasNext()){
			BlockNode elemento = itCenterMass2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins())
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElementC=element.getWidth();
	        heigthElementC=element.getHeigth()+1;
	        
	        areaC=widthElementC*heigthElementC;
	        xC=xInitial+(widthElementC/2);
	        yC=yInitial-(heigthElementC/2);
	        //System.out.println(elemento.getIdElement()+"x "+xC+"y "+yC);
	        
	        summatoryAreasC=summatoryAreasC+areaC;
	        summatoryAreasXC=summatoryAreasXC+(areaC*(xC));
	        summatoryAreasYC=summatoryAreasYC+(areaC*(yC));
			}
	        
	        		        
		}
		
		/*System.out.println("summatoryAreasX "+summatoryAreasX);
        System.out.println("summatoryAreasY "+summatoryAreasY);
        System.out.println("summatoryAreas "+summatoryAreas);*/
		
		xCenterMassCoins=summatoryAreasXC/summatoryAreasC;
        yCenterMassCoins=summatoryAreasYC/summatoryAreasC;
        
        yCenterMassCoins=floor/2;
        //System.out.println("xCenterMassC "+xCenterMassCoins);
        //System.out.println("yCenterMassC "+yCenterMassCoins);
	}
    
	public void centerOfMass(ArrayList states,ElementsToPlace objElemP,double height,int floor)
	{
		//System.out.println("llamada2");
		boolean flagPivotFloating=false;
		double summatoryAreasXG=0;
		double summatoryAreasYG=0;
		double summatoryAreasG=0;
		double widthElementG=0;
		double heigthElementG=0;
		double areaG;
		double xG,yG;
		
		double summatoryAreasXC=0;
		double summatoryAreasYC=0;
		double summatoryAreasC=0;
		double widthElementC=0;
		double heigthElementC=0;
		double areaC;
		double xC,yC;
		
		//Center of mass of all objects
		Iterator<BlockNode> itCenterMass = states.iterator();
		while(itCenterMass.hasNext()){
			BlockNode elemento = itCenterMass.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElementG=element.getWidth();
	        heigthElementG=element.getHeigth()+1;
	        
	        areaG=widthElementG*heigthElementG;
	        xG=xInitial+(widthElementG/2);
	        yG=yInitial-(heigthElementG/2);
	        //System.out.println(elemento.getIdElement()+"x "+xG+"y "+yG);
	        
	        summatoryAreasG=summatoryAreasG+areaG;
	        summatoryAreasXG=summatoryAreasXG+(areaG*(xG));
	        summatoryAreasYG=summatoryAreasYG+(areaG*(yG));
	        
	        if(element.getIdElem()==0 && element.getTypeElem()==objElemP.getBlockElement() || element.getTypeElem()==objElemP.getOddsHillStraightFloat())
	        {
	        	flagPivotFloating=true;
	        }
	        
			}
	        
	        		        
		}
		
		xCenterMassGeneral=summatoryAreasXG/summatoryAreasG;
        yCenterMassGeneral=summatoryAreasYG/summatoryAreasG;
        
        yCenterMassGeneral=9.0;
        if(flagPivotFloating==true)
        {
        	yCenterMassGeneral=7.0;
        }
        //System.out.println("xCenterMassG "+xCenterMassGeneral);
        //System.out.println("yCenterMassG "+yCenterMassGeneral);
		
		//center of mass of Coins
		Iterator<BlockNode> itCenterMass2 = states.iterator();
		while(itCenterMass2.hasNext()){
			BlockNode elemento = itCenterMass2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins())
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElementC=element.getWidth();
	        heigthElementC=element.getHeigth()+1;
	        
	        areaC=widthElementC*heigthElementC;
	        xC=xInitial+(widthElementC/2);
	        yC=yInitial-(heigthElementC/2);
	        //System.out.println(elemento.getIdElement()+"x "+xC+"y "+yC);
	        
	        summatoryAreasC=summatoryAreasC+areaC;
	        summatoryAreasXC=summatoryAreasXC+(areaC*(xC));
	        summatoryAreasYC=summatoryAreasYC+(areaC*(yC));
			}
	        
	        		        
		}
		
		/*System.out.println("summatoryAreasX "+summatoryAreasX);
        System.out.println("summatoryAreasY "+summatoryAreasY);
        System.out.println("summatoryAreas "+summatoryAreas);*/
		
		xCenterMassCoins=summatoryAreasXC/summatoryAreasC;
        yCenterMassCoins=summatoryAreasYC/summatoryAreasC;
        
        yCenterMassCoins=floor/2;
        //System.out.println("xCenterMassC "+xCenterMassCoins);
        //System.out.println("yCenterMassC "+yCenterMassCoins);
	}
	
	public double SubstractionRythm(double [] Arr1, double [] Arr2,int type)
	{double summatory=0;
	//for (int i=0;i< Arr1.length;i++)
	//{
	summatory= Math.abs(Arr1[type]-Arr2[type]);
	//}
	return summatory;
	}
	
	public double verticalSymmetry(ArrayList states, double xCenterMassGeneral, double yCenterMassGeneral, ElementsToPlace objElemP)
	{

//		XsQuadrant=new ArrayList<Double>();
//    	YsQuadrant=new ArrayList<Double>();
//    	
//		bestXs=new ArrayList<Double>();
//    	bestYs=new ArrayList<Double>();
		
		double [] gulAG;
		double [] gurAG;
		double [] gllAG;
		double [] glrAG;

		double [] gulAC;
		double [] gurAC;
		double [] gllAC;
		double [] glrAC;

		double rythmValueX;
		double rythmValueY;
		double rythmValueA;
		double rythmValueGeneral;

		gulAG=new double[4];
		gurAG=new double[4];
		gllAG=new double[4];
		glrAG=new double[4];

		gulAC=new double[4];
		gurAC=new double[4];
		gllAC=new double[4];
		glrAC=new double[4];


		double [] gulATG=new double[4];
		double [] gurATG=new double[4];
		double [] gllATG=new double[4];
		double [] glrATG=new double[4];

		double [] gulATC=new double[4];
		double [] gurATC=new double[4];
		double [] gllATC=new double[4];
		double [] glrATC=new double[4];

		partialXSummatory[0]=0;
		partialXSummatory[1]=0;
		partialXSummatory[2]=0;
		partialXSummatory[3]=0;
		
		partialYSummatory[0]=0;
		partialYSummatory[1]=0;
		partialYSummatory[2]=0;
		partialYSummatory[3]=0;
		
		partialASummatory[0]=0;
		partialASummatory[1]=0;
		partialASummatory[2]=0;
		partialASummatory[3]=0;

		double widthElement=0;
		double heigthElement=0;
		double x,y;

		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			widthElement=(double)element.getWidth();
			heigthElement=(double)element.getHeigth()+1;


			if((xInitial+widthElement)<=xCenterMassGeneral )
			{
				//block up left
				if(yInitial<=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=widthElement;

					gulAG[3]=heigthElement;

					//gul.add(gulA);

					//new symmetry with areas

					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

				}

				//block low left
				else if(yInitial-heigthElement>=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=widthElement;

					gllAG[3]=heigthElement;

					//gll.add(gllA);


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);

				}
				else
				{
					x=xInitial+(widthElement/2);

					//first block of the element (up left)
					//y=(yInitial-heigthElement)+(yCenterMassGeneral-yInitial)/2;
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=widthElement;

					gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);

					//gul.add(gulA);	        		

					//new symmetry with areas
					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

					//second block of the element (low left)
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=widthElement;

					gllAG[3]=(yInitial-yCenterMassGeneral);

					//gll.add(gllA);


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);
				}
			}
			else if(xInitial>=xCenterMassGeneral )
			{

				//block up right
				if(yInitial<=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=widthElement;

					gurAG[3]=heigthElement;

					//gur.add(gurA);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);
				}
				//block low right
				else if(yInitial-heigthElement>=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=widthElement;

					glrAG[3]=heigthElement;

					//glr.add(glrA);


					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}
				else
				{

					x=xInitial+(widthElement/2);

					//first block of the element (up right)
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=widthElement;

					gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);

					//gur.add(gulA);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);

					//second block of the element  (low right)
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=widthElement;

					glrAG[3]=yInitial-yCenterMassGeneral;

					//glr.add(gllA);


					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}

			}
			else
			{
				if(yInitial<=yCenterMassGeneral)
				{
					y=yInitial-(heigthElement/2);

					//first block of the element (up left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=xCenterMassGeneral-xInitial;

					gulAG[3]=heigthElement;

					//gul.add(gurA);


					//new symmetry with areas
					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

					//second block of the element (up right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					gurAG[3]=heigthElement;

					//gur.add(gurA);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);

				}
				else if(yInitial-heigthElement>=yCenterMassGeneral)
				{
					y=yInitial-(heigthElement/2);

					//first block of the element (low left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=(xCenterMassGeneral-xInitial);

					gllAG[3]=heigthElement;

					//gll.add(gurA);


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);

					//second block of the element (low right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					glrAG[3]=heigthElement;

					//gur.add(gurA);


					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}
				else
				{
					//falta implementar caso todos los cuadrantes
					//first block of the element (up left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=xCenterMassGeneral-xInitial;

					gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);


					//new symmetry with areas
					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

					//second block of the element (up right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);

					//first block of the element (low left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=(xCenterMassGeneral-xInitial);

					gllAG[3]=yInitial-yCenterMassGeneral;


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);

					//second block of the element (low right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					glrAG[3]=yInitial-yCenterMassGeneral;



					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}
			}


		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		rythmValueX=SubstractionRythm(gulATG,gurATG,0)+SubstractionRythm(gllATG,glrATG,0);
		rythmValueX=rythmValueX;
		//System.out.println("rythmValueX "+rythmValueX);

		rythmValueY=SubstractionRythm(gulATG,gurATG,1)+SubstractionRythm(gllATG,glrATG,1);
		rythmValueY=rythmValueY;
		//System.out.println("rythmValueY "+rythmValueY);

		rythmValueA=SubstractionRythm(gulATG,gurATG,2)+SubstractionRythm(gllATG,glrATG,2);
		rythmValueA=rythmValueA;
		//System.out.println("rythmValueA "+rythmValueA);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		rythmValueGeneral=( Math.abs(rythmValueX)+Math.abs(rythmValueY)+Math.abs(rythmValueA) );
		//System.out.println("rythmValueGeneral "+rythmValueGeneral);
		return rythmValueGeneral;
	}
	
	public double allSymetry(ArrayList states, double xCenterMassGeneral, double yCenterMassGeneral, ElementsToPlace objElemP)
	{
//
//		bestXs=new ArrayList<Double>();
//    	bestYs=new ArrayList<Double>();
//    	XsQuadrant=new ArrayList<Double>();
//    	YsQuadrant=new ArrayList<Double>();
    	
		double [] gulAG;
		double [] gurAG;
		double [] gllAG;
		double [] glrAG;

		double [] gulAC;
		double [] gurAC;
		double [] gllAC;
		double [] glrAC;

		double rythmValueX;
		double rythmValueY;
		double rythmValueA;
		double rythmValueGeneral;

		gulAG=new double[4];
		gurAG=new double[4];
		gllAG=new double[4];
		glrAG=new double[4];

		gulAC=new double[4];
		gurAC=new double[4];
		gllAC=new double[4];
		glrAC=new double[4];


		double [] gulATG=new double[4];
		double [] gurATG=new double[4];
		double [] gllATG=new double[4];
		double [] glrATG=new double[4];

		double [] gulATC=new double[4];
		double [] gurATC=new double[4];
		double [] gllATC=new double[4];
		double [] glrATC=new double[4];
		
		partialXSummatory[0]=0;
		partialXSummatory[1]=0;
		partialXSummatory[2]=0;
		partialXSummatory[3]=0;
		
		partialYSummatory[0]=0;
		partialYSummatory[1]=0;
		partialYSummatory[2]=0;
		partialYSummatory[3]=0;
		
		partialASummatory[0]=0;
		partialASummatory[1]=0;
		partialASummatory[2]=0;
		partialASummatory[3]=0;

		double widthElement=0;
		double heigthElement=0;
		double x,y;

		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());

			double xInitial = elemento.getX();
			double yInitial= elemento.getY();

			widthElement=(double)element.getWidth();
			heigthElement=(double)element.getHeigth()+1;


			if((xInitial+widthElement)<=xCenterMassGeneral )
			{
				//block up left
				if(yInitial<=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=widthElement;

					gulAG[3]=heigthElement;

					//gul.add(gulA);

					//new symmetry with areas

					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

				}

				//block low left
				else if(yInitial-heigthElement>=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=widthElement;

					gllAG[3]=heigthElement;

					//gll.add(gllA);


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);

				}
				else
				{
					x=xInitial+(widthElement/2);

					//first block of the element (up left)
					//y=(yInitial-heigthElement)+(yCenterMassGeneral-yInitial)/2;
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=widthElement;

					gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);

					//gul.add(gulA);	        		

					//new symmetry with areas
					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

					//second block of the element (low left)
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=widthElement;

					gllAG[3]=(yInitial-yCenterMassGeneral);

					//gll.add(gllA);


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);
				}
			}
			else if(xInitial>=xCenterMassGeneral )
			{

				//block up right
				if(yInitial<=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=widthElement;

					gurAG[3]=heigthElement;

					//gur.add(gurA);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);
				}
				//block low right
				else if(yInitial-heigthElement>=yCenterMassGeneral)
				{
					x=xInitial+(widthElement/2);
					y=yInitial-(heigthElement/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=widthElement;

					glrAG[3]=heigthElement;

					//glr.add(glrA);


					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}
				else
				{

					x=xInitial+(widthElement/2);

					//first block of the element (up right)
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=widthElement;

					gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);

					//gur.add(gulA);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);

					//second block of the element  (low right)
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=widthElement;

					glrAG[3]=yInitial-yCenterMassGeneral;

					//glr.add(gllA);


					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}

			}
			else
			{
				if(yInitial<=yCenterMassGeneral)
				{
					y=yInitial-(heigthElement/2);

					//first block of the element (up left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=xCenterMassGeneral-xInitial;

					gulAG[3]=heigthElement;

					//gul.add(gurA);


					//new symmetry with areas
					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

					//second block of the element (up right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					gurAG[3]=heigthElement;

					//gur.add(gurA);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);

				}
				else if(yInitial-heigthElement>=yCenterMassGeneral)
				{
					y=yInitial-(heigthElement/2);

					//first block of the element (low left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=(xCenterMassGeneral-xInitial);

					gllAG[3]=heigthElement;

					//gll.add(gurA);


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);

					//second block of the element (low right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);
					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					glrAG[3]=heigthElement;

					//gur.add(gurA);


					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}
				else
				{
					//falta implementar caso todos los cuadrantes
					//first block of the element (up left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					gulAG[0]=Math.abs(x-xCenterMassGeneral);
					gulATG[0]=gulATG[0]+gulAG[0];
					gulAG[1]=Math.abs(y-yCenterMassGeneral);
					gulATG[1]=gulATG[1]+gulAG[1];
					gulAG[2]=xCenterMassGeneral-xInitial;

					gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);


					//new symmetry with areas
					gulAG[2]=gulAG[2]*gulAG[3];
					gulAG[3]=0;
					gulATG[2]=gulATG[2]+gulAG[2];
					gulATG[3]=gulATG[3]+gulAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		partialASummatory[0]=partialASummatory[0]+(gulAG[2]);

					//second block of the element (up right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
					y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					gurAG[0]=Math.abs(x-xCenterMassGeneral);
					gurATG[0]=gurATG[0]+gurAG[0];
					gurAG[1]=Math.abs(y-yCenterMassGeneral);
					gurATG[1]=gurATG[1]+gurAG[1];
					gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);


					//new symmetry with areas
					gurAG[2]=gurAG[2]*gurAG[3];
					gurAG[3]=0;
					gurATG[2]=gurATG[2]+gurAG[2];
					gurATG[3]=gurATG[3]+gurAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		partialASummatory[1]=partialASummatory[1]+(gurAG[2]);

					//first block of the element (low left)
					x=(xInitial+(xCenterMassGeneral-xInitial)/2);
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					gllAG[0]=Math.abs(x-xCenterMassGeneral);
					gllATG[0]=gllATG[0]+gllAG[0];
					gllAG[1]=Math.abs(y-yCenterMassGeneral);
					gllATG[1]=gllATG[1]+gllAG[1];
					gllAG[2]=(xCenterMassGeneral-xInitial);

					gllAG[3]=yInitial-yCenterMassGeneral;


					//new symmetry with areas
					gllAG[2]=gllAG[2]*gllAG[3];
					gllAG[3]=0;
					gllATG[2]=gllATG[2]+gllAG[2];
					gllATG[3]=gllATG[3]+gllAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		partialASummatory[2]=partialASummatory[2]+(gllAG[2]);

					//second block of the element (low right)
					x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
					y=yInitial-(yInitial-yCenterMassGeneral)/2;
//					XsQuadrant.add(x);
//	        		YsQuadrant.add(y);

					glrAG[0]=Math.abs(x-xCenterMassGeneral);
					glrATG[0]=glrATG[0]+glrAG[0];
					glrAG[1]=Math.abs(y-yCenterMassGeneral);
					glrATG[1]=glrATG[1]+glrAG[1];
					glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;

					glrAG[3]=yInitial-yCenterMassGeneral;



					//new symmetry with areas
					glrAG[2]=glrAG[2]*glrAG[3];
					glrAG[3]=0;
					glrATG[2]=glrATG[2]+glrAG[2];
					glrATG[3]=glrATG[3]+glrAG[3];
//					bestXs.add(gulAG[0]);
//	        		bestYs.add(gulAG[1]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		partialASummatory[3]=partialASummatory[3]+(glrAG[2]);
				}
			}


		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		rythmValueX=SubstractionRythm(gulATG,gllATG,0)+SubstractionRythm(gurATG,glrATG,0)+SubstractionRythm(gulATG,gurATG,0)+SubstractionRythm(gllATG,glrATG,0)+SubstractionRythm(gulATG,glrATG,0)+SubstractionRythm(gurATG,gllATG,0);
		rythmValueX=rythmValueX;
		//System.out.println("rythmValueX "+rythmValueX);

		rythmValueY=SubstractionRythm(gulATG,gllATG,1)+SubstractionRythm(gurATG,glrATG,1)+SubstractionRythm(gulATG,gurATG,1)+SubstractionRythm(gllATG,glrATG,1)+SubstractionRythm(gulATG,glrATG,1)+SubstractionRythm(gurATG,gllATG,1);
		rythmValueY=rythmValueY;
		//System.out.println("rythmValueY "+rythmValueY);

		rythmValueA=SubstractionRythm(gulATG,gllATG,2)+SubstractionRythm(gurATG,glrATG,2)+SubstractionRythm(gulATG,gurATG,2)+SubstractionRythm(gllATG,glrATG,2)+SubstractionRythm(gulATG,glrATG,2)+SubstractionRythm(gurATG,gllATG,2);
		rythmValueA=rythmValueA;
		//System.out.println("rythmValueA "+rythmValueA);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		rythmValueGeneral=( Math.abs(rythmValueX)+Math.abs(rythmValueY)+Math.abs(rythmValueA) );
		return rythmValueGeneral;
	}
	
	public double symettry1Areas(ArrayList states,ElementsToPlace objElemP, double xCenterMassGeneral, double yCenterMassGeneral, double xCenterMassCoins, double yCenterMassCoins)
	{
		
		partialHeight[0]=0;
		partialHeight[1]=0;
		partialHeight[2]=0;
		partialHeight[3]=0;
		
		partialWidth[0]=0;
		partialWidth[1]=0;
		partialWidth[2]=0;
		partialWidth[3]=0;
		
		partialXSummatory[0]=0;
		partialXSummatory[1]=0;
		partialXSummatory[2]=0;
		partialXSummatory[3]=0;
		
		partialYSummatory[0]=0;
		partialYSummatory[1]=0;
		partialYSummatory[2]=0;
		partialYSummatory[3]=0;
		
		bestXs=new ArrayList<Double>();
    	bestYs=new ArrayList<Double>();
    	
    	XsQuadrant=new ArrayList<Double>();
    	YsQuadrant=new ArrayList<Double>();
		//gul=new ArrayList<double[]>();
		//gur=new ArrayList<double[]>();
		//gll=new ArrayList<double[]>();
		//glr=new ArrayList<double[]>();
		double symmetryValue;
		double symmetryValueGeneral=0;
		double symmetryValueCoins=0;
		double symmetryValueEnemies;
		
		gulAG=new double[4];
		gurAG=new double[4];
		gllAG=new double[4];
		glrAG=new double[4];
		
		gulAC=new double[4];
		gurAC=new double[4];
		gllAC=new double[4];
		glrAC=new double[4];
	
		
		double [] gulATG=new double[4];
		double [] gurATG=new double[4];
		double [] gllATG=new double[4];
		double [] glrATG=new double[4];
		
		double [] gulATC=new double[4];
		double [] gurATC=new double[4];
		double [] gllATC=new double[4];
		double [] glrATC=new double[4];
	
		
		double widthElement=0;
		double heigthElement=0;
		double x,y;
		
		int counterQuadrants=0;
		
		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        if(elemento.getType()!=-1)
	        {
	        	widthElement=element.getWidth();
	        	heigthElement=element.getHeigth()+1;
	        }
	        else
	        {
	        	while(distributionsWidth[counterQuadrants]==0 && distributionsHeight[counterQuadrants]==0)
	        	{
	        		counterQuadrants++;
	        	}
	        	widthElement=distributionsWidth[counterQuadrants];
	        	heigthElement=distributionsHeight[counterQuadrants];
	        	counterQuadrants++;
	        }
	        
	        if((xInitial+widthElement)<=xCenterMassGeneral )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		
	        		gulAG[3]=heigthElement;
	        		
	        		//gul.add(gulA);
	        		
	        		bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		//new symmetry with areas
	        		
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		
	        		gllAG[3]=heigthElement;
	        		
	        		//gll.add(gllA);
	        		
	        		bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];

	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassGeneral-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		
	        		gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		
	        		//gul.add(gulA);
	        		
	        		bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		//new symmetry with areas
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		
	        		gllAG[3]=(yInitial-yCenterMassGeneral);
	        		
	        		//gll.add(gllA);
	        		
	        		
	        		bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        	}
	        }
	        else if(xInitial>=xCenterMassGeneral )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		
	        		gurAG[3]=heigthElement;
	        		
	        		//gur.add(gurA);
	        		
	        		bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		
	        		glrAG[3]=heigthElement;
	        		
	        		//glr.add(glrA);
	        		
	        		bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		
	        		gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		
	        		//gur.add(gulA);
	        		
	        		bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		
	        		glrAG[3]=yInitial-yCenterMassGeneral;
	        		
	        		//glr.add(gllA);
	        		
	        		
	        		bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        
			        gulAG[3]=heigthElement;
			        
			        //gul.add(gurA);
			        
			        bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		//new symmetry with areas
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        gurAG[3]=heigthElement;
			        
			        //gur.add(gurA);
			        
			        bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        
			        gllAG[3]=heigthElement;
			        
			        //gll.add(gurA);
			        
			        bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        glrAG[3]=heigthElement;
			        
			        //gur.add(gurA);
			        
			        bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        
			        gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        
			        
			        bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        		
	        		//new symmetry with areas
	        		gulAG[2]=gulAG[2]*gulAG[3];
	        		gulAG[3]=0;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulATG[3]=gulATG[3]+gulAG[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        
			        
			        bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		//new symmetry with areas
	        		gurAG[2]=gurAG[2]*gurAG[3];
	        		gurAG[3]=0;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        
			        gllAG[3]=yInitial-yCenterMassGeneral;
			        
			        
			        bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        		
	        		//new symmetry with areas
	        		gllAG[2]=gllAG[2]*gllAG[3];
	        		gllAG[3]=0;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllATG[3]=gllATG[3]+gllAG[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        
			        glrAG[3]=yInitial-yCenterMassGeneral;
			        
			        
			        bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        		
	        		//new symmetry with areas
	        		glrAG[2]=glrAG[2]*glrAG[3];
	        		glrAG[3]=0;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        	}
	        }
	        
		}
		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		symmetryValueGeneral=SubstractionSymmetries(gulATG,gllATG)+SubstractionSymmetries(gurATG,glrATG)+SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG)+SubstractionSymmetries(gulATG,glrATG)+SubstractionSymmetries(gurATG,gllATG);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		
		
		//Symmetry Coins
		Iterator<BlockNode> itSymmetry2 = states.iterator();
		while(itSymmetry2.hasNext()){
			BlockNode elemento = itSymmetry2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins() )
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
	        if((xInitial+widthElement)<=xCenterMassCoins )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=heigthElement;
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=heigthElement;
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassCoins-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=(yInitial-yCenterMassCoins);
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        }
	        else if(xInitial>=xCenterMassCoins )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=heigthElement;
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=heigthElement;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(glrA);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gulA);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=yInitial-yCenterMassCoins;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(gllA);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=heigthElement;
			        gulATC[3]=gulATC[3]+gulAC[3];
			        //gul.add(gurA);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=heigthElement;
			        gurATC[3]=gurATC[3]+gurAC[3];
			        //gur.add(gurA);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=heigthElement;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        //gll.add(gurA);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xCenterMassCoins-xInitial);
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=heigthElement;
			        glrATC[3]=glrATC[3]+glrAC[3];
			        //gur.add(gurA);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gulATC[3]=gulATC[3]+gulAC[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gurATC[3]=gurATC[3]+gurAC[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=yInitial-yCenterMassCoins;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=yInitial-yCenterMassCoins;
			        glrATC[3]=glrATC[3]+glrAC[3];
	        	}
	        }
	        
		}
		}
		//System.out.println("gulAT "+gulATC[0]+" "+gulATC[1]+" "+gulATC[2]+" "+gulATC[3]);
		//System.out.println("gurAT "+gurATC[0]+" "+gurATC[1]+" "+gurATC[2]+" "+gurATC[3]);
		//System.out.println("gllAT "+gllATC[0]+" "+gllATC[1]+" "+gllATC[2]+" "+gllATC[3]);
		//System.out.println("glrAT "+glrATC[0]+" "+glrATC[1]+" "+glrATC[2]+" "+glrATC[3]);
		//symmetryValueCoins=SubstractionSymmetries(gulATC,gllATC)+SubstractionSymmetries(gurATC,glrATC)+SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC)+SubstractionSymmetries(gulATC,glrATC)+SubstractionSymmetries(gurATC,gllATC);
		symmetryValueCoins=SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC);

		//System.out.println("symmetryValue "+symmetryValueCoins);
		
		symmetryValue=symmetryValueGeneral+symmetryValueCoins;
		return symmetryValue;
	}

	public double symettry1(ArrayList states,ElementsToPlace objElemP, double xCenterMassGeneral, double yCenterMassGeneral, double xCenterMassCoins, double yCenterMassCoins)
	{
		
		partialHeight[0]=0;
		partialHeight[1]=0;
		partialHeight[2]=0;
		partialHeight[3]=0;
		
		partialWidth[0]=0;
		partialWidth[1]=0;
		partialWidth[2]=0;
		partialWidth[3]=0;
		
		partialXSummatory[0]=0;
		partialXSummatory[1]=0;
		partialXSummatory[2]=0;
		partialXSummatory[3]=0;
		
		partialYSummatory[0]=0;
		partialYSummatory[1]=0;
		partialYSummatory[2]=0;
		partialYSummatory[3]=0;
		
		bestXs=new ArrayList<Double>();
    	bestYs=new ArrayList<Double>();
    	
    	XsQuadrant=new ArrayList<Double>();
    	YsQuadrant=new ArrayList<Double>();
		//gul=new ArrayList<double[]>();
		//gur=new ArrayList<double[]>();
		//gll=new ArrayList<double[]>();
		//glr=new ArrayList<double[]>();
		double symmetryValue;
		double symmetryValueGeneral=0;
		double symmetryValueCoins=0;
		double symmetryValueEnemies;
		
		gulAG=new double[4];
		gurAG=new double[4];
		gllAG=new double[4];
		glrAG=new double[4];
		
		gulAC=new double[4];
		gurAC=new double[4];
		gllAC=new double[4];
		glrAC=new double[4];
	
		
		double [] gulATG=new double[4];
		double [] gurATG=new double[4];
		double [] gllATG=new double[4];
		double [] glrATG=new double[4];
		
		double [] gulATC=new double[4];
		double [] gurATC=new double[4];
		double [] gllATC=new double[4];
		double [] glrATC=new double[4];
	
		
		double widthElement=0;
		double heigthElement=0;
		double x,y;
		
		int counterQuadrants=0;
		
		//Symmetry General
		Iterator<BlockNode> itSymmetry1 = states.iterator();
		while(itSymmetry1.hasNext()){
			BlockNode elemento = itSymmetry1.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()!=objElemP.getCoins() && elemento.getType()!=objElemP.getEnemyArmoredTurtle() && elemento.getType()!=objElemP.getEnemyCannonBall() && elemento.getType()!=objElemP.getEnemyChompFlower() && elemento.getType()!=objElemP.getEnemyFlower() && elemento.getType()!=objElemP.getEnemyGoomba() && elemento.getType()!=objElemP.getEnemyGreenKoopa() && elemento.getType()!=objElemP.getEnemyJumpFlower() && elemento.getType()!=objElemP.getEnemyRedKoopa() && elemento.getType()!=objElemP.getEnemySpiky())
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        if(elemento.getType()!=-1)
	        {
	        	widthElement=element.getWidth();
	        	heigthElement=element.getHeigth()+1;
	        }
	        else
	        {
	        	while(distributionsWidth[counterQuadrants]==0 && distributionsHeight[counterQuadrants]==0)
	        	{
	        		counterQuadrants++;
	        	}
	        	widthElement=distributionsWidth[counterQuadrants];
	        	heigthElement=distributionsHeight[counterQuadrants];
	        	counterQuadrants++;
	        }
	        
	        if((xInitial+widthElement)<=xCenterMassGeneral )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulAG[3]=heigthElement;
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		//gul.add(gulA);
	        		
	        		bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllAG[3]=heigthElement;
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		//gll.add(gllA);
	        		
	        		bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);

	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassGeneral-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
	        		gulAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gulATG[1]=gulATG[1]+gulAG[1];
	        		gulAG[2]=widthElement;
	        		gulATG[2]=gulATG[2]+gulAG[2];
	        		gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		gulATG[3]=gulATG[3]+gulAG[3];
	        		//gul.add(gulA);
	        		
	        		bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
	        		gllAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gllATG[1]=gllATG[1]+gllAG[1];
	        		gllAG[2]=widthElement;
	        		gllATG[2]=gllATG[2]+gllAG[2];
	        		gllAG[3]=(yInitial-yCenterMassGeneral);
	        		gllATG[3]=gllATG[3]+gllAG[3];
	        		//gll.add(gllA);
	        		
	        		
	        		bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
	        	}
	        }
	        else if(xInitial>=xCenterMassGeneral )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurAG[3]=heigthElement;
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		//gur.add(gurA);
	        		
	        		bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrAG[3]=heigthElement;
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		//glr.add(glrA);
	        		
	        		bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
	        		gurAG[1]=Math.abs(y-yCenterMassGeneral);
	        		gurATG[1]=gurATG[1]+gurAG[1];
	        		gurAG[2]=widthElement;
	        		gurATG[2]=gurATG[2]+gurAG[2];
	        		gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
	        		gurATG[3]=gurATG[3]+gurAG[3];
	        		//gur.add(gulA);
	        		
	        		bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
	        		glrAG[1]=Math.abs(y-yCenterMassGeneral);
	        		glrATG[1]=glrATG[1]+glrAG[1];
	        		glrAG[2]=widthElement;
	        		glrATG[2]=glrATG[2]+glrAG[2];
	        		glrAG[3]=yInitial-yCenterMassGeneral;
	        		glrATG[3]=glrATG[3]+glrAG[3];
	        		//glr.add(gllA);
	        		
	        		
	        		bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        gulATG[2]=gulATG[2]+gulAG[2];
			        gulAG[3]=heigthElement;
			        gulATG[3]=gulATG[3]+gulAG[3];
			        //gul.add(gurA);
			        
			        bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        gurATG[2]=gurATG[2]+gurAG[2];
			        gurAG[3]=heigthElement;
			        gurATG[3]=gurATG[3]+gurAG[3];
			        //gur.add(gurA);
			        
			        bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassGeneral)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        gllATG[2]=gllATG[2]+gllAG[2];
			        gllAG[3]=heigthElement;
			        gllATG[3]=gllATG[3]+gllAG[3];
			        //gll.add(gurA);
			        
			        bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        glrATG[2]=glrATG[2]+glrAG[2];
			        glrAG[3]=heigthElement;
			        glrATG[3]=glrATG[3]+glrAG[3];
			        //gur.add(gurA);
			        
			        bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gulAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gulATG[0]=gulATG[0]+gulAG[0];
			        gulAG[1]=Math.abs(y-yCenterMassGeneral);
			        gulATG[1]=gulATG[1]+gulAG[1];
			        gulAG[2]=xCenterMassGeneral-xInitial;
			        gulATG[2]=gulATG[2]+gulAG[2];
			        gulAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        gulATG[3]=gulATG[3]+gulAG[3];
			        
			        bestXs.add(gulAG[0]);
	        		bestYs.add(gulAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[0]=partialWidth[0]+(gulAG[2]);
	        		partialHeight[0]=partialHeight[0]+(gulAG[3]);
	        		
	        		partialXSummatory[0]=partialXSummatory[0]+(gulAG[0]);
	        		partialYSummatory[0]=partialYSummatory[0]+(gulAG[1]);
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassGeneral-(yInitial-heigthElement))/2;
	        		
	        		gurAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gurATG[0]=gurATG[0]+gurAG[0];
			        gurAG[1]=Math.abs(y-yCenterMassGeneral);
			        gurATG[1]=gurATG[1]+gurAG[1];
			        gurAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        gurATG[2]=gurATG[2]+gurAG[2];
			        gurAG[3]=yCenterMassGeneral-(yInitial-heigthElement);
			        gurATG[3]=gurATG[3]+gurAG[3];
			        
			        bestXs.add(gurAG[0]);
	        		bestYs.add(gurAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[1]=partialWidth[1]+(gurAG[2]);
	        		partialHeight[1]=partialHeight[1]+(gurAG[3]);
	        		
	        		partialXSummatory[1]=partialXSummatory[1]+(gurAG[0]);
	        		partialYSummatory[1]=partialYSummatory[1]+(gurAG[1]);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassGeneral-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		gllAG[0]=Math.abs(x-xCenterMassGeneral);
	        		gllATG[0]=gllATG[0]+gllAG[0];
			        gllAG[1]=Math.abs(y-yCenterMassGeneral);
			        gllATG[1]=gllATG[1]+gllAG[1];
			        gllAG[2]=(xCenterMassGeneral-xInitial);
			        gllATG[2]=gllATG[2]+gllAG[2];
			        gllAG[3]=yInitial-yCenterMassGeneral;
			        gllATG[3]=gllATG[3]+gllAG[3];
			        
			        bestXs.add(gllAG[0]);
	        		bestYs.add(gllAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[2]=partialWidth[2]+(gllAG[2]);
	        		partialHeight[2]=partialHeight[2]+(gllAG[3]);
	        		
	        		partialXSummatory[2]=partialXSummatory[2]+(gllAG[0]);
	        		partialYSummatory[2]=partialYSummatory[2]+(gllAG[1]);
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassGeneral)/2;
	        		y=yInitial-(yInitial-yCenterMassGeneral)/2;
	        		
	        		glrAG[0]=Math.abs(x-xCenterMassGeneral);
	        		glrATG[0]=glrATG[0]+glrAG[0];
			        glrAG[1]=Math.abs(y-yCenterMassGeneral);
			        glrATG[1]=glrATG[1]+glrAG[1];
			        glrAG[2]=(xInitial+widthElement)-xCenterMassGeneral;
			        glrATG[2]=glrATG[2]+glrAG[2];
			        glrAG[3]=yInitial-yCenterMassGeneral;
			        glrATG[3]=glrATG[3]+glrAG[3];
			        
			        bestXs.add(glrAG[0]);
	        		bestYs.add(glrAG[1]);
	        		
	        		XsQuadrant.add(x);
	        		YsQuadrant.add(y);
	        		
	        		partialWidth[3]=partialWidth[3]+(glrAG[2]);
	        		partialHeight[3]=partialHeight[3]+(glrAG[3]);
	        		
	        		partialXSummatory[3]=partialXSummatory[3]+(glrAG[0]);
	        		partialYSummatory[3]=partialYSummatory[3]+(glrAG[1]);
	        	}
	        }
	        
		}
		}
		//System.out.println("gulAT "+gulATG[0]+" "+gulATG[1]+" "+gulATG[2]+" "+gulATG[3]);
		//System.out.println("gurAT "+gurATG[0]+" "+gurATG[1]+" "+gurATG[2]+" "+gurATG[3]);
		//System.out.println("gllAT "+gllATG[0]+" "+gllATG[1]+" "+gllATG[2]+" "+gllATG[3]);
		//System.out.println("glrAT "+glrATG[0]+" "+glrATG[1]+" "+glrATG[2]+" "+glrATG[3]);
		symmetryValueGeneral=SubstractionSymmetries(gulATG,gllATG)+SubstractionSymmetries(gurATG,glrATG)+SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG)+SubstractionSymmetries(gulATG,glrATG)+SubstractionSymmetries(gurATG,gllATG);
		//symmetryValueGeneral=SubstractionSymmetries(gulATG,gurATG)+SubstractionSymmetries(gllATG,glrATG);
		//System.out.println("symmetryValue "+symmetryValueGeneral);
		
		
		//Symmetry Coins
		Iterator<BlockNode> itSymmetry2 = states.iterator();
		while(itSymmetry2.hasNext()){
			BlockNode elemento = itSymmetry2.next();
			Elements element=(Elements)objElemP.getFinalList().get(elemento.getIdElement());
			if(elemento.getType()==objElemP.getCoins() )
			{
			double xInitial = elemento.getX();
	        double yInitial= elemento.getY();
	        widthElement=element.getWidth();
	        heigthElement=element.getHeigth()+1;
	        
	        if((xInitial+widthElement)<=xCenterMassCoins )
	        {
	        	//block up left
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=heigthElement;
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	}
	        	
	        	//block low left
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=heigthElement;
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        	else
	        	{
	        		x=xInitial+(widthElement/2);
	        	
	        		//first block of the element (up left)
	        		//y=(yInitial-heigthElement)+(yCenterMassCoins-yInitial)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
	        		gulAC[1]=Math.abs(y-yCenterMassCoins);
	        		gulATC[1]=gulATC[1]+gulAC[1];
	        		gulAC[2]=widthElement;
	        		gulATC[2]=gulATC[2]+gulAC[2];
	        		gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gulATC[3]=gulATC[3]+gulAC[3];
	        		//gul.add(gulA);
	        	
	        		//second block of the element (low left)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
	        		gllAC[1]=Math.abs(y-yCenterMassCoins);
	        		gllATC[1]=gllATC[1]+gllAC[1];
	        		gllAC[2]=widthElement;
	        		gllATC[2]=gllATC[2]+gllAC[2];
	        		gllAC[3]=(yInitial-yCenterMassCoins);
	        		gllATC[3]=gllATC[3]+gllAC[3];
	        		//gll.add(gllA);
	        	}
	        }
	        else if(xInitial>=xCenterMassCoins )
	        {
	        	
	        	//block up right
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=heigthElement;
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gurA);
	        	}
	        	//block low right
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		x=xInitial+(widthElement/2);
	        		y=yInitial-(heigthElement/2);
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=heigthElement;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(glrA);
	        	}
	        	else
	        	{
	        		
	        		x=xInitial+(widthElement/2);
	        		
	        		//first block of the element (up right)
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
	        		gurAC[1]=Math.abs(y-yCenterMassCoins);
	        		gurATC[1]=gurATC[1]+gurAC[1];
	        		gurAC[2]=widthElement;
	        		gurATC[2]=gurATC[2]+gurAC[2];
	        		gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
	        		gurATC[3]=gurATC[3]+gurAC[3];
	        		//gur.add(gulA);
		        	
	        		//second block of the element  (low right)
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
	        		glrAC[1]=Math.abs(y-yCenterMassCoins);
	        		glrATC[1]=glrATC[1]+glrAC[1];
	        		glrAC[2]=widthElement;
	        		glrATC[2]=glrATC[2]+glrAC[2];
	        		glrAC[3]=yInitial-yCenterMassCoins;
	        		glrATC[3]=glrATC[3]+glrAC[3];
	        		//glr.add(gllA);
	        	}
	        	
	        }
	        else
	        {
	        	if(yInitial<=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=heigthElement;
			        gulATC[3]=gulATC[3]+gulAC[3];
			        //gul.add(gurA);
			        
			        //second block of the element (up right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=heigthElement;
			        gurATC[3]=gurATC[3]+gurAC[3];
			        //gur.add(gurA);
	        		
	        	}
	        	else if(yInitial-heigthElement>=yCenterMassCoins)
	        	{
	        		y=yInitial-(heigthElement/2);
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=heigthElement;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        //gll.add(gurA);
			        
			        //second block of the element (low right)
			        x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xCenterMassCoins-xInitial);
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=heigthElement;
			        glrATC[3]=glrATC[3]+glrAC[3];
			        //gur.add(gurA);
	        	}
	        	else
	        	{
	        		//falta implementar caso todos los cuadrantes
	        		//first block of the element (up left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gulAC[0]=Math.abs(x-xCenterMassCoins);
	        		gulATC[0]=gulATC[0]+gulAC[0];
			        gulAC[1]=Math.abs(y-yCenterMassCoins);
			        gulATC[1]=gulATC[1]+gulAC[1];
			        gulAC[2]=xCenterMassCoins-xInitial;
			        gulATC[2]=gulATC[2]+gulAC[2];
			        gulAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gulATC[3]=gulATC[3]+gulAC[3];
			        
	        		//second block of the element (up right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=(yInitial-heigthElement)+(yCenterMassCoins-(yInitial-heigthElement))/2;
	        		
	        		gurAC[0]=Math.abs(x-xCenterMassCoins);
	        		gurATC[0]=gurATC[0]+gurAC[0];
			        gurAC[1]=Math.abs(y-yCenterMassCoins);
			        gurATC[1]=gurATC[1]+gurAC[1];
			        gurAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        gurATC[2]=gurATC[2]+gurAC[2];
			        gurAC[3]=yCenterMassCoins-(yInitial-heigthElement);
			        gurATC[3]=gurATC[3]+gurAC[3];
	        		
	        		//first block of the element (low left)
	        		x=(xInitial+(xCenterMassCoins-xInitial)/2);
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		gllAC[0]=Math.abs(x-xCenterMassCoins);
	        		gllATC[0]=gllATC[0]+gllAC[0];
			        gllAC[1]=Math.abs(y-yCenterMassCoins);
			        gllATC[1]=gllATC[1]+gllAC[1];
			        gllAC[2]=(xCenterMassCoins-xInitial);
			        gllATC[2]=gllATC[2]+gllAC[2];
			        gllAC[3]=yInitial-yCenterMassCoins;
			        gllATC[3]=gllATC[3]+gllAC[3];
			        
	        		//second block of the element (low right)
	        		x=(xInitial+widthElement)-((xInitial+widthElement)-xCenterMassCoins)/2;
	        		y=yInitial-(yInitial-yCenterMassCoins)/2;
	        		
	        		glrAC[0]=Math.abs(x-xCenterMassCoins);
	        		glrATC[0]=glrATC[0]+glrAC[0];
			        glrAC[1]=Math.abs(y-yCenterMassCoins);
			        glrATC[1]=glrATC[1]+glrAC[1];
			        glrAC[2]=(xInitial+widthElement)-xCenterMassCoins;
			        glrATC[2]=glrATC[2]+glrAC[2];
			        glrAC[3]=yInitial-yCenterMassCoins;
			        glrATC[3]=glrATC[3]+glrAC[3];
	        	}
	        }
	        
		}
		}
		//System.out.println("gulAT "+gulATC[0]+" "+gulATC[1]+" "+gulATC[2]+" "+gulATC[3]);
		//System.out.println("gurAT "+gurATC[0]+" "+gurATC[1]+" "+gurATC[2]+" "+gurATC[3]);
		//System.out.println("gllAT "+gllATC[0]+" "+gllATC[1]+" "+gllATC[2]+" "+gllATC[3]);
		//System.out.println("glrAT "+glrATC[0]+" "+glrATC[1]+" "+glrATC[2]+" "+glrATC[3]);
		//symmetryValueCoins=SubstractionSymmetries(gulATC,gllATC)+SubstractionSymmetries(gurATC,glrATC)+SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC)+SubstractionSymmetries(gulATC,glrATC)+SubstractionSymmetries(gurATC,gllATC);
		symmetryValueCoins=SubstractionSymmetries(gulATC,gurATC)+SubstractionSymmetries(gllATC,glrATC);

		//System.out.println("symmetryValue "+symmetryValueCoins);
		
		symmetryValue=symmetryValueGeneral+symmetryValueCoins;
		return symmetryValue;
	}
	
	public double SubstractionSymmetries(double [] Arr1, double [] Arr2)
	{double summatory=0;
		for (int i=0;i< Arr1.length;i++)
		{
			
			
			summatory= summatory+Math.abs(Arr1[i]-Arr2[i]);
		}
		return summatory;
	}
	
	public long getCounterIDs()
    {
    	return counterIDs;
    }
	
	public TreeSet UpdateStringState(int typeElem,int widthElem,int heigthElem,int x, int y, TreeSet currentState) {
		
		
		String newSubStringCurrentState=Integer.toString(typeElem)+"|"+Integer.toString(widthElem)+"|"+Integer.toString(heigthElem)+"|"+Integer.toString(x)+"|"+Integer.toString(y);		
		TreeSet copycurrentState=(TreeSet)currentState.clone(); 
		copycurrentState.add(newSubStringCurrentState);
		
		return copycurrentState;
	}
	
    public Hashtable UpdateTranspositionTable(Hashtable hTable, TreeSet currentState) {
		// TODO Auto-generated method stub
    	TreeSet copycurrentState=(TreeSet)currentState.clone(); 
    	hTable.put(copycurrentState, "1");
    	return hTable;
	}
    public ArrayList FormattingElementsSingle(ArrayList bestStates, int maxObjLeft)
    {

    	
		Iterator<BlockNode> it = (bestStates).iterator();
		int counterNodes=0;
		while(it.hasNext()){
			BlockNode elemento = it.next();
			BlockNode elementoNew=new BlockNode(elemento);
			elementoNew.setX((elementoNew.getX()-maxObjLeft)+1);		
			(bestStates).set(counterNodes, elementoNew);
			counterNodes++;
		}
    	return bestStates;
    }
    public Branch FormattingElementsTopK(Branch objBranch, int maxObjLeft)
    {

    	
		Iterator<BlockNode> it = (objBranch.getStates()).iterator();
		int counterNodes=0;
		while(it.hasNext()){
			BlockNode elemento = it.next();
			BlockNode elementoNew=new BlockNode(elemento);
			elementoNew.setX((elementoNew.getX()-maxObjLeft)+1);		
			(objBranch.getStates()).set(counterNodes, elementoNew);
			counterNodes++;
		}
    	return objBranch;
    }
    public int RandomCoordenateGenerator(Random random, int maxLef,int  maxRi)
    {
    	return maxLef+random.nextInt(maxRi-maxLef);
    }
    public void setWparamether(double wParamether)
    {
    	this.wParamether=wParamether;
    }
}

// End HelloJGraphT.java