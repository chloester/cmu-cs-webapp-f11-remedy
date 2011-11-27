package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.MedLog;
import databeans.SideEffectLog;
import formbeans.LoginForm;

import model.Model;
import model.LogMedDAO;
import model.LogSideDAO;

import java.util.ArrayList;
import java.util.List;

//import org.mybeans.dao.DAOException;
//import org.mybeans.form.FormBeanException;
//import org.mybeans.form.FormBeanFactory;

/*
	* Logs out by setting the "user" session attribute to null.
	* (Actions don't be much simpler than this.)
	*/
public class VisualizeAction extends Action {
	private LogMedDAO logMedDAO;
	private LogSideDAO logSideDAO;
	private String redirectTo = "visualize.jsp";

	public VisualizeAction(Model model) {
		logMedDAO = model.getLogMedDAO();
		logSideDAO = model.getLogSideDAO();
	}

	public String getName() { return "visualize.do"; }

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		HttpSession session = request.getSession();
		User user = (User) request.getSession().getAttribute("user");

		if(user == null){
			session.setAttribute("user", user);
			LoginForm form = new LoginForm();
			form.setRedirect("/login.do");
			request.setAttribute("loginform", form);
			errors.add("please login");
			session.setAttribute("redirectTo",redirectTo);
			return "homepage.jsp";
		}
		MedLog[] medlog = logMedDAO.getLogMedicationList(user.getEmailAddress());
		SideEffectLog[] sidelog = logSideDAO.getLogSideList(user.getEmailAddress());
		//SideEffectLog[] sidelog = logSideDAO.getLogSide(user.getEmailAddress());
		session.setAttribute("user", user);
		request.setAttribute("medicationlist", medlog);
		request.setAttribute("sideeffectslist", sidelog);
		return "visualize.jsp";
	}
}