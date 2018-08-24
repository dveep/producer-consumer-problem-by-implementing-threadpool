import java.util.ArrayList;
public class ThreadPool {
	int numberOfThread;
	int runningThread;
	Object lock;
	boolean isRunning;
	ArrayList<Producer> threadList;
	IScheduling iScheduling;
	ThreadPool(int numberOfThread) {
		this.numberOfThread=numberOfThread;
		iScheduling=null;
		threadList=new ArrayList<>();
		runningThread=0;
		lock=new Object();
		isRunning=true;
		monitor.start();
		startThread.start();
	}
	public void setSchedulingAlog(IScheduling iScheduling)
	{
		this.iScheduling = iScheduling;
	}
	public void submit(Producer t) {
		threadList.add(t);
	}
	public void terminate() {
		isRunning=false;
	}
	Thread monitor = new Thread(new Runnable() {
		public void run() {
			while(true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock) {
					int size=threadList.size();
					if(size==0 && !isRunning)
						break;
					ArrayList<Producer> newThreadList=new ArrayList<>();
					for(int i=0;i<size;i++) {
						Producer t=threadList.get(i);
						if(t.getState()==Thread.State.TERMINATED) {
							runningThread--;
							newThreadList.add(t);	
						}
					}
					threadList.removeAll(newThreadList);
				}
			}
		}
	});
	Thread startThread = new Thread(new Runnable() {
		public void run() {
			while(true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(runningThread<numberOfThread) {
					synchronized (lock) {
						if(threadList.size()==0 && !isRunning)
							break;
						while(runningThread<numberOfThread) {
							if(iScheduling==null)
							{
								int i=0;
								while(i<threadList.size()) {
									if(threadList.get(i).getState()==Thread.State.NEW) {
										threadList.get(i).start();
										runningThread++;
										break;
									}
									i++;
								}
								break;
							}
							else
							{
								Producer t=iScheduling.getThread(new ArrayList<Producer>(threadList));
								if(t!=null) {
									t.start();
									runningThread++;
								}
								break;
							}
						}
					}
				}
			}
		}
		
	});
	
}
