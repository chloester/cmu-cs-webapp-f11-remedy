package model;

import org.mybeans.dao.DAOException;

import databeans.Medication;

public interface medInterface {
	  /**
     * Add the user to the list in time sequence
     * @param user text to be added to the user list.
     * @throws DAOException 
     */
    public abstract void create(Medication AddMed) throws DAOException;
    /**
	  * Gets the user row from the user table with particular Medication Name. 
	  * @return all the row of particular Medication Name.
     *  @throws DAOException 
	  */
    public abstract  Medication lookup(String MedName) throws DAOException;    
     /**
      * Gets the rows of medications from table using particular userName(emailaddress).
      * @return the corresponding list of Medications in the table
      * @throws DAOException 
      */ 
     public Medication[] getMedicationList(String userName) throws DAOException;
     /**
      * Gets the number of rows of medications from table using particular userName(emailaddress).
      * @return the number of Medications with particular user in the table
      * @throws DAOException 
      */ 
     public int getMedNum(String userName) throws DAOException;/**
      * Gets the total size of medication list. 
      * @return the number of items in the table
      * @throws DAOException 
      */  
     public int size() throws DAOException;
     /**
      * Gets the last id of medication from medication.
      * @return the id of the last items in the table.
      * @throws DAOException 
      */   
     public int getLastId() throws DAOException;
     /*Hi Chloe, you could define other functions you like, and implements them.
      * 
      * */
}
