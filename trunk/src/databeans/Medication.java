package databeans;

public class Medication {
	private int    id;
	private String owner;
	private String name;
	private String purpose;
	private String freq1;
	private int    freq2;
	private String days;
	private int    startHr;
	private int    startMin;
	private String startAMPM;
	private int    dosage;
	private String dosageUnit;
	
	public int    getId()			{ return id;         }
    public String getOwner()		{ return owner;      }
    public String getName()			{ return name;       }
    public String getPurpose()		{ return purpose;    }
    public String getFreq1()		{ return freq1;      }
    public int    getFreq2()		{ return freq2;      }
    public String getDays()			{ return days;       }
    public int    getStartHr()		{ return startHr;    }
    public int    getStartMin()		{ return startMin;   }
    public String getStartAMPM()	{ return startAMPM;  }
    public int    getDosage()		{ return dosage;     }
    public String getDosageUnit()	{ return dosageUnit; }

	public void setId(int i)			{ id = i;         }
    public void setOwner(String o)		{ owner = o;      }
    public void setName(String n)		{ name = n;       }
    public void setPurpose(String p)	{ purpose = p;    }
    public void setFreq1(String f)		{ freq1 = f;      }
    public void setFreq2(int f)			{ freq2 = f;      }
    public void setDays(String d)		{ days = d;       }
    public void setStartHr(int s)		{ startHr = s;    }
    public void setStartMin(int s)		{ startMin = s;   }
    public void setStartAMPM(String s)	{ startAMPM = s;  }
    public void setDosage(int d)		{ dosage = d;     }
    public void setDosageUnit(String d)	{ dosageUnit = d; }

}