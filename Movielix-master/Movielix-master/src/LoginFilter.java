

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		
     // Check if the URL is allowed to be accessed without log in
        if (this.isUrlAllowedWithoutLogin(httpRequest.getRequestURI())) {
            // Keep default action: pass along the filter chain
            chain.doFilter(request, response);
            return;
        } // Redirect to login page if the "user" attribute doesn't exist in session
        if (httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.sendRedirect("login.html");
        } else {
            // If the user exists in current session, redirects the user to the corresponding URL
            chain.doFilter(request, response);
        }
        
        
		chain.doFilter(request, response);
	}




	
	
	private boolean isUrlAllowedWithoutLogin(String requestURI) {
		return true;
//        requestURI = requestURI.toLowerCase();
//
//        return requestURI.endsWith("login.html") || requestURI.endsWith("login.js")
//                || requestURI.endsWith("api/login") || requestURI.endsWith("employeelogin.html");
    }
	
	

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void destroy() {
	}
}
