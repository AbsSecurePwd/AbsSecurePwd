package tesina.interceptors;

import java.util.Map;

import tesina.data.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckLoginInterceptor implements Interceptor {
	private static final long serialVersionUID = -7364932114840883321L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User userData = (User)session.get("userData");
		
		if(userData==null) {
			return "login";
		} else {
			return arg0.invoke();
		}
	}

}
