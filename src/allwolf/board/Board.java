package allwolf.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	
	public Collection<Agent> getAgents()
	{
		return map.values();
	}
	
	public List<Agent> getAgents(Class<?> clazz)
	{
		List<Agent> agents = new ArrayList<Agent>();
		
		for (Agent a : map.values())
		{
			if (a.getClass().equals(clazz))
				agents.add(a);
		}
		
		return agents;
	}
	
	public List<Agent> getAgents(Area area)
	{
		List<Agent> agents = new ArrayList<Agent>();
		
		for (Agent a : map.values())
		{
			if (area.contains(a.getPosition()))
				agents.add(a);
		}
		
		return agents;
	}
	
	public Point getRandomPosition()
	{
		int x = (int) (size.x() * Math.random());
		int y = (int) (size.y() * Math.random());
		return new Point(x, y);
	}

	public boolean isValidPos(Point pos)
	{
		return size.contains(pos);
	}

	protected void isValidMove(Point src, Point dest) throws PositionException
	{
		if (!isValidPos(src))
			throw new PositionException("Agent source position is not valid", src);
		
		if (map.get(src) == null)
			throw new PositionException("No agent at this position", src);

		if (!isValidPos(dest))
			throw new PositionException("Agent destination position is not valid", dest);
	}
	
	public Agent getAgent(Point pos)
	{
		return map.get(pos);
	}
	
	public void addAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPosition();

		if (!isValidPos(pos))
			throw new PositionException("Unit's position is off the map "+size, pos);

		if (map.putIfAbsent(pos, agent) != null)
			throw new PositionException("Agent already present at this position", pos);
		
		agent.setBoard(this);

		notifyObservers();
	}

	public synchronized void moveAgent(Agent agent, Point dest) throws PositionException
	{
		Point src = agent.getPosition();

		isValidMove(src, dest);

		map.remove(src);
		map.put(dest, agent);
		agent.setPosition(dest);

		notifyObservers();
	}
	
	public synchronized void removeAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPosition();
		
		if (!isValidPos(pos))
			throw new PositionException("Invalid position to remove", pos);
		
		if (map.get(pos) == null)
			throw new PositionException("No agent at this position", pos);
		
		map.remove(agent.getPosition());
	}
	
	@Override
	public String toString()
	{
		return "Board "+size;
	}
}