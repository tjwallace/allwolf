package allwolf;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;

import allwolf.board.Board;
import allwolf.math.Area;

public class Game implements Observer
{
	public static Area SIZE = new Area(50, 50);
	
	private Board board;
	private CyclicBarrier barrier;

	public Game()
	{
		board = new Board(SIZE);
		board.addObserver(this);
		
		barrier = new CyclicBarrier(1);
	}

	public void run()
	{
		
	}
	
	private void generateWolves(int n)
	{
		
	}
	
	private void generateSheep(int n)
	{
		
	}

	public void update(Observable o, Object arg)
	{
		System.out.println("Game recieved " + arg + " update from " + o);
	}

}
