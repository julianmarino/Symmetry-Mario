package graphBuilder;

public class BlockNode {

	private int typeElement;
	private long id;
	private double x;
	private double y;
	private int idElement;
	public BlockNode(int x,int y, long id, int idElementToPlace, int idElement){
		this.x=x;
		this.y=y;
		this.id=id;
		this.typeElement=idElementToPlace;
		this.idElement=idElement;
	};
	public BlockNode(double x,double y, long id, int idElementToPlace, int idElement){
		this.x=x;
		this.y=y;
		this.id=id;
		this.typeElement=idElementToPlace;
		this.idElement=idElement;
	};
	public BlockNode(BlockNode obj2)
	{
		this.x=obj2.x;
		this.y=obj2.y;
		this.id=obj2.id;
		this.typeElement=obj2.typeElement;
		this.idElement=obj2.idElement;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public long getID()
	{
		return id;
	}
	public int getType()
	{
		return typeElement;
	}
	public int getIdElement() {
		return idElement;
	}
}
