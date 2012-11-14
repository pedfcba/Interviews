/* **********未实现******************
 * 对于一个整数矩阵，存在一种运算，
 * 对矩阵中任意元素加一时，需要其相邻（上下左右）某一个元素也加一。
 * 现给出一正数矩阵，判断其是否能够由一个全零矩阵经过上述运算得到。
 */


package interview;



import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Brick {

	//初始数组
	public static int map[][];
	//数组大小
	private final int MAXSIZE = 10;
	//待处理的坐标列表，存储数组中最小数所在的坐标
	List<Coordinate> coordi;

	//坐标
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
	 * 对指定坐标及其相邻坐标进行加1操作
	 * 
	 * @param x 目标横坐标 
	 * @param y 目标纵坐标
	 * @param rand 生成随机数，按照随机数的大小确定对哪个相邻坐标做加操作
	 * 
	 * @return 若产生减操作，返回true，否则返回false
	 */
	public void put(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("输入错误！");
			System.out.println("坐标：" + x + ", " + y);
			return;
		}
		//本坐标加一
		map[x][y]++;
		//确定摆放的方向
		Random rand = new Random();
		int direction = rand.nextInt(4001);

		//任一方向坐标加一
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
	 * 对输入坐标及其相邻坐标进行减操作，两坐标同时减去输入坐标的数值
	 * 
	 * @param x 目标横坐标 
	 * @param y 目标纵坐标
	 * @param top 记录四邻元素中的最大值，确定进行减操作的第二个元素
	 * @param half 除输入坐标外，需要进行减操作的第二个坐标
	 * 
	 * @return 若产生减操作，返回true，否则返回false
	 */
	public boolean carry(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("输入错误！");
			System.out.println("坐标：" + x + ", " + y);
			return false;
		}
		//寻找四邻元素中的最大值
		int top = 0;	
		//记录另一个坐标的位置
		int tempx = x;
		int tempy = y;
		//寻找另一个需要进行减操作的坐标,选四向中最大者
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


		//若未找到合适的元素，返回false
		if(top == 0)
			return false;
		else
		{
			Coordinate half = new Coordinate(tempx, tempy);
			//拿走
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
	 * 检查一个数组是否满足要求的条件
	 * 
	 * @param foundsingle 记录是否找到目标相邻元素只有一个不为0的
	 * @param comp 记录第一个目标临接元素非零的值
	 * @param sum 记录目标临接元素的和
	 * @param found 记录是否找到合适的两个坐标进行减操作
	 * @param top 记录数组中最小的整数值
	 */
	public void carryBrick()
	{
		//满足要求则置为真
		boolean found = false;
		//找到只有一个临接元素的置为真
		boolean foundsingle = false;
		//堆放的高度
		int top = Integer.MAX_VALUE;


		//找到临接元素中只有一个非零的元素坐标并记录
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
					//四邻相或等于四邻相加，说明该坐标只有一个临接元素不为0
					if(sum == comp && sum != 0)
					{
						foundsingle = true;
						coordi.add(new Coordinate(i,j));
					}
				}
			}


		/*
		 * 没有找到临接元素个数为一的元素
		 * 找到边缘且最小的元素
		 * 及与其临接的最大元素
		 * 记录并进行减操作
		*/
		if(foundsingle == false)
		{
			//找到最边缘且最小的坐标并记录
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
		
		//若没有坐标记录，则数组满足要求，返回
		if(coordi.size() == 0)
		{
			System.out.println("砖搬完了！");
			return;
		}
		
		//按照坐标集进行减操作，相邻元素选择四向中数值最大的
		System.out.println("这次要搬的目标：");
		for(int i = 0; i < coordi.size(); i++)
		{
			if(map[coordi.get(i).x][coordi.get(i).y] == 0)
				continue;
			System.out.println(coordi.get(i).x + ", " + coordi.get(i).y);
			found = carry(coordi.get(i).x, coordi.get(i).y);
		}
		if(found == false)
		{
			System.out.println("该数组不满足要求！");
			return;
		}
		//下一轮寻找
		coordi.clear();
		output();
		System.out.println("继搬砖中。。。");
		carryBrick();
	}

	//输出目前的数组情况
	public void output()
	{
		System.out.println("当前状态");
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
当前状态
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
这次要搬的目标：
1, 9
1
1, 9
1, 8
8, 0
2
8, 0
9, 0
当前状态
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
继搬砖中。。。
这次要搬的目标：
9, 0
1
9, 0
9, 1
当前状态
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
继搬砖中。。。
这次要搬的目标：
9, 1
3
9, 1
9, 2
当前状态
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
继搬砖中。。。
这次要搬的目标：
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
当前状态
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
继搬砖中。。。
这次要搬的目标：
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
当前状态
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
继搬砖中。。。
这次要搬的目标：
3, 7
1
3, 7
3, 6
当前状态
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
继搬砖中。。。
这次要搬的目标：
3, 6
1
3, 6
3, 5
当前状态
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
继搬砖中。。。
这次要搬的目标：
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
当前状态
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
继搬砖中。。。
这次要搬的目标：
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
该数组不满足要求！
当前状态
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