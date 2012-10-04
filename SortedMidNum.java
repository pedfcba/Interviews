package interview;

import java.util.Random;

public class SortedMidNum {

	private final int MAXSIZE = 100;
	private int a[] = new int[MAXSIZE];
	private int b[] = new int[MAXSIZE];

	public SortedMidNum()
	{
		iniArray();
	}

	//输出结果
	public void midOut()
	{
		System.out.println("折半法：");
		System.out.println(getMid(a,b));
		System.out.println("归并法：");
		System.out.println(merge(a,b));
	}


	//判断两数组个数为奇数或偶数
	private boolean isOdd(int a, int b)
	{
		if ((a+b)%2 == 0)
			return false;
		else
			return true;
	}
	//获取A、B两数组中所有元素的中位数
	private double getMid(int[] arraya, int[] arrayb)
	{
		if(arraySize(arraya) < arraySize(arrayb))
			return getMid(arrayb,arraya);
		else
		{
			//数组A、B的元素个数
			int asize = arraySize(arraya);
			int bsize = arraySize(arrayb);

			//判断元素为奇数或偶数个
			final boolean isodd = isOdd(asize, bsize);

//			System.out.println(isodd);

			//元素总量的一半
			int half = (asize + bsize + 1)/2;

			//数组A的上下限
			int alow = 0;
			int ahigh = half-1;
			//当前比较的A数组元素下标
			int amid = ahigh;

			//数组B的上下限
			int blow = 0;
			int bhigh = bsize - 1;
			//当前比较的B数组元素下标
			int bmid = blow;
/*
 * *****各标记的初始位置*****
			System.out.println("start amid:");
			System.out.println(amid);
			System.out.println(arraya[amid]);
			System.out.println("start ahigh:");
			System.out.println(ahigh);
			System.out.println(arraya[ahigh]);
			System.out.println("start bmid:");
			System.out.println(bmid);
			System.out.println(arrayb[bmid]);
			System.out.println("start bhigh:");
			System.out.println(bhigh);
			System.out.println(arrayb[bhigh]);
* *****
*/

			if(arraya[ahigh] <= arrayb[blow])
			{
				if(isodd)
					return arraya[ahigh];
				else
				{
					if(ahigh+1 != asize && arraya[ahigh+1] < arrayb[bmid])
						return (arraya[ahigh] + arraya[ahigh+1])/2.0;
					else
						return (arraya[ahigh] + arrayb[blow])/2.0;
				}
			}
			//在A数组以总长度的half长度为起点寻找中位数
			else
			{
				while(true)
				{
/*
 * *****各标记的当前位置*****
					System.out.println("alow");
					System.out.println(alow);
					System.out.println("ahigh");
					System.out.println(ahigh);
					System.out.println("amid");
					System.out.println(amid);
					System.out.println("bmid");
					System.out.println(bmid);
					System.out.println("blow");
					System.out.println(blow);
					System.out.println("bhigh");
					System.out.println(bhigh);
 * *****
*/

					if(arraya[amid] == arrayb[bmid])
						return arraya[amid];
					//确定了a的上限ahigh，bmid之前都比ahigh小,amid后退bmid-blow
					else if(arraya[amid] > arrayb[bmid])
					{

						ahigh = amid;
						blow = bmid;
						bmid = arrayPos(arrayb, blow, bhigh);		
						amid -= bmid - blow;							
						//已达上限的，直接跳出	
						//B无法找到比arraya[amid]更大的数了，更新后的arraya[amid]再次与bmid比较
						if(bmid == bhigh && bmid == blow && ahigh == amid)
						{	
							if(amid > 0)
								amid--;
							break;
						}
					}
					//确定了a的后退下限alow,b的上限bhigh,alow之前都比bhigh小，amid前进bmid - blow
					else 
					{
						alow = amid;
						bhigh = bmid;
						bmid = arrayPos(arrayb, blow, bhigh);
						amid += bhigh - bmid;
						//当前比较元素已达到带比较元素的上限
						if(bmid == bhigh && alow == amid)
						{
							if(bmid > 0)
								bmid--;
							break;
						}
					}
				}
			}

			//奇数的情况
			if(isodd)
			{
				//[66]  [68 76]  >  [68] [66 76]
				//
				if(arraya[amid] > arrayb[bmid])
				{	
					return arraya[amid];
				}
				else 
				{
					return arrayb[bmid];
				}
			}
			//偶数的情况
			else 
			{
				//amid,bmid之下的所有数都小于arraya[amid]
				if(arraya[amid] > arrayb[bmid])
				{
					//arraya[amid]之前有half-1个比它小的，为第一个中位数
					if(amid + bmid + 1 == half - 1 )
					{
						if(bmid + 1 != bsize)
						{
							if( arraya[amid+1] < arrayb[bmid+1])
								return (arraya[amid]+arraya[amid+1])/2.0;
							else
								return (arraya[amid]+arrayb[bmid+1])/2.0;
						}
						else return (arraya[amid]+arraya[amid+1])/2.0;
					}
				}
				//amid,bmid之下的所有数都小于arrayb[bmid]
				else if(arraya[amid] < arrayb[bmid])
				{
					if(bmid + amid + 1 != half - 1)
					{
						if(arrayb[bmid+1] > arraya[amid+1])
							return (arrayb[bmid]+arraya[amid+1])/2.0;
						else
							return (arrayb[bmid]+arrayb[bmid+1])/2.0;
					}
					else
						return (arrayb[bmid] + arraya[amid+1])/2.0;
				}
				//其他
				return (arraya[amid]+arrayb[bmid])/2.0;
			}



			/*
			while(true)
			{	
				System.out.println("amid:");
				System.out.println(amid);
				System.out.println("bmid:");
				System.out.println(bmid);
				//若a[amid]大于b[bmid]则b[bmid]及之前的元素都比a[amid]小，开始找b中比a更大的值
				if (arraya[amid] > arrayb[bmid])
				{
					blow = bmid;
					ahigh = amid + half - (amid + bmid + 2);
					bmid = findBigger(arrayb, blow, bhigh, arraya[amid]);
					if(arrayb[bmid] < arraya[amid])
					{
						amid = amid + half - (amid + bmid + 2);
						return a[amid];
					}
				}
				//否则，开始找a中比b更大的值
				else
				{
					alow = amid;
					ahigh = amid + half - (amid + bmid + 2);
					if(bmid + half - (amid + bmid + 2) < bhigh)
						bhigh = bmid + half - (amid + bmid + 2); 
					amid = findBigger(arraya, alow, ahigh, arrayb[bmid]);	
				}
			}
			 */
		}
	}

