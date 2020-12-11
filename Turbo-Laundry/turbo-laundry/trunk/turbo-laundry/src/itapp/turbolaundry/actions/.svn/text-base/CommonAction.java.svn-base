/**
 * 
 */
package itapp.turbolaundry.actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sergey
 *
 */

public class CommonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String redirectUrl;
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	
	public void setRedirectUrl(String url){
		this.redirectUrl = url;
	}
	
	public String redirect(String to){
		redirectUrl = to;
		return "redirect";
	}

}
