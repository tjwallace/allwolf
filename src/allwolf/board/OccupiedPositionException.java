package allwolf.board;

import allwolf.math.Point;

public class OccupiedPositionException extends PositionException
{
	private static final long serialVersionUID = 1L;
	
	public OccupiedPositionException(Point position)
	{
		super("Agent already present at this position", position);
	}
}
