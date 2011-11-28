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
	private MedLog AddLogMed;
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
		Medication[] Medicationlist;
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
						Medicationlist = medDAO.getMedicationList(user.getEmailAddress());
						request.setAttribute("medicationlist", Medicationlist);
						return "logMed.jsp";
					}    
					String DelMed = (String) session.getAttribute("deletid");
					String NewMed;
					/*
						* For Multiple Selection options.
						* */
						/*String[] DayCheckList = request.getParameterValues("dayChecks");
					String DayCheckDL = null;
					for(String daychecks : DayCheckList){
						DayCheckDL = DayCheckDL + daychecks;
					}
					DayCheckDL = DayCheckDL.substring(4,DayCheckDL.length());*/
						//if user want some medication schedule be deleted.
					if(DelMed != null){
						NewMed = DelMed;
						AddLogMed = new MedLog(Integer.parseInt(NewMed));
						AddLogMed.setOwner(user.getEmailAddress());
						AddLogMed.setName(form.getName());
						AddLogMed.setDate(form.getDate());
						AddLogMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
						AddLogMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
						AddLogMed.setTimeAMPM(form.getTimeAMPM());   
						//create a new user.
						logmedDAO.create(AddLogMed);
						//if no scheduled medication be deleted.
					}else{
						int AllSize = logmedDAO.size();
						//initialization situation.
						if(AllSize == 0){
							NewMed = Integer.toString(AllSize);
							AddLogMed = new MedLog(Integer.parseInt(NewMed));
							AddLogMed.setOwner(user.getEmailAddress());
							AddLogMed.setName(form.getName());
							AddLogMed.setDate(form.getDate());
							AddLogMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
							AddLogMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
							AddLogMed.setTimeAMPM(form.getTimeAMPM());  
							logmedDAO.create(AddLogMed);
						}else{
							AllSize = logmedDAO.getLastId();
							NewMed = Integer.toString(AllSize);
							AddLogMed = new MedLog(Integer.parseInt(NewMed) + 1);
							AddLogMed.setOwner(user.getEmailAddress());
							AddLogMed.setName(form.getName());
							AddLogMed.setDate(form.getDate());
							AddLogMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
							AddLogMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
							AddLogMed.setTimeAMPM(form.getTimeAMPM());  
							logmedDAO.create(AddLogMed);
						}
					}

					session.setAttribute("redirectto", null);
					session.setAttribute("deleteid", null);
					session.setAttribute("user", user);
					String RedirectTo = (String) session.getAttribute("redirectto");
					//LogMedicationlist = logmedDAO.getLogMedicationList(user.getEmailAddress());
					Medicationlist = medDAO.getMedicationList(user.getEmailAddress());
					if(RedirectTo != null){
						//request.setAttribute("logmedicationlist",LogMedicationlist);
						request.setAttribute("medicationlist", Medicationlist);
						return RedirectTo;
					}
					//request.setAttribute("logmedicationlist", LogMedicationlist);
					request.setAttribute("medicationlist", Medicationlist);
					return "logMed.jsp";
				}catch(DAOException e1){
					e1.printStackTrace();
				} catch (FormBeanException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//if user did not log any new medication.
			Medicationlist = medDAO.getMedicationList(user.getEmailAddress());
			request.setAttribute("medicationlist", Medicationlist);
			return "logMed.jsp";
		}else{
			Medicationlist = medDAO.getMedicationList(user.getEmailAddress());
			request.setAttribute("medicationlist", Medicationlist);
			return "logMed.jsp";
		}
	}
}
