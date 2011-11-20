package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LogSideForm extends FormBean {
	private String owner;
	private String name;
	private String date;
	private int    timeHr;
	private int    timeMin;
	private String timeAMPM;
	private int    value;
	
	public String getOwner()	{ return owner;    }
	public String getName()		{ return name;     }
	public String getDate()		{ return date;     }
	public int 	  getTimeHr()	{ return timeHr;   }
	public int 	  getTimeMin()	{ return timeMin;  }
	public String getTimeAMPM() { return timeAMPM; }
	public int 	  getValue()	{ return value;    }
	
	public void setOwner(String o)	{ owner = o;   }
	public void setName(String n)	{ name = n;    }
	public void setDate(String d)	{ date = d;    }
	public void setTimeHr(int t)	{ timeHr = t;  }
	public void setTimeMin(int t)	{ timeMin = t; }
	public void setTimeAMPM(String t) 	{ timeAMPM = t; }
	public void setValue(int v)		{ value = v;   }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (name == null) {
			errors.add("Please select a side effect from the dropdown list, or add a new side effect.");
		}
		
		if (date == null) {
			errors.add("Please select the date when you experienced the side effect.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}