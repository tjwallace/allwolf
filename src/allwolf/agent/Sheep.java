package allwolf.agent;

import java.util.concurrent.CyclicBarrier;

import allwolf.math.Point;

public final class Sheep extends Agent
{
	public static final int SIGHT = 2;
	public static final int SPEED = 1;

	public Sheep(CyclicBarrier barrier)
	{
		super(barrier, SIGHT, SPEED);
	}

	@Override
	protected Point nextPos()
	{
		return null;
	}

	@Override
	protected boolean canContinue()
	{
		return false;
	}

}
