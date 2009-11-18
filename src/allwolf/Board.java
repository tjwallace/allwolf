package allwolf;

import java.util.Observable;

/**
 * 
 * @author jeff
 */
public class Board extends Observable implements Runnable
{
	private int sizeX;
	private int sizeY;

	private Unit[][] map;

	public Board(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		map = new Unit[sizeX][sizeY];
	}

	@Override
	public void run()
	{

	}
}
