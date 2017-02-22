package fsss_package;

import java.util.ArrayList;


public class AdjusterGroup 
{
	public String ID;
	public ArrayList<String> macTypes;
	public ArrayList<Integer> macDays;
	public int numOfAdj;
	
	public AdjusterGroup(String name, int num, ArrayList<String> mac, ArrayList<Integer> days)
	{
		ID=name;
		numOfAdj=num;
		macTypes=mac;
		macDays=days;
	}
	
	
	public AdjusterGroup()
	{
	}
	
	
}
