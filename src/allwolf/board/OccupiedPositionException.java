package allwolf.board;

import allwolf.agent.Agent;
import allwolf.math.Point;

public class OccupiedPositionException extends PositionException
{
	private static final long serialVersionUID = 1L;
	
	public OccupiedPositionException(Agent agent, Point position)
	{
		super(agent+" already present at this position", position);
	}
}
