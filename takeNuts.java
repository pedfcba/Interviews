package interview;

public class takeNuts {

	public void take(String A, String B, int x, int max)
	{

		System.out.println("ʣ��" + x + "��");
		if(x == 1)
		{
			System.out.println(A + "��һ��");
			System.out.println(A + "Ӯ�ˣ�");
		}
		//��ʼN > 2ʱ
		if(x == 2)
		{
			System.out.println(A + "������");
			System.out.println(A + "Ӯ��");
		}
		//��ʼN > 3ʱ
		if(x == 3)
		{
			System.out.println(A + "������");
			System.out.println(B + "��һ��");
			System.out.println(A + "����");
		}
		int winnum = 3;
			if(x > winnum)
			{
				System.out.println(A + "��" + (x-1)/3 + "��");
				take(B, A, x - (x-1)/3, 1);
			}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		takeNuts tk = new takeNuts();
		tk.take("����", "����", 8, 1);
	}

}
