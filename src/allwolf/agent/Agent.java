package allwolf.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CyclicBarrier;

import allwolf.board.Board;
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
	
	public final void kill()
	{
		kill = true;
	}
	
	public Area getVision()
	{
		Point tl = new Point(position.x - sight, position.y - sight);
		Point br = new Point(position.x + sight, position.y + sight);
		return new Area(tl, br);
	}
	
	public List<Agent> getAgentsInSight()
	{
		return board.getAgents(getVision());
	}
	
	public boolean isWolf()
	{
		return this instanceof Wolf;
	}
	
	public boolean isSheep()
	{
		return this instanceof Sheep;
	}
	
	protected Point randomNextPosition() throws MoveException
	{
		// pick random directions
		Point goal;
		int xDir = 0, yDir = 0;
		while (xDir == 0 && yDir == 0)
		{
			goal = board.getRandomPosition();
			xDir = Integer.signum(goal.x - position.x);
			yDir = Integer.signum(goal.y - position.y);
		}
		
		return calculateNextPosition(xDir, yDir);
	}
	
	protected Point calculateNextPosition(Point goal) throws MoveException
	{
		int xDir = Integer.signum(goal.x - position.x);
		int yDir = Integer.signum(goal.y - position.y);
		
		return calculateNextPosition(xDir, yDir, goal);
	}
	
	protected Point calculateNextPosition(int xDir, int yDir) throws MoveException
	{
		return calculateNextPosition(xDir, yDir, null);
	}
	
	private Point calculateNextPosition(int xDir, int yDir, Point goal) throws MoveException
	{
		if (xDir == 0 && yDir == 0)
			throw new MoveException();
		
		Stack<Point> moves = new Stack<Point>();
		moves.push(new Point(position));
		
		int range = speed;
		Point next = null;
		while (range > 0)
		{
			if (Math.random() > 0.5 && xDir != 0) // move x
			{
				next = new Point(moves.peek());
				if (board.isValidPos(next.translateX(xDir)))
				{
					moves.push(next);
					range--;
				}
				else 
					xDir = (Math.random() > 0.5) ? 1 : -1;
			}
			else if (yDir != 0) // move y
			{
				next = new Point(moves.peek());
				if (board.isValidPos(next.translateY(yDir)))
				{
					moves.push(next);
					range--;
				}
				else
					yDir = (Math.random() > 0.5) ? 1 : -1;
			}
			
			if (goal != null)
			{
				if (moves.peek().equals(goal))
					break;
				
				if (moves.peek().x == goal.x)
					xDir = 0;
				else if (moves.peek().y == goal.y)
					yDir = 0;
			}
		}			
		
		return moves.peek();
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
				Point next = nextPosition();
				barrier.await();
				
				if (!kill)
					board.moveAgent(this, next);
				
				hasMoved = true;
			}
			catch (Exception e)
			{
				System.err.println(this+" in step() > "+e);
			}
		}

		return !kill;
	}

	protected abstract Point nextPosition() throws MoveException;

	@Override
	public void run()
	{
		boolean run = true;
		while (run)
		{
			run = step();
		}
			
	}
	
	public static List<Agent> filterWolves(List<Agent> agents)
	{
		List<Agent> wolves = new ArrayList<Agent>();
		for(Agent a : agents)
		{
			if (a instanceof Wolf)
				wolves.add(a);
		}
		
		return wolves;
	}
	
	public static List<Agent> filterSheep(List<Agent> agents)
	{
		List<Agent> sheep = new ArrayList<Agent>();
		for(Agent a : agents)
		{
			if (a instanceof Sheep)
				sheep.add(a);
		}
		
		return sheep;
	}
	
	public static List<Point> getPoints(List<Agent> agents)
	{
		List<Point> points = new ArrayList<Point>();
		
		for (Agent a : agents)
			points.add(a.position);
		
		return points;
	}
}
