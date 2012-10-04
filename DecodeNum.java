/*
 * ****
翻译数字串，类似于电话号码翻译：给一个数字
串，比如12259，映射到字母数组，
比如，1 -> a， 2-> b，... ， 12 -> l ，... 26-> z。
那么，12259 -> lyi 或 abbei 或 lbei 或 abyi。
输入一个数字串，判断是否能转换成字符串，如果能，则打印所以有可能的转换成的字符串。
 
 ***
 **/

package interview;

import java.util.HashMap;
import java.util.Map;

public class DecodeNum {

	private Map<Integer, Character> map;

	public DecodeNum()
	{
		map = new HashMap<Integer, Character>();
		init();
	}

	//初始化数字-字母对应的map容器
	private void init()
	{
		char cha = 'a';
		for(int i = 1; i <= 26; i++, cha++)
			map.put(i, cha);
	}

	//解码数字
	public void decode(int num)
	{
		String s = "";
		int count = countNumd(num);
		System.out.println("待解码数字：");
		System.out.println(num);
		System.out.println("可解码为：");
		decoding(num, s, 0, count);
	}

	//num为待解码数字，s为当前已解码的字符串，ccount为数字已解码位数，count为数字总位数
	private void decoding(int num, String s, int ccount, int count) {
		// TODO Auto-generated method stub
		if(num == 0)
		{
			if(ccount == count)
			{
				reversOut(s);
			}
			return;
		}
		int temp = num;
		String ttemp = s;
		String htemp = s;
		//一位数计数
		int scount = ccount;
		//两位数计数
		int dcount = ccount;

		//与100的余数
		int hun = temp % 100;
		//与10的余数
		int ten = temp % 10;

		//针对一位数移动，与100相余是两位数的，解码末位和首位并移动
		if(hun >= 10)
		{
			//末位不为零，取末位，移动一位
			if(ten > 0)
			{
				htemp += map.get(ten);
				dcount++;
				decoding(temp/10, htemp, dcount, count);
			}
			//末位为0的整数，无法取数，移动两位
			else if (ten == 0)
			{
				dcount += 2;
				htemp += map.get(hun);
				decoding(temp/100, htemp, dcount, count);
			}
			//两位数可构成字母的
			if(hun <= 26)
			{
				scount += 2;
				ttemp += map.get(hun);
				decoding(temp/100, ttemp, scount, count);
			}
		}
		//与100相余是一位数，只对末位进行记录和解码
		else
		{
			if(hun == 0)
			{
				System.out.println("存在无法转换的数");
				return;
			}

			//取数，移动一位
			if(temp/100 != 0)
			{
				dcount++;
				htemp += map.get(ten);
				decoding(temp/10, htemp, dcount, count);
			}
			htemp += map.get(ten);
			dcount++;
			decoding(temp/10, htemp, dcount, count);
		}
	}

	//反转字符串
	private void reversOut(String s) {
		// TODO Auto-generated method stub
		for(int i = s.length()-1; i >= 0; i--)
			System.out.print(s.charAt(i));
		System.out.println();
	}

	//计算数字位数
	private int countNumd(int num) {
		// TODO Auto-generated method stub
		int count = 0;
		for(;num != 0;num /= 10)
			count++;
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecodeNum dn = new DecodeNum();
		dn.decode(212);
	}

}