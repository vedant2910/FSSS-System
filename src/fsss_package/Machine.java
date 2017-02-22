package fsss_package;

public class Machine extends MachineType
{
	public int runningDays;
	public int qStatus;
	
	public Machine(String name, int mttf)
	{
		Name = name;
		MTTF = mttf;
		runningDays=0;
	}
	
	public Machine()
	{
		Name="";
		MTTF=0;
		runningDays=0;
	}
	
	public void setData(String name, int mttf)
	{
		Name = name;
		MTTF = mttf;
		runningDays=0;
	}
	
	public void setRunningDays(int days)
	{
		runningDays=days;
	}
	
	public void resetRunningDays()
	{
		runningDays=0;
	}
	
	public void addRunningDays(int d)
	{
		runningDays+=d;
	}
	
	public int getRunningDays()
	{
		return runningDays;
	}
}
