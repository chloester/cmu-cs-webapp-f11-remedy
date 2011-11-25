package formbeans;

import org.mybeans.form.FormBean;

public class DelSideForm extends FormBean{
	private String sideid;
	//getters and setters.
	public String getSideid() {
		return sideid;
	}
	public void setSideid(String sideid) {
		this.sideid = trimAndConvert(sideid,"<>\"");
	}

}
