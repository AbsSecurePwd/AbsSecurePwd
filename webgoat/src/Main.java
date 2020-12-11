
public class Main {
	public static void main(String[] args) throws Exception {
		WebSession s = new WebSession(null, null) ;
		String pass = s.getParser().getStringParameter("PASSWORD", "");
	//	StringBuilder pass = new StringBuilder("passwordString");
		String password = pass;
		//password.append("OtherString");
		new TestMain().test2(password);
		testSameClass(password);
	//	new WeakAuthenticationCookie().checkParams(s,password);
		
	}

	private static  String testSameClass(String password) {
	if(Math.random()>.9){
		System.out.println("Inside Test Same Class");
	}
		return password; 
	}
	
}
