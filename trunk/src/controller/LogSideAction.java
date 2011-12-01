package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.SideEffectLog;
import databeans.SideEffect;
import databeans.User;
import formbeans.LogSideForm;
import formbeans.LoginForm;

import model.LogSideDAO;
import model.SideDAO;
import model.Model;

/*
	* Logs out by setting the "user" session attribute to null.
	* (Actions don't be much simpler than this.)
	*/
public class LogSideAction extends Action {
	private LogSideDAO logsideDAO;
	private SideEffectLog logSideMed;
	private SideDAO sideDAO;
	private FormBeanFactory<LogSideForm> formBeanFactory = FormBeanFactory.getInstance(LogSideForm.class);

	public LogSideAction(Model model) {
		logsideDAO = model.getLogSideDAO();
		sideDAO = model.getSideDAO();
	}
	public String getName() { return "logSide.do"; }

	public String perform(HttpServletRequest request) {
		//must be logged in for this one.
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			LoginForm form = new LoginForm();
			form.setRedirect("/logSide.do");
			request.setAttribute("loginform", form);
			return "homePage.jsp";
		}
		/*
			* if the user has already logged in.
			* */

			//SideEffectLog[] LogSidelist;
		SideEffect[] sidelist;
		//error list for error mention function.
		List<String> errors = new ArrayList<String>();
		String button;
		button = request.getParameter("button");
		if(button != null){
			if(button.equals("Log Side Effects")){
				try{
					LogSideForm form = formBeanFactory.create(request);
					request.setAttribute("logsideform", form);
					if(!form.isPresent()){
						return "homepage.jsp";
					}
					HttpSession session = request.getSession(false);
					//check the errors.
					errors.addAll(form.getValidationErrors());
					if (errors.size()!= 0) {
						sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
						request.setAttribute("sideeffectslist",sidelist);
						request.setAttribute("errors",errors);
						return "logSide.jsp";
					}    
					System.out.println("the loged sideeffect name is " + form.getName());
					String delMed = (String) session.getAttribute("deletid");
					String newMed;
					//if user want some medication schedule be deleted.
					if(delMed != null){
						newMed = delMed;
						logSideMed = new SideEffectLog(Integer.parseInt(newMed));
						logSideMed.setOwner(user.getEmailAddress());
						logSideMed.setName(form.getName());
						logSideMed.setDate(form.getDate());
						logSideMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
						logSideMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
						logSideMed.setTimeAMPM(form.getTimeAMPM());   
						logSideMed.setValue(Integer.parseInt(form.getValue()));
						//create a new user.
						logsideDAO.create(logSideMed);
						//if no scheduled medication be deleted.
					}else{
						int allSize = logsideDAO.size();
						//initialization situation.
						if(allSize == 0){
							newMed = Integer.toString(allSize);
							logSideMed = new SideEffectLog(Integer.parseInt(newMed));
							logSideMed.setOwner(user.getEmailAddress());
							logSideMed.setName(form.getName());
							logSideMed.setDate(form.getDate());
							logSideMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
							logSideMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
							logSideMed.setTimeAMPM(form.getTimeAMPM());   
							logSideMed.setValue(Integer.parseInt(form.getValue()));
							//create a new user.
							logsideDAO.create(logSideMed);
						}else{
							allSize = logsideDAO.getLastId();
							newMed = Integer.toString(allSize);
							logSideMed = new SideEffectLog(Integer.parseInt(newMed) + 1);
							logSideMed.setOwner(user.getEmailAddress());
							logSideMed.setName(form.getName());
							logSideMed.setDate(form.getDate());
							logSideMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
							logSideMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
							logSideMed.setTimeAMPM(form.getTimeAMPM());   
							logSideMed.setValue(Integer.parseInt(form.getValue()));
							//create a new user.
							logsideDAO.create(logSideMed);
						}
					}

					session.setAttribute("redirectto", null);
					session.setAttribute("deleteid", null);
					session.setAttribute("user", user);
					String redirectTo = (String) session.getAttribute("redirectto");
					//LogSidelist = logsideDAO.getLogSideList(user.getEmailAddress());
					sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
					if(redirectTo != null){
						request.setAttribute("sideeffectslist",sidelist);
						return redirectTo;
					}
					request.setAttribute("sideeffectslist", sidelist);
					return "logSide.jsp";
				}catch(DAOException e1){
					e1.printStackTrace();
				} catch (FormBeanException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//if user did not add any new side effect.
			sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
			request.setAttribute("sideeffectslist", sidelist);
			return "logSide.jsp";
		}else{
			sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
			request.setAttribute("sideeffectslist", sidelist);
			return "logSide.jsp";
		}
	}
}
