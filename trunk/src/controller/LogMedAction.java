package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogMedAction extends Action1 {

	public LogMedAction(Model model) { }

	public String getName() { return "logmed.do"; }

	public String perform(HttpServletRequest request) {
        return "logMed.jsp";
    }
}
