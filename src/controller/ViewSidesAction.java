package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.SideEffect;
import databeans.SideEffectLog;
import formbeans.LoginForm;

import model.Model;
import model.SideDAO;
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
public class ViewSidesAction extends Action {
	private SideDAO sideDAO;
	private LogSideDAO logSideDAO;
	private String redirectTo = "showAddSide.jsp";

	public ViewSidesAction(Model model) {
		sideDAO = model.getSideDAO();
		logSideDAO = model.getLogSideDAO();
	}

	public String getName() { return "viewSides.do"; }

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
		SideEffect[] SideEffectsList = sideDAO.getSideEffectsList(user.getEmailAddress());
		SideEffectLog[] sidelog = logSideDAO.getLogSideList(user.getEmailAddress());
		session.setAttribute("user", user);
		request.setAttribute("sideeffectslist", SideEffectsList);
		request.setAttribute("sideeffectslog", sidelog);
		session.setAttribute("redirectTo",redirectTo);
		return "showAddSide.jsp";

	}
}