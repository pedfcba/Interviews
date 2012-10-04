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

	//������
	public void midOut()
	{
		System.out.println("�۰뷨��");
		System.out.println(getMid(a,b));
		System.out.println("�鲢����");
		System.out.println(merge(a,b));
	}


	//�ж����������Ϊ������ż��
	private boolean isOdd(int a, int b)
	{
		if ((a+b)%2 == 0)
			return false;
		else
			return true;
	}
	//��ȡA��B������������Ԫ�ص���λ��
	private double getMid(int[] arraya, int[] arrayb)
	{
		if(arraySize(arraya) < arraySize(arrayb))
			return getMid(arrayb,arraya);
		else
		{
			//����A��B��Ԫ�ظ���
			int asize = arraySize(arraya);
			int bsize = arraySize(arrayb);

			//�ж�Ԫ��Ϊ������ż����
			final boolean isodd = isOdd(asize, bsize);

//			System.out.println(isodd);

			//Ԫ��������һ��
			int half = (asize + bsize + 1)/2;

			//����A��������
			int alow = 0;
			int ahigh = half-1;
			//��ǰ�Ƚϵ�A����Ԫ���±�
			int amid = ahigh;

			//����B��������
			int blow = 0;
			int bhigh = bsize - 1;
			//��ǰ�Ƚϵ�B����Ԫ���±�
			int bmid = blow;
/*
 * *****����ǵĳ�ʼλ��*****
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
			//��A�������ܳ��ȵ�half����Ϊ���Ѱ����λ��
			else
			{
				while(true)
				{
/*
 * *****����ǵĵ�ǰλ��*****
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
					//ȷ����a������ahigh��bmid֮ǰ����ahighС,amid����bmid-blow
					else if(arraya[amid] > arrayb[bmid])
					{

						ahigh = amid;
						blow = bmid;
						bmid = arrayPos(arrayb, blow, bhigh);		
						amid -= bmid - blow;							
						//�Ѵ����޵ģ�ֱ������	
						//B�޷��ҵ���arraya[amid]��������ˣ����º��arraya[amid]�ٴ���bmid�Ƚ�
						if(bmid == bhigh && bmid == blow && ahigh == amid)
						{	
							if(amid > 0)
								amid--;
							break;
						}
					}
					//ȷ����a�ĺ�������alow,b������bhigh,alow֮ǰ����bhighС��amidǰ��bmid - blow
					else 
					{
						alow = amid;
						bhigh = bmid;
						bmid = arrayPos(arrayb, blow, bhigh);
						amid += bhigh - bmid;
						//��ǰ�Ƚ�Ԫ���Ѵﵽ���Ƚ�Ԫ�ص�����
						if(bmid == bhigh && alow == amid)
						{
							if(bmid > 0)
								bmid--;
							break;
						}
					}
				}
			}

			//���������
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
			//ż�������
			else 
			{
				//amid,bmid֮�µ���������С��arraya[amid]
				if(arraya[amid] > arrayb[bmid])
				{
					//arraya[amid]֮ǰ��half-1������С�ģ�Ϊ��һ����λ��
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
				//amid,bmid֮�µ���������С��arrayb[bmid]
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
				//����
				return (arraya[amid]+arrayb[bmid])/2.0;
			}



			/*
			while(true)
			{	
				System.out.println("amid:");
				System.out.println(amid);
				System.out.println("bmid:");
				System.out.println(bmid);
				//��a[amid]����b[bmid]��b[bmid]��֮ǰ��Ԫ�ض���a[amid]С����ʼ��b�б�a�����ֵ
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
				//���򣬿�ʼ��a�б�b�����ֵ
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
	//�ҳ�array��ָ����Χ�ڱ�i��������±�
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

	//��ȡ����arrayָ����Χ����λ�ã�һ��ǰ��[high-low+1]/2
	private int arrayPos(int[] array, int low, int high)
	{
		if (high-low != 1 && high < arraySize(array) && low >= 0)
			return low + (high - low)/2;
		else 
			return high;
	}

	//�鲢��
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

			//�����󳬳���Χ��
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

		//������λ��,��С��Ϊ��λ��
		if(isodd)
		{
			if(a[as] > b[bs])
				return b[bs];
			else 
				return a[as];
		}
		else
		{
			//�жϵڶ�����λ�������ؾ�ֵ
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

		System.out.println("����A:");
		for(int i = 0; i < this.arraySize(a); i++)
		{
			System.out.println(a[i]);
		}
		System.out.println("����B:");
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
