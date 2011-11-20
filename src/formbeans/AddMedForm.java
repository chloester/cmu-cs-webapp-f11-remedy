package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.mybeans.form.FormBean;

public class AddMedForm extends FormBean{
	private String name;
	private String purpose;
	private String freqSelect1;
	private String freqSelect2;
	private String dayChecks;
	private String startTimeHour;
	private String startTimeMin;
	private String startAMPM;
	private String dosage;
    
	private static final String DOSAGE_PATTERN = "^[0-9]*$";
	private Pattern pattern;
	private Matcher matcher;

	//getters and setters.
	public String getName() {
		return name;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getFreqSelect1() {
		return freqSelect1;
	}
	public String getFreqSelect2() {
		return freqSelect2;
	}
	public String getStartTimeHour() {
		return startTimeHour;
	}
	public String getStartTimeMin() {
		return startTimeMin;
	}
	public String getDayChecks() {
		return dayChecks;
	}
	public String getStartAMPM() {
		return startAMPM;
	}
	public String getDosage() {
		return dosage;
	}
	public String getDosageUnit() {
		return dosageUnit;
	}
	
	public void setName(String name) {
		this.name = trimAndConvert(name,"<>\"");
	}
    public void setPurpose(String purpose) {
		this.purpose = trimAndConvert(purpose,"<>\"");
	}
	
	public void setFreqSelect1(String freqSelect1) {
		this.freqSelect1 = trimAndConvert(freqSelect1,"<>\"");
	}
	
	public void setFreqSelect2(String freqSelect2) {
		this.freqSelect2 = trimAndConvert(freqSelect2,"<>\"");
	}
	
	public void setDayChecks(String dayChecks) {
		this.dayChecks = trimAndConvert(dayChecks,"<>\"");
	}
	
	public void setStartTimeHour(String startTimeHour) {
		this.startTimeHour = trimAndConvert(startTimeHour,"<>\"");
	}
	
	public void setStartTimeMin(String startTimeMin) {
		this.startTimeMin = trimAndConvert(startTimeMin,"<>\"");
	}
	
	public void setStartAMPM(String startAMPM) {
		this.startAMPM = trimAndConvert(startAMPM,"<>\"");
	}
	
	public void setDosage(String dosage) {
		this.dosage = trimAndConvert(dosage,"<>\"");
	}
	
	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit =trimAndConvert(dosageUnit,"<>\"");
	}
	private String dosageUnit;
	
	//adding medication form for checking errors.
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if( name==null|| name.length()==0){
			errors.add("Please enter medicaion Name");
		}else if (name.length() >= 255) {
			errors.add("Medication Name is too long");
		}else if(purpose==null||purpose.length()==0){
			errors.add("For better MDTracking, Purpose is required");
		}else if(purpose.length() >= 20) {
			errors.add("Purpose's length is 1-20, please re-enter.");
		}else if(dayChecks==null||dayChecks.length()==0){
			errors.add("Please select fill up the dayChecks");
		}else if(freqSelect2==null||freqSelect2.length()==0){
			errors.add("Please fill up the hours.");
		}else if (dosage == null || dosage.length() == 0) {
			errors.add("dosage is required");
		}else if (!dosageValidator(dosage)){
			errors.add("Please enter intergers.");
		}
			return errors;
		}
	
		private boolean dosageValidator(String dosage){
			pattern = Pattern.compile(DOSAGE_PATTERN);
			matcher = pattern.matcher(dosage);
		return matcher.matches();
		}
}
