package allwolf.math;

public final class Area extends Dimension2D
{
	public Area(int xSize, int ySize)
	{
		super(xSize, ySize);
	}
	
	public int area()
	{
		return x * y;
	}
	
	public boolean contains(Point p)
	{
		return !(p.x < 0 || p.x >= x) && !(p.y < 0 || p.y >= y);
	}
}
