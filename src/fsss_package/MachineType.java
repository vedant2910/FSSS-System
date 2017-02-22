/**
 * 
 */

package fsss_package;

/**
 * @author ARPIT KUMAR
 *
 */
public class MachineType 
{
	protected String Name;
	protected int MTTF;
	
	public MachineType(String name, int mttf)
	{
		Name = name;
		MTTF = mttf;
	}
	
	public MachineType()
	{
		Name="";
		MTTF=0;
	}
	
	public String getName()
	{
		return Name; 
	}
	
	public void setName(String name)
	{
		Name = name;
	}
	
	public int getMTTF()
	{
		return MTTF;
	}
	
	public void setMTTF(int mttf)
	{
		MTTF = mttf;
	}
}