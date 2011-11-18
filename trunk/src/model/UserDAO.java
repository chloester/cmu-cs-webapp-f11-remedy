package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.User;

public class UserDAO implements userInterface{
	private BeanFactory<User> factory;

	public UserDAO() throws DAOException {
		try {
			// Get a BeanTable.
			BeanTable<User> userTable = BeanTable.getInstance(User.class,"remedy_user");
			if (!userTable.exists()) userTable.create("emailaddress");    
			// Long running web application need to clean up idle database connections.
			// So we can tell each BeanTable to clean them up.  (You would only notice
			// a problem after leaving your web app running for several hours.)
			userTable.setIdleConnectionCleanup(true);
			// Get a BeanFactory which the actions will use to read and write rows of the "user" table
			factory = userTable.getFactory();
		} catch (BeanFactoryException e) {
			throw new DAOException(e);
		}
	}

	public void create(User user) throws DAOException {
		try {
			Transaction.begin();
			User dbUser = factory.create(user.getEmailAddress());
			factory.copyInto(user,dbUser);
			Transaction.commit();
		} catch (DuplicateKeyException e) {
			throw new DAOException("A user named "+user.getEmailAddress()+" already exists");
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

	public User lookup(String EmailAddress) throws DAOException{
		try {
			return factory.lookup(EmailAddress);
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	protected BeanFactory<User> getFactory(){ 
		return factory; 
	}

	public void updateUser(User user) throws DAOException {
		try {
			Transaction.begin();
			User dbUser = factory.lookup(user.getEmailAddress());

			if (dbUser == null){
				throw new DAOException("User "+user.getEmailAddress()+" no longer exists");
			}
			dbUser.setFirstName(user.getFirstName());   	
			dbUser.setLastName(user.getLastName());
			dbUser.setGender(user.getGender());
			Transaction.commit();
		} catch (RollbackException e) {
			throw new DAOException(e);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}	

	public User[] getGender(String gendertype) throws DAOException {
		try {
			User[] users = factory.match(MatchArg.equals("gender",gendertype));
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public User[] getFirstNameUser(String firstName) throws DAOException {
		try {
			User[] users = factory.match(MatchArg.equals("firstName",firstName));
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public User[] getLastNameUser(String lastName) throws DAOException {
		try {
			User[] users = factory.match(MatchArg.equals("lastName",lastName));
			return users;
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public int size() throws DAOException {
		try {
			return factory.getBeanCount();
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}


}
