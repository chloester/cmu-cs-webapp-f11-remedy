package databeans;

public class MedLog {
	private int    medId;
	private String owner;
	private String date;
	private int    timeHr;
	private int    timeMin;
	private String timeAMPM;
	
	public int    getMedId()	{ return medId;    }
	public String getOwner()    { return owner;    }
	public String getDate()		{ return date;     }
	public int    getTimeHr()	{ return timeHr;   }
	public int    getTimeMin()	{ return timeMin;  }
	public String getTimeAMPM() { return timeAMPM; }
	
	public void setMedId(int i)			{ medId = i;    }
	public void setOwner(String o)		{ owner = o; 	}
	public void setDate(String d)		{ date = d;     }
	public void setTimeHr(int t)		{ timeHr = t;   }
	public void setTimeMin(int t)		{ timeMin = t;  }
	public void setTimeAMPM(String t) 	{ timeAMPM = t; }
}