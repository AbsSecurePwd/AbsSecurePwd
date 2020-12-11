package itapp.turbolaundry.actions;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport {
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

// all struts logic here
 public String execute() {
  
  ServletActionContext.getRequest().getSession().invalidate();
  addActionMessage("You are successfully logout!");
  return "logout";

 }
}