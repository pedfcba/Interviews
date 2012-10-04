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
		//找到起始位置边缘的坐标集
		for(int i = 0; i < MAXSIZE; i++)
			for(int j = 0; j < MAXSIZE; j++)
			{
				if(map[i][j] == 0)
					continue;
				else
				{
					if (map[i][j] > least)
						continue;
					if (map[i][j] < least)
					{
						least = map[i][j];
						coordi.clear();
					}
					coordi.add(new Coordinate(i,j));
				}
			}
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
			System.out.println("揭掉: " + target.x + ", " + target.y);
			if(target.x == -1)
			{
				System.out.println("无法继续完成状态");
				output();
				return;
			}
			uncover(target.x, target.y);
		}
		coordi.clear();
		System.out.println("继续寻找中。。。");
		//进入下一轮寻找
		uncoverOKBand();
	}
	
	//找到需要揭开的胶布所在坐标
	private Coordinate findTarget(int x, int y) {
		// TODO Auto-generated method stub
		//在上,右边小于，右上等，上边大于等于
		if(x > 0 && y < MAXSIZE-1 && map[x][y+1] < map[x][y] && map[x-1][y-1] == map[x][y] && map[x-1][y] >= map[x][y])
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
		ok.past(1, 1);
		ok.past(1, 2);
		ok.past(0, 4);
		ok.past(0, 4);
		ok.past(3, 3);
		ok.past(3, 3);

		ok.output();

		ok.uncoverOKBand();
		ok.output();
	}

}
