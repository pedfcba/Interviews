package interview;

public class takeNuts {

	public void take(String A, String B, int x, int max)
	{

		System.out.println("剩余" + x + "个");
		if(x == 1)
		{
			System.out.println(A + "拿一个");
			System.out.println(A + "赢了！");
		}
		//初始N > 2时
		if(x == 2)
		{
			System.out.println(A + "拿两个");
			System.out.println(A + "赢了");
		}
		//初始N > 3时
		if(x == 3)
		{
			System.out.println(A + "拿两个");
			System.out.println(B + "拿一个");
			System.out.println(A + "输了");
		}
		int winnum = 3;
			if(x > winnum)
			{
				System.out.println(A + "拿" + (x-1)/3 + "个");
				take(B, A, x - (x-1)/3, 1);
			}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		takeNuts tk = new takeNuts();
		tk.take("先手", "后手", 8, 1);
	}

}
