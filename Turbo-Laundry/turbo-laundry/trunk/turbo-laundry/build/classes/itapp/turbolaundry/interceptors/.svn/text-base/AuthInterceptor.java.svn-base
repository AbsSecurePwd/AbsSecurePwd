package itapp.turbolaundry.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy(){
		//TODO auto-generated method stub
	}
	
	public void init(){
		//TODO auto-generated method stub
	}
	
	public String intercept(ActionInvocation invocation) throws Exception{
		Map<String,Object> sessionAttributes = invocation.getInvocationContext().getSession();
		if(sessionAttributes==null || sessionAttributes.get("authorized")==null){ //check if session attributes are null, or authorized attribute is null
			return "failure";
		}else{
			if(((String)sessionAttributes.get("authorized")).equals("yes")){
				return invocation.invoke(); //execute another action in the quene
			}else{
				return "failure";
			}
		}
		
	}
	
	
}
