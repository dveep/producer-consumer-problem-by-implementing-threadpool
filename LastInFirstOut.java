import java.util.ArrayList;

public class LastInFirstOut implements IScheduling{
	public Producer getThread(ArrayList<Producer> threadList) {
		int i=threadList.size()-1;
		while(i>=0) {
			if(threadList.get(i).getState()==Thread.State.NEW)
				return threadList.get(i);
			i--;
		}
		return null;
	}
}
