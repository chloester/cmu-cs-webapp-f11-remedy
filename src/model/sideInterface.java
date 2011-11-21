package model;

import org.mybeans.dao.DAOException;

import databeans.SideEffect;

public interface sideInterface {
	  /**
     * Add the corresponding side effects to the table. 
     * @param create side effects object into table.
     * @throws DAOException 
     */
    public abstract void create(SideEffect AddSide) throws DAOException;
    /**
	  * Gets the user row from the user table with particular sideEffect ID. 
	  * @return all the row of particular side effects Name.
     *  @throws DAOException 
	  */
    public abstract  SideEffect lookup(int SideId) throws DAOException;    
     /**
      * Gets the rows of side effects from table using particular userName(emailaddress).
      * @return the corresponding list of Medicaitons in the table
      * @throws DAOException 
      */ 
     public SideEffect[] getSideEffectsList(String userName) throws DAOException;
     /**
      * Gets the number of rows of side effects from table using particular userName(emailaddress).
      * @return the number side effects with particular user in the table
      * @throws DAOException 
      */ 
     public int getSideNum(String userName) throws DAOException;
     /**
      * Gets the total size of side effects list. 
      * @return the number of items in the table
      * @throws DAOException 
      */  
     public int size() throws DAOException;
     /**
      * Gets the last id of side effects from side effects list.
      * @return the id of the last items in the table.
      * @throws DAOException 
      */   
     public int getLastId() throws DAOException;
     /*Hi Chloe, you could define other functions you like, and implements them.
      * 
      * */
}
