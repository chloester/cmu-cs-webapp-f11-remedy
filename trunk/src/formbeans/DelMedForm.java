package formbeans;

import org.mybeans.form.FormBean;

public class DelMedForm extends FormBean{
	private String medid;
	//getters and setters.
	public String getMedid() {
		return medid;
	}
	public void setMedid(String medid) {
		this.medid = trimAndConvert(medid,"<>\"");
	}

}
