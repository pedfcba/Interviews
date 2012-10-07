/*
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
		//��һ����Ҫ���м�����������,ѡ��������С��
		Coordinate half = new Coordinate(x, y);
		if(x > 0 && map[x-1][y] > top && map[x-1][y] > 0)
		{
			top = map[x-1][y];
			half.x = x-1;
		}
		if(x < MAXSIZE-1 && map[x+1][y] > top && map[x+1][y] > 0)
		{
			top = map[x+1][y];
			half.x = x+1;
		}
		if(y > 0 && map[x][y-1] > top && map[x][y-1] > 0)
		{
			top = map[x][y-1];
			half.y = y-1;
		}
		if(y < MAXSIZE-1 && map[x][y+1] > top && map[x][y+1] > 0)
		{
			top = map[x][y+1];
			half.y = y+1;
		}

		//����
		map[half.x][half.y] -= map[x][y];
		map[x][y] -= map[x][y];

		//��δ�ҵ����ʵ�Ԫ�أ�����false
		if(top == 0)
			return false;
		else
			return true;
	}


	/**
	 * ���һ�������Ƿ�����Ҫ�������
	 * 
	 * @param foundsingle ��¼�Ƿ��ҵ�Ŀ������Ԫ��ֻ��һ����Ϊ0��
	 * @param compor ��¼Ŀ���ٽ�Ԫ������ֵ
	 * @param compsum ��¼Ŀ���ٽ�Ԫ�صĺ�
	 * @param found ��¼�Ƿ��ҵ����ʵ�����������м������������򷵻���
	 * @param top ��¼��������С������ֵ
	 */
	public void carryBrick()
	{
		//����Ҫ������Ϊ��
		boolean found = false;
		//�ҵ�ֻ��һ���ٽ�Ԫ�ص���Ϊ��
		boolean foundsingle = false;
		//�ѷŵĸ߶�
		int top = 0;


		//�ҵ��ٽ�Ԫ����ֻ��һ�������Ԫ�����겢��¼
		for(int i = 0; i < MAXSIZE; i++)
			for(int j = 0; j < MAXSIZE; j++)
			{
				int compor = 0;
				int compsum = 0;
				if (map[i][j] != 0)
				{
					if(i > 0)
					{
						compor |= map[i-1][j];
						compsum += map[i-1][j];
					}
					if(i < MAXSIZE-1)
					{
						compor |= map[i+1][j];
						compsum += map[i+1][j];
					}
					if(j > 0)
					{
						compor |= map[i][j-1];
						compsum += map[i][j-1];
					}
					if(j < MAXSIZE-1)
					{
						compor |= map[i][j+1];
						compsum += map[i][j+1];
					}
					//����������������ӣ�˵��������ֻ��һ���ٽ�Ԫ�ز�Ϊ0
					if(compsum == compor && compsum != 0)
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
					if (map[i][j] == 0 || map[i][j] < top)
						continue;
					else 
					{
						if(map[i][j] > top)
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
		br.put(0, 0);
		br.put(0, 0);
		br.put(0, 0);
		br.put(0, 1);
		br.put(0, 1);
		br.put(0, 1);
		br.put(0, 9);
		br.put(9, 0);
		br.put(9, 9);
		br.put(0, 0);
		br.put(0, 1);
		br.put(0, 9);
		br.put(9, 0);
		br.put(9, 9);
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
0 6 5 1 0 0 0 0 0 0 2 
1 3 1 0 0 0 0 0 0 0 2 
2 0 0 0 0 0 0 0 0 0 0 
3 0 0 0 0 0 0 0 0 0 0 
4 0 0 0 0 0 0 0 0 0 0 
5 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 0 0 0 0 0 0 0 
8 2 0 0 0 0 0 0 0 0 0 
9 2 0 0 0 0 0 0 0 2 2 
���Ҫ���Ŀ�꣺
0, 2
0, 9
1, 0
8, 0
9, 8
�̰�ש�С�����
���Ҫ���Ŀ�꣺
0, 0
1, 1
�̰�ש�С�����
ש�����ˣ�
��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 0 0 0 0 0 0 0 0 0 0 
1 0 0 0 0 0 0 0 0 0 0 
2 0 0 0 0 0 0 0 0 0 0 
3 0 0 0 0 0 0 0 0 0 0 
4 0 0 0 0 0 0 0 0 0 0 
5 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 0 0 0 0 0 0 0 
8 0 0 0 0 0 0 0 0 0 0 
9 0 0 0 0 0 0 0 0 0 0 

 *
 *
 */