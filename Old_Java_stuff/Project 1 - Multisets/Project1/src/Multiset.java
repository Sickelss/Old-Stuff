import java.util.ArrayList;

// Seth Sickels


public class Multiset<T>{
	ArrayList multiset;
	
	public Multiset()
	{
		multiset = new ArrayList<Element<T>>();
	}
	
	public Multiset(int item)
	{
		multiset = new ArrayList<Element<Integer>>();
		Element<Integer> element = new Element<Integer>();
		element.data = item;
		element.numOcc = 1;
		multiset.add(multiset.size() , element);
		
	}
	
	public Multiset<T> union(Multiset<T> set2)
	{
		System.out.println("Union");
		int size = this.multiset.size();
		Multiset<T> set3 = new Multiset<T>();
		int r = 1;
		
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> temp = new Element<T>();
			Element<T> search1 = (Element<T>) this.multiset.get(i);
			temp.data = search1.data;
			temp.numOcc = search1.numOcc;
			set3.multiset.add(temp);
			set3.multiset.set(i, temp);
		}
		
		for (int i=0; i < set2.multiset.size(); i++)
		{
			Element<T> temp = new Element<T>();
			Element<T> search2 = (Element<T>) set2.multiset.get(i);
			r=0;
			
			for (int j=0; j < set3.multiset.size(); j++)
			{
				Element<T> search3 = (Element<T>) set3.multiset.get(j);
				if (search2.data == search3.data)
				{
					temp.numOcc = search2.numOcc;
					search3.numOcc = Math.max(temp.numOcc, search3.numOcc);
					r = 1;
				}
			}
			if (r == 0)
			{
				temp.data = search2.data;
				temp.numOcc = search2.numOcc;
				set3.multiset.add(temp);
			}
		}
		
		
		return set3;
	}
	
	public Multiset<T> intersect(Multiset<T> set2)
	{
		System.out.println("Intersect");
		Multiset<T> set3 = new Multiset<T>();
		
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> search1 = (Element<T>) this.multiset.get(i);
			
			for (int j=0; j < set2.multiset.size(); j++)
			{
				Element<T> search2 = (Element<T>) set2.multiset.get(j);
				
				if (search1.data == search2.data)		//both sets contain the element.data
				{
					Element<T> temp = new Element<T>();
					temp.numOcc = Math.min(search1.numOcc, search2.numOcc);
					temp.data = search1.data;
					set3.multiset.add(temp);
					
				}
			}
			
		}
		
		return set3;
	}
	
	public Multiset<T> difference(Multiset<T> set2)
	{
		System.out.println("Difference");
		int size = this.multiset.size();
		Multiset<T> set3 = new Multiset<T>();
		int r = 0;
		
		
		for (int i=0; i < size; i++)
		{
			Element<T> search1 = (Element<T>) this.multiset.get(i);
			
			for (int j=0; j < set2.multiset.size(); j++)
			{
				Element<T> search2 = (Element<T>) set2.multiset.get(j);
				
				if (search1.data == search2.data)		//both sets contain the element.data
				{
					int occDiff = search1.numOcc - search2.numOcc;		//check the differences in number of occurrences
					if (occDiff > 0)		//if we have a positive difference, we still that element occurring in the list, so add it to the new list.
					{
						Element<T> temp = new Element<T>();
						temp.numOcc = occDiff;
						temp.data = search1.data;
						set3.multiset.add(temp);
					}
					r = 0;
					// else there are less than 0 occurrences, so don't add it to the new list.
				}
				else {r = 1;}
			}
			
			//add elements from 1 not found in 2
			if (r == 1)
			{
				Element<T> temp = new Element<T>();
				temp.data = search1.data;
				temp.numOcc = search1.numOcc;
				set3.multiset.add(temp);
				r =0;
			}
			
			
		}
		
		return set3;
	}
	
	public Multiset<T> join(Multiset<T> set2)
	{
		System.out.println("Join");
		Multiset<T> set3 = new Multiset<T>();
		int r = 0;
		
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> temp = new Element<T>();
			Element<T> search1 = (Element<T>) this.multiset.get(i);
			temp.data = search1.data;
			temp.numOcc = search1.numOcc;
			set3.multiset.add(temp);
			set3.multiset.set(i, temp);
		}
		
		for (int i=0; i < set2.multiset.size(); i++)
		{
			Element<T> temp = new Element<T>();
			Element<T> search2 = (Element<T>) set2.multiset.get(i);
			r=0;
			
			for (int j=0; j < set3.multiset.size(); j++)
			{
				Element<T> search3 = (Element<T>) set3.multiset.get(j);
				if (search2.data == search3.data)
				{
					temp.numOcc = search2.numOcc;
					search3.numOcc += temp.numOcc;
					r = 1;
				}
			}
			if (r == 0)
			{
				temp.data = search2.data;
				temp.numOcc = search2.numOcc;
				set3.multiset.add(temp);
			}
		}
		
		
		return set3;
	}
	
	public void unique()		//return all unique elements
	{
		System.out.print("Unique: {");
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> print = new Element<T>();
			print = (Element<T>) this.multiset.get(i);
			System.out.print(print.printData() + " ");
		}
		System.out.print("}");
		System.out.println();
	}
	
	public int length()
	{
		int total = 0;
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> temp = (Element<T>) this.multiset.get(i);
			total += temp.numOcc;
		}
		
		return total;
	}
	
	public int occurrence(T in)
	{
		int occ = 0;
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> search1 = (Element<T>) this.multiset.get(i);
			if (search1.data == in)
			{
				occ = search1.numOcc;
			}
		}
		
		return occ;
	}
	
	public void display()	// all elements
	{
		System.out.print("Display: {");
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> print = new Element<T>();
			print = (Element<T>) this.multiset.get(i);
			for (int j=0; j< print.numOcc; j++)
			{
				System.out.print(print.data + ", ");
			}
		}
		System.out.print("}");
		System.out.println();
	}
	
	public void print()		//all elements and counts
	{
		System.out.print("Print: {");
		for (int i=0; i < this.multiset.size(); i++)
		{
			Element<T> print = new Element<T>();
			print = (Element<T>) this.multiset.get(i);
			System.out.print("{" + print.printData() + "," + print.printOcc() + "}, " );
			
		}
		System.out.println("}");
	}
	
	public void sort()
	{
		Element<T> temp1 = new Element<T>();
		Element<T> temp2 = new Element<T>();
		
		int nr1 = 0;
		int nr2 = 0;
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arrInd = new ArrayList<Integer>();
		
		for (int i=0; i < this.multiset.size(); i++)
		{
			temp1 = (Element<T>) this.multiset.get(i);
			arr.add((Integer)temp1.data);
			arrInd.add(this.multiset.indexOf(i));
		}
		
		for (int j=0; j < arr.size(); j++)
		{
			if (nr1 > arr.get(j))
			{
				nr1 = arr.get(j);
			}
		}
		
	}
	
}
