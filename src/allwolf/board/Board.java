package allwolf.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import allwolf.Game;
import allwolf.agent.Agent;
import allwolf.agent.Wolf;
import allwolf.math.Area;
import allwolf.math.Point;

public class Board extends Observable
{
	private Area size;
	private ConcurrentMap<Point, Agent> map;
	
	private Game game;

	public Board(Area size, Game game)
	{
		super();
		this.size = size;
		this.map = new ConcurrentHashMap<Point, Agent>();
		this.game = game;
	}

	public Area getSize()
	{
		return size;
	}
	
	public Collection<Agent> getAgents()
	{
		return map.values();
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

	protected void validateMove(Point src, Point dest) throws PositionException
	{
		if (!isValidPos(src))
			throw new PositionException("Source position not valid", src);
		
		if (map.get(src) == null)
			throw new EmptyPositionException(src);
		
		if (src.equals(dest))
			throw new PositionException("Cannot move to source position", src);

		if (!isValidPos(dest))
			throw new PositionException("Destination position not valid", dest);
		
		if (map.get(dest) != null)
			throw new OccupiedPositionException(map.get(dest), dest);
	}
	
	public Agent getAgent(Point pos)
	{
		return map.get(pos);
	}
	
	public void addAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPosition();

		if (!isValidPos(pos))
			throw new PositionException("Unit's position is off the map", pos);

		if (map.putIfAbsent(pos, agent) != null)
			throw new OccupiedPositionException(map.get(pos),pos);
		
		agent.setBoard(this);
		
		signal();
	}

	public synchronized void moveAgent(Agent agent, Point dest) throws PositionException
	{
		Point src = agent.getPosition();

		try
		{
			validateMove(src, dest);
			
			// only move an agent if it exists at src position
			if (map.remove(src, agent))
			{
				map.put(dest, agent);
				agent.setPosition(dest);
			}
		}
		catch (OccupiedPositionException e)
		{
			// check if a wolf is killing a sheep
			if (agent.isWolf() && map.get(dest).isSheep())
			{
				map.remove(dest).kill();
				Wolf w = new Wolf(game.getBarrier(), dest);
				map.put(dest, w);
				w.setBoard(this);
				w.start();
			}
			else
				throw e;
		}
		
		signal();
	}
	
	private void signal()
	{
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString()
	{
		return "Board "+size;
	}
}