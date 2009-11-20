package allwolf;

import java.util.Observable;

import allwolf.agent.Agent;

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

	@Override
	public String toString()
	{
		return "Board ("+sizeX+","+sizeY+")";
	}
	
	abstract public void run();

	abstract public void moveAgent(Agent agent, Point dest) throws MoveException;

	abstract public void addAgent(Agent agent) throws Exception;

	abstract public Agent getAgent(Point pos);

}