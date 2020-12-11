/**
 * 
 */
package itapp.turbolaundry.model.calendar;



import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.calendar.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Sergii
 *
 */
public class SuperLDay extends LDay implements Bookable {

	public SuperLDay(Long userId, DataService dataService) {
		super(userId, dataService);		
	}

	// Methods
	public List<Window> showDay(){
		Set<Window> windowsS = this.dDay.getWindows();
		List<Window> windows = new ArrayList<Window>();		
		if(null != windowsS){	
			windows.addAll(windowsS);
			Collections.sort(windows);
			// Disable past windows for current day
			if(dDay.isCurrent() == true){
				int winCurrent = DateUtility.getCurrentWindow();
				for(Window win : windows){
					if(win.getWindowNumber() <= winCurrent){
						win.setActive(false);
					}else{
						break;
					}
				}
				// Disable windows yesterday
			} else if(dDay.isYesterday() == true){
				for(Window win : windows){
					win.setActive(false);
				}
			}			
			return windows;
		}
		return null;		
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
					result = "Can't book the time slot. Database error.";
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
		long user = dataService.getWindowUserId(index);
		dataService.chargeAccount(user, 2.0f);
		return dataService.cancelWindow(index);
	}
	
	public boolean lockWindow(long index){
		
		return dataService.lockWindow(index);		
	}
	
	public boolean unlockWindow(long index){
		
		return dataService.unlockWindow(index);		
	}
	
}
