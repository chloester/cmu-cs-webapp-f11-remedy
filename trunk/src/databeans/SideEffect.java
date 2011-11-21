package databeans;

public class SideEffect {
	private int	   sideid;
	private String owner;
	private String name;
	
	public SideEffect(int id) {
		this.sideid = id;
	}
	public String getOwner()	{ return owner;  }
	public String getName()		{ return name;   }
	public int getSideid()   { return sideid; }
	
	public void setOwner(String o)	{ owner = o;   }
	public void setName(String n)	{ name = n;    }
	
}