
public class SchedulingFactory {
	public static IScheduling getSchedulingAlgo(String algo)
	{
		if(algo.equalsIgnoreCase("priority"))
			return new PriorityScheduling();
		else if(algo.equalsIgnoreCase("FIFO"))
			return new FirstInFirstOut();
		else if(algo.equalsIgnoreCase("LIFO"))
			return new LastInFirstOut();
		else if(algo.equalsIgnoreCase("random"))
			return new RandomThread();
		return null;
	}
}
