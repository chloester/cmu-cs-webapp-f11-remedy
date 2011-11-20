package databeans;

public class Medication {
	private int medid;
	private String username;
	private String name;
	private String purpose;
	private String freqSelect1;
	private String freqSelect2;
	private String dayChecks;
	private String startTimeHour;
	private String startTimeMin;
	private String startAMPM;
	private int    dosage;//which is different from formbean.
	private String dosageUnit;
	//consturctor.
	public Medication(int id) {
		this.medid = id;
	}
	//getters and setters.
	public int getMedid() {
		return medid;
	}
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
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
	public String getDayChecks() {
		return dayChecks;
	}
	public String getStartTimeHour() {
		return startTimeHour;
	}
	public String getStartAMPM() {
		return startAMPM;
	}
	public int getDosage() {
		return dosage;
	}
	public String getDosageUnit() {
		return dosageUnit;
	}
	public String getStartTimeMin() {
		return startTimeMin;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public void setFreqSelect1(String freqSelect1) {
		this.freqSelect1 = freqSelect1;
	}
	
	public void setFreqSelect2(String freqSelect2) {
		this.freqSelect2 = freqSelect2;
	}
	
	public void setDayChecks(String dayChecks) {
		this.dayChecks = dayChecks;
	}
	
	public void setStartTimeHour(String startTimeHour) {
		this.startTimeHour = startTimeHour;
	}
	
	public void setStartTimeMin(String startTimeMin) {
		this.startTimeMin = startTimeMin;
	}
	
	public void setStartAMPM(String startAMPM) {
		this.startAMPM = startAMPM;
	}
	
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}
	
	public void setDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}
	
}