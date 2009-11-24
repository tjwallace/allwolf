package allwolf.board;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import allwolf.MoveException;
import allwolf.Point;
import allwolf.PositionException;
import allwolf.agent.Agent;

public class MapBoard extends Board
{
	private Map<Point, Agent> map;
	
	public MapBoard(int sizeX, int sizeY)
	{
		super(sizeX, sizeY);
		map = new TreeMap<Point, Agent>();
	}

	@Override
	public void addAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPos();
		
		if (!isValidPos(pos))
			throw new PositionException("Unit's position is off the map ("+sizeX+","+sizeY+")", pos);
		
		agent.setBoard(this);
		map.put(pos, agent);
		
		notifyObservers();
	}

	@Override
	public Agent getAgent(Point pos)
	{
		return map.get(pos);
	}

	@Override
	public synchronized void moveAgent(Agent agent, Point dest) throws PositionException
	{
		Point src = agent.getPos();
		
		if (!isValidPos(src))
			throw new PositionException("Agent source position is not valid", src);
		
		if (!isValidPos(dest))
			throw new PositionException("Agent destination position is not valid", dest);
		
		map.remove(src);
		map.put(dest, agent);
		agent.setPos(dest);

		notifyObservers();
	}

	@Override
	public void run()
	{
		for(Entry<Point, Agent> obj : map.entrySet())
			obj.getValue().start();
	}

}
