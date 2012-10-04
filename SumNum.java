/*
 * 
 * 写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷。
 * 
 */
package interview;

public class SumNum {

	public int sum(int a, int b)
	{
		//不带进位的结果
		int result = a | b;
		System.out.println(result);
		//有进位的位置
		int carry = a & b;
		System.out.println(carry);
		while(carry != 0)
		{
			//消除进位位置的1
			int temp = result & (~carry);
			//进位左移，准备加到结果中
			carry <<= 1;
			//将进位添加到结果中
			temp |= carry;
			//检查是否产生新的进位
			carry &= result;
			result = temp;
		}
		System.out.println(result);
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SumNum sn = new SumNum();
		sn.sum(12,58);

	}

}
