package allwolf;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author jeff
 */
public class Game implements Observer
{
	private Board board;

	public Game()
	{
		board = new Board(50, 50);
		board.addObserver(this);
	}

	public void run()
	{
		board.run();
	}

	public void update(Observable o, Object arg)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
