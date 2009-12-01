package allwolf.board;

import allwolf.math.Point;

public class EmptyPositionException extends PositionException
{
	private static final long serialVersionUID = 1L;

	public EmptyPositionException(Point position)
	{
		super("No agent at this position", position);
	}

}
