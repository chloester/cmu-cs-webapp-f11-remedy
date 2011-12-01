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
	private Medication addMed;
	private FormBeanFactory<AddMedForm> formBeanFactory = FormBeanFactory.getInstance(AddMedForm.class);

	public AddMedAction(Model model) {
		medDAO = model.getMedDAO();
	}
	public String getName() { return "addMed.do"; }

	@SuppressWarnings("unchecked")
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
	
		Medication[] medicationlist_1;
		List<String> delList = new ArrayList<String>();
		String delMed = null;
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
        	int allNum = medDAO.size();
        	if(allNum != 0){
        		delList = (List<String>) session.getAttribute("deletelist");
        		if(delList != null){
        			if(!delList.isEmpty()){
        				delMed = delList.get(delList.size()-1);
        				System.out.println("the demed is " + delMed);
        				delList.remove(delList.size()-1);
        				synchronized(session) {session.setAttribute("deletelist", delList);}
        			}else{
        				System.out.println("The dellist is zero");
        				delMed = null;
        				synchronized(session){session.setAttribute("deletelist", null);}
        			}
        		}else{
        			delMed = null;
        			synchronized(session){session.setAttribute("deletelist", null);}
        		}
        	}
        	String newMed;
        	/*
        	 * For Multiple Selection options.
        	 * */
        	String[] dayCheckList = request.getParameterValues("dayChecks");
        	String dayCheckDL = null;
        	for(String daychecks : dayCheckList){
        		       dayCheckDL = dayCheckDL + daychecks;
        	}
        	dayCheckDL = dayCheckDL.substring(4,dayCheckDL.length());
        	//if user want some medication schedule be deleted.
        	if(delMed != null){
        		newMed = delMed;
        		addMed = new Medication(Integer.parseInt(newMed));
        		createMed(addMed,form);
        		addMed.setUsername(user.getEmailAddress());
        		addMed.setDayChecks(dayCheckDL);
        		//addMed.setAllNum(allNum + 1);	
        		//create a new user.
        		medDAO.create(addMed);
        		synchronized(session){
				session.setAttribute("deletelist",delList);
				request.setAttribute("addmedform", null);
				request.setAttribute("message","Successfully added " + form.getName()+ ". ");
				}
        	//if no scheduled medication be deleted.
        	}else{
        		int allSize = medDAO.size();
        		//System.out.println("all size is " + Integer.toString(allSize));
        		//initialization situation.
        		if(allSize == 0){
        			newMed = Integer.toString(allSize);
            		System.out.println("all size is " + newMed);
        			addMed = new Medication(Integer.parseInt(newMed));
        			createMed(addMed,form);
            		addMed.setUsername(user.getEmailAddress());
            		addMed.setDayChecks(dayCheckDL);
            		medDAO.create(addMed);
            		synchronized(session){
    				session.setAttribute("deletelist", null);
    				request.setAttribute("addmedform", null);
    				request.setAttribute("message","Successfully added " + form.getName() + ".");
            		}
        		}else{
        			allSize = medDAO.getLastId();
        			newMed = Integer.toString(allSize);
        			addMed = new Medication(Integer.parseInt(newMed) + 1);
        			createMed(addMed,form);
            		addMed.setUsername(user.getEmailAddress());
            		addMed.setDayChecks(dayCheckDL);
            		medDAO.create(addMed);
            		synchronized(session){
    				session.setAttribute("deletelist", null);
    				request.setAttribute("addmedform", null);
    				request.setAttribute("message","Successfully added " + form.getName()+ "."); 
    				}
        		}
        	}
        	synchronized(session){
        	session.setAttribute("redirectto", null);
    		session.setAttribute("deleteid", null);
            session.setAttribute("user", user);
        	}
            String redirectTo = (String) session.getAttribute("redirectto");
            medicationlist_1 = medDAO.getMedicationList(user.getEmailAddress());
            if(redirectTo != null){
            	request.setAttribute("medicationlist",medicationlist_1);
            	return redirectTo;
            }
    		request.setAttribute("medicationlist", medicationlist_1);
    		synchronized(session){
    		session.setAttribute("pagenumber", 1);
    		}
    		return "addMed.jsp";
	}catch(DAOException e1){
		e1.printStackTrace();
	} catch (FormBeanException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}//if user did not add any new medication.
		medicationlist_1 = medDAO.getMedicationList(user.getEmailAddress());
 		request.setAttribute("medicationlist", medicationlist_1);
		return "addMed.jsp";
	}else{
		medicationlist_1 = medDAO.getMedicationList(user.getEmailAddress());
		request.setAttribute("medicationlist", medicationlist_1);
		return "addMed.jsp";
	}
}
	private void createMed(Medication addMed,AddMedForm form){
		addMed.setName(form.getName());
		addMed.setPurpose(form.getPurpose());
		addMed.setFreqSelect1(form.getFreqSelect1());
		addMed.setFreqSelect2(form.getFreqSelect2());
		addMed.setStartTimeHour(form.getStartTimeHour());
		addMed.setStartTimeMin(form.getStartTimeMin());
		addMed.setStartAMPM(form.getStartAMPM());   
		addMed.setDosage(Integer.parseInt(form.getDosage()));
		addMed.setDosageUnit(form.getDosageUnit());
	}
}
