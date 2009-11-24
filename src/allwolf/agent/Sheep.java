package allwolf.agent;

import allwolf.board.Board;
import allwolf.math.Point;

public final class Sheep extends Agent
{
	public static final int SIGHT_RANGE = 2;

	public Sheep()
	{
		this(null, null);
	}

	public Sheep(Board board, Point pos)
	{
		super(board, pos, SIGHT_RANGE);
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
