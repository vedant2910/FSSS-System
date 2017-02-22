package fsss_package;

import java.util.ArrayList;

public class Adjuster 
{
	public ArrayList<String> macType;
	public ArrayList<Integer> repairDays;
	private String id;
	
	public MachineType mc;
	public int reDays;
	public int DaysWorked;
	public int qStatus;
	
	public Adjuster()
	{
		setId("");
		macType = new ArrayList<String>();
		repairDays = new ArrayList<Integer>();
		DaysWorked = 0;
	}
	
	public Adjuster(String ID)
	{
		setId(ID);
		macType = new ArrayList<String>();
		repairDays = new ArrayList<Integer>();
		DaysWorked = 0;
	}
	
	public Adjuster(int reday)
	{
		reDays=reday;
	}
	
	public void AddRepairableMachine(String mac, int repDays)
	{
		macType.add(mac);
		repairDays.add(repDays);
	}
	
	public void AddRepairableMachines(ArrayList<String> mac, ArrayList<Integer> repDays)
	{
		for(int i=0; i<mac.size(); i++)
		{
			AddRepairableMachine(mac.get(i),repDays.get(i));
		}
	}
	
	public String getID()
	{
		return getId();
	}
	
	public void setID(String ID)
	{
		setId(ID);
	}
	
	public void AddDays(int d)
	{
		DaysWorked+=d;
	}
	
	public int getDays()
	{
		return DaysWorked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
