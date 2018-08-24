import java.util.ArrayList;

public class FirstInFirstOut implements IScheduling{
	public Producer getThread(ArrayList<Producer> threadList) {
		int i=0;
		while(i<threadList.size()) {
			if(threadList.get(i).getState()==Thread.State.NEW)
				return threadList.get(i);
			i++;
		}
		return null;
	}
}
