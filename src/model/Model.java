package model;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanTable;

public class Model {
	//DAO object instances. 
	private UserDAO userDAO;
	
	private String jdbcDriver;
	private String jdbcURL;
	private String jdbcUser;
	private String jdbcPassword;
	private boolean requireSSL;
	/*
	 * In the model package, connect all the DAO method to the database 
	 * with bean factory.
	 */
	public Model(ServletConfig config) throws ServletException {
	
		try {
			String csvDirStr = config.getInitParameter("csvDir");
			if (csvDirStr != null && csvDirStr.length() > 0) {
				File csvDir = new File(csvDirStr);
				BeanTable.useCSVFiles(csvDir);
			} else {
				jdbcDriver = config.getInitParameter("jdbcDriver");
				jdbcURL    = config.getInitParameter("jdbcURL");
				requireSSL     = new Boolean(config.getInitParameter("requireSSL")); 
				jdbcUser     = config.getInitParameter("jdbcUser");
				jdbcPassword = config.getInitParameter("jdbcPassword");
				//connecting to database
				BeanTable.useJDBC(jdbcDriver, jdbcURL, jdbcUser, jdbcPassword);
			}
	    userDAO = new UserDAO();
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	public boolean getRequireSSL(){return requireSSL;}
	//Hi Chloe, you could expand your DAO here.
	public UserDAO getUserDAO() {return userDAO; }
}
