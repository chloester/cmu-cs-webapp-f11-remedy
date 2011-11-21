package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LogSideForm extends FormBean {
	private String owner;
	private String name;
	private String date;
	private String timeHr;
	private String timeMin;
	private String timeAMPM;
	private String value;
	
	public String getOwner()	{ return owner;    }
	public String getName()		{ return name;     }
	public String getDate()		{ return date;     }
	public String getTimeHr()	{ return timeHr;   }
	public String getTimeMin()	{ return timeMin;  }
	public String getTimeAMPM() { return timeAMPM; }
	public String getValue()	{ return value;    }
	
	public void setOwner(String o)	{ owner =trimAndConvert(o,"<>\"");}
	public void setName(String n)	{ name = trimAndConvert(n,"<>\"");}
	public void setDate(String d)	{ date = trimAndConvert(d,"<>\"");}
	public void setTimeHr(String t)	{ timeHr = t;}
	public void setTimeMin(String t)	{ timeMin = t; }
	public void setTimeAMPM(String t) 	{ timeAMPM = t; }
	public void setValue(String v)		{ value = v;   }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		/*if (name == null|| name.length()==0) {
			errors.add("Please select a side effect from the dropdown list, or add a new side effect.");
		}*/
		/*
		I just comment this line of code to test my code.
		*/
		
		if (date == null|| date.length()==0) {
			errors.add("Please select the date when you experienced the side effect.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}