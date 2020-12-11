/**
 * 
 */
package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.model.calendar.Bookable;
import itapp.turbolaundry.model.calendar.CalendarFactory;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;


/**
 * @author Sergey
 *
 */
public class BookWindow extends CommonAction implements SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CalendarFactory cFactory;
	private Long rwin;	// url parameter
	private Long rday;	// url parameter
		
	@SuppressWarnings("rawtypes")
	private Map session;	
	private boolean book; // url parameter
	
	@SuppressWarnings("unchecked")
	public String execute(){
		Long userId = (Long) session.get("userId");		
		String message ="";				
		boolean isSuper = (boolean) session.get("isSuper");
		if(userId == null || userId < 0){
			addActionError("Can't access the calendar.");
			return ERROR;
		}
		Bookable lDay = (Bookable) cFactory.getDay(userId,false,isSuper);	
		if(book == true){
			message = lDay.reserveWindow(rwin);						
		}else{
			if(lDay.cancelWindow(rwin) == false){
				message = "Error. Cant cancel the window.";
			}
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
	
	public boolean isBook() {
		return book;
	}

	public void setBook(boolean book) {
		this.book = book;
	}

	public Long getRday() {
		return rday;
	}

	public void setRday(Long rday) {
		this.rday = rday;
	}
	
}
