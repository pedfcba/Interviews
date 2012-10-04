/*
 * 
 * 一个小镇有N个人，有些人互相认识，有些不，且认识关系不一定是对称的，
 * 比如，我认识你，你不一定认识我。现在小镇要找一个有名且公正的镇长，
 * 要求两个条件：
 * 1.所有镇上的人都认识他；
 * 2.除了他自己，不认识镇上的任何人。写个程序来帮他们找到符合条件的所有人选。
 * 
 */

package interview;


import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Election {

	//小镇人口
	private int population;
	//人物名单
	private List<Villager> villagers;
	
	private class Villager
	{
		//名字
		private String name;
		//认识的人
		private BitSet know;
		//被谁认识
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
		//符合条件的那个人
		Villager target = new Villager();
		target.know = new BitSet(population);
		target.known = new BitSet(population);
		target.name = "Bob";
		//只认识自己
		target.know.set(0,true);
		target.known.set(0, true);
		villagers.add(target);
		
		//初始化村民信息
		//认识谁
		for(int j = 1; j < population; j++)
		{
			Villager villager = new Villager();
			villagers.add(villager);
			villager.name = "村民" + j;
			Random rand = new Random();
			//认识的人
			villager.know.set(0, rand.nextInt(population)+1, true);
			//大家都认识Bob
			villager.know.set(0, true);
			//认识自己
			villager.know.set(j, true);
			villager.known.set(j, true);
		}

		//初始化每位村民的know位图
		for (int j = 0; j < population; j++)
		{
			Villager v = villagers.get(j);
			for (int k = 0; k < population; k++)
			{
				//该村民被j认识了
				if(v.know.get(k) == true)
				{
					villagers.get(k).known.set(j, true);
				}
			}
		}
		//村民关系
		System.out.println("村民关系");
		for (int i = 0; i < population; i++)
		{
			System.out.print(villagers.get(i).name + "认识: \t");
			System.out.println(villagers.get(i).know);
			System.out.print("认识他的：\t");
			System.out.println(villagers.get(i).known);
			System.out.println();
		}
	}
	
	//选举过程
	public void vote()
	{
		for(int i = 0; i < population; i++)
		{
			Villager challanger = villagers.get(i);
			//只认识自己且大家都认识他的，当选
			if(challanger.know.cardinality() == 1 && challanger.known.cardinality() == population)
				System.out.println(challanger.name + "当选了");
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
村民关系
Bob认识: 	{0}
认识他的：	{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}

村民1认识: 	{0, 1, 2, 3, 4, 5, 6}
认识他的：	{1, 2, 3, 4, 5, 6, 7, 8, 9}

村民2认识: 	{0, 1, 2}
认识他的：	{1, 2, 3, 5, 6, 7, 8, 9}

村民3认识: 	{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
认识他的：	{1, 3, 5, 6, 7, 8, 9}

村民4认识: 	{0, 1, 4}
认识他的：	{1, 3, 4, 5, 6, 7, 8, 9}

村民5认识: 	{0, 1, 2, 3, 4, 5, 6, 7, 8}
认识他的：	{1, 3, 5, 6, 7, 8, 9}

村民6认识: 	{0, 1, 2, 3, 4, 5, 6}
认识他的：	{1, 3, 5, 6, 7, 8, 9}

村民7认识: 	{0, 1, 2, 3, 4, 5, 6, 7, 8}
认识他的：	{3, 5, 7}

村民8认识: 	{0, 1, 2, 3, 4, 5, 6, 8}
认识他的：	{3, 5, 7, 8}

村民9认识: 	{0, 1, 2, 3, 4, 5, 6, 9}
认识他的：	{3, 9}

Bob当选了


*
*
*
*/
