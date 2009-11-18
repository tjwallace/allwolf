package allwolf;

abstract public class Unit extends Thread
{
	private Board board;
	
	private int xPos;
	private int yPos;
	
	public Unit()
	{
		this(null, -1, -1);
	}
	
	public Unit(Board board, int xPos, int yPos)
	{
		this.board = board;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public final Board getBoard()
	{
		return board;
	}

	public final void setBoard(Board board)
	{
		this.board = board;
	}

	public int getXPos()
	{
		return xPos;
	}

	public void setXPos(int xPos)
	{
		this.xPos = xPos;
	}

	public int getYPos()
	{
		return yPos;
	}

	public void setYPos(int yPos)
	{
		this.yPos = yPos;
	}
	
	public boolean isValidMove(Point p)
	{
		return isValidMove(p.x, p.y);
	}
	
	public boolean isValidMove(int x, int y)
	{
		if (!board.isValidPos(x, y))
			return false;
		
		if (xPos == x && (yPos - 1) == y)
			return true; // up
		
		if (xPos == x && (yPos + 1) == y)
			return true; // down
		
		if ((xPos - 1) == x && yPos == y)
			return true; // left
		
		if ((xPos + 1) == x && yPos == y)
			return true; // right
		
		return false;
	}
	
	/**
	 * Calculate next move and execute
	 * @return <code>true</code> if the thread should continue, <code>false</code> otherwise.
	 */
	protected boolean step()
	{
		board.moveUnit(this, nextPos());
		return true;
	}
	
	abstract protected Point nextPos();

	@Override
	public void run()
	{
		boolean run = true;
		while (run)
			run = step();
	}
	
	
}
