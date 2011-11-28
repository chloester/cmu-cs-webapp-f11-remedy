package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.User;
import databeans.Medication;

import formbeans.DelMedForm;
import formbeans.LoginForm;

import model.MedDAO;
import model.Model;

public class DelMedAction extends Action {
	private MedDAO medDAO;
	private FormBeanFactory<DelMedForm> formBeanFactory = FormBeanFactory.getInstance(DelMedForm.class);
	List<String> Dellist = new ArrayList<String>();
	
	public DelMedAction(Model model){
		medDAO = model.getMedDAO();
	}
	public String getName() {return "delMed.do";}
	
	public String perform(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			LoginForm form = new LoginForm();
			form.setRedirect("/addMed.do");
			request.setAttribute("loginform", form);
			return "homePage.jsp";
		}
	/*
	 * Delete the corresponding Medication, then show the medication list.
	 * */
	Medication[] Medicationlist;
	String button = request.getParameter("button");
	String DelName = null;
	if(button != null){
		if(button.equals("Delete")){
		try {
			DelMedForm form = new DelMedForm();
			form = formBeanFactory.create(request);	
			Dellist.add(form.getMedid());
		if(!form.isPresent()){
			return "showAddMed.jsp";
		}
		//add deleted id into the delete list.
		try {
		    DelName = medDAO.getMedName(Integer.parseInt(form.getMedid())).getName();
			medDAO.Delete(Integer.parseInt(form.getMedid()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession(false);
		request.setAttribute("message","Successfully deleted " + DelName + ". ");
		session.setAttribute("deletelist", Dellist);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else{
		HttpSession session = request.getSession(false);
		session.setAttribute("deletelist", Dellist);
		Medicationlist = medDAO.getMedicationList(user.getEmailAddress());
		request.setAttribute("medicationlist",Medicationlist);
		return "showAddMed.jsp";
	}
	HttpSession session = request.getSession(false);
	session.setAttribute("deletelist", Dellist);
	Medicationlist = medDAO.getMedicationList(user.getEmailAddress());
	request.setAttribute("medicationlist", Medicationlist);
	return "showAddMed.jsp";
	}
	return button;
	}
}
