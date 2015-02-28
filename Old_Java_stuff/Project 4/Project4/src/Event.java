
public class Event implements Comparable<Event>
{
	public enum EType
	{
		Arrival,
		FinishShopping,
		Leave
		
	}
	
	public EType Type;
	public double Time;
	public double[] Parameter;
	
	Event (EType type, double time, double[] param)
	{
		Type = type;
		Time = time;
		Parameter = param;
	}
	
	@Override
	public int compareTo(Event arg0) 
	{
		// TODO Auto-generated method stub
		return Double.compare(Time, arg0.Time);
	}
}
