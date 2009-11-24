package allwolf.math;

public class Dimension2D extends Dimension
{
	public final int y;
	
	public Dimension2D(int x, int y)
	{
		super(x);
		this.y = y;
	}
	
	public boolean equals(int x, int y)
	{
		return equals(new Dimension2D(x, y));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Dimension2D)
			return super.equals(o) && ((Dimension2D) o).y == y;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
