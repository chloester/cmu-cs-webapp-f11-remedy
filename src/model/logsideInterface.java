package model;

import org.mybeans.dao.DAOException;

import databeans.SideEffectLog;

public interface logsideInterface {
	 /**
     * Add the log side effects into database in time sequence
     * @param Log side effects object to be added to the user list.
     * @throws DAOException 
     */
    public abstract void create(SideEffectLog AddSideMed) throws DAOException;
     /**
      * Gets the rows of Log side effect from table using particular userName(emailaddress).
      * @return the corresponding list of side effect in the table
      * @throws DAOException 
      */ 
     public SideEffectLog[] getLogSideList(String userName) throws DAOException;
     /**
      * Gets the number of rows of side effects from table using particular userName(emailaddress).
      * @return the number of side effects with particular user in the table
      * @throws DAOException 
      */ 
     public int getLogSideNum(String userName) throws DAOException;/**
      * Gets the total size of side effects list. 
      * @return the number of items in the table
      * @throws DAOException 
      */  
     public int size() throws DAOException;
     /**
      * Gets the last id of side effects from table.
      * @return the id of the last items in the table.
      * @throws DAOException 
      */   
     public int getLastId() throws DAOException;
     /*Hi Chloe, you could define other functions you like, and implements them.
      * 
      * */
}
