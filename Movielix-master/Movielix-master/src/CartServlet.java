

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet(name = "CartServlet", urlPatterns = "/api/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String item = request.getParameter("quantity");
        String id = request.getParameter("id");
        System.out.println(id);
		HttpSession session = request.getSession();
		ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<>();    
		}
		
		//if not already in cart, add
		if(cart.contains(id) == false) {
			cart.add(id);
            session.setAttribute("cart", cart);
		}
		
		System.out.println(cart.size());
        for(int i = 0; i < cart.size(); i++) {
        	System.out.println(cart.get(i));
        }
        
	}


}
