package fsss_package;

public class MachineGroup 
{
	public Machine[] mach;
	public MachineGroup(String name, int mttf, int number)
	{
		mach = new Machine[number];
		for(int i=0; i<mach.length; i++)
		{
			mach[i] = new Machine(name,mttf);
		}
	}
	
	
}
