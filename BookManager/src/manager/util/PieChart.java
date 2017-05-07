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
	 * ����ָ����С��״ͼ
	 * @param v
	 * @param TypeBuffer
	 * @param number
	 */
	public PieChart(Dimension v,StringBuffer[] TypeBuffer,int[] number)
	{
		  DefaultPieDataset data = getDataSet(TypeBuffer,number);
	      JFreeChart chart = ChartFactory.createPieChart3D("���ķ���ֲ�",data,true,false,false);
	    //���ðٷֱ�
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
	      NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����
	      pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�
	  
	  //û�����ݵ�ʱ����ʾ������
	      pieplot.setNoDataMessage("��������ʾ");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
	      pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
	      frame1=new ChartPanel (chart,true);
	      frame1.setPreferredSize(new Dimension(v));
	      chart.getTitle().setFont(new Font("����",Font.BOLD,35));//���ñ�������
	      PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
	      piePlot.setLabelFont(new Font("����",Font.BOLD,30));//�������
	      chart.getLegend().setItemFont(new Font("����",Font.BOLD,35));
	}
	/**
	 * ����細��һ����Сͼ
	 * @param TypeBuffer
	 * @param number
	 */
	public PieChart(StringBuffer[] TypeBuffer,int[] number)
	{
		  DefaultPieDataset data = getDataSet(TypeBuffer,number);
	      JFreeChart chart = ChartFactory.createPieChart3D("���ķ���ֲ�",data,true,false,false);
	    //���ðٷֱ�
	      PiePlot pieplot = (PiePlot) chart.getPlot();
	      DecimalFormat df = new DecimalFormat("0.00%");//���һ��DecimalFormat������Ҫ������С������
	      NumberFormat nf = NumberFormat.getNumberInstance();//���һ��NumberFormat����
	      StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//���StandardPieSectionLabelGenerator����
	      pieplot.setLabelGenerator(sp1);//���ñ�ͼ��ʾ�ٷֱ�
	  
	  //û�����ݵ�ʱ����ʾ������
	      pieplot.setNoDataMessage("��������ʾ");
	      pieplot.setCircular(false);
	      pieplot.setLabelGap(0.02D);
	  
	      pieplot.setIgnoreNullValues(true);//���ò���ʾ��ֵ
	      pieplot.setIgnoreZeroValues(true);//���ò���ʾ��ֵ
	      frame1=new ChartPanel (chart,true);
	      chart.getTitle().setFont(new Font("����",Font.BOLD,35));//���ñ�������
	      PiePlot piePlot= (PiePlot) chart.getPlot();//��ȡͼ���������
	      piePlot.setLabelFont(new Font("����",Font.BOLD,30));//�������
	      chart.getLegend().setItemFont(new Font("����",Font.BOLD,35));
	}
	/**
	 * �������
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
//        dataset.setValue("ƻ��",100);
//        dataset.setValue("����",200);
//        dataset.setValue("����",300);
//        dataset.setValue("�㽶",400);
//        dataset.setValue("��֦",500);
        return dataset;
  }
    public ChartPanel getChartPanel(){
    	return frame1;    	
    }
}
