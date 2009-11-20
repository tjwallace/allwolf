package allwolf.agent;

import allwolf.Board;
import allwolf.Point;

abstract public class Agent extends Thread
{
	protected Board board;
	
	protected Point position;
	
	public Agent()
	{
		this(null, new Point(-1, -1));
	}
	
	public Agent(Board board, Point pos)
	{
		this.board = board;
		this.position = pos;
	}

	public final Board getBoard()
	{
		return board;
	}

	public final void setBoard(Board board)
	{
		this.board = board;
	}

	public Point getPos()
	{
		return position;
	}
	
	public void setPos(Point pos)
	{
		this.position = pos;
	}
	
	protected boolean isValidMove(Point dest)
	{
		if (!board.isValidPos(dest))
			return false;
		
		if (dest.isAbove(position))
			return true; // up
		
		if (dest.isBelow(position))
			return true; // down
		
		if (dest.isLeft(position))
			return true; // left
		
		if (dest.isRight(position))
			return true; // right
		
		return false;
	}
	
	/**
	 * Calculate next move and execute
	 * @return <code>true</code> if the thread should continue, <code>false</code> otherwise.
	 */
	protected boolean step()
	{
		try
		{
			board.moveAgent(this, nextPos());
		}
		catch (Exception e)
		{
			
		}
		return true;
	}
	
	abstract protected Point nextPos();

	@Override
	public void run()
	{
		boolean run = true;
		while (run)
			run = step();
	}
	
	
}
