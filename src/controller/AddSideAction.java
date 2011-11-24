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
	private SideEffect AddSide;
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
	
		SideEffect[] SideEffectslist;
		List<String> Dellistside = new ArrayList<String>();
		String DelSide = null;
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
        	int AllNum = sideDAO.size();
        	if(AllNum != 0){
        	Dellistside = (List<String>) session.getAttribute("deletelistside");
        	if(Dellistside != null){
    			if(!Dellistside.isEmpty()){
    				System.out.println("the Deletlistside size is " + Dellistside.size());
    				DelSide = Dellistside.get(Dellistside.size()-1);
    				System.out.println("the deside is " + DelSide);
    				Dellistside.remove(Dellistside.size()-1);
    				session.setAttribute("deletelistside", Dellistside);
    			}else{
    				System.out.println("The dellist is zero");
    				DelSide = null;
    				session.setAttribute("deletelistside", null);
    			}
    		}else{
    			DelSide = null;
    			session.setAttribute("deletelistside", null);
    		}
        	}
        	String NewSide;
        	//if user want some side effects  be deleted.
        	if(DelSide != null){
        		NewSide = DelSide;
        		AddSide = new SideEffect(Integer.parseInt(NewSide));
        		AddSide.setName(form.getName());
        		AddSide.setOwner(user.getEmailAddress());
        		//AddSide.setAllNum(AllNum + 1);	
        		//create a new user.
        		sideDAO.create(AddSide);
				session.setAttribute("deletelistside",Dellistside);
        	//if no scheduled medication be deleted.
        	}else{
        		int AllSize = sideDAO.size();
        		//initialization situation.
        		if(AllSize == 0){
        			NewSide = Integer.toString(AllSize);
        			AddSide = new SideEffect(Integer.parseInt(NewSide));
        			AddSide.setName(form.getName());
            		AddSide.setOwner(user.getEmailAddress());
            		sideDAO.create(AddSide);
    				session.setAttribute("deletelistside",Dellistside);
        		}else{
        			AllSize = sideDAO.getLastId();
        			NewSide = Integer.toString(AllSize);
        			AddSide = new SideEffect(Integer.parseInt(NewSide) + 1);
        			AddSide.setName(form.getName());
            		AddSide.setOwner(user.getEmailAddress());
            		sideDAO.create(AddSide);
    				session.setAttribute("deletelistside",Dellistside);
        		}
        	}
        	
        	session.setAttribute("redirectto", null);
    		session.setAttribute("deleteid", null);
            session.setAttribute("user", user);
            String RedirectTo = (String) session.getAttribute("redirectto");
            SideEffectslist = sideDAO.getSideEffectsList(user.getEmailAddress());
            if(RedirectTo != null){
            	request.setAttribute("sideeffectslist",SideEffectslist);
            	return RedirectTo;
            }
    		request.setAttribute("sideeffectslist", SideEffectslist);
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
