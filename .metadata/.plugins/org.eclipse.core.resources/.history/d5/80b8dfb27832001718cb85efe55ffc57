package manager.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import manager.entity.DateInt;

public class DateUtil {

	//Date是java.sql.Date类型  
	public static Date DateAdd(Date OriginDate,int day) {  
		Calendar calendar =new GregorianCalendar();  
		calendar.setTime(OriginDate);  
		calendar.add(calendar.DATE, day);  
		// calendar的time转成java.util.Date格式日期  
		java.util.Date utilDate = (java.util.Date)calendar.getTime();  
//		calendar.add(calendar.DATE, 6);  
//		utilDate = (java.util.Date)calendar.getTime();  
		//java.util.Date日期转换成转成java.sql.Date格式  
		Date newDate =new Date(utilDate.getTime());  
		return newDate;
		}  
	/**
	 * 扣钱计算事件
	 * @param OriginDate
	 * @param ReturnDate
	 * @return
	 */
	public static float balanceaccount(Date DueDate,Date ReturnDate)
	{
		  int balanceRedu;//减少的钱数
		  Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(DueDate);
	      Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(ReturnDate);
	       int day1= cal1.get(Calendar.DAY_OF_YEAR);
	       int day2 = cal2.get(Calendar.DAY_OF_YEAR);	      
	       int year1 = cal1.get(Calendar.YEAR);
	       int year2 = cal2.get(Calendar.YEAR);
	        if(year1 != year2)   //不是同一年
	        {
	            int timeDistance = 0 ;
	            for(int i = year1 ; i < year2 ; i ++)
	            {
	                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
	                {
	                    timeDistance += 366;
	                }
	                else    //不是闰年
	                {
	                    timeDistance += 365;
	                }
	            }
	            
	            balanceRedu=timeDistance + (day2-day1) ;
	        }
	        else    //不同年
	        {
	        	balanceRedu=day2-day1;
	        }
	        if(balanceRedu<=0)
	        	return 0;//规定期限还，不要钱
	        else
		return (float) (balanceRedu*0.1);//超过一天1毛钱
	}
	
	/**
	 * 从date类型中取得具体年，月，日,用于画折线图
	 * @param OriginDate
	 * @param year
	 * @param month
	 * @param day
	 */
	public static void getdate(Date OriginDate,DateInt date)
	{
		Calendar calendar =new GregorianCalendar();  
		calendar.setTime(OriginDate);
		int year=calendar.get(Calendar.YEAR);   
		//得到年 
		int month=calendar.get(Calendar.MONTH);   
		//得到月，但是，月份要加上1   
		month=month+1; 
		int day=calendar.get(Calendar.DATE);   
		//获得日期   
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
	}
}
