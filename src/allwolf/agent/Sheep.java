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
		// get average location of the visible wolves
		List<Point> points = getPoints(filterWolves(getAgentsInSight()));
		Point avg = Point.getAverage(points);
		
		// move away from it
		Point dest = new Point(position);
		int range = speed;
		int xDir = Integer.signum(avg.x - position.x);
		int yDir = Integer.signum(avg.y - position.y);
		while (range > 0)
		{
			// move x
			if (range > 0 && xDir != 0)
			{
				dest.translateX(xDir);
				range--;
			}
			
			// move y
			if (range > 0 && yDir != 0)
			{
				dest.translateY(yDir);
				range--;
			}
		}
		
		return dest;
	}

}
