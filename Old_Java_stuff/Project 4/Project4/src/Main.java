import java.util.*;
import java.io.*;



public class Main {
	
	int x;
	int y;
	double low = 999;
	double high =0;
	int lowIndex;
	int highIndex;
	Customers people = new Customers();
	PriorityQueue <Event> EventQueue = new PriorityQueue <Event> ();
	SimClock clock = new SimClock();
	int totalCustomers;
	PrintWriter writer;
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		Main obj = new Main();
		obj.readArrivals();
		obj.cocktailSort();
		
		try {
			obj.writer = new PrintWriter("sim_outputs.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//output sorted array
		for (int i =0; i< obj.people.customers.size(); i++ )
		{
			//System.out.println(obj.people.customers.get(i)[0] + " " + obj.people.customers.get(i)[1] + " " + obj.people.customers.get(i)[2]);
		}
		RegisterArray checkouts = new RegisterArray();
		
		// read registers.txt
		// begin sim
		obj.eventSim(checkouts);
		
		System.out.println(checkouts.rRegisters[0].currentQueueLength);
		System.out.println(checkouts.rRegisters[1].currentQueueLength);
		System.out.println(checkouts.rRegisters[2].currentQueueLength);
		System.out.println(checkouts.xRegisters[0].currentQueueLength);
		
		obj.writer.println(" ======End of Simulation Statistics====== ");
		obj.writer.println("Total customers disappointed:" + obj.totalCustomers);
		obj.writer.println("Average Queue Lengths:");
		
		for (int i=0; i <= checkouts.rRegisters.length - 1; i++)
		{
			obj.writer.println("    Register " + i + ": "  + checkouts.rRegisters[i].averageQueueLength);
		}
		for (int i=0; i <= checkouts.xRegisters.length - 1; i++)
		{
			obj.writer.println("    Express Register " + i + ": "  + checkouts.xRegisters[i].averageQueueLength);
		}
		
		obj.writer.println("Average Wait Times:");
		for (int i=0; i <= checkouts.rRegisters.length - 1; i++)
		{
			obj.writer.println("    Register " + i + ": "  + checkouts.rRegisters[i].averageWait(obj.clock.time()));
		}
		for (int i=0; i <= checkouts.xRegisters.length - 1; i++)
		{
			obj.writer.println("    Register " + i + ": "  + checkouts.xRegisters[i].averageWait(obj.clock.time()));
		}
		
		obj.writer.println("Total Customers in each line:");
		for (int i=0; i <= checkouts.rRegisters.length - 1; i++)
		{
			obj.writer.println("Register " + i + ": "  + checkouts.rRegisters[i].totalCustomers);
		}
		for (int i=0; i <= checkouts.xRegisters.length - 1; i++)
		{
			obj.writer.println("Register " + i + ": "  + checkouts.xRegisters[i].totalCustomers);
		}
		
		obj.writer.close();
	}
	
	
	////////////////////////////////////////////////////////////methods
	public void readArrivals() throws FileNotFoundException
	{
		File file = new File("arrival.txt");
		Scanner sc = new Scanner(file);
		
		int i = 0;
		while (sc.hasNext())
		{
				double temp[] = {sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), 0, 0};		//arriveTime, numItems, searchTime, registerIndex, 
				people.customers.add(i, temp);
				i++;
		}
		sc.close();
	}
	
	public void cocktailSort()
	{
		boolean swapped = true;
		int i = 0;
		int j = people.customers.size() -1;
		//System.out.println(j);
		
		while (i < j && swapped)
		{
			swapped = false;
			for (int k = i; k < j; k++)
			{
				if (people.customers.get(k)[0] > people.customers.get(k+1)[0])
				{
					double temp0[] = people.customers.get(k);
					people.customers.set(k, people.customers.get(k+1));
					people.customers.set(k+1, temp0);
					swapped = true;
				}
			}
			j--;
			if (swapped)
			{
				swapped = false;
				for (int k = j; k > i; k--)
				{
					if (people.customers.get(k)[0] < people.customers.get(k-1)[0])
					{
						double temp1[] = people.customers.get(k);
						people.customers.set(k, people.customers.get(k-1));
						people.customers.set(k-1, temp1);
						swapped = true;
					}
				}
			}
			i++;
		}
	}
	
	public int[] shortestRQueue(RegisterArray checkouts)
	{
		int registerNum = 0;
		int length = 900;
		for (int i = 0; i <= checkouts.rRegisters.length -1; i++)
		{
			if (checkouts.rRegisters[i].currentQueueLength < length)
			{
				registerNum = i;
				length = checkouts.rRegisters[i].currentQueueLength;
			}
		}
		int[] info = {registerNum, length};
		return info;
	}
	
