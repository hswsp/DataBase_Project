package manager.util;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
	ChartPanel frame1;
	
	public PieChart() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 画出指定大小饼状图
	 * @param v
	 * @param TypeBuffer
	 * @param number
	 */
	public PieChart(Dimension v,StringBuffer[] TypeBuffer,int[] number)
	{
		  DefaultPieDataset data = getDataSet(TypeBuffer,number);
	      JFreeChart chart = ChartFactory.createPieChart3D("借阅分类分布",data,true,false,false);
	    //设置百分比
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
	      NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
	      pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
	  
	  //没有数据的时候显示的内容
	      pieplot.setNoDataMessage("无数据显示");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//设置不显示空值
	      pieplot.setIgnoreZeroValues(true);//设置不显示负值
	      frame1=new ChartPanel (chart,true);
	      frame1.setPreferredSize(new Dimension(v));
	      chart.getTitle().setFont(new Font("宋体",Font.BOLD,35));//设置标题字体
	      PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	      piePlot.setLabelFont(new Font("宋体",Font.BOLD,30));//解决乱码
	      chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,35));
	}
	/**
	 * 与外界窗口一样大小图
	 * @param TypeBuffer
	 * @param number
	 */
	public PieChart(StringBuffer[] TypeBuffer,int[] number)
	{
		  DefaultPieDataset data = getDataSet(TypeBuffer,number);
	      JFreeChart chart = ChartFactory.createPieChart3D("借阅分类分布",data,true,false,false);
	    //设置百分比
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
	      NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象
	      pieplot.setLabelGenerator(sp1);//设置饼图显示百分比
	  
	  //没有数据的时候显示的内容
	      pieplot.setNoDataMessage("无数据显示");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//设置不显示空值
	      pieplot.setIgnoreZeroValues(true);//设置不显示负值
	      frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("宋体",Font.BOLD,35));//设置标题字体
	      PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象
	      piePlot.setLabelFont(new Font("宋体",Font.BOLD,30));//解决乱码
	      chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,35));
	}
	/**
	 * 获得数据
	 * @param TypeBuffer
	 * @param number
	 * @return
	 */
    private static DefaultPieDataset getDataSet(StringBuffer[] TypeBuffer,int[] number) 
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(int i=0;i<TypeBuffer.length;++i)
        {
        	if(StringUtil.isEmpty(TypeBuffer[i].toString()))
        	{
        		continue;
        	}
        	else
        	{
        		dataset.setValue(TypeBuffer[i].toString(),number[i]);
        	}
        }
//        dataset.setValue("苹果",100);
//        dataset.setValue("梨子",200);
//        dataset.setValue("葡萄",300);
//        dataset.setValue("香蕉",400);
//        dataset.setValue("荔枝",500);
        return dataset;
  }
    public ChartPanel getChartPanel(){
    	return frame1;    	
    }
}
