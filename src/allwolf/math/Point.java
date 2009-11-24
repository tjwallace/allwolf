package allwolf.math;

public final class Point extends Dimension2D implements Comparable<Point>
{
	public Point(int x, int y)
	{
		super(x, y);
	}

	/**
	 * Check if the point is above a given point
	 * 
	 * @param p
	 *            Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isAbove(Point p)
	{
		return x == p.x && (y + 1) == p.y;
	}

	/**
	 * Check if the point is below a given point
	 * 
	 * @param p
	 *            Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isBelow(Point p)
	{
		return x == p.x && (y - 1) == p.y;
	}

	/**
	 * Check if the point is to the left of a given point
	 * 
	 * @param p
	 *            Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isLeftOf(Point p)
	{
		return (x + 1) == p.x && y == p.y;
	}

	/**
	 * Check if the point is to the right of a given point
	 * 
	 * @param p
	 *            Point of reference
	 * @return {@link Boolean}
	 */
	public boolean isRightOf(Point p)
	{
		return (x - 1) == p.x && y == p.y;
	}

	public int distanceTo(Point p)
	{
		return Math.abs(x - p.x) + Math.abs(y - p.y);
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
}
