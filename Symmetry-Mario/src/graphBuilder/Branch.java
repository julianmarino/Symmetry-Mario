package graphBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Branch {
	
	private double  heuristicValue;
	private ArrayList states;
	
	public Branch(double heuristicValue, ArrayList states )
	{
		this.heuristicValue=heuristicValue;
		this.states=states;
	}
	public Branch()
	{
		
	}

	public ArrayList sortBranches(ArrayList<Branch> bestBranches)
	{
		Collections.sort(bestBranches, new Comparator<Branch>() {
	        public int compare(Branch object1, Branch object2) {
	            return Double.compare(object1.getHeuristicValue(), object2.getHeuristicValue());
	        }
	    });
	
		return bestBranches;
	}
	public double getHeuristicValue() {
		return heuristicValue;
	}
	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	public ArrayList getStates() {
		return states;
	}
	public void setStates(ArrayList states) {
		this.states = states;
	}
	
}
