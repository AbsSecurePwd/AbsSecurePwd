import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class pickup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession(true);
       // FileInputStream fis=null;
        String a=request.getParameter("name");
        String b=request.getParameter("address");
        String c=request.getParameter("address1");
        String d=request.getParameter("address2");
        
       String e=request.getParameter("date");
        String f=request.getParameter("time");
        
        try{
             Class.forName("com.mysql.jdbc.Driver");
        
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/login","root","myserver");

           PreparedStatement ps=con.prepareStatement("insert into pickup(name,address,address1,address2,date,time) values(?,?,?,?,?,?)");

    ps.setString(1, a);
    ps.setString(2, b);
    ps.setString(3,c);
    ps.setString(4, d);
    ps.setString(5,e);
    ps.setString(6,f);

   int it=ps.executeUpdate();
        
          if(it>0)
          {
              session.setAttribute("username",a);
              //out.println("done");
RequestDispatcher rd=request.getRequestDispatcher("/confirm.jsp");
                            rd.forward(request, response);
          }
        con.close();
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
    }}
    