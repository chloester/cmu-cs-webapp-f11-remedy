package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.MedLog;
import databeans.User;
import databeans.Medication;
import formbeans.AddLogForm;
import formbeans.LoginForm;

import model.LogMedDAO;
import model.MedDAO;
import model.Model;

/*
	* Logs out by setting the "user" session attribute to null.
	* (Actions don't be much simpler than this.)
	*/
public class LogMedAction extends Action {
	private LogMedDAO logmedDAO;
	private MedDAO medDAO;
	//create medication bean;
	private MedLog addLogMed;
	private FormBeanFactory<AddLogForm> formBeanFactory = FormBeanFactory.getInstance(AddLogForm.class);

	public LogMedAction(Model model) {
		logmedDAO = model.getLogMedDAO();
		medDAO = model.getMedDAO();
	}
	public String getName() { return "logMed.do"; }

	public String perform(HttpServletRequest request) {
		//must be logged in for this one.
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			LoginForm form = new LoginForm();
			form.setRedirect("/logmed.do");
			request.setAttribute("loginform", form);
			return "homePage.jsp";
		}
		/*
			* if the user has already logged in.
			* */

			//MedLog[] LogMedicationlist;
		Medication[] medicationlist;
		//error list for error mention function.
		List<String> errors = new ArrayList<String>();
		String button;
		button = request.getParameter("button");
		if(button != null){
			if(button.equals("Log medication")){
				try{
					AddLogForm form = formBeanFactory.create(request);
					request.setAttribute("addlogform", form);
					if(!form.isPresent()){
						return "homepage.jsp";
					}
					HttpSession session = request.getSession(false);
					//check the errors.
					errors.addAll(form.getValidationErrors());
					if (errors.size()!= 0) {
						request.setAttribute("errors",errors);
						medicationlist = medDAO.getMedicationList(user.getEmailAddress());
						request.setAttribute("medicationlist", medicationlist);
						return "logMed.jsp";
					}    
					String delMed = (String) session.getAttribute("deletid");
					String newMed;
					/*
						* For Multiple Selection options.
						* */
						/*String[] dayCheckList = request.getParameterValues("dayChecks");
					String dayCheckDL = null;
					for(String daychecks : dayCheckList){
						dayCheckDL = dayCheckDL + daychecks;
					}
					dayCheckDL = dayCheckDL.substring(4,dayCheckDL.length());*/
						//if user want some medication schedule be deleted.
					if(delMed != null){
						newMed = delMed;
						addLogMed = new MedLog(Integer.parseInt(newMed));
						addLogMed.setOwner(user.getEmailAddress());
						addLogMed.setName(form.getName());
						addLogMed.setDate(form.getDate());
						addLogMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
						addLogMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
						addLogMed.setTimeAMPM(form.getTimeAMPM());   
						//create a new user.
						logmedDAO.create(addLogMed);
						//if no scheduled medication be deleted.
					}else{
						int allSize = logmedDAO.size();
						//initialization situation.
						if(allSize == 0){
							newMed = Integer.toString(allSize);
							addLogMed = new MedLog(Integer.parseInt(newMed));
							addLogMed.setOwner(user.getEmailAddress());
							addLogMed.setName(form.getName());
							addLogMed.setDate(form.getDate());
							addLogMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
							addLogMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
							addLogMed.setTimeAMPM(form.getTimeAMPM());  
							logmedDAO.create(addLogMed);
						}else{
							allSize = logmedDAO.getLastId();
							newMed = Integer.toString(allSize);
							addLogMed = new MedLog(Integer.parseInt(newMed) + 1);
							addLogMed.setOwner(user.getEmailAddress());
							addLogMed.setName(form.getName());
							addLogMed.setDate(form.getDate());
							addLogMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
							addLogMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
							addLogMed.setTimeAMPM(form.getTimeAMPM());  
							logmedDAO.create(addLogMed);
						}
					}

					session.setAttribute("redirectto", null);
					session.setAttribute("deleteid", null);
					session.setAttribute("user", user);
					String redirectTo = (String) session.getAttribute("redirectto");
					//LogMedicationlist = logmedDAO.getLogMedicationList(user.getEmailAddress());
					medicationlist = medDAO.getMedicationList(user.getEmailAddress());
					if(redirectTo != null){
						//request.setAttribute("logmedicationlist",LogMedicationlist);
						request.setAttribute("medicationlist", medicationlist);
						return redirectTo;
					}
					//request.setAttribute("logmedicationlist", LogMedicationlist);
					request.setAttribute("medicationlist", medicationlist);
					return "logMed.jsp";
				}catch(DAOException e1){
					e1.printStackTrace();
				} catch (FormBeanException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//if user did not log any new medication.
			medicationlist = medDAO.getMedicationList(user.getEmailAddress());
			request.setAttribute("medicationlist", medicationlist);
			return "logMed.jsp";
		}else{
			medicationlist = medDAO.getMedicationList(user.getEmailAddress());
			request.setAttribute("medicationlist", medicationlist);
			return "logMed.jsp";
		}
	}
}
