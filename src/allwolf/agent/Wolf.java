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
	protected Point nextPos()
	{
		// find closest sheep
		List<Agent> sheep = filterSheep(getAgentsInSight());
		
		if (sheep.size() > 0)
		{
			// move towards closest sheep
			Point goal = getClosestAgent(sheep).position;
			int xDir = Integer.signum(goal.x - position.x);
			int yDir = Integer.signum(goal.y - position.y);

			return calculateMove(xDir, yDir, goal);
		}
		else
			return randomMove();
		
	}
	
	private Agent getClosestAgent(List<Agent> agents)
	{
		if (agents.size() == 0)
			return null;
		
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
