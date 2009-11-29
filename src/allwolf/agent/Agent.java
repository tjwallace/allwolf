package allwolf.agent;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import allwolf.board.Board;
import allwolf.board.PositionException;
import allwolf.math.Area;
import allwolf.math.Point;

abstract public class Agent extends Thread
{
	private CyclicBarrier barrier;
	protected Board board;
	protected Point position;
	protected int sight;
	protected int speed;
	
	private volatile boolean kill;

	public Agent(CyclicBarrier barrier, Point position, int sight, int speed)
	{
		this.barrier = barrier;
		this.position = position;
		this.sight = sight;
		this.speed = speed;
		this.kill = false;
	}

	public final void setBoard(Board board)
	{
		this.board = board;
	}

	public final Point getPosition()
	{
		return position;
	}

	public final void setPosition(Point position)
	{
		this.position = position;
	}
	
	public final int sight()
	{
		return sight;
	}
	
	public final int speed()
	{
		return speed;
	}
	
	public synchronized void kill()
	{
		kill = true;
	}
	
	protected Area getVision()
	{
		Point tl = new Point(position.x - sight, position.y - sight);
		Point br = new Point(position.x + sight, position.y + sight);
		return new Area(tl, br);
	}
	
	protected List<Agent> getAgentsInSight()
	{
		return board.getAgents(getVision());
	}
	
	public boolean isWolf()
	{
		return this instanceof Wolf;
	}
	
	public boolean isSheep()
	{
		return !isWolf();
	}

	protected boolean isValidMove(Point dest)
	{
		return position.distanceTo(dest) <= speed;
	}

	/**
	 * Calculate next move and execute
	 * 
	 * @return <code>true</code> if the thread should continue, <code>false</code> otherwise.
	 */
	private boolean step()
	{
		boolean hasMoved = false;
		while(!hasMoved)
		{
			try
			{
				board.moveAgent(this, nextPos());
				hasMoved = true;
				barrier.await();
			}
			catch (Exception e)
			{
				System.err.println("Exception in step()...");
				System.err.println("  "+e);
			}
		}

		return !kill;
	}

	protected abstract Point nextPos();

	@Override
	public void run()
	{
		boolean run = true;
		while (run)
			run = step();
		
		try
		{
			board.removeAgent(this);
		}
		catch (PositionException e)
		{
			System.err.println("Agent couldn't remove themselves from the board");
			e.printStackTrace();
		}
	}
	
	protected static List<Agent> filterWolves(List<Agent> agents)
	{
		for(Agent a : agents)
		{
			if (a instanceof Sheep)
				agents.remove(a);
		}
		
		return agents;
	}
	
	protected static List<Agent> filterSheep(List<Agent> agents)
	{
		for(Agent a : agents)
		{
			if (a instanceof Wolf)
				agents.remove(a);
		}
		
		return agents;
	}

}
