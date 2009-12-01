package allwolf.agent;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import allwolf.math.Point;

public final class Sheep extends Agent
{
	public static final int SIGHT = 2;
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
			int xDir = Integer.signum(avg.x - position.x);
			int yDir = Integer.signum(avg.y - position.y);
			
			return calculateMove(xDir, yDir);
		}
		else
			return randomMove();
	}

}
