package allwolf.agent;

import allwolf.Point;
import allwolf.board.Board;

public final class Wolf extends Agent
{
	public static final int SIGHT_RANGE = 4;
	
	public Wolf()
	{
		this(null, null);
	}

	public Wolf(Board board, Point pos)
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
