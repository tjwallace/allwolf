package allwolf.agent;

import allwolf.Board;
import allwolf.Point;

public final class Wolf extends Agent
{

	public Wolf()
	{
		this(null, null);
	}

	public Wolf(Board board, Point pos)
	{
		super(board, pos, 4);
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
