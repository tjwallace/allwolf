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
		List<Agent> wolves = filterWolves(getAgentsInSight());
		
		
		return null;
	}

}
