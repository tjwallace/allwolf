package allwolf.board;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import allwolf.PositionException;
import allwolf.agent.Agent;
import allwolf.math.Point;

public class MapBoard extends Board
{
	private ConcurrentMap<Point, Agent> map;

	public MapBoard(int sizeX, int sizeY)
	{
		super(sizeX, sizeY);
		map = new ConcurrentHashMap<Point, Agent>();
	}

	@Override
	public void addAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPos();

		if (!isValidPos(pos))
			throw new PositionException("Unit's position is off the map (" + sizeX + "," + sizeY + ")", pos);

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

		isValidMove(src, dest);

		map.remove(src);
		map.put(dest, agent);
		agent.setPos(dest);

		notifyObservers();
	}

	@Override
	protected boolean execRun()
	{
		for (Entry<Point, Agent> obj : map.entrySet())
			obj.getValue().start();
		
		return true;
	}

}
