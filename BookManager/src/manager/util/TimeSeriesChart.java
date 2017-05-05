package manager.util;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {
	ChartPanel frame1;
	public TimeSeriesChart(Dimension v,int []BookMonthly,int CurrentYear){
		XYDataset xydataset = createDataset(BookMonthly,CurrentYear);
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("��������", "�·�", "����",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();  // ��ȡ��ͼ������
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		DecimalFormat df = new DecimalFormat("#0");//����
//		NumberAxis numberaxis = (NumberAxis)plot.getRangeAxis();
//		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); //�ؼ��������
		//y������
		((NumberAxis) ((XYPlot)jfreechart.getPlot()).getRangeAxis()).setNumberFormatOverride(df);
		
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM"));//�����ʽ-yyyy
        
        frame1=new ChartPanel(jfreechart,true);
        frame1.setPreferredSize(new Dimension(v));
        
        dateaxis.setLabelFont(new Font("����",Font.BOLD,30));         //ˮƽ�ײ�����
        dateaxis.setTickLabelFont(new Font("����",Font.BOLD,30));  //��ֱ����
       // axis0.setLabelFont(new Font("����", Font.PLAIN, 12));// y������
        ValueAxis rangeAxis=xyplot.getRangeAxis();//��ȡ��״
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,35));
        jfreechart.getLegend().setItemFont(new Font("����", Font.BOLD, 30));
        //�趨y������ʾ��Χ
	   // NumberAxis domainAxis = (NumberAxis)xyplot.getDomainAxis();
        //�����0��ʼ
        //((NumberAxis)rangeAxis).setAutoRangeIncludesZero(true); 
        rangeAxis.setLowerBound(0);
        //�����ֵ
         int max = BookMonthly[0];//�������
    		for (int x=1; x<BookMonthly.length; x++ )
    		{
    			if (BookMonthly[x]>max)
    			{
    				max = BookMonthly[x];
    			}
    		}   	
        rangeAxis.setUpperBound(max+1);
     // ����y�᲻��ʹ���Զ��̶�
        ((NumberAxis)rangeAxis).setAutoTickUnitSelection(false);
        // ���ÿ̶�
        NumberTickUnit numberTickUnit = new NumberTickUnit(1);
        ((NumberAxis)rangeAxis).setTickUnit(numberTickUnit);
        jfreechart.getTitle().setFont(new Font("����",Font.BOLD,35));//���ñ�������

	} 
	 private static XYDataset createDataset(int []BookMonthly,int CurrentYear) 
	 {  //������ݼ��е�࣬������������
	        TimeSeries timeseries = new TimeSeries(Integer.toString(CurrentYear)+"���½�������",
	                org.jfree.data.time.Month.class);//ʱ���ᾫȷ����
	        timeseries.add(new Month(1,CurrentYear), BookMonthly[1]);
	        timeseries.add(new Month(2,CurrentYear), BookMonthly[2]);
	        timeseries.add(new Month(3,CurrentYear), BookMonthly[3]);
	        timeseries.add(new Month(4,CurrentYear), BookMonthly[4]);
	        timeseries.add(new Month(5,CurrentYear), BookMonthly[5]);
	        timeseries.add(new Month(6,CurrentYear), BookMonthly[6]);
	        timeseries.add(new Month(7,CurrentYear), BookMonthly[7]);
	        timeseries.add(new Month(8,CurrentYear), BookMonthly[8]);
	        timeseries.add(new Month(9,CurrentYear), BookMonthly[9]);
	        timeseries.add(new Month(10,CurrentYear), BookMonthly[10]);
	        timeseries.add(new Month(11,CurrentYear), BookMonthly[11]);
	        timeseries.add(new Month(12,CurrentYear), BookMonthly[12]);
	       
//	        timeseries.add(new Month(2,2001), 181.80000000000001D);
//	        timeseries.add(new Month(3, 2001), 167.30000000000001D);
//	        timeseries.add(new Month(4, 2001), 153.80000000000001D);
//	        timeseries.add(new Month(5, 2001), 167.59999999999999D);
//	        timeseries.add(new Month(6, 2001), 158.80000000000001D);
//	        timeseries.add(new Month(7, 2001), 148.30000000000001D);
//	        timeseries.add(new Month(8, 2001), 153.90000000000001D);
//	        timeseries.add(new Month(9, 2001), 142.69999999999999D);
//	        timeseries.add(new Month(10, 2001), 123.2D);
//	        timeseries.add(new Month(11, 2001), 131.80000000000001D);
//	        timeseries.add(new Month(12, 2001), 139.59999999999999D);
//	        timeseries.add(new Month(1, 2002), 142.90000000000001D);
//	        timeseries.add(new Month(2, 2002), 138.69999999999999D);
//	        timeseries.add(new Month(3, 2002), 137.30000000000001D);
//	        timeseries.add(new Month(4, 2002), 143.90000000000001D);
//	        timeseries.add(new Month(5, 2002), 139.80000000000001D);
//	        timeseries.add(new Month(6, 2002), 137D);
//	        timeseries.add(new Month(7, 2002), 132.80000000000001D);
//	        TimeSeries timeseries1 = new TimeSeries("legal & generalӢ��ָ������",
//	                org.jfree.data.time.Month.class);
//	        timeseries1.add(new Month(2, 2001), 129.59999999999999D);
//	        timeseries1.add(new Month(3, 2001), 123.2D);
//	        timeseries1.add(new Month(4, 2001), 117.2D);
//	        timeseries1.add(new Month(5, 2001), 124.09999999999999D);
//	        timeseries1.add(new Month(6, 2001), 122.59999999999999D);
//	        timeseries1.add(new Month(7, 2001), 119.2D);
//	        timeseries1.add(new Month(8, 2001), 116.5D);
//	        timeseries1.add(new Month(9, 2001), 112.7D);
//	        timeseries1.add(new Month(10, 2001), 101.5D);
//	        timeseries1.add(new Month(11, 2001), 106.09999999999999D);
//	        timeseries1.add(new Month(12, 2001), 110.3D);
//	        timeseries1.add(new Month(1, 2002), 111.7D);
//	        timeseries1.add(new Month(2, 2002), 111D);
//	        timeseries1.add(new Month(3, 2002), 109.59999999999999D);
//	        timeseries1.add(new Month(4, 2002), 113.2D);
//	        timeseries1.add(new Month(5, 2002), 111.59999999999999D);
//	        timeseries1.add(new Month(6, 2002), 108.8D);
//	        timeseries1.add(new Month(7, 2002), 101.59999999999999D);
	        
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        timeseriescollection.addSeries(timeseries);
//	        timeseriescollection.addSeries(timeseries1);
	        return timeseriescollection;
	    }
	  public ChartPanel getChartPanel()
	  {
	    	return frame1;	    	
	    }
}