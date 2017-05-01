package manager.util;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

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
	
}
