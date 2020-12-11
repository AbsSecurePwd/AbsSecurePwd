package tesina.data;

public class Biglietto {
	
	private String titolo,
				   cinema,
				   data,
				   fila;
	private int numero_posto, sala;
	private String numero_biglietto;
	private String email;
	
	public Biglietto(String titolo, final String cinema, final String data, final String fila, 
			final int numero_posto, final String numero_biglietto, final int sala) {
		this.titolo = titolo;
		this.cinema = cinema;
		this.data = data;
		this.fila = fila;
		this.numero_posto = numero_posto;
		this.numero_biglietto = numero_biglietto;
		this.setSala(sala);
	}
	
	public Biglietto() {
		// TODO Auto-generated constructor stub
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getCinema() {
		return cinema;
	}
	public void setCinema(String cinema) {
		this.cinema = cinema;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPosto() {
		return fila+" / "+numero_posto;
	}
	public String getNumero_biglietto() {
		return numero_biglietto;
	}
	public void setNumero_biglietto(String numero_biglietto) {
		this.numero_biglietto = numero_biglietto;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public void setNumero_posto(int numero_posto) {
		this.numero_posto = numero_posto;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getEmail() {
		return email;
	}
}
