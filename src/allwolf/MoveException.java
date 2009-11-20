package allwolf;

import allwolf.agent.Agent;

public class MoveException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private Agent agent;

	private Point dest;
	
	public MoveException(Agent agent, Point dest)
	{
		this.agent = agent;
		this.dest = dest;
	}

	public final Agent getAgent()
	{
		return agent;
	}
	
	public final Point getDest()
	{
		return dest;
	}

	@Override
	public String toString()
	{
		return agent+" can not be moved to "+dest;
	}
}
