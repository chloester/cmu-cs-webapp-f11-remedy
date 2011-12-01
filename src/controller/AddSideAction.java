package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;
import databeans.SideEffect;
import formbeans.AddSideForm;
import formbeans.LoginForm;

import model.Model;
import model.SideDAO;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class AddSideAction extends Action {
	private SideDAO sideDAO;
	//create medication bean;
	private SideEffect addSide;
	private FormBeanFactory<AddSideForm> formBeanFactory = FormBeanFactory.getInstance(AddSideForm.class);

	public AddSideAction(Model model) {
		sideDAO = model.getSideDAO();
	}
	public String getName() { return "addSide.do"; }

	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request) {
	    //must be logged in for this one.
    	User user = (User) request.getSession().getAttribute("user");
    	if(user == null){
    		LoginForm form = new LoginForm();
    		form.setRedirect("/addSide.do");
    		request.setAttribute("loginform", form);
    		return "homePage.jsp";
    	}
    	/*
    	 * if the user has already logged in.
    	 * */
	
		SideEffect[] sideEffectslist;
		List<String> delListside = new ArrayList<String>();
		String delSide = null;
		//error list for error mention function.
		List<String> errors = new ArrayList<String>();
		String button;
		button = request.getParameter("button");
		if(button != null){
		if(button.equals("Add side effect")){
		try{
			AddSideForm form = formBeanFactory.create(request);
        	request.setAttribute("addsideform", form);
        	if(!form.isPresent()){
        		return "homepage.jsp";
        	}
        	HttpSession session = request.getSession(false);
        	//check the errors.
        	errors.addAll(form.getValidationErrors());
        	if (errors.size()!= 0) {
		        request.setAttribute("errors",errors);
		        return "logSide.jsp";
		    }    
        	int allNum = sideDAO.size();
        	if(allNum != 0){
        	delListside = (List<String>) session.getAttribute("deletelistside");
        	if(delListside != null){
    			if(!delListside.isEmpty()){
    				delSide = delListside.get(delListside.size()-1);
    				delListside.remove(delListside.size()-1);
    				synchronized(session){session.setAttribute("deletelistside", delListside);}
    			}else{
    				System.out.println("The dellist is zero");
    				delSide = null;
    				synchronized(session){session.setAttribute("deletelistside", null);}
    			}
    		}else{
    			delSide = null;
    			synchronized(session){session.setAttribute("deletelistside", null);}
    		}
        	}
        	String newSide;
        	//if user want some side effects  be deleted.
        	if(delSide != null){
        		newSide = delSide;
        		addSide = new SideEffect(Integer.parseInt(newSide));
        		addSide.setName(form.getName());
        		addSide.setOwner(user.getEmailAddress());
        		//addSide.setAllNum(allNum + 1);	
        		//create a new user.
        		sideDAO.create(addSide);
        		synchronized(session){session.setAttribute("deletelistside",delListside);}
        	//if no scheduled medication be deleted.
        	}else{
        		int allSize = sideDAO.size();
        		//initialization situation.
        		if(allSize == 0){
        			newSide = Integer.toString(allSize);
        			addSide = new SideEffect(Integer.parseInt(newSide));
        			addSide.setName(form.getName());
            		addSide.setOwner(user.getEmailAddress());
            		sideDAO.create(addSide);
            		synchronized(session){session.setAttribute("deletelistside",delListside);}
        		}else{
        			allSize = sideDAO.getLastId();
        			newSide = Integer.toString(allSize);
        			addSide = new SideEffect(Integer.parseInt(newSide) + 1);
        			addSide.setName(form.getName());
            		addSide.setOwner(user.getEmailAddress());
            		sideDAO.create(addSide);
            		synchronized(session){session.setAttribute("deletelistside",delListside);}
        		}
        	}
        	
    		session.setAttribute("deleteid", null);
            session.setAttribute("user", user);
            String redirectTo = (String) session.getAttribute("redirectTo");
            sideEffectslist = sideDAO.getSideEffectsList(user.getEmailAddress());
            if(redirectTo != null){
            	request.setAttribute("sideeffectslist",sideEffectslist);
            	return redirectTo;
            }
    		request.setAttribute("sideeffectslist", sideEffectslist);
    		return "logSide.jsp";
	}catch(DAOException e1){
		e1.printStackTrace();
	} catch (FormBeanException e) {
		e.printStackTrace();
	}
	}//if user did not add any new medication.
		return "logSide.jsp";
	}else{
		return "logSide.jsp";
	}
}
}
