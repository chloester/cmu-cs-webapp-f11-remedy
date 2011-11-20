package databeans;

public class SideEffectLog {
	private String owner;
	private String name;
	private String date;
	private int    timeHr;
	private int    timeMin;
	private String timeAMPM;
	private int    value;
	
	public String getOwner()	{ return owner;  }
	public String getName()		{ return name;   }
	public String getDate()		{ return date;   }
	public int 	  getTimeHr()	{ return timeHr; }
	public int 	  getTimeMin()	{ return timeMin;}
	public String getTimeAMPM() { return timeAMPM; }
	public int 	  getValue()	{ return value;  }
	
	public void setOwner(String o)		{ owner = o;   }
	public void setName(String n)		{ name = n;    }
	public void setDate(String d)		{ date = d;    }
	public void setTimeHr(int t)		{ timeHr = t;  }
	public void setTimeMin(int t)		{ timeMin = t; }
	public void setTimeAMPM(String t) 	{ timeAMPM = t; }
	public void setValue(int v)			{ value = v;   }
}