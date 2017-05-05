package manager.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import manager.entity.DateInt;

public class DateUtil {

	//Date��java.sql.Date����  
	public static Date DateAdd(Date OriginDate,int day) {  
		Calendar calendar =new GregorianCalendar();  
		calendar.setTime(OriginDate);  
		calendar.add(calendar.DATE, day);  
		// calendar��timeת��java.util.Date��ʽ����  
		java.util.Date utilDate = (java.util.Date)calendar.getTime();  
//		calendar.add(calendar.DATE, 6);  
//		utilDate = (java.util.Date)calendar.getTime();  
		//java.util.Date����ת����ת��java.sql.Date��ʽ  
		Date newDate =new Date(utilDate.getTime());  
		return newDate;
		}  
	/**
	 * ��Ǯ�����¼�
	 * @param OriginDate
	 * @param ReturnDate
	 * @return
	 */
	public static float balanceaccount(Date DueDate,Date ReturnDate)
	{
		  int balanceRedu;//���ٵ�Ǯ��
		  Calendar cal1 = Calendar.getInstance();
	      cal1.setTime(DueDate);
	      Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(ReturnDate);
	       int day1= cal1.get(Calendar.DAY_OF_YEAR);
	       int day2 = cal2.get(Calendar.DAY_OF_YEAR);	      
	       int year1 = cal1.get(Calendar.YEAR);
	       int year2 = cal2.get(Calendar.YEAR);
	        if(year1 != year2)   //����ͬһ��
	        {
	            int timeDistance = 0 ;
	            for(int i = year1 ; i < year2 ; i ++)
	            {
	                if(i%4==0 && i%100!=0 || i%400==0)    //����            
	                {
	                    timeDistance += 366;
	                }
	                else    //��������
	                {
	                    timeDistance += 365;
	                }
	            }
	            
	            balanceRedu=timeDistance + (day2-day1) ;
	        }
	        else    //��ͬ��
	        {
	        	balanceRedu=day2-day1;
	        }
	        if(balanceRedu<=0)
	        	return 0;//�涨���޻�����ҪǮ
	        else
		return (float) (balanceRedu*0.1);//����һ��1ëǮ
	}
	
	/**
	 * ��date������ȡ�þ����꣬�£���,���ڻ�����ͼ
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
		//�õ��� 
		int month=calendar.get(Calendar.MONTH);   
		//�õ��£����ǣ��·�Ҫ����1   
		month=month+1; 
		int day=calendar.get(Calendar.DATE);   
		//�������   
		date.setDay(day);
		date.setMonth(month);
		date.setYear(year);
	}
}
