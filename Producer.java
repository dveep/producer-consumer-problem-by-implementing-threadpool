import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
	BlockingQueue<Pair> queue;
	boolean threadTerminate;
	Callback callback;
	int priority;
	int count;
	Producer(BlockingQueue<Pair> queue,Callback callback,int priority,int count) {
		this.queue=queue;
		threadTerminate = false;
		this.callback = callback;
		this.priority=priority;
		this.count=count;
	}
	public void run() {
		try {
			queue.put(new Pair(priority,count));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		callback.finish();
	}
}
