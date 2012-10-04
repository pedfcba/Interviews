/*
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
	 * 对指定坐标及其相邻坐标进行减操作，减去四向中最大的量
	 * 
	 * @param x 目标横坐标 
	 * @param y 目标纵坐标
	 * @param top 一次最多能进行的减操作
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
		//一次最多能取走的量
		int top = 0;	
		//另一个需要进行减操作的坐标,选四向中最大者
		Coordinate half = new Coordinate(x, y);
		if(x > 0 && map[x-1][y] > top)
		{
			top = map[x-1][y];
			half.x = x-1;
		}
		if(x < MAXSIZE-1 && map[x+1][y] > top)
		{
			top = map[x+1][y];
			half.x = x+1;
		}
		if(y > 0 && map[x][y-1] > top)
		{
			top = map[x][y-1];
			half.y = y-1;
		}
		if(y < MAXSIZE-1 && map[x][y+1] > top)
		{
			top = map[x][y+1];
			half.y = y+1;
		}

		//拿走
		if(map[x][y] != 0)
			map[x][y] -= top;
		map[half.x][half.y] -= top;

		if(top == 0)
			return false;
		else
			return true;
	}


	/**
	 * 从数组中找到最大值并与其四向参数中的最大值进行减操作
	 * 
	 * 
	 * @param found 记录是否找到合适的两个坐标进行减操作，满足则返回真
	 * @param top 记录数组中最大的整数值
	 */
	public void carryBrick()
	{
		//满足要求则置为真
		boolean found = false;
		//堆放最高的高度
		int top = 0;

		//找到高度最高的坐标并记录
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
		br.put(0, 0);
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
当前状态
X 0 1 2 3 4 5 6 7 8 9 
0 2 3 0 0 0 0 0 0 1 2 
1 1 2 0 0 0 0 0 0 0 1 
2 0 0 0 0 0 0 0 0 0 0 
3 0 0 0 0 0 0 0 0 0 0 
4 0 0 0 0 0 0 0 0 0 0 
5 0 0 0 0 0 0 0 0 0 0 
6 0 0 0 0 0 0 0 0 0 0 
7 0 0 0 0 0 0 0 0 0 0 
8 2 0 0 0 0 0 0 0 0 0 
9 2 0 0 0 0 0 0 0 2 2 
这次要搬的目标：
0, 1
继搬砖中。。。
这次要搬的目标：
0, 0
0, 9
8, 0
9, 8
继搬砖中。。。
这次要搬的目标：
0, 0
0, 8
继搬砖中。。。
砖搬完了！
当前状态
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