package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class AddSideForm extends FormBean {
	private int	   id;
	private String owner;
	private String name;
	
	public int	  getId()		{ return id;     }
	public String getOwner()	{ return owner;  }
	public String getName()		{ return name;   }
	
	public void setId(int i)		{ id = i;	   }
	public void setOwner(String o)	{ owner = o;   }
	public void setName(String n)	{ name = trimAndConvert(n,"<>\"");    }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (name == null) {
			errors.add("Please enter a name for the side effect.");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
	
}