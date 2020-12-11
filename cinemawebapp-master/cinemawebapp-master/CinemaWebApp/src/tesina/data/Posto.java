package tesina.data;

public class Posto {
	
	private final int codice;
	private String fila;
	private int numero;
	private boolean occupato;
	
	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public Posto(final int codice, final String fila, final int numero) {
		this.codice = codice;
		this.fila = fila;
		this.numero = numero;
		this.occupato = false;
	}
	
	public Posto(final int codice, final String fila, final int numero, final boolean o) {
		this.codice = codice;
		this.fila = fila;
		this.numero = numero;
		this.occupato = o;
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCodice() {
		return codice;
	}

}
