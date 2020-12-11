/**
 * 
 */
package itapp.turbolaundry.model.calendar;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.Floor;
import itapp.turbolaundry.entities.calendar.Day;

/**
 * @author Sergii
 *
 */
public class AdminLCalendar extends LCalendar{
	
		
	public AdminLCalendar(Long userId, DataService dataService) {
		super(userId, dataService);		
	}
	
	public List<Day> showCalendar(Date today, long floorId){
		List<Day> calendar = dataService.getDayListByDateFloor(today, FOURWEEKS, floorId);
		if(null!=calendar){
			int length = calendar.size();
			if(length == 0 ){
				calendar = DateUtility.fakeDays(today,FOURWEEKS);
			}else{
				Collections.sort(calendar);
			}			
			// Adding not active days from past (first week)
			List<Day> tempDays;
			if((tempDays = DateUtility.getStartOfWeek(today)) != null){
				calendar.addAll(0,tempDays);
			}
			// Adding not active days from future (last week)	
			length = calendar.size();
			if((tempDays = DateUtility.getEndOfWeek(calendar.get(length-1).getDate(),length)) != null){
				calendar.addAll(tempDays);			
			}
			return calendar;
		}
		return null;
	}		
	
	
	public List<Floor> getFloors(){
		return dataService.getAllFloors();
	}
}