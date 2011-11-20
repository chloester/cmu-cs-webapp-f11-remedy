package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class AddMedForm extends FormBean {
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
	private String    dosage;
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
    public String    getDosage()	{ return dosage;     }
    public String getDosageUnit()	{ return dosageUnit; }

	public void setId(int i)			{ id = i;         }
    public void setOwner(String o)		{ owner = o;      }
    public void setName(String n)		{ name = trimAndConvert(n,"<>\"");       }
    public void setPurpose(String p)	{ purpose = trimAndConvert(p,"<>\"");    }
    public void setFreq1(String f)		{ freq1 = f;      }
    public void setFreq2(int f)			{ freq2 = f;      }
    public void setDays(String d)		{ days = d;       }
    public void setStartHr(int s)		{ startHr = s;    }
    public void setStartMin(int s)		{ startMin = s;   }
    public void setStartAMPM(String s)	{ startAMPM = s;  }
    public void setDosage(String d)		{ dosage = d;     }
    public void setDosageUnit(String d)	{ dosageUnit = trimAndConvert(d,"<>\""); }

	private boolean validateDosage() {
		// returns true if dosage input is a number
		boolean b = Pattern.matches("^\\d+$", dosage);
		return b;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (name == null) {
			errors.add("Please add the medication or supplement name.");
		}
		
		if (purpose == null) {
			errors.add("Please add the medication's purpose.");
		}
		
		if (!validateDosage()) {
			errors.add("Please enter the dosage as a number.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}