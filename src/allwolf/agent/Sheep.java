package allwolf.agent;

import allwolf.Board;
import allwolf.Point;

public final class Sheep extends Agent
{

	public Sheep()
	{
		
	}

	public Sheep(Board board, Point pos)
	{
		super(board, pos);
	}

	@Override
	protected Point nextPos()
	{
		return null;
	}

}
