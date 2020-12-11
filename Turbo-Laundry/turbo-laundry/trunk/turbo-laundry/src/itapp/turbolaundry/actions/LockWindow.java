/**
 * 
 */
package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.model.calendar.CalendarFactory;
import itapp.turbolaundry.model.calendar.SuperLDay;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


/**
 * @author Sergey
 *
 */
public class LockWindow extends CommonAction implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CalendarFactory cFactory;
	private boolean lock;	// url parameter
	private Long rwin;	// url parameter
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
		SuperLDay lDay = (SuperLDay) cFactory.getDay(userId,false,isSuper);	
		boolean result;
		if(lock == true){
			result = lDay.lockWindow(rwin);
		} else {
			result = lDay.unlockWindow(rwin);
		}					
		if(result != true){
			message = "Error. Can't lock/unlock.";		
		}		
		session.put("message",message);
		return redirect(String.format("ADay?rday=%d",rday));
	}
	
	public void setcFactory(CalendarFactory cFactory) {
		this.cFactory = cFactory;
	}

	public Long getRwin() {
		return rwin;
	}

	public void setRwin(Long rDay) {
		this.rwin = rDay;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}	
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	
	public Long getRday() {
		return rday;
	}

	public void setRday(Long rday) {
		this.rday = rday;
	}

}
