package allwolf.board;

import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import allwolf.agent.Agent;
import allwolf.math.Area;
import allwolf.math.Point;

public class Board extends Observable
{
	private Area size;
	private ConcurrentMap<Point, Agent> map;

	public Board(Area size)
	{
		super();
		this.size = size;
		map = new ConcurrentHashMap<Point, Agent>();
	}

	public Area getSize()
	{
		return size;
	}

	public boolean isValidPos(Point pos)
	{
		return size.contains(pos);
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
		return "Board "+size;
	}
	
	public Agent getAgent(Point pos)
	{
		return map.get(pos);
	}
	
	public void addAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPos();

		if (!isValidPos(pos))
			throw new PositionException("Unit's position is off the map "+size, pos);

		agent.setBoard(this);
		map.put(pos, agent);

		notifyObservers();
	}

	public synchronized void moveAgent(Agent agent, Point dest) throws PositionException
	{
		Point src = agent.getPos();

		isValidMove(src, dest);

		map.remove(src);
		map.put(dest, agent);
		agent.setPos(dest);

		notifyObservers();
	}
}