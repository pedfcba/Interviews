/*
 * 对于一个整数矩阵，存在一种运算，
 * 对矩阵中任意元素加一时，需要其相邻（上下左右）元素也加一。
 * 现给出一正数矩阵，判断其是否能够由一个全零矩阵经过上述运算得到。
 */

package interview;

import java.util.LinkedList;
import java.util.List;

public class OKBand {

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

	public OKBand()
	{
		map = new int[MAXSIZE][MAXSIZE];
		coordi = new LinkedList<Coordinate>();
	}

	//贴胶布
	public void past(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("输入错误！");
			System.out.println("坐标：" + x + ", " + y);
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

	//一次揭掉以该坐标为中心的所有胶布
	public void uncover(int x, int y)
	{
		if(x < 0 || x >= MAXSIZE || y < 0 || y >= MAXSIZE)
		{
			System.out.println("输入错误！");
			System.out.println("坐标：" + x + ", " + y);
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

	//从边缘开始揭胶布
	private void uncoverOKBand()
	{
		int least = Integer.MAX_VALUE;
		//找到胶布的置为真
		boolean found = false;
		//找到起始位置边缘的坐标集
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
		//数组满足要求，返回
		if(coordi.size() == 0)
		{
			System.out.println("数组空了");
			return;
		}
		//按照取得的坐标集揭胶布
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
			System.out.println("揭掉: " + target.x + ", " + target.y);
			uncover(target.x, target.y);
		}
		coordi.clear();
		//若数组不满足要求，返回
		if(found == false)
		{
			System.out.println("该数组不满足要求。");
			return;
		}
		System.out.println("继续寻找中。。。");
		//进入下一轮寻找
		uncoverOKBand();
	}
	
	//找到需要揭开的胶布所在坐标
	private Coordinate findTarget(int x, int y) {
		// TODO Auto-generated method stub
		//在上,右边小于，右上等，上边大于等于
		if(x > 0 && y < MAXSIZE-1 && map[x][y+1] < map[x][y] && map[x-1][y+1] == map[x][y] && map[x-1][y] >= map[x][y])
			return new Coordinate(x-1, y);
		//在右，下边小于，右下等于，右边大于等于
		if(x < MAXSIZE-1 && y < MAXSIZE-1 && map[x+1][y] < map[x][y] && map[x+1][y+1] == map[x][y] && map[x][y+1] >= map[x][y])
			return new Coordinate(x, y+1);
		//在下，左边小于，左下等于，下边大于等于
		if(x < MAXSIZE-1 && y > 0 && map[x][y-1] < map[x][y] && map[x+1][y-1] == map[x][y] && map[x+1][y] >= map[x][y])
			return new Coordinate(x+1, y);
		//在左，上边小于，左上等于，左边大于等于
		if(x > 0 && y > 0 && y < MAXSIZE-1 && map[x-1][y] < map[x][y] && map[x-1][y-1] == map[x][y] && map[x][y-1] >= map[x][y])
			return new Coordinate(x, y-1);
		//没有找到
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
 * 当前状态
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
揭掉: 0, 9
揭掉: 1, 2
揭掉: 9, 9
揭掉: 9, 0
继续寻找中。。。
揭掉: 0, 0
继续寻找中。。。
揭掉: 0, 4
揭掉: 3, 3
继续寻找中。。。
数组空了
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
