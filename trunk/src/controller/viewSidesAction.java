package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.SideEffect;
import formbeans.LoginForm;

import model.Model;
import model.SideDAO;

import java.util.ArrayList;
import java.util.List;

//import org.mybeans.dao.DAOException;
//import org.mybeans.form.FormBeanException;
//import org.mybeans.form.FormBeanFactory;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class viewSidesAction extends Action {
    private SideDAO sideDAO;
    private String redirectTo = "showAddSide.jsp";
	
    public viewSidesAction(Model model) {
		sideDAO = model.getSideDAO();
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
		if(SideEffectsList!= null){
			session.setAttribute("user", user);
			request.setAttribute("sideeffectslist", SideEffectsList);
			return "showAddSide.jsp";
		}else{
			session.setAttribute("user",user);
			return "showAddSide.jsp";
		}
		
    }
}