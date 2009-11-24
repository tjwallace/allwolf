package allwolf;

public class PositionException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private Point position;
	
	public PositionException(String message, Point position)
	{
		super(message);
		this.position = position;
	}
	
	@Override
	public String toString()
	{
		return getMessage()+" "+position;
	}
}
