package allwolf.agent;

import java.util.concurrent.CyclicBarrier;

import allwolf.math.Point;

public final class Wolf extends Agent
{
	public static final int SIGHT = 4;
	public static final int SPEED = 2;

	public Wolf(CyclicBarrier barrier)
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
