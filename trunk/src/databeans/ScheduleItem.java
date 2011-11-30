package databeans;

import java.util.Arrays;
import java.util.Comparator;

public class ScheduleItem implements Comparable<ScheduleItem> {
	private int	   sortTime;
	private String time;
	private String name;
	
	public int compareTo(ScheduleItem other) {
		// order by time
		if (sortTime < other.sortTime) return 1;
		if (sortTime > other.sortTime) return -1;
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof ScheduleItem) {
		    ScheduleItem other = (ScheduleItem) obj;
		    return compareTo(other) == 0;
		}
		return false;
	}

	public int 	  getSortTime() { return sortTime; }
	public String getTime()		{ return time;     }
	public String getName()		{ return name;     }
	
	public void setSortTime(int s)	{ sortTime = s; }
	public void setTime(String t)	{ time = t;     }
	public void setName(String n)	{ name = n;     }
	
}