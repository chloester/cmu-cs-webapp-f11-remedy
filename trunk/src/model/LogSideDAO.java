package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.SideEffectLog;

public class LogSideDAO implements logsideInterface {
	private BeanFactory<SideEffectLog> factory;
	//construtor for initialization.
	public LogSideDAO() throws DAOException{
		try{
			BeanTable<SideEffectLog> MedTable = BeanTable.getInstance(SideEffectLog.class, "Logsideeffect_table");
			if(!MedTable.exists()) MedTable.create("sideId");
			MedTable.setIdleConnectionCleanup(true);
			factory = MedTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	public void create(SideEffectLog side) throws DAOException {
		try{
			Transaction.begin();
			SideEffectLog dbMed = factory.create(side.getSideId());
			factory.copyInto(side,dbMed);
			Transaction.commit();
		}catch(DuplicateKeyException e){
			throw new DAOException("A log medication belongs to " + side.getOwner() + "has already existed.");
		}catch(RollbackException e){
			throw new DAOException(e);
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	public SideEffectLog[] getLogSideList(String UserName){
		try{
			SideEffectLog[] sides = factory.match(MatchArg.equals("owner",UserName));
			return sides;
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
	public int getLogSideNum(String UserName) throws DAOException{
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
		    SideEffectLog[] sortedArray = factory.match();
		    /*for ( MyBookbean check : sortedArray){
		    	System.out.println("The sorted Array is sorted by count as " + check.getCount());
		    }*/
		    if(sortedArray != null){
		    	int len = sortedArray.length;
		    	Large = sortedArray[0].getSideId();
		    	for(int i = 0; i < len ; i++){
		    		int temp = sortedArray[i].getSideId();
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

