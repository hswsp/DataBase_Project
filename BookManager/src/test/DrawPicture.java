package test;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class DrawPicture {
	public static void main(String args[]){  
	    JFrame frame=new JFrame("Java数据统计图");  
	    frame.setLayout(new GridLayout(2,2,10,10)); //创建具有指定行数、列数以及组件水平、纵向一定间距的网格布局。 
	    Dimension PieDi=new Dimension(50,80);
	   // frame.add(new PieChart(PieDi).getChartPanel());           //添加饼状图  
	   // frame.add(new TimeSeriesChart().getChartPanel());    //添加折线图  
	    frame.setBounds(50, 50,1000, 1600);  
	    frame.setVisible(true);  
	}  
}
