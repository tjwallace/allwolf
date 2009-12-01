package allwolf;

import java.util.HashMap;
import java.util.Map;

import allwolf.math.Point;

public class Test
{
	public static int LIMIT = 50;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Map<Point, Object> map = new HashMap<Point, Object>();
		
		for (int y = 0 ; y < LIMIT ; y++)
		{
			for (int x = 0 ; x < LIMIT ; x++)
			{
				Point p = new Point(x, y);
				if (map.get(p) == null)
					map.put(p, new Object());
				else
					System.err.println("Collision @ "+p);
			}
		}
		
		for (int y = 0 ; y < LIMIT ; y++)
		{
			for (int x = 0 ; x < LIMIT ; x++)
			{
				Point p = new Point(x, y);
				if (map.get(p) != null)
					map.put(p, new Object());
				else
					System.err.println("No collision @ "+p);
			}
		}
		
		System.out.println("DONE!");
	}

}
