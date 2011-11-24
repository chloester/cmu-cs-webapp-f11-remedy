package model;


import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.Medication;

public class MedDAO implements medInterface {
	private BeanFactory<Medication> factory;
	//construtor for initialization.
	public MedDAO() throws DAOException{
		try{
			BeanTable<Medication> MedTable = BeanTable.getInstance(Medication.class, "medication_table");
			if(!MedTable.exists()) MedTable.create("medid");
			MedTable.setIdleConnectionCleanup(true);
			factory = MedTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	public void create(Medication med) throws DAOException {
		try{
			Transaction.begin();
			Medication dbMed = factory.create(med.getMedid());
			factory.copyInto(med,dbMed);
			Transaction.commit();
		}catch(DuplicateKeyException e){
			throw new DAOException("A medication named " + med.getName() + "has already existed.");
		}catch(RollbackException e){
			throw new DAOException(e);
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	public Medication lookup(String MedName){
		try{
			return factory.lookup(MedName);
		}catch(RollbackException e){
			try {
				throw new DAOException(e);
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	public void Delete(int medid)throws DAOException{
		try{
		    Transaction.begin();
			factory.delete(medid);
		    Transaction.commit();
		}catch (RollbackException e){
			throw new DAOException(e);
		}
	}		
	public Medication[] getMedicationList(String UserName){
		try{
			Medication[] meds = factory.match(MatchArg.equals("username",UserName));
			return meds;
		}catch(RollbackException e){
			try {
				throw new DAOException(e);
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	public int getMedNum(String UserName) throws DAOException{
		try{
			return factory.match(MatchArg.equals("username",UserName)).length;
		}catch(RollbackException e){
			throw new DAOException(e);
		}
	}
	public int size() throws DAOException{
		try{
			return factory.getBeanCount();
		}catch(RollbackException e){
			throw new DAOException(e);
		}
	}
	public int getLastId(){
		try{
			int Large;
			Transaction.begin();
		    //MyBookbean dbbookmark = getFactory().lookup(bookid);
		    Medication[] sortedArray = factory.match();
		    /*for ( MyBookbean check : sortedArray){
		    	System.out.println("The sorted Array is sorted by count as " + check.getCount());
		    }*/
		    if(sortedArray != null){
		    	int len = sortedArray.length;
		    	Large = sortedArray[0].getMedid();
		    	for(int i = 0; i < len ; i++){
		    		int temp = sortedArray[i].getMedid();
		    		if(temp>Large) Large = temp;
		    	}
		    }else{
		    	Large = 0;
		    }
		    	Transaction.commit();
		    	return Large;
			}catch (RollbackException e) {
				try {
					throw new DAOException(e);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return -1;
		}
}

