package tesina.data;

public class Spettacolo {
	
	private String titolo_film;
	private String nome_cinema;
	private String data_e_ora;
	
	private final int codice,
					  codice_sala,
					  numero_sala,
					  durata,
					  tipo_3d;
	public int getCodice_sala() {
		return codice_sala;
	}

	private final float prezzo;
	
	public Spettacolo(final int codice, final int codice_sala, final String cinema, final String titolo, final int durata, final int sala, final String data, final float p, final int tipo) {
		titolo_film = titolo;
		this.nome_cinema = cinema;
		this.durata = durata;
		this.codice = codice;
		this.codice_sala = codice_sala;
		this.numero_sala = sala;
		this.prezzo = p;
		this.tipo_3d = tipo;
	    this.data_e_ora = data;
	}

	public String getData_e_ora() {
		return data_e_ora.substring(0, data_e_ora.length()-3);
	}

	public String getTitolo_film() {
		return titolo_film;
	}

	public void setTitolo_film(String titolo_film) {
		this.titolo_film = titolo_film;
	}

	public String getNome_cinema() {
		return nome_cinema;
	}

	public void setNome_cinema(String nome_cinema) {
		this.nome_cinema = nome_cinema;
	}

	public int getCodice() {
		return codice;
	}

	public int getNumero_sala() {
		return numero_sala;
	}

	public String getTipo_3d() {
		if(tipo_3d==1) return "3D";
		else return "2D";
	}

	public float getPrezzo() {
		return prezzo;
	}

	public int getDurata() {
		return durata;
	}

}
