/**
 * 
 */
package itapp.turbolaundry.actions;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.entities.Floor;
import itapp.turbolaundry.entities.calendar.Day;
import itapp.turbolaundry.model.calendar.AdminLCalendar;
import itapp.turbolaundry.model.calendar.CalendarFactory;
import itapp.turbolaundry.model.calendar.DateUtility;
import itapp.turbolaundry.model.calendar.LCalendar;

/**
 * @author Sergey
 *
 */
public class ACalendar extends CommonAction implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CalendarFactory cFactory;		
	private List<Day> calendar;
	@SuppressWarnings("rawtypes")
	private Map session;	
	private long floorId = 1;
	private List<Floor> floorList;
	private String message;
	
	@SuppressWarnings("unchecked")
	public String execute(){		
		Long userId = (Long) session.get("userId");
		boolean isAdmin = (boolean) session.get("isAdmin");
		boolean isSuper = (boolean) session.get("isSuper");
		if(userId == null || userId < 0){
			addActionError("User is unknown.");
			return ERROR;
		}
		message = (String) session.get("message");
		session.put("message", "");
		Date today = DateUtility.getCurrentDate();
		if(isAdmin == true){
			AdminLCalendar lCalendar = (AdminLCalendar) cFactory.getCalendar(userId,isAdmin,isSuper);
			calendar = lCalendar.showCalendar(today,floorId);
			floorList = lCalendar.getFloors();
			if(calendar != null){
				return "adminCalendar";
			}else{
				addActionError("Can't access the calendar.");
				return ERROR;
			}
		}
		LCalendar lCalendar = cFactory.getCalendar(userId,isAdmin,isSuper);				
		calendar = lCalendar.showCalendar(today);
		if(calendar != null){
			if(isSuper==true){
				return "superCalendar";
			}else{
				return "userCalendar";
			}
		}
		addActionError("Can't access the calendar.");
		return ERROR;
	}

	public List<Day> getCalendar(){
		return calendar;
	}
	
	public void setcFactory(CalendarFactory cFactory) {
		this.cFactory = cFactory;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}

	public long getFloorId() {
		return floorId;
	}

	public void setFloorId(long floorId) {
		this.floorId = floorId;
	}

	public List<Floor> getFloorList() {
		return floorList;
	}

	public void setFloorList(List<Floor> floorList) {
		this.floorList = floorList;
	}
	
	public String getMessage() {
		return message;
	}	
	
}
