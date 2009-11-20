package allwolf;

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
	public void addAgent(Agent agent) throws Exception
	{
		Point pos = agent.getPos();
		
		if (!isValidPos(pos))
			throw new Exception("Unit's position ("+pos.x+","+pos.y+") is off the map ("+sizeX+","+sizeY+")");
		
		agent.setBoard(this);
		map[pos.x][pos.y] = agent;
		
		notifyObservers();
	}
	
	@Override
	public synchronized void moveAgent(Agent agent, Point dest) throws MoveException
	{
		Point src = agent.getPos();
		
		if (!isValidPos(src))
			throw new MoveException(agent, dest);
		
		if (!isValidPos(dest))
			throw new MoveException(agent, dest);
		
		Agent temp = getAgent(src);
		map[src.x][src.y] = null;
		map[dest.x][dest.y] = temp;
		temp.setPos(dest);
		
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
