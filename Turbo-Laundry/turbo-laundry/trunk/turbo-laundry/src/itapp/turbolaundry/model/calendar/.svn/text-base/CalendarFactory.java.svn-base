/**
 * 
 */
package itapp.turbolaundry.model.calendar;

import itapp.turbolaundry.dataservices.DataService;

/**
 * @author Sergii
 *
 */
	
public class CalendarFactory {
	
	protected DataService dataService;	

	public LCalendar getCalendar(Long userId,boolean isAdmin,boolean isSuper){
		LCalendar lCalendar;
		if(isAdmin==true){
			lCalendar = new AdminLCalendar(userId, dataService);			
		}else if(isSuper==true){
			lCalendar = new SuperLCalendar(userId, dataService);
		}else{
			lCalendar = new UserLCalendar(userId, dataService);
		}
		return lCalendar;
	}
	
	public LDay getDay(Long userId,boolean isAdmin,boolean isSuper){
		LDay lDay;
		if(isAdmin==true){
			lDay = new AdminLDay(userId, dataService);			
		}else if(isSuper==true){
			lDay = new SuperLDay(userId, dataService);
		}else{
			lDay = new UserLDay(userId, dataService);
		}
		return lDay;
	}
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
}
