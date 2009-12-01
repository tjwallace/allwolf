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
	protected Point nextPos()
	{
		List<Point> points = getPoints(filterWolves(getAgentsInSight()));
		if (points.size() != 0)
		{
			// get average location of the visible wolves
			Point avg = Point.getAverage(points);
			
			// move away from it
			int xDir = Integer.signum(position.x - avg.x);
			int yDir = Integer.signum(position.y - avg.y);
			
			return calculateMove(xDir, yDir);
		}
		else
			return randomMove();
	}
	
	@Override
	public String toString()
	{
		return "Sheep @ "+position;
	}

}
