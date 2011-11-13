package model;

import org.mybeans.dao.DAOException;

import databeans.User;

public interface userInterface {

    /**
     * Add the user to the list in time sequence
     * @param user text to be added to the user list.
     * @throws DAOException 
     */
    public abstract void create(User emailaddress) throws DAOException;
    /**
	  * Gets the user row from the user table with particular email address  
	  * @return all the row of particular email address.
     *  @throws DAOException 
	  */
    public abstract  User lookup(String Emailaddress) throws DAOException;    
     /**
      * Update the user's information in the user table for particular user 
     * @throws DAOException 
      */    
     public void updataUser(User user) throws DAOException;
     /**
      * Gets the rows of user  from user table with particular Country.
      * @return the list of users in the table
      * @throws DAOException 
      */  
     public User[] getCountry(String countryname) throws DAOException;
     /**
      * Gets the rows of user  from user table with particular State.
      * @return the list of users in the table
      * @throws DAOException 
      */  
     public User[] getState(String statename) throws DAOException;
     /**
      * Gets the rows of user  from user table with particular Gender.
      * @return the list of users in the table
      * @throws DAOException 
      */  
     public User[] getGender(String gendertype) throws DAOException;
     /**
      * Gets the rows of user  from user table with particular FirstName,maybe less used.
      * @return the list of users in the table
      * @throws DAOException 
      */  
     public User[] getFirstNameUser(String firstName) throws DAOException;
     /**
      * Gets the rows of user  from user table with particular LastName,maybe less used.
      * @return the list of users in the table
      * @throws DAOException 
      */   
     public User[] getLastNameUser(String lastName) throws DAOException;
     /**
      * Gets the number of users in the Table.
      * @return the number of users in the table.
     * @throws DAOException 
      */
     public int size() throws DAOException;
     /*Hi Chloe, you could define other functions you like, and implements them.
      * 
      * */
}
