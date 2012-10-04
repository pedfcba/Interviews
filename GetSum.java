/*
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷。
 *
 */

package interview;

public class GetSum {

	//进位标志
	private static boolean carry;

	public GetSum()
	{
		carry = false;
	}

	//求和
	public void sum(int a, int b)
	{
		int first = a;
		int second = b;
		System.out.println("待求和的两个数：");
		System.out.println(first);
		System.out.println(second);
		int sum = singlesum(first,second);

		System.out.println("两数之和为：");
		if(carry == true)
		{
			System.out.print("1");
			System.out.println(sum);
		}
		else
			System.out.println(sum);
	}

	//比较并返回该位结果
	private int singlesum(int first, int second) {
		// TODO Auto-generated method stub
		if(first > second)
			return singlesum(second, first);
		if(first == 0 && second == 0 || first == 1 && second == 9 || first == 2 && second == 8 || first == 3 && second == 7 || first == 4 && second == 6 || first == 5 && second == 5)
		{
			if (first == 0)
				carry = false;
			else
				carry = true;
			return 0;
		}
		if(first == 0 && second == 1 || first == 2 && second == 9 || first == 3 && second == 8 || first == 4 && second == 7 || first == 5 && second == 6)
		{
			if (first == 0)
				carry = false;
			else
				carry = true;
			return 1;
		}
		if(first == 0 && second == 2 || first == 1 && second == 1 || first == 3 && second == 9 || first == 4 && second == 8 || first == 5 && second == 7 || first == 6 && second == 6)
		{
			if (first == 0 || first == 1)
				carry = false;
			else
				carry = true;
			return 2;
		}
		if(first == 0 && second == 3 || first == 1 && second == 2 || first == 4 && second == 9 || first == 5 && second == 8 || first == 6 && second == 7)
		{
			if (first == 0 || first == 1)
				carry = false;
			else
				carry = true;
			return 3;
		}
		if(first == 0 && second == 4|| first == 1 && second == 3 || first == 2 && second == 2 || first == 5 && second == 9 || first == 6 && second == 8 || first == 7 && second == 7)
		{
			if (first == 0)
				carry = false;
			else
				carry = true;
			return 4;
		}
		if(first == 0 && second == 5|| first == 1 && second == 4 || first == 2 && second == 3 || first == 6 && second == 9 || first == 7 && second == 8)
		{
			if (first == 0 || first == 1 || first == 2)
				carry = false;
			else
				carry = true;
			return 5;
		}
		if(first == 0 && second == 6|| first == 1 && second == 5 || first == 2 && second == 4 || first == 3 && second == 3 || first == 7 && second == 9 || first == 8 && second == 8)
		{
			if (first == 0 || first == 1 || first == 2 || first == 3)
				carry = false;
			else
				carry = true;
			return 6;
		}
		if(first == 0 && second == 7 || first == 1 && second == 6 || first == 2 && second == 5 || first == 3 && second == 4 || first == 8 && second == 9)
		{
			if (first != 8)
				carry = false;
			else
				carry = true;
			return 7;
		}
		if(first == 0 && second == 8|| first == 1 && second == 7 || first == 2 && second == 6 || first == 3 && second == 5 || first == 4 && second == 4 || first == 9 && second == 9)
		{
			if (first != 9)
				carry = false;
			else
				carry = true;
			return 8;
		}
		if(first == 0 && second == 9|| first == 1 && second == 8 || first == 2 && second == 7 || first == 3 && second == 6 || first == 4 && second == 5)
		{
			carry = false;
			return 9;
		}
		return -1;
	}

	//有进位的情况，比正常多返回1
	private int carrysum(int first, int second) {
		// TODO Auto-generated method stub
		if(first > second)
			return carrysum(second, first);
		if(first == 0 && second == 0 || first == 1 && second == 9 || first == 2 && second == 8 || first == 3 && second == 7 || first == 4 && second == 6 || first == 5 && second == 5)
		{
			if (first == 0)
				carry = false;
			else
				carry = true;
			return 1;
		}
		if(first == 0 && second == 1 || first == 2 && second == 9 || first == 3 && second == 8 || first == 4 && second == 7 || first == 5 && second == 6)
		{
			if (first == 0)
				carry = false;
			else
				carry = true;
			return 2;
		}
		if(first == 0 && second == 2 || first == 1 && second == 1 || first == 3 && second == 9 || first == 4 && second == 8 || first == 5 && second == 7 || first == 6 && second == 6)
		{
			if (first == 0 || first == 1)
				carry = false;
			else
				carry = true;
			return 3;
		}
		if(first == 0 && second == 3 || first == 1 && second == 2 || first == 4 && second == 9 || first == 5 && second == 8 || first == 6 && second == 7)
		{
			if (first == 0 || first == 1)
				carry = false;
			else
				carry = true;
			return 4;
		}
		if(first == 0 && second == 4|| first == 1 && second == 3 || first == 2 && second == 2 || first == 5 && second == 9 || first == 6 && second == 8 || first == 7 && second == 7)
		{
			if (first == 0)
				carry = false;
			else
				carry = true;
			return 5;
		}
		if(first == 0 && second == 5|| first == 1 && second == 4 || first == 2 && second == 3 || first == 6 && second == 9 || first == 7 && second == 8)
		{
			if (first == 0 || first == 1 || first == 2)
				carry = false;
			else
				carry = true;
			return 6;
		}
		if(first == 0 && second == 6|| first == 1 && second == 5 || first == 2 && second == 4 || first == 3 && second == 3 || first == 7 && second == 9 || first == 8 && second == 8)
		{
			if (first == 0 || first == 1 || first == 2 || first == 3)
				carry = false;
			else
				carry = true;
			return 7;
		}
		if(first == 0 && second == 7 || first == 1 && second == 6 || first == 2 && second == 5 || first == 3 && second == 4 || first == 8 && second == 9)
		{
			if (first != 8)
				carry = false;
			else
				carry = true;
			return 8;
		}
		if(first == 0 && second == 8|| first == 1 && second == 7 || first == 2 && second == 6 || first == 3 && second == 5 || first == 4 && second == 4 || first == 9 && second == 9)
		{
			if (first != 9)
				carry = false;
			else
				carry = true;
			return 9;
		}
		if(first == 0 && second == 9|| first == 1 && second == 8 || first == 2 && second == 7 || first == 3 && second == 6 || first == 4 && second == 5)
		{
			carry = true;
			return 0;
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetSum gs = new GetSum();
		gs.sum(9, 7);

	}

}