	public int[] shortestXQueue (RegisterArray checkouts)
	{
		int registerNum = 0;
		int length = 900;
		for (int i = 0; i <= checkouts.xRegisters.length -1; i++)
		{
			if (checkouts.xRegisters[i].currentQueueLength < length)
			{
				registerNum = i;
				length = checkouts.xRegisters[i].currentQueueLength;
			}
		}
		int[] info = {registerNum, length};
		return info;
	}
	
	public void eventSim(RegisterArray checkouts)
	{
		
		int n = 1;
		for (int i = 0; i <= people.customers.size()-1; i++)
		{
			EventQueue.add(new Event(Event.EType.Arrival, people.customers.get(i)[0], people.customers.get(i))); //put all arrivals in the eventQueue
			totalCustomers++;
		}
		while (EventQueue.size() > 0)
		{
			
			clock.time(EventQueue.peek().Time);				//set clock time to next event.
			Event currentEvent = EventQueue.poll();			//get next event
			double[] eventPerson = currentEvent.Parameter;	//the customer the current event is refering to
			if (currentEvent.Type.toString() == "Arrival" )		// person gets to the store
			{
				double nextTime = currentEvent.Time + eventPerson[1] * eventPerson[2];
				EventQueue.add(new Event(Event.EType.FinishShopping, nextTime, eventPerson));		//assign time to finish shopping
			}
			
			if (currentEvent.Type.toString() == "FinishShopping" ) //get in a register queue
			{
				int[] reg = shortestRQueue(checkouts);
				int[] xpr = shortestXQueue(checkouts);
				int where;
				currentEvent.Parameter[4] = clock.time(); 		//store Finish Shopping time for analysis
				if (currentEvent.Parameter[1] <= 12 && xpr[1] <= reg[1])	//goes in shortest Express Lane
				{
					checkouts.xRegisters[xpr[0]].enqueue(eventPerson, clock.time());
					System.out.println("Express:" + xpr[0] + " Length:" + checkouts.xRegisters[xpr[0]].currentQueueLength);
					eventPerson[3] = xpr[0]+checkouts.rRegisters.length;
					where = 1;
				}
				else 	// goes to shortest regular lane
				{
					checkouts.rRegisters[reg[0]].enqueue(eventPerson, clock.time());
					System.out.println("Regular:" + reg[0] + " Length:" + checkouts.rRegisters[reg[0]].currentQueueLength);
					eventPerson[3] = reg[0];
					where =2;
				}
				
				if (xpr[1] == 0 || reg[1] == 0)	//if that's the first customer at the register schedule their finish. If not, we'll take care of it later.
				{
					double nextTime = 0;
					if (where == 1)
					{
						nextTime = currentEvent.Time + (eventPerson[1] * checkouts.xRegisters[xpr[0]].itemTime) + checkouts.xRegisters[xpr[0]].payTime;
					}
					if (where == 2)
					{
						nextTime = currentEvent.Time + (eventPerson[1] * checkouts.rRegisters[xpr[0]].itemTime) + checkouts.rRegisters[xpr[0]].payTime;
					}
					
					EventQueue.add(new Event(Event.EType.Leave, nextTime, currentEvent.Parameter));
				}
			}
			
			
			if (currentEvent.Type.toString() == "Leave" )	//take person out of register queue
			{
				System.out.println( "leave register: " +currentEvent.Parameter[3]);
				double nextTime = 0;
				if ( currentEvent.Parameter[3] >= checkouts.rRegisters.length)
				{
					checkouts.xRegisters[(int) (eventPerson[3] - checkouts.rRegisters.length)].dequeue(clock.time(), eventPerson);
					if (checkouts.xRegisters[(int) eventPerson[3]- checkouts.rRegisters.length].first != null)
					{
						// nextTime = Now + numItems * register.itemTime + payTime;
						nextTime = currentEvent.Time + (eventPerson[1] * checkouts.xRegisters[(int) eventPerson[3] - checkouts.rRegisters.length].itemTime) + checkouts.xRegisters[(int) eventPerson[3] - checkouts.rRegisters.length].payTime;
						EventQueue.add(new Event(Event.EType.Leave, nextTime, checkouts.xRegisters[(int) eventPerson[3]- checkouts.rRegisters.length].first.person));
					}
				}
				else
				{
					checkouts.rRegisters[(int) (eventPerson[3])].dequeue(clock.time(), eventPerson);
					if (checkouts.rRegisters[(int) eventPerson[3]].first != null)
					{
						nextTime = currentEvent.Time + eventPerson[1] * checkouts.rRegisters[(int) eventPerson[3]].itemTime + checkouts.rRegisters[(int) eventPerson[3]].payTime;
						EventQueue.add(new Event(Event.EType.Leave, nextTime,  checkouts.rRegisters[(int) eventPerson[3]].first.person));
					}
				}
				n++;
			}
			
		}
				
	}
	
	
}
