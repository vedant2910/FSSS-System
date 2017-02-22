package fsss_package;

import java.util.ArrayList;

public class NormalSimulation 
{
	private MachineGroup[] macGroup;
	private AdjusterGroup[] adjGroup;	
	private int noDays;
	
	public int[][] result;
	
	public NormalSimulation(MachineGroup[] mac, AdjusterGroup[] adj, int noDay)
	{
		macGroup = mac;
		adjGroup = adj;
		noDays = noDay;
		result = new int[macGroup.length][4];
		Group();
	}
	
	private void Group()
	{
		
		int Dis[][][] = new int[macGroup.length][adjGroup.length][2];
		
		for(int i=0;i<adjGroup.length;i++)
		{
			for(int j=0;j<adjGroup[i].macTypes.size();j++)
			{
				String name = adjGroup[i].macTypes.get(j);
				
				for(int k=0;k<macGroup.length;k++)
				{
					//System.out.println(macGroup[k].mach[0].getName());
					if(macGroup[k].mach[0].getName().equals(name))
					{
						Dis[k][i][0]=adjGroup[i].numOfAdj;
						Dis[k][i][1]=adjGroup[i].macDays.get(j);
					}
				}
			}
		}
		
	
		/**
		 * 
		 * Important: Distribute the adjuster having multiple machines to repair
		 */
		
		int num;
		float arr[] = new float[macGroup.length];
		float sumarr;
		for(int j=0;j<adjGroup.length;j++)
		{
			sumarr=0;
			for(int i=0;i<macGroup.length;i++)
			{
				num=0;
				if(Dis[i][j][0]!=0)
				{
					for(int k=0;k<macGroup.length;k++)
						num+=Dis[k][j][0];
					arr[i]=(float)macGroup[i].mach.length/num;
				}
				else
				{
					num=0;
					arr[i]=0;
				}
				sumarr+=arr[i];
			}
			
			for(int i=0;i<macGroup.length;i++)
			{
				if(Dis[i][j][0]<20)
					Dis[i][j][0]=(int)Math.ceil(Dis[i][j][0]*arr[i]/sumarr);
				else
					Dis[i][j][0]=(int)Math.floor(Dis[i][j][0]*arr[i]/sumarr);
			}
			
		}
		
		
		int sum,t;
		
		for(int i=0;i<macGroup.length;i++)
		{
			sum=0;
			for(int j=0;j<adjGroup.length;j++)
			{
				sum+=Dis[i][j][0];
			}
			Adjuster[] ag = new Adjuster[sum];
			t=0;
			for(int j=0;j<adjGroup.length;j++)
			{
				for(int k=0;k<Dis[i][j][0];k++)
				{
					ag[t] = new Adjuster(Dis[i][j][1]);
					t++;
				}
			}
			
			simulateSingle(macGroup[i].mach,ag,noDays,i);
		}
		
	}
	
	public void simulateSingle(Machine[] mg, Adjuster[] ag, int noDays, int index)
	{
		
		for(int i=0;i<mg.length;i++)
		{
			mg[i].qStatus=0;
		}
		
		for(int i=0;i<ag.length;i++)
		{
			ag[i].qStatus=1;
		}
		
		int var1, var2;
		var1 = var2 = 0;
		for(int i=1;i<noDays;i++)
		{
			for(int j=0;j<mg.length;j++)
			{
				if(mg[j].qStatus==0)
				{
					var1++;
					mg[j].runningDays++;
					if(mg[j].runningDays==mg[j].MTTF)
					{
						mg[j].qStatus=1;
						mg[j].runningDays=0;
						for(int k=0;k<ag.length;k++)
						{
							if(ag[k].qStatus==1)
							{
								ag[k].qStatus=0;
								break;
							}
						}
					}
				}
			}
			
			for(int j=0;j<ag.length;j++)
			{
				if(ag[j].qStatus==0)
				{
					var2++;
					ag[j].DaysWorked++;
					if(ag[j].DaysWorked==ag[j].reDays)
					{
						ag[j].qStatus=1;
						ag[j].DaysWorked=0;
						for(int k=0;k<mg.length;k++)
						{
							if(mg[k].qStatus==1)
							{
								mg[k].qStatus=0;
								break;
							}
						}
					}
				}
			}
			
			
			for(int j=0;j<mg.length;j++)
			{
				if(mg[j].qStatus==1)
				{
					for(int k=0;k<ag.length;k++)
						if(ag[k].qStatus==1)
						{
							ag[k].qStatus=0;
							break;
						}
				}
			}

		}
		
		result[index][0]=var1;
		result[index][1]=mg.length;
		result[index][2]=var2;
		result[index][3]=ag.length;
		
		
		//System.out.println(var1+" "+mg.length+" "+var2+" "+ag.length);
	
	}
	
	public float getAverageAdjusterUtilization()
	{
		float utilization;
		int totalDays, totalAdjuster;
		totalDays=0;
		totalAdjuster=0;
		for(int i=0;i<macGroup.length;i++)
		{
			totalDays+=result[i][2];
			totalAdjuster+=result[i][3];
		}
		utilization = (float)totalDays/(totalAdjuster*noDays);
		
		return utilization;
	}
	
	public float getAverageMachineUtilization()
	{
		float utilization;
		int totalDays, totalMachine;
		totalDays=0;
		totalMachine=0;
		for(int i=0;i<macGroup.length;i++)
		{
			totalDays+=result[i][0];
			totalMachine+=result[i][1];
		}
		utilization = (float)totalDays/(totalMachine*noDays);
		
		return utilization;

	}
	
	public ArrayList<Float> getMachineUtilization()
	{
		ArrayList<Float> res = new ArrayList<Float>();
		for(int i=0;i<macGroup.length;i++)
		{
			float temp = (float)result[i][0]/(result[i][1]*noDays);
			res.add(temp);
		}
		return res;
	}
}
