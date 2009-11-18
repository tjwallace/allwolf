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
	
	/**
	 * Generate some wolves and place them randomly on the map
	 * @param count The number of wolves to generate
	 */
	public void generateWolfs(int count)
	{
		
	}
	
	/**
	 * Generate some sheep and place them randomly on the map
	 * @param count The number of sheep to generate
	 */
	public void generateSheep(int count)
	{
		
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
