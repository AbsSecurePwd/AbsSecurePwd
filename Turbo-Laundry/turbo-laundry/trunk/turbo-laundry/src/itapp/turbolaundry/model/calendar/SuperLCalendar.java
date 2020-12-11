/**
 * 
 */
package itapp.turbolaundry.model.calendar;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.calendar.Day;

/**
 * @author Sergii
 *
 */
public class SuperLCalendar extends LCalendar{
	
	public SuperLCalendar(Long userId, DataService dataService) {
		super(userId, dataService);		
	}

	// Methods
	
	@Override
	public List<Day> showCalendar(Date today){
		Date yesterday = DateUtility.getYesterday(today);
		List<Day> calendar = dataService.getDayListByDate(yesterday, FOURWEEKS+1, userId);
		if(null!=calendar){
			Collections.sort(calendar);
			// Adding not active days from past (first week)
			List<Day> tempDays;
			if((tempDays = DateUtility.getStartOfWeek(yesterday)) != null){
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
		
	
	public boolean lockDay(long index){
		return dataService.lockAllByDateId(index);
	}
	
	
	public boolean unlockDay(long index){
		return dataService.unlockAllByDateId(index);
	}
	
	
	public boolean cancelDay(long index){
		return dataService.cancelAllByDateId(index);
	}
}
