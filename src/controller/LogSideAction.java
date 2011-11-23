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
	private SideEffectLog LogSideMed;
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
		SideEffect[] Sidelist;
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
        		Sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
            	request.setAttribute("sideeffectslist",Sidelist);
		        request.setAttribute("errors",errors);
		        return "logSide.jsp";
		    }    
        	System.out.println("the loged sideeffect name is " + form.getName());
        	String DelMed = (String) session.getAttribute("deletid");
        	String NewMed;
        	//if user want some medication schedule be deleted.
        	if(DelMed != null){
        		NewMed = DelMed;
        		LogSideMed = new SideEffectLog(Integer.parseInt(NewMed));
        		LogSideMed.setOwner(user.getEmailAddress());
        		LogSideMed.setName(form.getName());
        		LogSideMed.setDate(form.getDate());
        		LogSideMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
        		LogSideMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
        		LogSideMed.setTimeAMPM(form.getTimeAMPM());   
        		LogSideMed.setValue(Integer.parseInt(form.getValue()));
        		//create a new user.
        		logsideDAO.create(LogSideMed);
        	//if no scheduled medication be deleted.
        	}else{
        		int AllSize = logsideDAO.size();
        		//initialization situation.
        		if(AllSize == 0){
        			NewMed = Integer.toString(AllSize);
        			LogSideMed = new SideEffectLog(Integer.parseInt(NewMed));
            		LogSideMed.setOwner(user.getEmailAddress());
            		LogSideMed.setName(form.getName());
            		LogSideMed.setDate(form.getDate());
            		LogSideMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
            		LogSideMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
            		LogSideMed.setTimeAMPM(form.getTimeAMPM());   
            		LogSideMed.setValue(Integer.parseInt(form.getValue()));
            		//create a new user.
            		logsideDAO.create(LogSideMed);
        		}else{
        			AllSize = logsideDAO.getLastId();
        			NewMed = Integer.toString(AllSize);
        			LogSideMed = new SideEffectLog(Integer.parseInt(NewMed) + 1);
            		LogSideMed.setOwner(user.getEmailAddress());
            		LogSideMed.setName(form.getName());
            		LogSideMed.setDate(form.getDate());
            		LogSideMed.setTimeHr(Integer.parseInt(form.getTimeHr()));
            		LogSideMed.setTimeMin(Integer.parseInt(form.getTimeMin()));
            		LogSideMed.setTimeAMPM(form.getTimeAMPM());   
            		LogSideMed.setValue(Integer.parseInt(form.getValue()));
            		//create a new user.
            		logsideDAO.create(LogSideMed);
        		}
        	}
        	
        	session.setAttribute("redirectto", null);
    		session.setAttribute("deleteid", null);
            session.setAttribute("user", user);
            String RedirectTo = (String) session.getAttribute("redirectto");
            //LogSidelist = logsideDAO.getLogSideList(user.getEmailAddress());
            Sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
            if(RedirectTo != null){
            	request.setAttribute("sideeffectslist",Sidelist);
            	return RedirectTo;
            }
    		request.setAttribute("sideeffectslist", Sidelist);
    		return "logSide.jsp";
	}catch(DAOException e1){
		e1.printStackTrace();
	} catch (FormBeanException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}//if user did not add any new side effect.
        Sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
		request.setAttribute("sideeffectslist", Sidelist);
		return "logSide.jsp";
	}else{
		Sidelist = sideDAO.getSideEffectsList(user.getEmailAddress());
	    request.setAttribute("sideeffectslist", Sidelist);
		return "logSide.jsp";
	}
}
}
