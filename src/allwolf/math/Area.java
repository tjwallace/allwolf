package allwolf.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Area implements Iterable<Point>
{
	private Point topLeft;
	private Point bottomRight;
	private int area;
	
	public Area(int xSize, int ySize)
	{
		this(new Point(0,0), new Point(xSize, ySize));
	}
	
	public Area(Point topLeft, Point bottomRight)
	{
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		
		calculateArea();
	}
	
	private void calculateArea()
	{
		area = topLeft.area(bottomRight);
	}
	
	public final Point getTopLeft()
	{
		return topLeft;
	}

	public final void setTopLeft(Point topLeft)
	{
		this.topLeft = topLeft;
		calculateArea();
	}

	public final Point getBottomRight()
	{
		return bottomRight;
	}

	public final void setBottomRight(Point bottomRight)
	{
		this.bottomRight = bottomRight;
		calculateArea();
	}

	public final int x1()
	{
		return topLeft.x;
	}
	
	public final int y1()
	{
		return topLeft.y;
	}
	
	public final int x2()
	{
		return bottomRight.x;
	}
	
	public final int y2()
	{
		return bottomRight.y;
	}
	
	public final int x()
	{
		return topLeft.xDistance(bottomRight);
	}
	
	public final int y()
	{
		return topLeft.yDistance(bottomRight);
	}
	
	public final int area()
	{
		return area;
	}
	
	public boolean contains(Point p)
	{
		return p.x >= topLeft.x && p.y >= topLeft.y && p.x <= bottomRight.x && p.y <= bottomRight.y;
	}

	@Override
	public Iterator<Point> iterator()
	{
		List<Point> itr = new ArrayList<Point>();
		
		for (int y = topLeft.y ; y <= bottomRight.y ; y++)
		{
			for (int x = topLeft.x ; x <= bottomRight.x ; x++)
				itr.add(new Point(x,y));
		}
		
		return itr.iterator();
	}
	
	@Override
	public String toString()
	{
		return x() + " x " + y();
	}
}
