import java.util.concurrent.BlockingQueue;

public class Consumer {
	public void setQueue(BlockingQueue<Pair> queue) {
		Pair pair=null;
		try {
			if(!queue.isEmpty())	
				pair=queue.take();
			else
				return;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("priority: "+pair.priority+"\tArrival: "+pair.count);
	}
}
