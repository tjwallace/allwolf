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
	
	public void addUnit(Unit unit) throws Exception
	{
		int x = unit.getPosX(), y = unit.getPosY();
		
		if (x < 0 || x >= sizeX)
			throw new Exception("Unit's X position is off the map ("+x+")");
		
		if (y < 0 || y >= sizeY)
			throw new Exception("Unit's Y position is off the map ("+y+")");
		
		unit.setBoard(this);
		map[x][y] = unit;
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
}
