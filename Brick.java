/* **********δʵ��******************
 * ����һ���������󣬴���һ�����㣬
 * �Ծ���������Ԫ�ؼ�һʱ����Ҫ�����ڣ��������ң�ĳһ��Ԫ��Ҳ��һ��
 * �ָ���һ���������ж����Ƿ��ܹ���һ��ȫ����󾭹���������õ���
 */


package interview;



import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Brick {

	//��ʼ����
	public static int map[][];
	//�����С
	private final int MAXSIZE = 10;
	//������������б��洢��������С�����ڵ�����
	List<Coordinate> coordi;

	//����
	private class Coordinate
	{
		int x;
		int y;
		Coordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}

	public Brick()
	{
		map = new int[MAXSIZE][MAXSIZE];
		coordi = new LinkedList<Coordinate>();
	}

	/**
	 * 
	 * ��ָ�����꼰������������м�1����
	 * 
	 * @param x Ŀ������� 
	 * @param y Ŀ��������
	 * @param rand ���������������������Ĵ�Сȷ�����ĸ������������Ӳ���
	 * 
	 * @return ������������������true�����򷵻�false
	 */
	public void put(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("�������");
			System.out.println("���꣺" + x + ", " + y);
			return;
		}
		//�������һ
		map[x][y]++;
		//ȷ���ڷŵķ���
		Random rand = new Random();
		int direction = rand.nextInt(4001);

		//��һ���������һ
		if(direction <= 1000 && y < MAXSIZE-1)
			map[x][y+1]++;
		else if(direction <= 2000 && y > 0)
			map[x][y-1]++;
		else if (direction <= 3000 && x < MAXSIZE-1)
			map[x+1][y]++;
		else 
		{
			if (x == 0)
				map[x+1][y]++;
			else
				map[x-1][y]++;
		}
	}

	/**
	 * 
	 * ���������꼰������������м�������������ͬʱ��ȥ�����������ֵ
	 * 
	 * @param x Ŀ������� 
	 * @param y Ŀ��������
	 * @param top ��¼����Ԫ���е����ֵ��ȷ�����м������ĵڶ���Ԫ��
	 * @param half �����������⣬��Ҫ���м������ĵڶ�������
	 * 
	 * @return ������������������true�����򷵻�false
	 */
	public boolean carry(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("�������");
			System.out.println("���꣺" + x + ", " + y);
			return false;
		}
		//Ѱ������Ԫ���е����ֵ
		int top = 0;	
		//��¼��һ�������λ��
		int tempx = x;
		int tempy = y;
		//Ѱ����һ����Ҫ���м�����������,ѡ�����������
		if(x > 0 && map[x-1][y] > top && map[x-1][y] > 0)
		{
			top = map[x-1][y];
			tempx = x-1;
			tempy = y;
		}
		if(x < MAXSIZE-1 && map[x+1][y] > top && map[x+1][y] > 0)
		{
			top = map[x+1][y];
			tempx = x+1;
			tempy = y;
		}
		if(y > 0 && map[x][y-1] > top && map[x][y-1] > 0)
		{
			top = map[x][y-1];
			tempx = x;
			tempy = y-1;
		}
		if(y < MAXSIZE-1 && map[x][y+1] > top && map[x][y+1] > 0)
		{
			top = map[x][y+1];
			tempx = x;
			tempy = y+1;
		}


		//��δ�ҵ����ʵ�Ԫ�أ�����false
		if(top == 0)
			return false;
		else
		{
			Coordinate half = new Coordinate(tempx, tempy);
			//����
			if(top > map[x][y])
				top = map[x][y];
			System.out.println(top);
			System.out.println(x + ", " + y);
			System.out.println(half.x + ", " + half.y);
			map[half.x][half.y] -= top;
			map[x][y] -= top;
			return true;
		}
	}


	/**
	 * ���һ�������Ƿ�����Ҫ�������
	 * 
	 * @param foundsingle ��¼�Ƿ��ҵ�Ŀ������Ԫ��ֻ��һ����Ϊ0��
	 * @param comp ��¼��һ��Ŀ���ٽ�Ԫ�ط����ֵ
	 * @param sum ��¼Ŀ���ٽ�Ԫ�صĺ�
	 * @param found ��¼�Ƿ��ҵ����ʵ�����������м�����
	 * @param top ��¼��������С������ֵ
	 */
	public void carryBrick()
	{
		//����Ҫ������Ϊ��
		boolean found = false;
		//�ҵ�ֻ��һ���ٽ�Ԫ�ص���Ϊ��
		boolean foundsingle = false;
		//�ѷŵĸ߶�
		int top = Integer.MAX_VALUE;


		//�ҵ��ٽ�Ԫ����ֻ��һ�������Ԫ�����겢��¼
		for(int i = 0; i < MAXSIZE; i++)
			for(int j = 0; j < MAXSIZE; j++)
			{
				int comp = 0;
				int sum = 0;
				if (map[i][j] != 0)
				{
					if(i > 0)
					{
						sum += map[i-1][j];
						if (comp == 0)
							comp = sum;
						else if(comp < sum)
								continue;
					}
					if(i < MAXSIZE-1)
					{
						sum += map[i+1][j];
						if (comp == 0)
							comp = sum;
						else if(comp < sum)
								continue;
					}
					if(j > 0)
					{
						sum += map[i][j-1];
						if (comp == 0)
							comp = sum;
						else if(comp < sum)
								continue;
					}
					if(j < MAXSIZE-1)
					{
						sum += map[i][j+1];
						if (comp == 0)
							comp = sum;
						else if(comp < sum)
								continue;
					}
					//����������������ӣ�˵��������ֻ��һ���ٽ�Ԫ�ز�Ϊ0
					if(sum == comp && sum != 0)
					{
						foundsingle = true;
						coordi.add(new Coordinate(i,j));
					}
				}
			}


		/*
		 * û���ҵ��ٽ�Ԫ�ظ���Ϊһ��Ԫ��
		 * �ҵ���Ե����С��Ԫ��
		 * �������ٽӵ����Ԫ��
		 * ��¼�����м�����
		*/
		if(foundsingle == false)
		{
			//�ҵ����Ե����С�����겢��¼
			for(int i = 0; i < MAXSIZE; i++)
				for(int j = 0; j < MAXSIZE; j++)
				{
					if (map[i][j] == 0 || map[i][j] > top)
						continue;
					else 
					{
						if(map[i][j] < top)
						{
							top = map[i][j];
							coordi.clear();
						}
						coordi.add(new Coordinate(i,j));
					}
				}
		}
		
		//��û�������¼������������Ҫ�󣬷���
		if(coordi.size() == 0)
		{
			System.out.println("ש�����ˣ�");
			return;
		}
		
		//�������꼯���м�����������Ԫ��ѡ����������ֵ����
		System.out.println("���Ҫ���Ŀ�꣺");
		for(int i = 0; i < coordi.size(); i++)
		{
			if(map[coordi.get(i).x][coordi.get(i).y] == 0)
				continue;
			System.out.println(coordi.get(i).x + ", " + coordi.get(i).y);
			found = carry(coordi.get(i).x, coordi.get(i).y);
		}
		if(found == false)
		{
			System.out.println("�����鲻����Ҫ��");
			return;
		}
		//��һ��Ѱ��
		coordi.clear();
		output();
		System.out.println("�̰�ש�С�����");
		carryBrick();
	}

	//���Ŀǰ���������
	public void output()
	{
		System.out.println("��ǰ״̬");
		System.out.print("X ");
		for (int i = 0; i < MAXSIZE; i++)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < MAXSIZE; i++)
		{
			for (int j = 0; j < MAXSIZE; j++)
			{
				if(j == 0)
					System.out.print(i + " ");
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Brick br = new Brick();

		Random rand = new Random();
		for(int i = 0; i < 100; i++)
		{
			br.put(rand.nextInt(10), rand.nextInt(10));
		}
		br.output();
		br.carryBrick();
		br.output();
	}

}


