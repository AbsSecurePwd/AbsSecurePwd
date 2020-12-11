/**
 * 
 */
package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.entities.calendar.Day;
import itapp.turbolaundry.entities.calendar.Window;
import itapp.turbolaundry.model.calendar.CalendarFactory;
import itapp.turbolaundry.model.calendar.DateUtility;
import itapp.turbolaundry.model.user.User;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

public class GenerateReport extends CommonAction implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	private CalendarFactory cFactory;		
	private List<Day> calendar;
	@SuppressWarnings("rawtypes")
	private Map session;
	private User userInfo;
	private String fromstr;
	private String tostr;
	

	public String execute(){
		
		
		
		Date from = convertStringToDate(fromstr);
		Date to = convertStringToDate(tostr);
			
		Collections.sort(this.calendar);
		//Date from = this.calendar.get(0).getDate();
		//Date to = this.calendar.get(this.calendar.size() - 1).getDate();
		
		Long userId = (Long) session.get("userId");	
		this.cFactory.getDay(userId, false, false);
		String outputFilePath = "";
	
		HttpServletResponse response = ServletActionContext.getResponse(); 
		
		try {
			//outputFilePath = this.renderReportUser(userId, from, to);
			ServletOutputStream out = response.getOutputStream();
			byte[] pdfByte = this.renderReportUser(userId, from, to);
			byte[] outputByte = new byte[4096];
			response.setContentType("application/pdf");
			response.setHeader("Content-DIsposition", "attachment; filename=\"report.pdf\"");
			InputStream in = 
					new ByteArrayInputStream(pdfByte);
			while(in.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096); 
			}
			
			in.close();
			out.flush();
			out.close();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "SUCCESS";
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	public byte[] renderReportUser(long userId, final Date from, final Date to) 
			throws DocumentException, IOException {
		TreeMap<Date, Window> booked = this.prepareReportUser(userId, from, to);
		LUser user = this.userInfo.getUserById(userId);
		
	    String outputFile = String.format("report-%s.pdf", user.getLogin().trim());
	    //OutputStream os = new FileOutputStream(outputFile);
	    ByteArrayOutputStream os = new ByteArrayOutputStream(4096);
	    String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title></title><style type=\"text/css\">%s</style></head><body>%s</body></html>";
	    StringBuilder sb = new StringBuilder();
	    StringBuilder styles = new StringBuilder();
	    DateTime tempFrom = new DateTime(from);
	    DateTime tempTo = new DateTime(to);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd");
	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy. MM. dd.");
	    
	    sb.append("<span id=\"dateSpan\">");
	    sb.append(String.format("%s %s", user.getLastName().trim(), user.getFirstName().trim()));
	    sb.append("<br />");
	    sb.append(String.format("%s - %s", sdf2.format(from), sdf2.format(to)));
	    sb.append("<br /><br />");
	    sb.append("</span>");
	    sb.append("<br />");
	    sb.append("b - booked");
	    sb.append("<br />");
	    
	    sb.append("<table border=\"1\">");
	    sb.append("<tr>");
	    sb.append("<th>Day</th>");
	    sb.append("<th colspan=\"24\" align=\"center\">Windows</th>");
	    sb.append("</tr><tr>");
	    sb.append("<th>&nsbp;</th>");
	    for (int i=1; i<=24; i++) {
	    	if (i < 10) {
	    		sb.append("<th>0" + i + "</th>");
	    	} else {
	    		sb.append("<th>" + i + "</th>");
	    	}
	    }
	    
	    sb.append("</tr>");

	    styles.append("table  { border-collapse:collapse; } table, td, th { border:1px solid black; }");
	    styles.append("#dateSpan { font-size:20px; font-weight:bold; }");
	    if (tempFrom.isBefore(tempTo)) {
	    	Iterator<Entry<Date, Window>> it = booked.entrySet().iterator();
	        while (it.hasNext()) {
	            @SuppressWarnings("rawtypes")
				Map.Entry pairs = (Map.Entry)it.next();
	            
	            sb.append("<tr>");
	    		sb.append("<td>");
	    		sb.append(sdf.format(pairs.getKey()));
	    		sb.append("</td>");
	    		Window w = (Window) pairs.getValue();
	    		for (int i = 0; i<24; i++ ) {
	    			
	    			if (w.getWindowNumber() == i) {
	    				sb.append("<td bgcolor=\"yellow\">");
	    				sb.append("b");
	    			} else {
	    				sb.append("<td>");
	    				sb.append("&nbsp;");
	    			}
	    			
	    			sb.append("</td>");
	    		}
	    		
	    		sb.append("</tr>");
	        }
	    }
	    
	    sb.append("</table>");
	        
	    content = String.format(content, styles, sb.toString());
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(content);
        renderer.layout();
        renderer.createPDF(os);
	        
        byte[] pdfResult = os.toByteArray();
        os.close();
        
        //return new File(outputFile).toURI().toURL().toString();
        return pdfResult;
		
	}
	
	private TreeMap<Date, Window> prepareReportUser(long userId, Date from, Date to) {
		TreeMap<Date, Window> booked = new TreeMap<Date, Window>();
		LUser user = this.userInfo.getUserById(userId);
		List<Window> windows = new ArrayList<Window>();
		windows.addAll(user.getWindows());
		for (Window window : windows) {
			Date currentDate = window.getDay().getDate();
			if ((currentDate.after(from) && currentDate.before(to))
					|| DateUtility.compareDates(currentDate, from)
					|| DateUtility.compareDates(currentDate, to)) {
				booked.put(currentDate, window);
			}
		}
		
		return booked;
	}
	
	public static Date convertStringToDate(String day){		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yyyy");
		try{
			DateTime dt = formatter.parseDateTime(day);
			Date date = dt.toLocalDate().toDate();
			return date;
		} catch(UnsupportedOperationException e){
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (IllegalFieldValueException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		}
	}

	public String getFromstr() {
		return fromstr;
	}

	public void setFromstr(String from) {
		this.fromstr = from;
	}

	public String getTostr() {
		return tostr;
	}

	public void setTostr(String to) {
		this.tostr = to;
	}
}
