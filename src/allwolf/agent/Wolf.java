package allwolf.agent;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import allwolf.math.Point;

public final class Wolf extends Agent
{
	public static final int SIGHT = 4;
	public static final int SPEED = 2;

	public Wolf(CyclicBarrier barrier, Point position)
	{
		super(barrier, position, SIGHT, SPEED);
	}

	@Override
	protected Point nextPos()
	{
		// find closest sheep
		List<Agent> sheep = filterSheep(getAgentsInSight());
		Agent closest = getClosestAgent(sheep);
		
		// move towards closest sheep
		Point dest = new Point(position);
		int range = speed;
		int xDir = Integer.signum(position.x - closest.getPosition().x);
		int yDir = Integer.signum(position.y - closest.getPosition().y);
		while (range > 0)
		{
			// move x
			if (range > 0 && xDir != 0)
			{
				dest.translateX(xDir);
				range--;
			}
			
			if (dest.equals(closest.position))
				break;
			
			// move y
			if (range > 0 && yDir != 0)
			{
				dest.translateY(yDir);
				range--;
			}
			
			if (dest.equals(closest.position))
				break;
		}
		
		
		
		return dest;
	}

}