	/*
	//找出array中指定范围内比i大的数的下标
	private int findBigger(int[] array, int low, int high, int i) {
		// TODO Auto-generated method stub
		int mid = arrayMid(array, low, high);
		while(low < high)
		{
			if(array[mid] <= i)
			{
				low = mid + 1;
			}
			else 
			{
				high = mid;
			}
			mid = arrayMid(array, low, high);
		}
		return mid;
	}	
	 */

	//获取数组array指定范围的新位置，一次前进[high-low+1]/2
	private int arrayPos(int[] array, int low, int high)
	{
		if (high-low != 1 && high < arraySize(array) && low >= 0)
			return low + (high - low)/2;
		else 
			return high;
	}

	//归并法
	private double merge(int[] a, int[] b)
	{		
		int asize = arraySize(a);
		int bsize = arraySize(b);
		final boolean isodd = isOdd(asize, bsize);

		if(asize < bsize)
			return merge(b,a);

		int half = (asize + bsize + 1)/2 - 1;


		int as = 0;
		int bs = 0;
		while(half > 0)
		{
			half--;
			if(a[as] > b[bs])
			{
				bs++;
			}
			else
			{
				as++;
			}		

			//增量后超出范围的
			if(as == asize)
			{
				as--;
				if(isodd)
					return b[bs];
				else
					return (b[bs] + a[as])/2.0;
			}		
			if(bs == bsize)
			{
				bs--;
				if(isodd)
					return a[as+half];
				else
					return (a[as+half+1] + a[as+half])/2.0;
			}	
		}

		//返回中位数,较小的为中位数
		if(isodd)
		{
			if(a[as] > b[bs])
				return b[bs];
			else 
				return a[as];
		}
		else
		{
			//判断第二个中位数，返回均值
			if(bs + 1 != bsize && a[as] > b[bs] && b[bs+1] < a[as])
				return (b[bs] + b[bs+1])/2.0;
			else if(as+1 != asize && a[as] < b[bs] && a[as+1] < b[bs])
				return (a[as] + a[as+1])/2.0;
			else 
				return (a[as] + b[bs])/2.0;
		}
	}

	private int[] putNum(int[] array)
	{
		Random ranlen = new Random();
		Random rand = new Random();
		array[0] = 1 + rand.nextInt(100);
		int inilen = ranlen.nextInt(5);
		for(int i = 1; i <= inilen; i++)
		{
			array[i] = array[i-1] + rand.nextInt(10);
		}
		return array;
	}

	private void iniArray()
	{
		/*
		int a[] = {2,4,6,8};
		int b[] = {3};
		this.a = a;
		this.b = b;
		 */
		putNum(a);
		putNum(b);	

		System.out.println("数组A:");
		for(int i = 0; i < this.arraySize(a); i++)
		{
			System.out.println(a[i]);
		}
		System.out.println("数组B:");
		for(int i = 0; i < this.arraySize(b); i++)
		{
			System.out.println(b[i]);
		}
		System.out.println("sizes:");
		System.out.println(this.arraySize(a));
		System.out.println(this.arraySize(b));
	}

	private int arraySize(int array[])
	{
		int size = 0;
		for(int s = 0; s < array.length; s++)
		{
			if(array[s] > 0)
				size++;
			else
				break;
		}
		return size;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedMidNum s = new SortedMidNum();
		s.midOut();

	}

}
