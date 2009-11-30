package allwolf.math;

import java.util.Collection;

public final class Point implements Comparable<Point>
{
	public final int x;
	public final int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point getAbove()
	{
		return new Point(x, y - 1);
	}
	
	public Point getBelow()
	{
		return new Point(x, y + 1);
	}
	
	public Point getLeft()
	{
		return new Point(x - 1, y);
	}
	
	public Point getRight()
	{
		return new Point(x + 1, y);
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
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
	
	public static Point getAverage(Collection<Point> points)
	{
		int x = 0, y = 0;
		
		for (Point p : points)
		{
			x += p.x;
			y += p.y;
		}
		
		return new Point(x / points.size(), y / points.size());
	}
}
