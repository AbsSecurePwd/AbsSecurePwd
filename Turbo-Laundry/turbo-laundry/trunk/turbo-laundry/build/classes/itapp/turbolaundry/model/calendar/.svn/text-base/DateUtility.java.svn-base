/**
 * 
 */
package itapp.turbolaundry.model.calendar;

import itapp.turbolaundry.entities.calendar.Day;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Sergey
 *
 */
public final class DateUtility {
	
	// size of the calendar
	public static final int CSIZE = 35;
	
	//Returns Monday of the today week
	public static Date getMonday(Date today){
		LocalDate localDate = new DateTime(today).toLocalDate();
		int delta = localDate.getDayOfWeek();
		localDate = localDate.minusDays(delta-1);
		return localDate.toDate();
	}
	
	//Returns Sunday of the today week
	public static Date getSunday(Date today){
		LocalDate localDate = new DateTime(today).toLocalDate();
		int delta = localDate.getDayOfWeek();
		localDate = localDate.plusDays(7 - delta);
		return localDate.toDate();		
	}
	
	// Converts int number to time representation HH:00
	public static String numberToTime(int n){
		if(n>=0){
			LocalTime localTime = new  LocalTime(n,0);
			DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
			return localTime.toString(formatter);
		}
		return "";
	}
	
	// Converts String to Date
	public static Date stringToDate(String day){		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
		try{
			DateTime dt = formatter.parseDateTime(day);
			Date date = dt.toLocalDate().toDate();
			return date;
		} catch(UnsupportedOperationException e){
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (IllegalFieldValueException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		}
	}
	
	// Returns current window number
	public static int getCurrentWindow(){
		Date date = new Date();
		LocalTime localTime = new DateTime(date).toLocalTime();
		return localTime.getHourOfDay();
	}
	

	// Returns current date without the time
	public static Date getCurrentDate(){
		Date date = new Date();
		// Dropping the time part
		LocalDate localDate = new DateTime(date).toLocalDate();
		return localDate.toDate();
	}
	
	// returns yesterday date
	public static Date getYesterday(Date date){
		LocalDate localDate = new DateTime(date).toLocalDate();
		localDate = localDate.minusDays(1);
		return localDate.toDate();
	}
	
	// Compares two dates without the time
	public static boolean compareDates(Date a, Date b){
		if(a != null && b != null){
			LocalDate aDate = new DateTime(a).toLocalDate();
			LocalDate bDate = new DateTime(b).toLocalDate();
			if(aDate.compareTo(bDate) == 0){
				return true;
			}			
		}
		return false;
	}
	
	// Compares two dates without the time
		public static int compareTo(Date a, Date b){			
				LocalDate aDate = new DateTime(a).toLocalDate();
				LocalDate bDate = new DateTime(b).toLocalDate();
				return aDate.compareTo(bDate);				
		}
	
	public static List<Day> getStartOfWeek(Date date){
		if(date != null){
			LocalDate ldate = new DateTime(date).toLocalDate();
			int i = ldate.getDayOfWeek() - 1;
			if(i>0){
				List<Day> days = new ArrayList<Day>();
				while(i>0){
					days.add(new Day(ldate.minusDays(i).toDate(),false,false,null));
					i--;
				}
				return days;
			}			
		}
		return null;
	}
	
	public static List<Day> getEndOfWeek(Date date, int n){
		if(date != null && n != 0){
			LocalDate ldate = new DateTime(date).toLocalDate();
			int i = CSIZE - n;
			if(i>0){
				List<Day> days = new LinkedList<Day>();
				while(i>0){
					days.add(0,new Day(ldate.plusDays(i).toDate(),false,false,null));
					i--;
				}
				return days;
			}	
		}
		return null;		
	}
	
	public static List<Day> fakeDays(Date date, int n){
		if(date != null && n != 0){
			LocalDate ldate = new DateTime(date).toLocalDate();			
				List<Day> days = new LinkedList<Day>();
				int i = 0;
				while(i<n){
					days.add(new Day(ldate.plusDays(i).toDate(),false,false,null));
					i++;
				}
				return days;			
		}
		return null;		
	}
}
