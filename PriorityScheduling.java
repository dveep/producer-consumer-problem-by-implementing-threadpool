import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PriorityScheduling implements IScheduling {
	public Producer getThread(ArrayList<Producer> threadList) {
		Collections.sort(threadList,new Comparator<Producer>() {
			public int compare(Producer p1, Producer p2) {
				if(p1.priority>p2.priority)
					return -1;
				return 1;
			}
		});
		int i=0;
		while(i<threadList.size()) {
			if(threadList.get(i).getState()==Thread.State.NEW)
				return threadList.get(i);
			i++;
		}
		return null;
	}

}
