package allwolf;

public final class Point
{
	public final int x;
	public final int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Check if the point is above a given point
	 * @param p Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isAbove(Point p)
	{
		return x == p.x && (y+1) == p.y;
	}
	
	/**
	 * Check if the point is below a given point
	 * @param p Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isBelow(Point p)
	{
		return x == p.x && (y-1) == p.y;
	}
	
	/**
	 * Check if the point is to the left of a given point
	 * @param p Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isLeftOf(Point p)
	{
		return (x+1) == p.x && y == p.y;
	}
	
	/**
	 * Check if the point is to the right of a given point
	 * @param p Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isRightOf(Point p)
	{
		return (x-1) == p.x && y == p.y;
	}
	
	public int distanceTo(Point p)
	{
		return Math.abs(x - p.x) + Math.abs(y - p.y);
	}
	
	public boolean equals(int x, int y)
	{
		return equals(new Point(x, y));
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Point)
		{
			Point p = (Point)obj;
			return x == p.x && y == p.y;
		}
		else
			return false;
		
	}
}
