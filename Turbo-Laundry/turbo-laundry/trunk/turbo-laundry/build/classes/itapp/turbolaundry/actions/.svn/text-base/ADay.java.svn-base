/**
 * 
 */
package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.entities.calendar.Window;
import itapp.turbolaundry.model.calendar.CalendarFactory;
import itapp.turbolaundry.model.calendar.LDay;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;


/**
 * @author Sergey
 *
 */
public class ADay extends CommonAction implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CalendarFactory cFactory;
	private Long rday;	// url parameter	
	private List<Window> day;
	@SuppressWarnings("rawtypes")
	private Map session;	
	private Long userId;
	private LDay lDay;	
	private boolean cancelled;
	private String message;

	
	@SuppressWarnings("unchecked")
	public String execute(){
		userId = (Long) session.get("userId");
		boolean isAdmin = (boolean) session.get("isAdmin");
		boolean isSuper = (boolean) session.get("isSuper");
		boolean isBlocked = (boolean) session.get("isBlocked");		
		if(userId == null || userId < 0){
			addActionError("User is unknown.");
			return ERROR;
		}
		message = (String) session.get("message");
		session.put("message", "");
		lDay = cFactory.getDay(userId,isAdmin,isSuper);		
		boolean initRes = lDay.initDay(rday);		
		if(initRes == true){			
			day = lDay.showDay();
			if(day.size() > 0){
				if(isAdmin==true){
					return "adminDay";			
				}else if(isSuper==true){
					return "superDay";
				}else if(isBlocked == true){
					return "blockedUserDay";
				}else{				
					return "userDay";
				}
			}else{
				addActionError("Can't access windows for " + lDay.getdDay().getDate());
				return ERROR;
			}
		} else {
			addActionError("Can't access " + lDay.getdDay().getDate());
			return ERROR;
		}
		
	}
	
	public LDay getLDay() {
		return lDay;
	}

	public void setLDay(LDay lDay) {
		this.lDay = lDay;
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

	public List<Window> getDay() {
		return day;
	}
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public String getMessage() {
		return message;
	}
}
