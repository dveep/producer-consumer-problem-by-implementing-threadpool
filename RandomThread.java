import java.util.ArrayList;
import java.util.Random;

public class RandomThread implements IScheduling{
	public Producer getThread(ArrayList<Producer> threadList) {
		int count=threadList.size();
		Random random=new Random();
		while(count--!=0) {
			int index=random.nextInt(threadList.size());
			if(threadList.get(index).getState()==Thread.State.NEW)
				return threadList.get(index);
		}
		return null;
	}
}
