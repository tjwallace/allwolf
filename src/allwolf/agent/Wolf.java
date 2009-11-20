package allwolf.agent;

import allwolf.Board;
import allwolf.Point;

public final class Wolf extends Agent
{

	public Wolf()
	{
		
	}

	public Wolf(Board board, Point pos)
	{
		super(board, pos);
	}

	@Override
	protected Point nextPos()
	{
		return null;
	}

}
