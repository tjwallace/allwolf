package allwolf.math;

public class Dimension
{
	public final int x;
	
	public Dimension(int x)
	{
		this.x = x;
	}
	
	public boolean equals(int x)
	{
		return equals(new Dimension(x));
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Dimension)
			return ((Dimension) o).x == x;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ")";
	}
}
