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

	/**
	 * 柱状图构造函数
	 * @param TypeBuffer 图书种类名称
	 * @param number   图书种类对应数目
	 */
	public  BarChart(StringBuffer[] TypeBuffer,int[] number)
	  {  
	  CategoryDataset dataset = getDataSet(TypeBuffer,number);  
	  JFreeChart chart = ChartFactory.createBarChart3D( "各类图书借阅统计", // 图表标题  
			                    "图书种类", // 目录轴的显示标签  
	                            "数量", // 数值轴的显示标签  
	                            dataset, // 数据集  
	                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直  
	                            true,           // 是否显示图例(对于简单的柱状图必须是false)  
	                            false,          // 是否生成工具  
	                            false           // 是否生成URL链接  
	                            );  
	          
	     //从这里开始  
	     CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象  
	     CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
	     domainAxis.setLabelFont(new Font("黑体",Font.BOLD,30));         //水平底部标题  
	     domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,30));  //垂直标题  
	     
	     ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
	     //设置刻度
	     NumberTickUnit numberTickUnit = new NumberTickUnit(1);
	     ((NumberAxis)rangeAxis).setTickUnit(numberTickUnit);
	     //设置柱状图顶部数字
	     BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer(); //获得当前数据
	     renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); //显示每个柱的数值  
	     
	     renderer.setBaseItemLabelsVisible(true);  
	     renderer.setBaseItemLabelPaint(Color.BLACK);  
	     renderer.setBaseItemLabelFont(new Font("宋书",Font.PLAIN,30));  
	   //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 
	     renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition
	    		 (ItemLabelAnchor.OUTSIDE12,TextAnchor.BASELINE_CENTER ));//表示显示在上方，中间
	     //ItemLabelAnchor.OUTSIDE3(显示在垂直方向中间）, TextAnchor.BASELINE_RIGHT（显示在柱子右边）
	     renderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值 
	     rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
	     chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 30));  
	     chart.getTitle().setFont(new Font("宋体",Font.BOLD,30));//设置标题字体  	            
	     //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题              
	      frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame             
	    } 
	    
	    /**
	     * 获取数据
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
//           dataset.addValue(100, "北京", "苹果");  
//           dataset.addValue(100, "上海", "苹果");  
//           dataset.addValue(100, "广州", "苹果");  
//           dataset.addValue(200, "北京", "梨子");  
//           dataset.addValue(200, "上海", "梨子");  
//           dataset.addValue(200, "广州", "梨子");  
//           dataset.addValue(300, "北京", "葡萄");  
//           dataset.addValue(300, "上海", "葡萄");  
//           dataset.addValue(300, "广州", "葡萄");  
//           dataset.addValue(400, "北京", "香蕉");  
//           dataset.addValue(400, "上海", "香蕉");  
//           dataset.addValue(400, "广州", "香蕉");  
//           dataset.addValue(500, "北京", "荔枝");  
//           dataset.addValue(500, "上海", "荔枝");  
//           dataset.addValue(500, "广州", "荔枝");  
           return dataset;  
}  
	public ChartPanel getChartPanel()
	{  
	    return frame1;  
	    
	}  
}
