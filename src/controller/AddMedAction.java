package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.Medication;
import formbeans.AddMedForm;
import formbeans.LoginForm;

import model.Model;
import model.MedDAO;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class AddMedAction extends Action {
	private MedDAO medDAO;
	//create medication bean;
	private Medication AddMed;
	private FormBeanFactory<AddMedForm> formBeanFactory = FormBeanFactory.getInstance(AddMedForm.class);

	public AddMedAction(Model model) {
		medDAO = model.getMedDAO();
	}
	public String getName() { return "addMed.do"; }

	public String perform(HttpServletRequest request) {
	    //must be logged in for this one.
    	User user = (User) request.getSession().getAttribute("user");
    	if(user == null){
    		LoginForm form = new LoginForm();
    		form.setRedirect("/addmed.do");
    		request.setAttribute("loginform", form);
    		return "homePage.jsp";
    	}
    	/*
    	 * if the user has already logged in.
    	 * */
	
		Medication[] Medicationlist_1;
		//error list for error mention function.
		List<String> errors = new ArrayList<String>();
		String button;
		button = request.getParameter("button");
		if(button != null){
		if(button.equals("Add medication")){
		try{
			AddMedForm form = formBeanFactory.create(request);
        	request.setAttribute("addmedform", form);
        	if(!form.isPresent()){
        		return "homepage.jsp";
        	}
        	HttpSession session = request.getSession(false);
        	//check the errors.
        	errors.addAll(form.getValidationErrors());
        	if (errors.size()!= 0) {
		        request.setAttribute("errors",errors);
		        return "addMed.jsp";
		    }    
        	String DelMed = (String) session.getAttribute("deletid");
        	String NewMed;
        	/*
        	 * For Multiple Selection options.
        	 * */
        	String[] DayCheckList = request.getParameterValues("dayChecks");
        	String DayCheckDL = null;
        	for(String daychecks : DayCheckList){
        		       DayCheckDL = DayCheckDL + daychecks;
        	}
        	DayCheckDL = DayCheckDL.substring(4,DayCheckDL.length());
        	//if user want some medication schedule be deleted.
        	if(DelMed != null){
        		NewMed = DelMed;
        		AddMed = new Medication(Integer.parseInt(NewMed));
        		AddMed.setName(form.getName());
        		AddMed.setUsername(user.getEmailAddress());
        		AddMed.setPurpose(form.getPurpose());
        		AddMed.setFreqSelect1(form.getFreqSelect1());
        		AddMed.setFreqSelect2(form.getFreqSelect2());
        		AddMed.setDayChecks(DayCheckDL);
        		AddMed.setStartTimeHour(form.getStartTimeHour());
        		AddMed.setStartTimeMin(form.getStartTimeMin());
        		AddMed.setStartAMPM(form.getStartAMPM());   
        		AddMed.setDosage(Integer.parseInt(form.getDosage()));
        		AddMed.setDosageUnit(form.getDosageUnit());
        		//AddMed.setAllNum(AllNum + 1);	
        		//create a new user.
        		medDAO.create(AddMed);
        	//if no scheduled medication be deleted.
        	}else{
        		int AllSize = medDAO.size();
        		//initialization situation.
        		if(AllSize == 0){
        			NewMed = Integer.toString(AllSize);
        			AddMed = new Medication(Integer.parseInt(NewMed));
        			AddMed.setName(form.getName());
            		AddMed.setUsername(user.getEmailAddress());
            		AddMed.setPurpose(form.getPurpose());
            		AddMed.setFreqSelect1(form.getFreqSelect1());
            		AddMed.setFreqSelect2(form.getFreqSelect2());
            		AddMed.setDayChecks(DayCheckDL);
            		AddMed.setStartTimeHour(form.getStartTimeHour());
            		AddMed.setStartTimeMin(form.getStartTimeMin());
            		AddMed.setStartAMPM(form.getStartAMPM());
            		AddMed.setDosage(Integer.parseInt(form.getDosage()));
            		AddMed.setDosageUnit(form.getDosageUnit());
            		medDAO.create(AddMed);
        		}else{
        			AllSize = medDAO.getLastId();
        			NewMed = Integer.toString(AllSize);
        			AddMed = new Medication(Integer.parseInt(NewMed) + 1);
        			AddMed.setName(form.getName());
            		AddMed.setUsername(user.getEmailAddress());
            		AddMed.setPurpose(form.getPurpose());
            		AddMed.setFreqSelect1(form.getFreqSelect1());
            		AddMed.setFreqSelect2(form.getFreqSelect2());
            		AddMed.setDayChecks(DayCheckDL);
            		AddMed.setStartTimeHour(form.getStartTimeHour());
            		AddMed.setStartTimeMin(form.getStartTimeMin());
            		AddMed.setStartAMPM(form.getStartAMPM());
            		AddMed.setDosage(Integer.parseInt(form.getDosage()));
            		AddMed.setDosageUnit(form.getDosageUnit());
            		medDAO.create(AddMed);
        		}
        	}
        	
        	session.setAttribute("redirectto", null);
    		session.setAttribute("deleteid", null);
            session.setAttribute("user", user);
            String RedirectTo = (String) session.getAttribute("redirectto");
            Medicationlist_1 = medDAO.getMedicationList(user.getEmailAddress());
            if(RedirectTo != null){
            	request.setAttribute("medicationlist",Medicationlist_1);
            	return RedirectTo;
            }
    		request.setAttribute("medicationlist", Medicationlist_1);
    		return "addMed.jsp";
	}catch(DAOException e1){
		e1.printStackTrace();
	} catch (FormBeanException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}//if user did not add any new medication.
		Medicationlist_1 = medDAO.getMedicationList(user.getEmailAddress());
 		request.setAttribute("medicationlist", Medicationlist_1);
		return "addMed.jsp";
	}else{
		Medicationlist_1 = medDAO.getMedicationList(user.getEmailAddress());
		request.setAttribute("medicationlist", Medicationlist_1);
		return "addMed.jsp";
	}
}
}
