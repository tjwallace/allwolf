package allwolf.agent;

import java.util.ArrayList;

import allwolf.board.Board;
import allwolf.math.Point;

abstract public class Agent extends Thread
{
	protected Board board;

	protected Point position;

	protected int sightRange;

	public Agent()
	{
		this(null, new Point(-1, -1), 0);
	}

	public Agent(Board board, Point pos, int sightRange)
	{
		this.board = board;
		this.position = pos;
		this.sightRange = sightRange;
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

	public int getSightRange()
	{
		return sightRange;
	}

	protected ArrayList<Agent> getVisibleAgents()
	{
		ArrayList<Agent> agents = new ArrayList<Agent>();

		for (int x = position.x - sightRange; x <= position.x + sightRange; x++)
		{
			for (int y = position.y - sightRange; y <= position.y + sightRange; y++)
			{
				if (!board.isValidPos(new Point(x, y)) || position.equals(x, y))
					continue;

				Agent a = board.getAgent(new Point(x, y));
				if (a != null)
					agents.add(a);
			}
		}

		return agents;
	}

	protected boolean isValidMove(Point dest)
	{
		return board.isValidPos(dest)
				&& (dest.isAbove(position) || dest.isBelow(position) || dest.isLeftOf(position) || dest.isRightOf(position));
	}

	/**
	 * Calculate next move and execute
	 * 
	 * @return <code>true</code> if the thread should continue, <code>false</code> otherwise.
	 */
	protected boolean step()
	{
		try
		{
			board.moveAgent(this, nextPos());
		} catch (Exception e)
		{
			System.err.println("Caught exception: " + e);
		}

		return canContinue();
	}

	abstract protected Point nextPos();

	abstract protected boolean canContinue();

	@Override
	public void run()
	{
		boolean run = true;
		while (run)
			run = step();
	}

}
