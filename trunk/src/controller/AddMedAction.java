package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class AddMedAction extends Action {

	public AddMedAction(Model model) { }

	public String getName() { return "addMed.do"; }

	public String perform(HttpServletRequest request) {
        return "addMed.jsp";
    }
}
