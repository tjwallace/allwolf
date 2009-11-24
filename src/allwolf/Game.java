package allwolf;

import java.util.Observable;
import java.util.Observer;

import allwolf.board.ArrayBoard;
import allwolf.board.Board;

public class Game implements Observer
{
	private Board board;

	public Game()
	{
		board = new ArrayBoard(50, 50);
		board.addObserver(this);
	}

	public void run()
	{
		board.run();
	}

	public void update(Observable o, Object arg)
	{
		System.out.println("Game recieved " + arg + " update from " + o);
	}

}
