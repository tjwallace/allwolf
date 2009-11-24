package allwolf.board;

import allwolf.MoveException;
import allwolf.Point;
import allwolf.PositionException;
import allwolf.agent.Agent;

public class ArrayBoard extends Board
{
	private Agent[][] map;
	
	public ArrayBoard(int sizeX, int sizeY)
	{
		super(sizeX, sizeY);
		map = new Agent[sizeX][sizeY];
	}

	@Override
	public Agent getAgent(Point pos)
	{
		if (!isValidPos(pos))
			return null;
		
		return map[pos.x][pos.y];
	}
	
	@Override
	public void addAgent(Agent agent) throws PositionException
	{
		Point pos = agent.getPos();
		
		if (!isValidPos(pos))
			throw new PositionException("Unit's position is off the map ("+sizeX+","+sizeY+")", pos);
		
		agent.setBoard(this);
		map[pos.x][pos.y] = agent;
		
		notifyObservers();
	}
	
	@Override
	public synchronized void moveAgent(Agent agent, Point dest) throws PositionException
	{
		Point src = agent.getPos();
		
		if (!isValidPos(src))
			throw new PositionException("Agent source position is not valid", src);
		
		if (!isValidPos(dest))
			throw new PositionException("Agent destination position is not valid", dest);
		
		map[src.x][src.y] = null;
		map[dest.x][dest.y] = agent;
		agent.setPos(dest);
		
		notifyObservers();
	}
	
	@Override
	public void run()
	{
		if (isRunning)
			return;
		
		for(int x = 0 ; x < sizeX ; x++)
		{
			for (int y = 0 ; y < sizeY ; y++)
			{
				if (map[x][y] != null)
					map[x][y].start();
			}
		}
		
		isRunning = true;
	}
}
