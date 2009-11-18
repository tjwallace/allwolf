package allwolf;

abstract public class Unit extends Thread
{
	private Board board;
	
	private int posX;
	private int posY;
	
	public Unit()
	{
		this(null, -1, -1);
	}
	
	public Unit(Board board, int posX, int posY)
	{
		this.board = board;
		this.posX = posX;
		this.posY = posY;
	}

	public final Board getBoard()
	{
		return board;
	}

	public final void setBoard(Board board)
	{
		this.board = board;
	}

	public int getPosX()
	{
		return posX;
	}

	public void setPosX(int posX)
	{
		this.posX = posX;
	}

	public int getPosY()
	{
		return posY;
	}

	public void setPosY(int posY)
	{
		this.posY = posY;
	}
}
