package allwolf;

import java.util.Observable;

public class Board extends Observable
{
	private int sizeX;
	private int sizeY;

	private Unit[][] map;
	
	private boolean isRunning;

	public Board(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		map = new Unit[sizeX][sizeY];
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
	
	public boolean isValidPos(int x, int y)
	{
		if (x < 0 || x >= sizeX)
			return false;
		
		if (y < 0 || y >= sizeY)
			return false;
		
		return true;
	}
	
	public Unit getUnit(int x, int y)
	{
		if (!isValidPos(x, y))
			return null;
		
		return map[x][y];
	}
	
	public void addUnit(Unit unit) throws Exception
	{
		int x = unit.getXPos(), y = unit.getYPos();
		
		if (!isValidPos(x, y))
			throw new Exception("Unit's position ("+x+","+y+") is off the map ("+sizeX+","+sizeY+")");
		
		unit.setBoard(this);
		map[x][y] = unit;
		
		notifyObservers();
	}
	
	public synchronized void moveUnit(Unit unit, Point next)
	{
		moveUnit(unit, next.x, next.y);
	}
	
	public synchronized void moveUnit(Unit unit, int xDest, int yDest)
	{
		int xSrc = unit.getXPos();
		int ySrc = unit.getYPos();
		
		if (!isValidPos(xSrc, ySrc) || !isValidPos(xDest, yDest))
			return;
		
		Unit temp = getUnit(xSrc, ySrc);
		map[xSrc][ySrc] = null;
		map[xDest][yDest] = temp;
		temp.setXPos(xDest);
		temp.setYPos(yDest);
		
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
