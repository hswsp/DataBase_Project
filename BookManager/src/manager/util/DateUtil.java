package manager.util;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

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
	
}
