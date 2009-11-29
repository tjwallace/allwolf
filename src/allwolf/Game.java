package allwolf;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;

import allwolf.agent.Agent;
import allwolf.agent.Sheep;
import allwolf.agent.Wolf;
import allwolf.board.Board;
import allwolf.board.PositionException;
import allwolf.math.Area;

public class Game implements Observer
{
	public static final Area SIZE = new Area(50, 50);
	public static final int NUM_OF_SHEEP = 7;
	public static final int NUM_OF_WOLVES = 1;
	
	private Board board;
	private CyclicBarrier barrier;

	public Game()
	{
		board = new Board(SIZE);
		board.addObserver(this);
		
		barrier = new CyclicBarrier(NUM_OF_SHEEP + NUM_OF_WOLVES, new EndGameCheck());
		generateWolves();
		generateSheep();
	}
	
	private void generateWolves()
	{		
		int count = 0;
		while (count < NUM_OF_WOLVES)
		{
			try
			{
				board.addAgent(new Wolf(barrier, board.getRandomPosition()));
				count++;
			}
			catch (PositionException e)
			{
				System.err.println("Couldn't add a wolf, trying again...");
			}
		}
	}
	
	private void generateSheep()
	{
		int count = 0;
		while (count < NUM_OF_SHEEP)
		{
			try
			{
				board.addAgent(new Sheep(barrier, board.getRandomPosition()));
				count++;
			}
			catch (PositionException e)
			{
				System.err.println("Couldn't add a sheep, trying again...");
			}
		}
	}
	
	public void run()
	{
		for (Agent a : board.getAgents())
			a.start();
	}

	public void update(Observable o, Object arg)
	{
		System.out.println("Game recieved " + arg + " update from " + o);
	}
	
	private class EndGameCheck implements Runnable
	{
		@Override
		public void run()
		{
			for (Agent a : board.getAgents())
			{
				if (a instanceof Sheep)
					return;
			}
			
			System.out.println("No more sheep left!  GAME OVER");
			System.exit(0);
		}
		
	}

}
