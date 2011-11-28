package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.MedLog;

public class LogMedDAO implements logmedInterface {
	private BeanFactory<MedLog> factory;
	//construtor for initialization.
	public LogMedDAO() throws DAOException{
		try{
			BeanTable<MedLog> MedTable = BeanTable.getInstance(MedLog.class, "Logmedication_table");
			if(!MedTable.exists()) MedTable.create("medId");
			MedTable.setIdleConnectionCleanup(true);
			factory = MedTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	public void create(MedLog med) throws DAOException {
		try{
			Transaction.begin();
			MedLog dbMed = factory.create(med.getMedId());
			factory.copyInto(med,dbMed);
			Transaction.commit();
		}catch(DuplicateKeyException e){
			throw new DAOException("A log medication belongs to " + med.getOwner() + "has already existed.");
		}catch(RollbackException e){
			throw new DAOException(e);
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	public MedLog[] getLogMedicationList(String UserName){
		try{
			MedLog[] meds = factory.match(MatchArg.equals("owner",UserName));
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
	public MedLog[] getLogMedication(String user, String name){
		try{
			MedLog[] meds = factory.match(MatchArg.and(MatchArg.equals("name",name), MatchArg.equals("owner", user)));
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
	public int getLogMedNum(String UserName) throws DAOException{
		try{
			return factory.match(MatchArg.equals("owner",UserName)).length;
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
		    MedLog[] sortedArray = factory.match();
		    /*for ( MyBookbean check : sortedArray){
		    	System.out.println("The sorted Array is sorted by count as " + check.getCount());
		    }*/
		    if(sortedArray != null){
		    	int len = sortedArray.length;
		    	Large = sortedArray[0].getMedId();
		    	for(int i = 0; i < len ; i++){
		    		int temp = sortedArray[i].getMedId();
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

