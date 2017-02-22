package fsss_package;

import java.awt.Color;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphicalSimulation 
{
	private MachineGroup[] macGroup;
	private AdjusterGroup[] adjGroup;
	
	private int noDays;
	
	
	public GraphicalSimulation(MachineGroup[] mac, AdjusterGroup[] adj, int noDay)
	{
		macGroup = mac;
		adjGroup = adj;
		noDays = noDay;
	}
	
	public ArrayList<String> getAdjusters()
	{
		ArrayList<String> adj = new ArrayList<String>();
		
		for(int i=0;i<adjGroup.length;i++)
		{
			if(adjGroup[i].macTypes.size()==1)
				adj.add(adjGroup[i].ID);
		}
		
		return adj;
	}
	
	int points[];
	float value[];
	
	public JFreeChart getChart(String AdjID, int MachIndex, int stValue, int endValue)
	{
		//System.out.println("I'm here");
		
		points = new int[25];
		value = new float[25];
		for(int i=0;i<25;i++)
			points[i] = (int)(stValue+i*(float)(endValue-stValue)/25);
		
		for(int i=0;i<adjGroup.length;i++)
		{
			if(adjGroup[i].ID.equals(AdjID))
			{
				ArrayList<Float> util;
				for(int j=0;j<25;j++)
				{
					adjGroup[i].numOfAdj=points[j];
					NormalSimulation temp = new NormalSimulation(macGroup,adjGroup,noDays);
					util = new ArrayList<Float>(temp.getMachineUtilization());
					value[j]=util.get(MachIndex);
				}
				
			}
		}
		
		XYSeries series = new XYSeries("Series 1");
		for(int i=0;i<25;i++)
		{
			//System.out.println(points[i]+" "+value[i]);
			series.add(points[i],value[i]*100);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Machine Utilization vs Number of Adjusters", "No of "+AdjID, macGroup[MachIndex].mach[0].getName()+" utilization(in %)", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		chart.setBackgroundPaint(Color.white);
		
		
		
		final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
		
		
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        final NumberAxis DomAxis = (NumberAxis) plot.getDomainAxis();
        DomAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
		return chart;
	}
	
	public JFreeChart getChartAvgAdj(String AdjID, int stValue, int endValue)
	{
		points = new int[25];
		value = new float[25];
		
		for(int i=0;i<25;i++)
			points[i] = (int)(stValue+i*(float)(endValue-stValue)/25);
		
		for(int i=0;i<adjGroup.length;i++)
		{
			if(adjGroup[i].ID.equals(AdjID))
			{
				for(int j=0;j<25;j++)
				{
					adjGroup[i].numOfAdj=points[j];
					NormalSimulation temp = new NormalSimulation(macGroup,adjGroup,noDays);
					value[j]=temp.getAverageAdjusterUtilization();
				}
				
			}
		}
		
		XYSeries series = new XYSeries("Series 1");
		for(int i=0;i<25;i++)
		{
			//System.out.println(points[i]+" "+value[i]);
			series.add(points[i],value[i]*100);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Adjuster Utilization vs Number of Adjusters", "No of "+AdjID, "Average Adjuster Utilization(in %)", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		chart.setBackgroundPaint(Color.white);
		
		
		
		final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
		
		
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        final NumberAxis DomAxis = (NumberAxis) plot.getDomainAxis();
        DomAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        
		return chart;
	
	}

	public JFreeChart getChartAvgMac(String AdjID, int stValue, int endValue)
	{
		points = new int[25];
		value = new float[25];
		
		for(int i=0;i<25;i++)
			points[i] = (int)(stValue+i*(float)(endValue-stValue)/25);
		
		for(int i=0;i<adjGroup.length;i++)
		{
			if(adjGroup[i].ID.equals(AdjID))
			{
				for(int j=0;j<25;j++)
				{
					adjGroup[i].numOfAdj=points[j];
					NormalSimulation temp = new NormalSimulation(macGroup,adjGroup,noDays);
					value[j]=temp.getAverageMachineUtilization();
				}
				
			}
		}
		
		XYSeries series = new XYSeries("Series 1");
		for(int i=0;i<25;i++)
		{
			//System.out.println(points[i]+" "+value[i]*100);
			series.add(points[i],value[i]*100);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Machine Utilization vs Number of Adjusters", "No of "+AdjID, "Average Machine Utilization(in %)", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		chart.setBackgroundPaint(Color.white);
		
		
		
		final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
		
		
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        final NumberAxis DomAxis = (NumberAxis) plot.getDomainAxis();
        DomAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        
		return chart;
	}

	public int[] getint()
	{
		return points;
	}
	
	public float[] getfloat()
	{
		return value;
	}
	
}
