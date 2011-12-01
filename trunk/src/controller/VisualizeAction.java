package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.Medication;
import databeans.SideEffect;
import databeans.MedLog;
import databeans.SideEffectLog;
import formbeans.LoginForm;

import model.Model;
import model.MedDAO;
import model.SideDAO;
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
	private MedDAO medDAO;
	private SideDAO sideDAO;
	private LogMedDAO logMedDAO;
	private LogSideDAO logSideDAO;
	private String redirectTo = "visualize.jsp";

	public VisualizeAction(Model model) {
		medDAO = model.getMedDAO();
		sideDAO = model.getSideDAO();
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

		String button = request.getParameter("button");
		String med = request.getParameter("med");
		String side = request.getParameter("side");

		// for dropdown menus
		Medication[] medicationlist = medDAO.getMedicationList(user.getEmailAddress());
		SideEffect[] sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
		// get medication and sides
		MedLog[] medlog = logMedDAO.getLogMedicationList(user.getEmailAddress());
		SideEffectLog[] sidelog = logSideDAO.getLogSideList(user.getEmailAddress());
		int medLength = 0;
		int sideLength = 0;
		if(med != null) {
			medlog = logMedDAO.getLogMedication(user.getEmailAddress(), med);
			if(medlog != null) {
				medLength = medlog.length;
			}
		}
		if(side != null) {
			sidelog = logSideDAO.getLogSide(user.getEmailAddress(), side);
			if (sidelog != null) {
				sideLength = sidelog.length;
			}
		}
		int arraySize = medLength + sideLength;
		session.setAttribute("user", user);
		request.setAttribute("medicationlist", medicationlist);
		request.setAttribute("sideeffectslist", sidelist);
		request.setAttribute("medloglist", medlog);
		request.setAttribute("sideloglist", sidelog);
		request.setAttribute("arraysize", arraySize);
		request.setAttribute("medname", med);
		request.setAttribute("sidename", side);
		return "visualize.jsp";
	}
}