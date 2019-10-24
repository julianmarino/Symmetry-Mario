package beauty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SingleElement {

	private int heigth;
	private int width;
	private String typeElement;	

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}
	
	public SingleElement(int heigth, int width, String typeElement)
	{
		this.heigth=heigth;
		this.width=width;
		this.typeElement=typeElement;
	}
	public ArrayList sortElementsbyType(ArrayList<Elements> bestElement)
	{
		Collections.sort(bestElement, new Comparator<Elements>() {
	        public int compare(Elements object1, Elements object2) {
	            return Integer.compare(object1.getTypeElem(), object2.getTypeElem());
	        }
	    });
	
		return bestElement;
	}
	public ArrayList sortElementsbyAreas(ArrayList<Elements> bestElement)
	{
		Collections.sort(bestElement, new Comparator<Elements>() {
	        public int compare(Elements object1, Elements object2) {
	            return Integer.compare(object1.getWidth()*(object1.getHeigth()+1), object2.getWidth()*(object2.getHeigth()+1));
	        }
	    });
	
		return bestElement;
	}
	public int getHeigth() {
		return heigth;
	}
	public int getWidth() {
		return width;
	}
	public String getTypeElement() {
		return typeElement;
	}
}
