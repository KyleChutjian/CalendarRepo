package calendar;
import java.util.*;

/**
 * This class contains useful methods for implementing the calendar, such as date comparators
 * for String formatted dates and tools to check the bounds on dates.
 *
 */
public class CalendarUtils {
	
	/**
	 * Accepts a single String, and verifies that it is a valid date of the form
	 * mm/dd/yyyy
	 *
	 * @param date The string to be verified
	 * @return True if the date is valid, otherwise false.
	 */
	public static boolean validDate(String date) {
		// For collecting the parsed date
		int month;
		int day;
		int year;
		// For parsing the date
		StringTokenizer temp = new StringTokenizer(date, "/", false);
		
		try {
			month = Integer.parseInt(temp.nextToken());
			day = Integer.parseInt(temp.nextToken());
			year = Integer.parseInt(temp.nextToken());
		} catch(Exception e) {
			return false;
		}
		
		if(month < 0 || month > 12 || day < 1) return false;
		if(day > 31) return false;
		if((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
			return false;
		if(!(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
				if(month == 2 && day > 28)
					return false;
		} else {
			if(month == 2 && day > 29)
				return false;
		}
		if(year < 0 || year > 3000)
			return false;
		
		return true;
	}
	
	/**
	 * Accepts a time, and determines whether it is valid.
	 *
	 * @param time The time to verify
	 * @return True if the time is a valid, military time, false otherwise.
	 */
	public static boolean validTime(String time) {
		// For collecting the parsed time
		int hours;
		int minutes;
		// For parsing the time
		StringTokenizer temp = new StringTokenizer(time, ":", false);
		
		try {
			hours = Integer.parseInt(temp.nextToken());
			minutes = Integer.parseInt(temp.nextToken());
		} catch(Exception e) {
			return false;
		}
		
		if(hours < 0 || hours > 24 || minutes < 0 || minutes > 60) 
			return false;	
		return true;
	}
	
	/**
	 * Accepts two dates, and determines whether the first date precedes the second.
	 * 
	 * @param date1 The first date, formatted mm/dd/yyyy
	 * @param date2 The current date, formatted mm/dd/yyyy
	 * @return True if the first date precedes the second, false otherwise.
	 */
	public static boolean precedingDate(String date1, String date2) {
		// For collecting the parts of the parsed dates.
		int month1;
		int month2;
		int day1;
		int day2;
		int year1;
		int year2;
		// For parsing the two dates.
		StringTokenizer temp = new StringTokenizer(date1, "/", false);
		
		month1 = Integer.parseInt(temp.nextToken());
		day1 = Integer.parseInt(temp.nextToken());
		year1 = Integer.parseInt(temp.nextToken());
		temp = new StringTokenizer(date2, "/", false);
		month2 = Integer.parseInt(temp.nextToken());
		day2 = Integer.parseInt(temp.nextToken());
		year2 = Integer.parseInt(temp.nextToken());
		
		if(year1 < year2) return true;
		if(year1 == year2 && month1 < month2) return true;
		if(year1 == year2 && month1 == month2 && day1 < day2) return true;
		return false;	
	}
	
	/**
	 * Accepts two times, and determines whether the first time precedes the second.
	 * 
	 * @param time1 The first date, formatted hh:mm, in 24 hour format
	 * @param time2 The first date, formatted hh:mm, in 24 hour format
	 * @return True if the first date precedes the second, false otherwise.
	 */
	public static boolean precedingTime(String time1, String time2) {
		// For collecting the parts of the parsed dates.
		int hour1;
		int hour2;
		int minute1;
		int minute2;
		// For parsing the two dates.
		StringTokenizer temp = new StringTokenizer(time1, ":", false);
		
		hour1 = Integer.parseInt(temp.nextToken());
	    minute1 = Integer.parseInt(temp.nextToken());
		temp = new StringTokenizer(time2, ":", false);
		hour2 = Integer.parseInt(temp.nextToken());
		minute2 = Integer.parseInt(temp.nextToken());
		
		if(hour1 < hour2) return true;
		if(hour1 == hour2 && minute1 < minute2) return true;
		return false;	
	}
	
	/**
	 * Compares to events, and returns true if the first event precedes the second.
	 *
	 * @param event1 The first of the two events to compare.
	 * @param event2 The second of the two events to compare.
	 * @return True if the first event precedes the second, otherwise false
	 */
	public static boolean precedingEvent(ScheduleEvent event1, ScheduleEvent event2) {
		if(CalendarUtils.precedingDate(event1.getDate(), event2.getDate()))
				return true;
		//TODO: if they are on the same date check times
		if(CalendarUtils.equalDates(event1.getDate(), event2.getDate())) {
			if(CalendarUtils.precedingTime(event1.getStartTime(), event2.getStartTime()))
				return true;
		}
		return false;
	} 
	
	
	/**
	 * Checks to see if two dates are equal, regardless of single digit format (i.e. 01 = 1)
	 *
	 * @param date1 The first date to check
	 * @param date2 The second date to check
	 * @return True if the two dates are the same, false otherwise.
	 */
	public static boolean equalDates(String date1, String date2) {
		// For collecting the parts of the parsed dates.
		int month1;
		int month2;
		int day1;
		int day2;
		int year1;
		int year2;
		// For parsing the two dates.
		StringTokenizer temp = new StringTokenizer(date1, "/", false);
		
		month1 = Integer.parseInt(temp.nextToken());
		day1 = Integer.parseInt(temp.nextToken());
		year1 = Integer.parseInt(temp.nextToken());
		temp = new StringTokenizer(date2, "/", false);
		month2 = Integer.parseInt(temp.nextToken());
		day2 = Integer.parseInt(temp.nextToken());
		year2 = Integer.parseInt(temp.nextToken());
		
		if(year1 == year2 && month1 == month2 && day1 == day2) return true;
		return false;	
	}
	
	/**
	 * Checks to see if two times are equal, regardless of single digit format (i.e. 01 = 1)
	 *
	 * @param date1 The first time to check
	 * @param date2 The second time to check
	 * @return True if the two times are the same, false otherwise.
	 */
	public static boolean equalTimes(String time1, String time2) {
		// For collecting the parts of the parsed times.
		int minute1;
		int minute2;
		int hour1;
		int hour2;
		// For parsing the two times.
		StringTokenizer temp = new StringTokenizer(time1, ":", false);
		
		hour1 = Integer.parseInt(temp.nextToken());
		minute1 = Integer.parseInt(temp.nextToken());
		temp = new StringTokenizer(time2, ":", false);
		hour2 = Integer.parseInt(temp.nextToken());
		minute2 = Integer.parseInt(temp.nextToken());
		
		if(minute1 == minute2 && hour1 == hour2) return true;
		return false;	
	}
	
	/**
	 * Checks to see if two events start at the same time, on the same day.
	 *
	 * @param date1 The first event to check
	 * @param date2 The second event to check
	 * @return True if the two event start at the same time, false otherwise.
	 */
	public static boolean concurrentEvents(ScheduleEvent event1, ScheduleEvent event2) {
		if(equalDates(event1.getDate(), event2.getDate()) &&
		   equalTimes(event1.getStartTime(), event2.getStartTime())) 
		   	return true;
		return false;	
	}
	
	/**
	 * Compares the date to the current date
	 *
	 * @param date The date to compare to the current date
	 * @return Returns true if the given date precedes the current date, false otherwise
	 */
	public static boolean precedesCurDate(String date) {
		// The current date
		GregorianCalendar cal = new GregorianCalendar();
		// Create a String formatted date so we can use existing methods for comparison
		String curDate = (cal.get(Calendar.MONTH) + 1) + "/" + 
						 cal.get(Calendar.DAY_OF_MONTH) + "/" +
						 cal.get(Calendar.YEAR);
	    if(precedingDate(date, curDate))
	    	return true;
	    return false;
	}
	
	/**
	 * Compares the time to the current date
	 *
	 * @param date The time to compare to the current time
	 * @return Returns true if the given time precedes the current time, false otherwise
	 */
	public static boolean precedesCurTime(String time) {
		// The current date (includes time)
		GregorianCalendar cal = new GregorianCalendar();
		// Create a String formatted time so we can use existing methods for comparison
		String curTime = cal.get(Calendar.HOUR_OF_DAY) + ":" + 
						 cal.get(Calendar.MINUTE);
	    return precedingTime(time, curTime);
	}
	
	/**
	 * Compares the date to the current date
	 *
	 * @param date The date to compare to the current date
	 * @return Returns true if the given date equals the current date, false otherwise
	 */
	public static boolean equalsCurDate(String date) {
		// The current date
		GregorianCalendar cal = new GregorianCalendar();
		// Create a String formatted date so we can use existing methods for comparison
		String curDate = (cal.get(Calendar.MONTH) + 1) + "/" + 
						 cal.get(Calendar.DAY_OF_MONTH) + "/" +
						 cal.get(Calendar.YEAR);
	    return equalDates(date, curDate);
	}
}
