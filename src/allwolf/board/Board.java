package allwolf.board;

import java.util.Observable;

import allwolf.MoveException;
import allwolf.Point;
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
	
	public abstract void run();

	public abstract void moveAgent(Agent agent, Point dest) throws MoveException;

	public abstract void addAgent(Agent agent) throws Exception;

	public abstract Agent getAgent(Point pos);

}