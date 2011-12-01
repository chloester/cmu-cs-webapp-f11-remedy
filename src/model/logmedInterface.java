package model;

import org.mybeans.dao.DAOException;

import databeans.MedLog;

public interface logmedInterface {
	  /**
     * Add the correponding log medication into database in time sequence
     * @param Log medication object to be added to the user list.
     * @throws DAOException 
     */
    public abstract void create(MedLog addLogMed) throws DAOException;
     /**
      * Gets the rows of Log medications from table using particular userName(emailaddress).
      * @return the corresponding list of Medicaitons in the table
      * @throws DAOException 
      */ 
     public MedLog[] getLogMedicationList(String userName) throws DAOException;
     /**
      * Gets the number of rows of medications from table using particular userName(emailaddress).
      * @return the number of Medicaitons with particular user in the table
      * @throws DAOException 
      */ 
     public int getLogMedNum(String userName) throws DAOException;/**
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
