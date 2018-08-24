import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main implements Callback{
	int noOfProducerFinish = 0;
	public int getNoOfProducerFinish() {
		return noOfProducerFinish;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the demonstration of Produer-Consumer problem by implementing ThreadPool and applying Scheduling Algorithms");
		System.out.println("How many producers do you want?");
		int noOfProducers=sc.nextInt();
		System.out.println("What size of Producer ThreadPool do you want?");
		int noOfThreadsInPool=sc.nextInt();
		System.out.println("How many Consumers do you want?");
		int noOfConsumers=sc.nextInt();
		System.out.println("What size of Queue do you want?");
		int queueSize=sc.nextInt();
		System.out.println("Which Scheduling Algorithm do you want?");
		System.out.println("A.Random\t\tB.Priority\nC.FIFO\t\tD.LIFO");
		sc.nextLine();
		String schedulingAlgo=sc.nextLine();
		Main mainProcess = new Main();
		Consumer consumer = new Consumer();
		Thread[] consume = new Thread[noOfConsumers];
		ThreadPool producerPool = new ThreadPool(noOfThreadsInPool);
		producerPool.setSchedulingAlog(SchedulingFactory.getSchedulingAlgo(schedulingAlgo));
		BlockingQueue<Pair> queue=new ArrayBlockingQueue<>(queueSize);
		Random random=new Random();
		for(int i=0;i<noOfProducers;i++)
			producerPool.submit(new Producer(queue,mainProcess,random.nextInt(100),i+1));
		for(int i=0;i<noOfConsumers;i++) {
			consume[i] = new Thread(new Runnable() {
				public void run() {
					while(mainProcess.getNoOfProducerFinish()<noOfProducers || !queue.isEmpty()) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						consumer.setQueue(queue);
					}
				}
			});
			consume[i].start();
		}
		producerPool.terminate();
	}
	public void finish() {
		noOfProducerFinish++;		
	}
}
