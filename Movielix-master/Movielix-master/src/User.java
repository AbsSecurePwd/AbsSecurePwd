import java.util.HashMap;
import java.util.Map;


public class User {

    private final String email;
    private final String id;
    private Map<String, String> cart;
    

    public User(String email, String id) {
        this.email = email;
        this.id = id;
        cart = new HashMap<>();
    }
    
    public User(String email) {
    	this.email=email;
    	this.id="";
    }

    public String getEmail() { return this.email; }
    public String getId() { return this.id; }
    public Map<String, String> getCart(){
    	return cart;
    }
    public void setCart(Map<String, String> newCart) {
    	cart = newCart;
    }
    

}
