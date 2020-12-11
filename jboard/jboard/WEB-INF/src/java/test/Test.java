package test;

import javax.security.auth.login.LoginException;

import net.sf.jboard.security.DBViaJNDILogin;
import net.sf.jboard.struts.reg.actions.LogonAction;
import net.sf.jboard.struts.reg.actions.UserDirectoryException;

public class Test {
	public static void main(String[] args) throws LoginException, UserDirectoryException {

		DBViaJNDILogin obj_1 = new DBViaJNDILogin();
		obj_1.validateUser(null, null);

		LogonAction obj_2 = new LogonAction();
		obj_2.isUserLogon(null, null);

	}
}
