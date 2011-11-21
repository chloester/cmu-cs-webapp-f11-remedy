package model;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import databeans.SideEffect;

public class SideDAO implements sideInterface {
	private BeanFactory<SideEffect> factory;
	//construtor for initialization.
	public SideDAO() throws DAOException{
		try{
			BeanTable<SideEffect> MedTable = BeanTable.getInstance(SideEffect.class, "SideEffect_table");
			if(!MedTable.exists()) MedTable.create("sideid");
			MedTable.setIdleConnectionCleanup(true);
			factory = MedTable.getFactory();
		}catch(BeanFactoryException e){
			throw new DAOException(e);
		}
	}
	public void create(SideEffect side) throws DAOException {
		try{
			Transaction.begin();
			SideEffect dbSide = factory.create(side.getSideid());
			factory.copyInto(side,dbSide);
			Transaction.commit();
		}catch(DuplicateKeyException e){
			throw new DAOException("A Side effect  named " + side.getName() + "has already existed.");
		}catch(RollbackException e){
			throw new DAOException(e);
		}finally{
			if(Transaction.isActive()) Transaction.rollback();
		}
	}
	public SideEffect lookup(int SideId){
		try{
			return factory.lookup(SideId);
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
	public SideEffect[] getSideEffectsList(String UserName){
		try{
			SideEffect[] meds = factory.match(MatchArg.equals("owner",UserName));
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
	public int getSideNum(String UserName) throws DAOException{
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
		    SideEffect[] sortedArray = factory.match();
		    /*for ( MyBookbean check : sortedArray){
		    	System.out.println("The sorted Array is sorted by count as " + check.getCount());
		    }*/
		    if(sortedArray != null){
		    	int len = sortedArray.length;
		    	Large = sortedArray[0].getSideid();
		    	for(int i = 0; i < len ; i++){
		    		int temp = sortedArray[i].getSideid();
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

