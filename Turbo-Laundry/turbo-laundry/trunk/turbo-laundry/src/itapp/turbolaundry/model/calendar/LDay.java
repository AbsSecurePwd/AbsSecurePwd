/**
 * 
 */
package itapp.turbolaundry.model.calendar;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.calendar.Day;
import itapp.turbolaundry.entities.calendar.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Sergii
 *
 */
public abstract class LDay {
	
	protected static final int AWAILABLE_WINDOWS = 3;
	// Variables
	protected DataService dataService;
	protected Day dDay;
	protected Long userId;
	
	public LDay(Long userId, DataService dataService){
		this.userId = userId;
		this.dataService = dataService;
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
			}	
			
			return windows;
		}
		return null;
	}
	
	// Geting Day entity from database and assigning to dDate
	public boolean initDay(Long rday){		
		if(rday != null && rday >= 0){
		dDay = dataService.getDayByID(rday);		
			if(dDay != null && dDay.isLocked() == false){				
				return true;				
			}
		}
		return false;
	}
	
	// getters setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Day getdDay() {
		return dDay;
	}
	
	public void setdDay(Day dDate) {
		this.dDay = dDate;
	}	
	
	
}
