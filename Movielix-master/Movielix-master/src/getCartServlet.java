

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet(name = "getCartServlet", urlPatterns = "/api/getcart")
public class getCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
		PrintWriter out = response.getWriter();
		JsonArray moviesInCart = new JsonArray();
		
		if(cart != null) {
//			System.out.println("GET CART ISNT NULL");
//			System.out.println(cart.size());
			for(int i = 0; i < cart.size(); i++) {
				System.out.println(cart.get(i));
				moviesInCart.add(cart.get(i));
			}
		}
		
        out.write(moviesInCart.toString());
        response.setStatus(200);
        out.close();
		
	}

	

}
