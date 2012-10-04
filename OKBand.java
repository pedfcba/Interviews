/*
 * ����һ���������󣬴���һ�����㣬
 * �Ծ���������Ԫ�ؼ�һʱ����Ҫ�����ڣ��������ң�Ԫ��Ҳ��һ��
 * �ָ���һ���������ж����Ƿ��ܹ���һ��ȫ����󾭹���������õ���
 */

package interview;

import java.util.LinkedList;
import java.util.List;

public class OKBand {

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

	public OKBand()
	{
		map = new int[MAXSIZE][MAXSIZE];
		coordi = new LinkedList<Coordinate>();
	}

	//������
	public void past(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("�������");
			System.out.println("���꣺" + x + ", " + y);
			return;
		}
		map[x][y]++;
		if(x != 0)
			map[x-1][y]++;
		if(x != MAXSIZE-1)
			map[x+1][y]++;
		if(y != 0)
			map[x][y-1]++;
		if(y != MAXSIZE-1)
			map[x][y+1]++;
	}

	//һ�νҵ��Ը�����Ϊ���ĵ����н���
	public void uncover(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("�������");
			System.out.println("���꣺" + x + ", " + y);
			return;
		}
		int least = map[x][y];
		if(x != 0 && map[x-1][y] < least)
			least = map[x-1][y];
		if(x != MAXSIZE-1 && map[x+1][y] < least)
			least = map[x+1][y];
		if(y != 0 && map[x][y-1] < least)
			least = map[x][y-1];
		if(y != MAXSIZE-1 && map[x][y+1] < least)
			least = map[x][y+1];
		
		map[x][y] -= least;
		if(x != 0)
			map[x-1][y] -= least;
		if(x != MAXSIZE-1)
			map[x+1][y] -= least;
		if(y != 0)
			map[x][y-1] -= least;
		if(y != MAXSIZE-1)
			map[x][y+1] -= least;
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

	//�ӱ�Ե��ʼ�ҽ���
	private void uncoverOKBand()
	{
		int least = Integer.MAX_VALUE;
		//�ҵ���������Ϊ��
		boolean found = false;
		//�ҵ���ʼλ�ñ�Ե�����꼯
		for(int i = 0; i < MAXSIZE; i++)
			for(int j = 0; j < MAXSIZE; j++)
			{
				if(map[i][j] == 0 || map[i][j] > least)
					continue;
				else
				{
					if (map[i][j] < least)
					{
						least = map[i][j];
						coordi.clear();
					}
					coordi.add(new Coordinate(i,j));
				}
			}
		//��������Ҫ�󣬷���
		if(coordi.size() == 0)
		{
			System.out.println("�������");
			return;
		}
		//����ȡ�õ����꼯�ҽ���
		for (int i = 0; i < coordi.size(); i++)
		{
			if(map[coordi.get(i).x][coordi.get(i).y] == 0)
				continue;
			Coordinate target = findTarget(coordi.get(i).x, coordi.get(i).y);
			if(target.x == -1)
			{
				continue;
			}
			found = true;
			System.out.println("�ҵ�: " + target.x + ", " + target.y);
			uncover(target.x, target.y);
		}
		coordi.clear();
		//�����鲻����Ҫ�󣬷���
		if(found == false)
		{
			System.out.println("�����鲻����Ҫ��");
			return;
		}
		System.out.println("����Ѱ���С�����");
		//������һ��Ѱ��
		uncoverOKBand();
	}
	
	//�ҵ���Ҫ�ҿ��Ľ�����������
	private Coordinate findTarget(int x, int y) {
		// TODO Auto-generated method stub
		//����,�ұ�С�ڣ����ϵȣ��ϱߴ��ڵ���
		if(x > 0 && y < MAXSIZE-1 && map[x][y+1] < map[x][y] && map[x-1][y+1] == map[x][y] && map[x-1][y] >= map[x][y])
			return new Coordinate(x-1, y);
		//���ң��±�С�ڣ����µ��ڣ��ұߴ��ڵ���
		if(x < MAXSIZE-1 && y < MAXSIZE-1 && map[x+1][y] < map[x][y] && map[x+1][y+1] == map[x][y] && map[x][y+1] >= map[x][y])
			return new Coordinate(x, y+1);
		//���£����С�ڣ����µ��ڣ��±ߴ��ڵ���
		if(x < MAXSIZE-1 && y > 0 && map[x][y-1] < map[x][y] && map[x+1][y-1] == map[x][y] && map[x+1][y] >= map[x][y])
			return new Coordinate(x+1, y);
		//�����ϱ�С�ڣ����ϵ��ڣ���ߴ��ڵ���
		if(x > 0 && y > 0 && y < MAXSIZE-1 && map[x-1][y] < map[x][y] && map[x-1][y-1] == map[x][y] && map[x][y-1] >= map[x][y])
			return new Coordinate(x, y-1);
		//û���ҵ�
		return new Coordinate(-1, -1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OKBand ok = new OKBand();
		ok.past(0, 0);
		ok.past(1, 2);
		ok.past(0, 4);
		ok.past(0, 4);
		ok.past(3, 3);
		ok.past(3, 3);
		ok.past(9, 0);
		ok.past(9, 9);
		ok.past(0, 9);

		ok.output();

		ok.uncoverOKBand();
		ok.output();
	}
}


/*
 * 
 * ***********************result**************************
 * 
 * ��ǰ״̬
X 0 1 2 3 4 5 6 7 8 9 
0 1 1 1 2 2 2 0 0 1 1 
1 1 1 1 1 2 0 0 0 0 1 
2 0 0 1 2 0 0 0 0 0 0 
3 0 0 2 2 2 0 0 0 0 0 
4 0 0 0 2 0 0 0 0 0 0 
5 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 0 0 0 0 0 0 0 
8 1 0 0 0 0 0 0 0 0 1 
9 1 1 0 0 0 0 0 0 1 1 
�ҵ�: 0, 9
�ҵ�: 1, 2
�ҵ�: 9, 9
�ҵ�: 9, 0
����Ѱ���С�����
�ҵ�: 0, 0
����Ѱ���С�����
�ҵ�: 0, 4
�ҵ�: 3, 3
����Ѱ���С�����
�������
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
