package allwolf.gui;

import java.util.Observable;

import allwolf.agent.Agent;
import allwolf.agent.Sheep;
import allwolf.board.Board;
import allwolf.math.Point;

public class TextGUI implements BoardGUI
{
	@Override
	public void update(Observable o, Object arg)
	{
		Board board = (Board) o;
		
		for (int y = 0 ; y <= board.getSize().y() ; y++)
		{
			for (int x = 0 ; x <= board.getSize().x() ; x++)
			{
				Agent a = board.getAgent(new Point(x, y));
				
				if (a != null)
				{
					if (a instanceof Sheep)
						System.out.print("S");
					else
						System.out.print("W");
				}
				else
					System.out.print("-");
			}
			System.out.println();
		}
	}

}
