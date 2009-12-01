package allwolf.agent;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import allwolf.math.Point;

public final class Sheep extends Agent
{
	public static final int SIGHT = 3;
	public static final int SPEED = 1;

	public Sheep(CyclicBarrier barrier, Point position)
	{
		super(barrier, position, SIGHT, SPEED);
	}

	@Override
	protected Point nextPosition() throws MoveException
	{
		List<Point> points = getPoints(filterWolves(getAgentsInSight()));
		if (points.size() != 0)
		{
			// get average location of the visible wolves		
			// move away from it			
			Point dir = calcDirection(points);
			
			// make sure we have options
			if (dir.x != 0 && dir.y != 0)
				return calculateNextPosition(dir.x, dir.y);
		}
		
		return randomNextPosition();
	}
	
	@Override
	public String toString()
	{
		return "Sheep @ "+position;
	}
	
	private Point calcDirection(List<Point> points)
	{
		double x = 0, y = 0;
		
		for (Point p : points)
		{
			x += p.x;
			y += p.y;
		}
		
		int xDir = (int) Math.signum(position.x - x / points.size());
		int yDir = (int) Math.signum(position.y - y / points.size());
		
		return new Point(xDir, yDir);
	}

}
