package controller;

import java.io.IOException;

import controller.Action;

import databeans.User;

import model.Model;

import javax.servlet.*;
import javax.servlet.http.*;

import org.mybeans.form.FormBeanException;

//The main controller, when the project first run, it goes into controller.
public class Controller extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
        Model model = new Model(getServletConfig());
        //user identification. 
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new RegisterAction(model));
        //user medication management.
		Action.add(new AddMedAction(model));
		Action.add(new LogMedAction(model));
	    //user side affect log.
		Action.add(new AddSideAction(model));
		Action.add(new LogSideAction(model));
        //show user information. 
		Action.add(new ViewMedsAction(model));
		Action.add(new ViewSidesAction(model));
		//for user deletion operation.
		Action.add(new DelMedAction(model));
		Action.add(new DelSideAction(model));
		//visualize schedule and graphs
		Action.add(new ScheduleAction(model));
		Action.add(new VisualizeAction(model));
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*if (model.getRequireSSL() && !request.isSecure()) {
    		// If we're required to use SSL and we're not on a secure connection, redirect
          	String host = request.getServerName();
          	String port = (request.getServerPort()==80) ? "" : ":8443";
            response.sendRedirect("https://"+host+port+request.getRequestURI());
            return;
         }*/ 
    	 String nextPage;
		try {
			nextPage = performTheAction(request);
			sendToNextPage(nextPage,request,response);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) throws FormBeanException {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        String      action = getActionName(servletPath);
		User  user = (User) session.getAttribute("user");

		if (action.equals("register.do") || action.equals("login.do")) {
			// Allow these actions without logging in
			return Action.perform(action,request);
		}
		//no session exists or one session exists.
		if (user == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do",request);
		}

		if (action.equals("welcome")){
			return "haslogin.jsp";
		}
		// Let the local or logged-in user run his chosen action
		return Action.perform(action,request);
	}
	/*
     * If nextPage is null, send back 404
     * If nextPage starts with a '/', redirect to this page.
     *    In this case, the page must be the whole servlet path including the webapp name
     * Otherwise dispatch to the page (the view)
     *    This is the common case
     * Note: If nextPage 		equals "image", we will dispatch to /image.  In the web.xml file, "/image"
     *    is mapped to the ImageServlet will which return the image bytes for display.
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	/*
    	 * if the user click a link, then redirect it to the corresponding website.
    	 */
    	//System.out.println("the next page is " + nextPage);
    	if (nextPage.substring(0, 3).equals("url")){
    		System.out.println("the nextpage.substring is " + nextPage.substring(3));
    		System.out.println("the nextpage.substring is " + nextPage.substring(3,7));
    		if(nextPage.substring(3,7).equals("http")){
        		response.sendRedirect(nextPage.substring(3));
        		return;
    		}
    		response.sendRedirect("http://" +  nextPage.substring(3));
    		return;
    	}
    	if (nextPage.charAt(0) == '/') {
    		String proto = request.isSecure() ? "https://" : "http://";
    		String host  = request.getServerName();
			String port  = ":"+String.valueOf(request.getServerPort());
			if (port.equals(":80")) port = "";
			if (port.equals(":443")) port = "";
			String context = request.getContextPath();
			int lastSlash = context.lastIndexOf('/');
			String prefix = ( lastSlash==0 ? context : context.substring(0,lastSlash) );
			response.sendRedirect(proto+host+port+prefix+nextPage);
			return;
    	}
    	/*
    	if (nextPage.charAt(0) == '.'&&nextPage.charAt(1)=='/') {
			String host  = request.getServerName();
			String port  = ":"+String.valueOf(request.getServerPort());
			/*if (port.equals(":80")) port = "";*/
		/*	System.out.println("http://"+host+port+"/DropFace"+nextPage.substring(1));
			response.sendRedirect("https://"+host+port+"/DropFace"+nextPage.substring(1));
			return;
    	}*/
   		RequestDispatcher d = request.getRequestDispatcher("/view/"+nextPage);
   		d.forward(request,response);
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
