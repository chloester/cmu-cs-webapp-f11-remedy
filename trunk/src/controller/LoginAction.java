package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.SideEffectLog;
import databeans.User;
import databeans.Medication;
import databeans.ScheduleItem;

import formbeans.LoginForm;

import model.Model;
import model.UserDAO;
import model.MedDAO;

import org.mybeans.dao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	private UserDAO userDAO;
	private MedDAO medDAO;
	private String button;
	ArrayList <ScheduleItem> monday = new ArrayList();
	ArrayList <ScheduleItem> tuesday = new ArrayList();
	ArrayList <ScheduleItem> wednesday = new ArrayList();
	ArrayList <ScheduleItem> thursday = new ArrayList();
	ArrayList <ScheduleItem> friday = new ArrayList();
	ArrayList <ScheduleItem> saturday = new ArrayList();
	ArrayList <ScheduleItem> sunday = new ArrayList();
	//instantiation.
	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
		medDAO = model.getMedDAO();
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

				if (!form.isPresent()) {
					return "error.jsp";
				}

				// direct registered people to jsp
				User registeruser = (User) request.getSession(true).getAttribute("user");
				if (registeruser != null) {
					errors.add("You are already logged in.");
					return "haslogin.jsp";
				}

				// Do Validation,if there was something wrong, return to the homepage.
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

				// After successful login, set the session.
				HttpSession session = request.getSession(false);
				session.setAttribute("user",user);

				// After successful login send to the page user wanted to.
				String redirectTo = (String) session.getAttribute("redirectTo");
				if (redirectTo != null) return redirectTo;

				// if sending to schedule page (haslogin), prepare schedule				
				Medication[] medlist = medDAO.getMedicationList(user.getEmailAddress());
				if(medlist!=null) {
					for(int i = 0; i < medlist.length; i++) {
						// create new schedule object for each medication time
						Medication m = medlist[i];
						int freq1 = getFreq1(m); // number of times
						int freq2 = getFreq2(m); // every x hours
						int startTimeInMin = getStartTimeInMin(m);
						int startHr = getStartHr(m);
						ScheduleItem[] daily = new ScheduleItem[freq1];
						// create array of start times
						int[] times = new int[freq1];
						for(int j = 0; j < freq1; j++) {
							times[j] = startHr+freq2*j;
							// create schedule item
							daily[j] = new ScheduleItem();
							daily[j].setSortTime(startTimeInMin+freq2*j*60);
							daily[j].setTime(times[j] + ":" + m.getStartTimeMin());
							daily[j].setName(m.getName());
						}
						// add schedule items to correct days
						String[] days = getDays(m);
						populateSchedule(days, daily);
					}
				}
				Collections.sort(monday);
				Collections.sort(tuesday);
				Collections.sort(wednesday);
				Collections.sort(thursday);
				Collections.sort(friday);
				Collections.sort(saturday);
				Collections.sort(sunday);

				request.setAttribute("mondayList",monday);
				request.setAttribute("tuesdayList",tuesday);
				request.setAttribute("wednesdayList",wednesday);
				request.setAttribute("thursdayList",thursday);
				request.setAttribute("fridayList",friday);
				request.setAttribute("saturdayList",saturday);
				request.setAttribute("sundayList",sunday);

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

		///////// Methods for creating schedule /////////

		public int getFreq1(Medication m) {
			String s = m.getFreqSelect1();
			if (s.equals("Once")) return 1;
			if (s.equals("Twice")) return 2;
			if (s.equals("3 times")) return 3;
			if (s.equals("4 times")) return 4;
			if (s.equals("6 times")) return 6;
			if (s.equals("8 times")) return 8;
			if (s.equals("12 times")) return 12;
			return 0;
		}
		public int getFreq2(Medication m) {
			if ((m.getFreqSelect2()).equals("")) return 0;
			return Integer.parseInt(m.getFreqSelect2());
		}
		public int getStartTimeInMin(Medication m) {
			// returns start time in minutes to facilitate comparison
			int getHour = Integer.parseInt(m.getStartTimeHour());
			int getMin = Integer.parseInt(m.getStartTimeMin());
			int hour = 0;
			int min = 0;
			if((m.getStartAMPM()).equals("p.m.")) {
				hour = getHour == 12 ? 12 : getHour+12;
			} else {
				hour = getHour == 12 ? 0 : getHour;
			}
			return hour*60+min;
		}
		public int getStartHr(Medication m) {
			int getHour = Integer.parseInt(m.getStartTimeHour());
			int hour = 0;
			if((m.getStartAMPM()).equals("p.m.")) {
				hour = getHour == 12 ? 12 : getHour+12;
			} else {
				hour = getHour == 12 ? 0 : getHour;
			}
			return hour;
		}
		public String[] getDays(Medication m) {
			String raw = m.getDayChecks();
			return raw.split(" ");
		}
		public void populateSchedule(String[] days, ScheduleItem[] times) {
			// adds each time to each day
			for(int i = 0; i < days.length; i++) {
				for(int j = 0; j < times.length; j++) {
					if(days[i].equals("Monday")) {
						monday.add(times[j]);
						return;
					}
					if(days[i].equals("Tuesday")) {
						tuesday.add(times[j]);
						return;
					}
					if(days[i].equals("Wednesday")) {
						wednesday.add(times[j]);
						return;
					}
					if(days[i].equals("Thursday")) {
						thursday.add(times[j]);
						return;
					}
					if(days[i].equals("Friday")) {
						friday.add(times[j]);
						return;
					}
					if(days[i].equals("Saturday")) {
						saturday.add(times[j]);
						return;
					}
					if(days[i].equals("Sunday")) {
						sunday.add(times[j]);
						return;
					}
				}
			}
		}
	}