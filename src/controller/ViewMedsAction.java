package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.Medication;
import formbeans.LoginForm;

import model.Model;
import model.MedDAO;

import java.util.ArrayList;
import java.util.List;

//import org.mybeans.dao.DAOException;
//import org.mybeans.form.FormBeanException;
//import org.mybeans.form.FormBeanFactory;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class ViewMedsAction extends Action {
    private MedDAO medDAO;
    private String redirectTo = "showAddMed.jsp";
	
    public ViewMedsAction(Model model) {
		medDAO = model.getMedDAO();
	}

	public String getName() { return "viewMeds.do"; }

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		HttpSession session = request.getSession();
		User user = (User) request.getSession().getAttribute("user");
        
		if(user == null){
			LoginForm form = new LoginForm();
			form.setRedirect("/login.do");
			request.setAttribute("loginform", form);
			errors.add("please login");
			synchronized(session){
			session.setAttribute("redirectTo",redirectTo);
			session.setAttribute("user", user);
			}
			return "homepage.jsp";
		}
		Medication[] MedicationList = medDAO.getMedicationList(user.getEmailAddress());
		//System.out.println("the class attribute is " + ("class"));
		//request.setAttribute("class", "active");
		if(MedicationList!= null){
			synchronized(session){session.setAttribute("user", user);}
			request.setAttribute("medicationlist", MedicationList);
			return "showAddMed.jsp";
		}else{
			synchronized(session){session.setAttribute("user",user);}
			request.setAttribute("medicationlist", MedicationList);
			return "showAddMed.jsp";
		}
		
    }
}