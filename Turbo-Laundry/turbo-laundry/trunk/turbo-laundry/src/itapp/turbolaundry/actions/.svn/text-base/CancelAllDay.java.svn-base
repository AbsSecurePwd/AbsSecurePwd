/**
 * 
 */
package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.model.calendar.CalendarFactory;
import itapp.turbolaundry.model.calendar.SuperLCalendar;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


/**
 * @author Sergey
 *
 */
public class CancelAllDay extends CommonAction implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CalendarFactory cFactory;
	private Long rday;	// url parameter	
	@SuppressWarnings("rawtypes")
	private Map session;		
	
	@SuppressWarnings("unchecked")
	public String execute(){
		Long userId = (Long) session.get("userId");		
		boolean isSuper = (boolean) session.get("isSuper");
		if(userId == null || userId < 0){
			addActionError("User is unknown.");
			return ERROR;
		}
		if(isSuper == false){
			addActionError("Permision Denied.");
			return ERROR;
		}
		String message = "";	
		SuperLCalendar lCalendar = (SuperLCalendar) cFactory.getCalendar(userId,false,isSuper);		
		if(lCalendar.cancelDay(rday) != true){
			message = "Error. Can't cancel the day";		
		}	
		session.put("message",message);
		return redirect("ACalendar");
	}
	
	public void setcFactory(CalendarFactory cFactory) {
		this.cFactory = cFactory;
	}

	public Long getRday() {
		return rday;
	}

	public void setRday(Long rDay) {
		this.rday = rDay;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}	
	
}
