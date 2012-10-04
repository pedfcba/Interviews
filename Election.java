/*
 * 
 * һ��С����N���ˣ���Щ�˻�����ʶ����Щ��������ʶ��ϵ��һ���ǶԳƵģ�
 * ���磬����ʶ�㣬�㲻һ����ʶ�ҡ�����С��Ҫ��һ�������ҹ������򳤣�
 * Ҫ������������
 * 1.�������ϵ��˶���ʶ����
 * 2.�������Լ�������ʶ���ϵ��κ��ˡ�д���������������ҵ�����������������ѡ��
 * 
 */

package interview;


import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Election {

	//С���˿�
	private int population;
	//��������
	private List<Villager> villagers;
	
	private class Villager
	{
		//����
		private String name;
		//��ʶ����
		private BitSet know;
		//��˭��ʶ
		private BitSet known;
		
		Villager()
		{
			name = "";
			know = new BitSet(population);
			known = new BitSet(population);
		}
	}
	
	public Election(int n)
	{
		population = n;
		villagers = new LinkedList<Villager>();
		iniRelation();
	}
	private void iniRelation() {
		// TODO Auto-generated method stub
		//�����������Ǹ���
		Villager target = new Villager();
		target.know = new BitSet(population);
		target.known = new BitSet(population);
		target.name = "Bob";
		//ֻ��ʶ�Լ�
		target.know.set(0,true);
		target.known.set(0, true);
		villagers.add(target);
		
		//��ʼ��������Ϣ
		//��ʶ˭
		for(int j = 1; j < population; j++)
		{
			Villager villager = new Villager();
			villagers.add(villager);
			villager.name = "����" + j;
			Random rand = new Random();
			//��ʶ����
			villager.know.set(0, rand.nextInt(population)+1, true);
			//��Ҷ���ʶBob
			villager.know.set(0, true);
			//��ʶ�Լ�
			villager.know.set(j, true);
			villager.known.set(j, true);
		}

		//��ʼ��ÿλ�����knowλͼ
		for (int j = 0; j < population; j++)
		{
			Villager v = villagers.get(j);
			for (int k = 0; k < population; k++)
			{
				//�ô���j��ʶ��
				if(v.know.get(k) == true)
				{
					villagers.get(k).known.set(j, true);
				}
			}
		}
		//�����ϵ
		System.out.println("�����ϵ");
		for (int i = 0; i < population; i++)
		{
			System.out.print(villagers.get(i).name + "��ʶ: \t");
			System.out.println(villagers.get(i).know);
			System.out.print("��ʶ���ģ�\t");
			System.out.println(villagers.get(i).known);
			System.out.println();
		}
	}
	
	//ѡ�ٹ���
	public void vote()
	{
		for(int i = 0; i < population; i++)
		{
			Villager challanger = villagers.get(i);
			//ֻ��ʶ�Լ��Ҵ�Ҷ���ʶ���ģ���ѡ
			if(challanger.know.cardinality() == 1 && challanger.known.cardinality() == population)
				System.out.println(challanger.name + "��ѡ��");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Election el = new Election(10);
		el.vote();
		

	}

}


/*
 * 
 * 
 * 
 * 
 * 
 * 
 * ***********result***********
 * 
 * 
�����ϵ
Bob��ʶ: 	{0}
��ʶ���ģ�	{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}

����1��ʶ: 	{0, 1, 2, 3, 4, 5, 6}
��ʶ���ģ�	{1, 2, 3, 4, 5, 6, 7, 8, 9}

����2��ʶ: 	{0, 1, 2}
��ʶ���ģ�	{1, 2, 3, 5, 6, 7, 8, 9}

����3��ʶ: 	{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
��ʶ���ģ�	{1, 3, 5, 6, 7, 8, 9}

����4��ʶ: 	{0, 1, 4}
��ʶ���ģ�	{1, 3, 4, 5, 6, 7, 8, 9}

����5��ʶ: 	{0, 1, 2, 3, 4, 5, 6, 7, 8}
��ʶ���ģ�	{1, 3, 5, 6, 7, 8, 9}

����6��ʶ: 	{0, 1, 2, 3, 4, 5, 6}
��ʶ���ģ�	{1, 3, 5, 6, 7, 8, 9}

����7��ʶ: 	{0, 1, 2, 3, 4, 5, 6, 7, 8}
��ʶ���ģ�	{3, 5, 7}

����8��ʶ: 	{0, 1, 2, 3, 4, 5, 6, 8}
��ʶ���ģ�	{3, 5, 7, 8}

����9��ʶ: 	{0, 1, 2, 3, 4, 5, 6, 9}
��ʶ���ģ�	{3, 9}

Bob��ѡ��


*
*
*
*/
