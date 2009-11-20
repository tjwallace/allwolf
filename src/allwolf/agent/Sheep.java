package allwolf.agent;

import allwolf.Board;
import allwolf.Point;

public final class Sheep extends Agent
{

	public Sheep()
	{
		this(null, null);
	}

	public Sheep(Board board, Point pos)
	{
		super(board, pos, 2);
	}

	@Override
	protected Point nextPos()
	{
		return null;
	}

	@Override
	protected boolean canContinue()
	{
		return false;
	}

}
