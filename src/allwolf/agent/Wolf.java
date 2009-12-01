package allwolf.agent;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import allwolf.math.Point;

public final class Wolf extends Agent
{
	public static final int SIGHT = 4;
	public static final int SPEED = 1;

	public Wolf(CyclicBarrier barrier, Point position)
	{
		super(barrier, position, SIGHT, SPEED);
	}

	@Override
	protected Point nextPosition() throws MoveException
	{
		// find closest sheep
		List<Agent> sheep = filterSheep(getAgentsInSight());
		
		if (sheep.size() > 0)
			return calculateNextPosition(getClosestAgent(sheep).position);
		else
			return randomNextPosition();
	}
	
	private Agent getClosestAgent(List<Agent> agents)
	{
		Agent closest = agents.get(0);
		for (Agent a : agents)
		{
			if (position.distanceTo(a.position) < position.distanceTo(closest.position))
				closest = a;
		}
		
		return closest;
	}
	
	@Override
	public String toString()
	{
		return "Wolf @ "+position;
	}

}
