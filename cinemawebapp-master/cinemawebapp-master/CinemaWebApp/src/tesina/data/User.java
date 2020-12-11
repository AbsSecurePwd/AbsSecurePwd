package tesina.data;

import tesina.utils.MD5Encoder;

public class User {
	
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

	private final String username,
				   password,
				   nome,
				   cognome,
				   email;
	private final int tipo;
	
	public User(int id, String username, String nome, String cognome, String email, int tipo) {
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = null;
		this.tipo = tipo;
	}
	
	public User(String username, String password, String nome, String cognome, String email, int tipo) {
		this.id = -1;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		if(password!=null) {
			MD5Encoder md5 = new MD5Encoder();
			this.password = md5.encodeString(password);
		} else {
			this.password = null;
		}
		this.tipo = tipo;
	}

	public User(String email) {
		this.id=1;
		this.username=email;
		this.password = " ";
		this.nome="Guest";
		this.cognome="Guest";
		this.email=email;
		this.tipo = -1;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getTipo() {
		return tipo;
	}
	
	public int getId() {
		return id;
	}
	
}