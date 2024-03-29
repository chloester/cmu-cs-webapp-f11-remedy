package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.SideEffect;
import databeans.User;

import formbeans.DelSideForm;
import formbeans.LoginForm;

import model.SideDAO;
import model.Model;

public class DelSideAction extends Action{
	private SideDAO sideDAO;
	private FormBeanFactory<DelSideForm> formBeanFactory = FormBeanFactory.getInstance(DelSideForm.class);
	List<String> delListSide = new ArrayList<String>();
	
	public DelSideAction(Model model){
		sideDAO = model.getSideDAO();
	}
	public String getName() {return "delSide.do";}
	
	public String perform(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			LoginForm form = new LoginForm();
			form.setRedirect("/addSide.do");
			request.setAttribute("loginform", form);
			return "homePage.jsp";
		}
	/*
	 * Delete the corresponding side effect, then show the side effect list.
	 * */
	SideEffect [] sideEffectlist;
	String delName = null;
	String button = request.getParameter("button");
	if(button != null){
		if(button.equals("Delete ")){
		try {
			DelSideForm form = new DelSideForm();
			form = formBeanFactory.create(request);	
			delListSide.add(form.getSideid());
		if(!form.isPresent()){
			System.out.println("form built wrong!");
			return "showAddSide.jsp";
		}
		//add deleted id into the delete list.
		try {
			if(form.getSideid() != null){
		    delName = sideDAO.getSideName(Integer.parseInt(form.getSideid())).getName();
			}
			sideDAO.Delete(Integer.parseInt(form.getSideid()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession(false);
		request.setAttribute("message","Successfully deleted " + delName + ". ");
		synchronized(session){session.setAttribute("deletelistside", delListSide);}
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else{
		HttpSession session = request.getSession(false);
		synchronized(session){session.setAttribute("deletelistside", delListSide);}
		sideEffectlist = sideDAO.getSideEffectsList(user.getEmailAddress());
		request.setAttribute("sideeffectslist",sideEffectlist);
		return "showAddSide.jsp";
	}
	HttpSession session = request.getSession(false);
	synchronized(session){session.setAttribute("deletelistside", delListSide);}
	sideEffectlist = sideDAO.getSideEffectsList(user.getEmailAddress());
	request.setAttribute("sideeffectslist", sideEffectlist);
	return "showAddSide.jsp";
	}
	return button;
	}
}
