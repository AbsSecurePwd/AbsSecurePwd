/**
 * 
 */
package itapp.turbolaundry.model.calendar;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.calendar.Day;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Sergii
 *
 */
public abstract class LCalendar {
	
	protected DataService dataService;
	protected static final int FOURWEEKS = 28;
	protected Long userId;
	
	public LCalendar(Long userId, DataService dataService) {
		this.userId = userId;
		this.dataService = dataService;
	}
	
	// Methods	
	
	public List<Day> showCalendar(Date today){
		List<Day> calendar = dataService.getDayListByDate(today, FOURWEEKS, userId);
		if(null!=calendar){
			Collections.sort(calendar);
			// Adding not active days from past (first week)
			List<Day> tempDays;
			if((tempDays = DateUtility.getStartOfWeek(today)) != null){
				calendar.addAll(0,tempDays);
			}
			// Adding not active days from future (last week)
			int length = calendar.size();
			if((tempDays = DateUtility.getEndOfWeek(calendar.get(length-1).getDate(),length)) != null){
				calendar.addAll(tempDays);
			}			
			return calendar;
		}
		return null;
	}		
	
}