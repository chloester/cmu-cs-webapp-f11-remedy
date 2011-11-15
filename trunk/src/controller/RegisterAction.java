package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.User;

import formbeans.RegisterForm;

import model.Model;
import model.UserDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;


public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	private UserDAO userDAO;
	//instantiation.
	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        //check session attribution.
        User registeruser = (User) request.getSession(true).getAttribute("user");
    	if (registeruser != null) {
    		//errors.add("You are already logged in.");
    		return "haslogin.jsp";
    	}
        
    	try {
	        RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("registerform",form);
	        if (!form.isPresent()) {
	            return "error.jsp";
	        }
	        /*
	         * If there are something wrong with the user input,
	         * e.g. no password, fail to repeat password , 
	         * 
	         */
	        //System.out.println("the eamil address is " + form.getUserName());
	        errors.addAll(form.registerCheckErrors());
	        if (errors.size()!= 0) {
	            return "register.jsp";
	        }
	        //check duplicated registration.
	        User checkuser = userDAO.lookup(form.getUserName());
	        if( checkuser != null){
	        	errors.add("Please change another emailadress, we are sorry.");
	        	return "register.jsp";
	        }
	        // Create the user bean
	        User user = new User(form.getUserName());
	        user.setPassword(form.getPassword());
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());
			user.setGender(form.getGender());
			user.setState(form.getState());
			user.setCountry(form.getCountry());
        	userDAO.create(user);   
			/*
			 * After successful registration, redirectTo personal page.
			 */
	        HttpSession session = request.getSession(false);
	        session.setAttribute("user",user);
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
    }
}
