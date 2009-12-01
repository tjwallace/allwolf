package allwolf;

import java.util.concurrent.CyclicBarrier;

import allwolf.agent.Agent;
import allwolf.agent.Sheep;
import allwolf.agent.Wolf;
import allwolf.board.Board;
import allwolf.board.PositionException;
import allwolf.gui.BoardGUI;
import allwolf.gui.TextGUI;
import allwolf.math.Area;

public class Game
{
	public static final int SIZE = 20;
	public static final int NUM_OF_SHEEP = 7;
	public static final int NUM_OF_WOLVES = 1;
	
	private Board board;
	private CyclicBarrier barrier;
	private BoardGUI gui;

	public Game()
	{
		gui = new TextGUI();
		
		board = new Board(new Area(SIZE, SIZE), this);
		board.addObserver(gui);
		
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
	
	public CyclicBarrier getBarrier()
	{
		return barrier;
	}
	
	public void run()
	{
		for (Agent a : board.getAgents())
			a.start();
	}
	
	private class EndGameCheck implements Runnable
	{
		@Override
		public void run()
		{			
			int sheep = 0, wolves = 0;
			
			for (Agent a : board.getAgents())
			{
				if (a instanceof Sheep)
					sheep++;
				else
					wolves++;
			}
			
			if (sheep > 0)
			{
				System.out.println("Agents left => Sheep: "+sheep+" | Wolves: "+wolves);
			}
			else
			{
				System.out.println("No more sheep left!  GAME OVER");
				for (Agent a : board.getAgents())
					a.kill();
				
				System.exit(0);
			}
		}
	}
}
