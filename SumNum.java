/*
 * 
 * дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ�ã������������¡�
 * 
 */
package interview;

public class SumNum {

	public int sum(int a, int b)
	{
		//������λ�Ľ��
		int result = a | b;
		System.out.println(result);
		//�н�λ��λ��
		int carry = a & b;
		System.out.println(carry);
		while(carry != 0)
		{
			//������λλ�õ�1
			int temp = result & (~carry);
			//��λ���ƣ�׼���ӵ������
			carry <<= 1;
			//����λ��ӵ������
			temp |= carry;
			//����Ƿ�����µĽ�λ
			carry &= result;
			result = temp;
		}
		System.out.println(result);
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SumNum sn = new SumNum();
		sn.sum(12,58);

	}

}
