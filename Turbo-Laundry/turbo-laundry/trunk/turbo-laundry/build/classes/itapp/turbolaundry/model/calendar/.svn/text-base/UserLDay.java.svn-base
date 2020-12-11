/**
 * 
 */
package itapp.turbolaundry.model.calendar;


import java.util.Date;

import itapp.turbolaundry.dataservices.DataService;


/**
 * @author Sergii
 *
 */
public class UserLDay extends LDay implements Bookable{
	
	public UserLDay(Long userId, DataService dataService) {
		super(userId, dataService);
	}
	
	public String reserveWindow(long index){
		String result = "";
		Date date = dataService.getWindowDate(index);
		Date monday = DateUtility.getMonday(date);
		Date sunday = DateUtility.getSunday(date);
		
		int winNumber = dataService.getNumberOfBookedWindows(monday, sunday, userId);
		
		if(winNumber < AWAILABLE_WINDOWS){
			Float money = dataService.getAccount(userId);
			if(money!=null && money >= 2.0f){
				dataService.chargeAccount(userId, -2.0f);
				if(dataService.bookWindow(index, userId) == false){
					result = "Can't book the window. Database error.";
				}
			}else{
				result = "Can't book the time slot. Not enough money.";
			}
		}else{
			result = "Can't book the time slot. No more time slots for this week.";
		}
		return result;
	}
	
	public boolean cancelWindow(long index){
		dataService.chargeAccount(userId, 2.0f);
		return dataService.cancelWindow(index);		
	}

}
