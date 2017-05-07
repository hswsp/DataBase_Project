package manager.util;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarChart {
	  ChartPanel frame1; 
	  
	  public BarChart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public  BarChart(StringBuffer[] TypeBuffer,int[] number)
	  {  
	  CategoryDataset dataset = getDataSet(TypeBuffer,number);  
	  JFreeChart chart = ChartFactory.createBarChart3D( "����ͼ�����ͳ��", // ͼ������  
			                    "ͼ������", // Ŀ¼�����ʾ��ǩ  
	                            "����", // ��ֵ�����ʾ��ǩ  
	                            dataset, // ���ݼ�  
	                            PlotOrientation.VERTICAL, // ͼ������ˮƽ����ֱ  
	                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)  
	                            false,          // �Ƿ����ɹ���  
	                            false           // �Ƿ�����URL����  
	                            );  
	          
	     //�����￪ʼ  
	     CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������  
	     CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�  
	     domainAxis.setLabelFont(new Font("����",Font.BOLD,30));         //ˮƽ�ײ�����  
	     domainAxis.setTickLabelFont(new Font("����",Font.BOLD,30));  //��ֱ����  
	     
	     ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
	     //���ÿ̶�
	     NumberTickUnit numberTickUnit = new NumberTickUnit(1);
	     ((NumberAxis)rangeAxis).setTickUnit(numberTickUnit);
	     //������״ͼ��������
	     BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer(); //��õ�ǰ����
	     renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); //��ʾÿ��������ֵ  
	     
	     renderer.setBaseItemLabelsVisible(true);  
	     renderer.setBaseItemLabelPaint(Color.BLACK);  
	     renderer.setBaseItemLabelFont(new Font("����",Font.PLAIN,30));  
	   //ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ���������� 
	     renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition
	    		 (ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_CENTER ));//��ʾ��ʾ���Ϸ����м�
	     //ItemLabelAnchor.OUTSIDE3(��ʾ�ڴ�ֱ�����м䣩, TextAnchor.BASELINE_RIGHT����ʾ�������ұߣ�
	     renderer.setItemLabelAnchorOffset(10D);// ��������ͼ�ϵ�����ƫ��ֵ 
	     rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
	     chart.getLegend().setItemFont(new Font("����", Font.BOLD, 30));  
	     chart.getTitle().setFont(new Font("����",Font.BOLD,30));//���ñ�������  	            
	     //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������              
	      frame1=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame             
	    } 
	    
	    /**
	     * ��ȡ����
	     * @return
	     */
       private static CategoryDataset getDataSet(StringBuffer[] TypeBuffer,int[] number) 
       {  
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
           for(int i=0;i<TypeBuffer.length;++i)
			{
        	   if(StringUtil.isEmpty(TypeBuffer[i].toString()))
				{
					continue;
				}
        	   else
        	   {
        		   dataset.addValue(number[i],TypeBuffer[i].toString(),TypeBuffer[i].toString());
        	   }
			}
//           dataset.addValue(100, "����", "ƻ��");  
//           dataset.addValue(100, "�Ϻ�", "ƻ��");  
//           dataset.addValue(100, "����", "ƻ��");  
//           dataset.addValue(200, "����", "����");  
//           dataset.addValue(200, "�Ϻ�", "����");  
//           dataset.addValue(200, "����", "����");  
//           dataset.addValue(300, "����", "����");  
//           dataset.addValue(300, "�Ϻ�", "����");  
//           dataset.addValue(300, "����", "����");  
//           dataset.addValue(400, "����", "�㽶");  
//           dataset.addValue(400, "�Ϻ�", "�㽶");  
//           dataset.addValue(400, "����", "�㽶");  
//           dataset.addValue(500, "����", "��֦");  
//           dataset.addValue(500, "�Ϻ�", "��֦");  
//           dataset.addValue(500, "����", "��֦");  
           return dataset;  
}  
	public ChartPanel getChartPanel()
	{  
	    return frame1;  
	    
	}  
}