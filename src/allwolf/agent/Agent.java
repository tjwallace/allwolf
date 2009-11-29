package allwolf.agent;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import allwolf.board.Board;
import allwolf.math.Point;

abstract public class Agent extends Thread
{
	protected CyclicBarrier barrier;
	protected Board board;
	protected Point position;
	protected int sight;
	protected int speed;

	public Agent(CyclicBarrier barrier, int sight, int speed)
	{
		this.barrier = barrier;
		this.sight = sight;
		this.speed = speed;
	}

	public final void setBoard(Board board)
	{
		this.board = board;
	}

	public final Point getPos()
	{
		return position;
	}

	public final void setPos(Point pos)
	{
		this.position = pos;
	}

	public final int getSightRange()
	{
		return sight;
	}

	protected ArrayList<Agent> getVisibleAgents()
	{
		ArrayList<Agent> agents = new ArrayList<Agent>();

		for (int x = position.x - sight; x <= position.x + sight; x++)
		{
			for (int y = position.y - sight; y <= position.y + sight; y++)
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
		return board.isValidPos(dest) && (dest.isAbove(position) || dest.isBelow(position) || dest.isLeftOf(position) || dest.isRightOf(position));
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
			barrier.await();
		}
		catch (Exception e)
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
