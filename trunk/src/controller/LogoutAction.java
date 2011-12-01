package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

	public LogoutAction(Model model) { }

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        synchronized(session){
        session.setAttribute("user",null);
        session.setAttribute("errors",null);
        session.setAttribute("deletelist", null);
        session.setAttribute("deletelistside", null);
        session.setAttribute("redirectTo",null);
        }
		request.setAttribute("message","You have logged out");
        return "homepage.jsp";
    }
}