/*
 * ****************result*******************
 * 
 * 
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 5 1 2 3 5 2 5 6 1 
2 1 3 1 2 0 2 1 0 1 0 
3 1 4 0 1 0 2 2 3 2 1 
4 1 3 3 3 4 2 0 1 1 2 
5 2 3 2 2 1 0 1 1 2 5 
6 0 4 2 2 2 1 2 2 3 4 
7 0 1 3 2 2 3 0 1 1 2 
8 2 0 2 1 2 3 2 2 3 3 
9 3 4 4 2 2 1 1 0 4 2 
���Ҫ���Ŀ�꣺
1, 9
1
1, 9
1, 8
8, 0
2
8, 0
9, 0
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 5 1 2 3 5 2 5 5 0 
2 1 3 1 2 0 2 1 0 1 0 
3 1 4 0 1 0 2 2 3 2 1 
4 1 3 3 3 4 2 0 1 1 2 
5 2 3 2 2 1 0 1 1 2 5 
6 0 4 2 2 2 1 2 2 3 4 
7 0 1 3 2 2 3 0 1 1 2 
8 0 0 2 1 2 3 2 2 3 3 
9 1 4 4 2 2 1 1 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
9, 0
1
9, 0
9, 1
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 5 1 2 3 5 2 5 5 0 
2 1 3 1 2 0 2 1 0 1 0 
3 1 4 0 1 0 2 2 3 2 1 
4 1 3 3 3 4 2 0 1 1 2 
5 2 3 2 2 1 0 1 1 2 5 
6 0 4 2 2 2 1 2 2 3 4 
7 0 1 3 2 2 3 0 1 1 2 
8 0 0 2 1 2 3 2 2 3 3 
9 0 3 4 2 2 1 1 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
9, 1
3
9, 1
9, 2
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 5 1 2 3 5 2 5 5 0 
2 1 3 1 2 0 2 1 0 1 0 
3 1 4 0 1 0 2 2 3 2 1 
4 1 3 3 3 4 2 0 1 1 2 
5 2 3 2 2 1 0 1 1 2 5 
6 0 4 2 2 2 1 2 2 3 4 
7 0 1 3 2 2 3 0 1 1 2 
8 0 0 2 1 2 3 2 2 3 3 
9 0 0 1 2 2 1 1 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
1, 2
1
1, 2
1, 1
2, 0
1
2, 0
2, 1
2, 2
1
2, 2
2, 1
2, 6
1
2, 6
1, 6
2, 8
1
2, 8
1, 8
3, 0
1
3, 0
3, 1
3, 3
1
3, 3
4, 3
3, 9
1
3, 9
4, 9
4, 0
1
4, 0
4, 1
4, 7
1
4, 7
3, 7
4, 8
1
4, 8
3, 8
5, 4
1
5, 4
4, 4
5, 6
1
5, 6
6, 6
5, 7
1
5, 7
6, 7
6, 5
1
6, 5
7, 5
7, 1
1
7, 1
6, 1
7, 7
1
7, 7
8, 7
7, 8
1
7, 8
6, 8
8, 3
1
8, 3
7, 3
9, 2
1
9, 2
8, 2
9, 5
1
9, 5
8, 5
9, 6
1
9, 6
8, 6
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 4 0 2 3 5 1 5 4 0 
2 0 1 0 2 0 2 0 0 0 0 
3 0 3 0 0 0 2 2 2 1 0 
4 0 2 3 2 3 2 0 0 0 1 
5 2 3 2 2 0 0 0 0 2 5 
6 0 3 2 2 2 0 1 1 2 4 
7 0 0 3 1 2 2 0 0 0 2 
8 0 0 1 0 2 2 1 1 3 3 
9 0 0 0 2 2 0 0 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
2, 3
2
2, 3
1, 3
3, 8
1
3, 8
3, 7
4, 9
1
4, 9
5, 9
5, 0
2
5, 0
5, 1
6, 6
1
6, 6
6, 7
8, 2
1
8, 2
7, 2
9, 3
2
9, 3
9, 4
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 4 0 0 3 5 1 5 4 0 
2 0 1 0 0 0 2 0 0 0 0 
3 0 3 0 0 0 2 2 1 0 0 
4 0 2 3 2 3 2 0 0 0 0 
5 0 1 2 2 0 0 0 0 2 4 
6 0 3 2 2 2 0 0 0 2 4 
7 0 0 2 1 2 2 0 0 0 2 
8 0 0 0 0 2 2 1 1 3 3 
9 0 0 0 0 0 0 0 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
3, 7
1
3, 7
3, 6
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 4 0 0 3 5 1 5 4 0 
2 0 1 0 0 0 2 0 0 0 0 
3 0 3 0 0 0 2 1 0 0 0 
4 0 2 3 2 3 2 0 0 0 0 
5 0 1 2 2 0 0 0 0 2 4 
6 0 3 2 2 2 0 0 0 2 4 
7 0 0 2 1 2 2 0 0 0 2 
8 0 0 0 0 2 2 1 1 3 3 
9 0 0 0 0 0 0 0 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
3, 6
1
3, 6
3, 5
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 4 0 0 3 5 1 5 4 0 
2 0 1 0 0 0 2 0 0 0 0 
3 0 3 0 0 0 1 0 0 0 0 
4 0 2 3 2 3 2 0 0 0 0 
5 0 1 2 2 0 0 0 0 2 4 
6 0 3 2 2 2 0 0 0 2 4 
7 0 0 2 1 2 2 0 0 0 2 
8 0 0 0 0 2 2 1 1 3 3 
9 0 0 0 0 0 0 0 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
1, 6
1
1, 6
1, 5
2, 1
1
2, 1
1, 1
3, 5
1
3, 5
2, 5
5, 1
1
5, 1
6, 1
7, 3
1
7, 3
6, 3
8, 6
1
8, 6
8, 5
8, 7
1
8, 7
8, 8
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 3 0 0 3 4 0 5 4 0 
2 0 0 0 0 0 1 0 0 0 0 
3 0 3 0 0 0 0 0 0 0 0 
4 0 2 3 2 3 2 0 0 0 0 
5 0 0 2 2 0 0 0 0 2 4 
6 0 2 2 1 2 0 0 0 2 4 
7 0 0 2 0 2 2 0 0 0 2 
8 0 0 0 0 2 1 0 0 2 3 
9 0 0 0 0 0 0 0 0 4 2 
�̰�ש�С�����
���Ҫ���Ŀ�꣺
2, 5
1
2, 5
1, 5
3, 1
2
3, 1
4, 1
4, 5
2
4, 5
4, 4
6, 1
2
6, 1
6, 2
7, 2
�����鲻����Ҫ��
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 2 5 2 2 2 4 0 2 3 0 
1 2 3 0 0 3 3 0 5 4 0 
2 0 0 0 0 0 0 0 0 0 0 
3 0 1 0 0 0 0 0 0 0 0 
4 0 0 3 2 1 0 0 0 0 0 
5 0 0 2 2 0 0 0 0 2 4 
6 0 0 0 1 2 0 0 0 2 4 
7 0 0 2 0 2 2 0 0 0 2 
8 0 0 0 0 2 1 0 0 2 3 
9 0 0 0 0 0 0 0 0 4 2 

 *
 *
 */