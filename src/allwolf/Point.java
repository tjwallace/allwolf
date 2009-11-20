package allwolf;

public final class Point
{
	public int x;
	public int y;
	
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
	 * Check f the point is to the left of a given point
	 * @param p Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isLeft(Point p)
	{
		return (x+1) == p.x && y == p.y;
	}
	
	/**
	 * Check f the point is to the right of a given point
	 * @param p Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isRight(Point p)
	{
		return (x-1) == p.x && y == p.y;
	}
	
	public boolean equals(int x, int y)
	{
		return this.x == x && this.y == y;
	}
}
