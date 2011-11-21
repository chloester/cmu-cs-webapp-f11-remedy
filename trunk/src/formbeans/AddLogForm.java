package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddLogForm extends FormBean {
	private int    medId;
	private String owner;
	private String name;
	private String date;
	private String timeHr;
	private String timeMin;
	private String timeAMPM;
	
	public int    getMedId()	{ return medId;    }
	public String getOwner()    { return owner;    }
	public String getName()     { return name;}
	public String getDate()		{ return date;     }
	public String getTimeHr()	{ return timeHr;   }
	public String getTimeMin()	{ return timeMin;  }
	public String getTimeAMPM() { return timeAMPM; }
	
	public void setMedId(int i)			{ medId = i;    }
	public void setOwner(String o)		{ owner = o; 	}
	public void setDate(String d)		{ date = trimAndConvert(d,"<>\"");     }
	public void setName(String name1)    { name = name1; }
	public void setTimeHr(String t)		{ timeHr = t;   }
	public void setTimeMin(String t)		{ timeMin = t;  }
	public void setTimeAMPM(String t) 	{ timeAMPM = t; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (medId == -1) {
			errors.add("Please select a medication from the dropdown list, or add a new medication.");
		}
		
		if (date == null||date.length() == 0) {
			errors.add("Please select the date when you took the medication.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}