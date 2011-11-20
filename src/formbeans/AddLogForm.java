package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddLogForm extends FormBean {
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
	public void setDate(String d)		{ date = trimAndConvert(d,"<>\"");     }
	public void setTimeHr(int t)		{ timeHr = t;   }
	public void setTimeMin(int t)		{ timeMin = t;  }
	public void setTimeAMPM(String t) 	{ timeAMPM = t; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (medId == -1) {
			errors.add("Please select a medication from the dropdown list, or add a new medication.");
		}
		
		if (date == null) {
			errors.add("Please select the date when you took the medication.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}