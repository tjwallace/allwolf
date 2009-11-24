package allwolf.board;

import java.util.Observable;

import allwolf.PositionException;
import allwolf.agent.Agent;
import allwolf.math.Point;

public abstract class Board extends Observable
{
	protected int sizeX;

	protected int sizeY;

	protected boolean isRunning;

	public Board(int sizeX, int sizeY)
	{
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.isRunning = false;
	}

	public int getSizeX()
	{
		return sizeX;
	}

	public int getSizeY()
	{
		return sizeY;
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public boolean isValidPos(Point pos)
	{
		return !(pos.x < 0 || pos.x >= sizeX) && !(pos.y < 0 || pos.y >= sizeY);
	}

	protected void isValidMove(Point src, Point dest) throws PositionException
	{
		if (!isValidPos(src))
			throw new PositionException("Agent source position is not valid", src);

		if (!isValidPos(dest))
			throw new PositionException("Agent destination position is not valid", dest);
	}

	@Override
	public String toString()
	{
		return "Board (" + sizeX + "," + sizeY + ")";
	}

	public void run()
	{
		if (isRunning)
			return;
		
		Boolean success = execRun();
		
		isRunning = success;
	}
	
	protected abstract boolean execRun();

	public abstract void moveAgent(Agent agent, Point dest) throws PositionException;

	public abstract void addAgent(Agent agent) throws PositionException;

	public abstract Agent getAgent(Point pos);

}