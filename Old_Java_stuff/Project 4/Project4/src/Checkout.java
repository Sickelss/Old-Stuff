import java.util.*;

public class Checkout
{
	public float itemTime;
	public float payTime;
	public float qTime=0;
	public checkoutQueue last;
	public checkoutQueue first;
	public double totalWaitTime = 0;
	public double averageWaitTime = 0;
	public int currentQueueLength;
	public int averageQueueLength;
	public int totalCustomers;
	public int maxLength;
	
	public Checkout (float a, float b)
	{
		itemTime = a;
		payTime = b;
	}
	
	public class checkoutQueue 	
	{
		double person[];
		public checkoutQueue next;
		public checkoutQueue (double[] data)
		{
			person = data;
		}
	}
	
	public void enqueue(double customers[], double time)
	{
		if (first == null) 
		{
			checkoutQueue newPerson = new checkoutQueue(customers);
			last = newPerson;
			first = newPerson;
		}
		else
		{
			checkoutQueue newPerson = new checkoutQueue(customers);
			last.next = newPerson;
			last = newPerson;
		}
		currentQueueLength++;
		totalCustomers++;
		if (currentQueueLength > maxLength)
		{
			maxLength++;
		}
		
	}
	
	public float scheduleNext(int person[]) //returns time until next customer will finish checkout in this queue.
	{
		float nextTime = itemTime * person[1] + payTime;
		return nextTime;
	}
	
	
	public void dequeue(double time,double[] customer)
	{
		if (first == null)
		{
			return;
		}
		
		if (first.next != null)
		{
			checkoutQueue temp;
			temp = first.next;
			first.next = null;
			first = temp;
		}
		else
		{
			first = null;
			last = null;
		}
		
		totalWaitTime = totalWaitTime + time - customer[0];
		currentQueueLength--;
	}
	
	public double averageWait(double time)
	{
		averageWaitTime = totalWaitTime / time; 
		return averageWaitTime;
	}
}
