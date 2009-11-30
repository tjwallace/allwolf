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
		List<Agent> sheep = filterSheep(getAgentsInSight());
		
		// find closest sheep
		Agent closest = getClosestAgent(sheep);
		
		// move towards closest sheep
		
		
		return null;
	}

}
