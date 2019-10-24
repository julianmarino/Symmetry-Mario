package beauty;

public class Elements {

	private int idElem;
	private int typeElem;
	private int width;
	private int heigth;
	
	public Elements()
	{
		
	}
	public Elements cloneElements(Elements objReceived, int newId)
	{
		Elements objNewElement=new Elements();
		objNewElement.idElem=newId;
		objNewElement.typeElem=objReceived.typeElem;
		objNewElement.width=objReceived.width;
		objNewElement.heigth=objReceived.heigth;
		return objNewElement;
	}

	public int getIdElem() {
		return idElem;
	}

	public void setIdElem(int idElem) {
		this.idElem = idElem;
	}

	public int getTypeElem() {
		return typeElem;
	}

	public void setTypeElem(int typeElem) {
		this.typeElem = typeElem;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	
	
}
