package allwolf.board;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import allwolf.MoveException;
import allwolf.Point;
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
	public void addAgent(Agent agent) throws Exception
	{
		Point pos = agent.getPos();
		
		if (!isValidPos(pos))
			throw new Exception("Unit's position ("+pos.x+","+pos.y+") is off the map ("+sizeX+","+sizeY+")");
		
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
	public synchronized void moveAgent(Agent agent, Point dest) throws MoveException
	{
		Point src = agent.getPos();
		
		if (!isValidPos(src))
			throw new MoveException(agent, src);
		
		if (!isValidPos(dest))
			throw new MoveException(agent, dest);
		
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
