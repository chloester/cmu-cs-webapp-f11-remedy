package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.SideEffectLog;
import databeans.User;

import formbeans.LoginForm;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	private UserDAO userDAO;
	private String button;
	//instantiation.
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}
    //return Name.
	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        button = request.getParameter("button");
        if(button == null){
        	return "homepage.jsp";
        }
        if(button.equals("login")){
        	try {
        		LoginForm form = formBeanFactory.create(request);
        		request.setAttribute("loginform",form);
        		
        		/*Hi Chloe, I just create an error jsp for correcting, 
        		  you could modify it  or simply replace it with other 
        		  jsp page for server errors.
        		*/
        		if (!form.isPresent()) {
        			return "error.jsp";
        		}
        		
        		//Hi Chloe, you could modify here, direct registered people to your jsp file.
        		User registeruser = (User) request.getSession(true).getAttribute("user");
        		if (registeruser != null) {
        			errors.add("You are already logged in.");
        			return "haslogin.jsp";
        		}
        		
        		//Do Validation,if there was something wrong, return to the homepage.
        		errors.addAll(form.checkLoginFormErrors());
        		if (errors.size() != 0) {
        			return "homepage.jsp";
        		}
        		
        		// Look up the user login in.
        		User user = userDAO.lookup(form.getEmailaddress());   
        		if (user == null) {
        			errors.add("The user does not exist!");
        			return "homepage.jsp";
        		}else if (!user.checkPassword(form.getPassword())) {
        			errors.add("Incorrect password");
        			return "homepage.jsp";
        		}
        	
        	/*
	         * After successful login, set the session.
	         */
	        HttpSession session = request.getSession(false);
	        /*if( session.isNew()){
        		request.setAttribute("message","Hi,Long time no see.");
        	}else{
        		//we could skip it out.
        		request.setAttribute("message","Hi, Welcome Back.");
        	}*/
	        session.setAttribute("user",user);
	        // After successful login send to the page user wanted to.
	        String redirectTo = (String) session.getAttribute("redirectTo");
	        if (redirectTo != null) return redirectTo;
			return "haslogin.jsp";
        } catch (DAOException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
        }else if(button.equals("register")){
    	 	return "register.jsp";
    	}
		return null;
    }
}
    

