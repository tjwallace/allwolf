package allwolf;

import java.util.Observable;

import allwolf.agent.Agent;

public class Board extends Observable
{
	private int sizeX;
	private int sizeY;

	private Agent[][] map;
	
	private boolean isRunning;

	public Board(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		map = new Agent[sizeX][sizeY];
		isRunning = false;
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
		if (pos.x < 0 || pos.x >= sizeX)
			return false;
		
		if (pos.y < 0 || pos.y >= sizeY)
			return false;
		
		return true;
	}
	
	public Agent getAgent(Point pos)
	{
		if (!isValidPos(pos))
			return null;
		
		return map[pos.x][pos.y];
	}
	
	public void addAgent(Agent agent) throws Exception
	{
		Point pos = agent.getPos();
		
		if (!isValidPos(pos))
			throw new Exception("Unit's position ("+pos.x+","+pos.y+") is off the map ("+sizeX+","+sizeY+")");
		
		agent.setBoard(this);
		map[pos.x][pos.y] = agent;
		
		notifyObservers();
	}
	
	public synchronized void moveAgent(Agent agent, Point dest) throws Exception
	{
		Point src = agent.getPos();
		
		if (!isValidPos(src))
			throw new Exception("Source position ("+src.x+","+src.y+") is off the map ("+sizeX+","+sizeY+")");
		
		if (!isValidPos(dest))
			throw new Exception("Destination position ("+dest.x+","+dest.y+") is off the map ("+sizeX+","+sizeY+")");
		
		Agent temp = getAgent(src);
		map[src.x][src.y] = null;
		map[dest.x][dest.y] = temp;
		temp.setPos(dest);
		
		notifyObservers();
	}
	
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
	
	@Override
	public String toString()
	{
		return "Board ("+sizeX+","+sizeY+")";
	}
}
