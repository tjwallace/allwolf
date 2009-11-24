package allwolf;

public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Object[] a = new Object[3];

		a[0] = 1;
		a[1] = 3;

		for (Object c : a)
			System.out.println(c);

		Object temp = a[1];
		a[1] = null;
		a[2] = temp;

		for (Object c : a)
			System.out.println(c);
	}

}
