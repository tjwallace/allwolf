package allwolf.math;


public final class Point implements Comparable<Point>
{
	public int x;
	public int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(Point position)
	{
		this.x = position.x;
		this.y = position.y;
	}
	
	public Point translate(int x, int y)
	{
		translateX(x);
		translateY(y);
		return this;
	}
	
	public Point translateX(int x)
	{
		this.x += x;
		return this;
	}

	public Point translateY(int y)
	{
		this.y += y;
		return this;
	}

	public int xDistance(Point p)
	{
		return Math.abs(x - p.x);
	}
	
	public int yDistance(Point p)
	{
		return Math.abs(y - p.y);
	}
	
	public int distanceTo(Point p)
	{
		return xDistance(p) + yDistance(p);
	}
	
	public int area(Point p)
	{
		return xDistance(p) * yDistance(p);
	}
	
	@Override
	public int compareTo(Point p)
	{
		if (equals(p))
			return 0;
		else if (y == p.y)
			return (x > p.x) ? 1 : -1;
		else
			return (y > p.y) ? 1 : -1;
	}
	
	public boolean equals(int x, int y)
	{
		return x == this.x && y == this.y;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Point)
			return ((Point) o).x == x && ((Point) o).y == y;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 1;
		hash = hash * 31 + y;
		hash = hash * 31 + x;
		return hash;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
