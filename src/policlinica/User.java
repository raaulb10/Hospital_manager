package policlinica;

public class User {
	
	// Fiecare fereastra este manipulata de userul conectat in acel moment
	// Pentru a facilita intoregarile sql, folosim aceasta clasa in care salvam
	// Acele date legate de utilizatorul conectat
	
	private String nume;
	private String prenume;
	private String email;
	private String parola;
	private String CNP;
	private String oras;
	private String telefon;
	private String IBAN;
	private String tip;
	private String functie;
	private int salariu;
	
	public User(String nume, String prenume, String email, String parola, String CNP, String oras,
			String telefon, String IBAN, String tip, String functie, int salariu) {
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.parola = parola;
		this.CNP = CNP;
		this.oras = oras;
		this.telefon = telefon;
		this.IBAN = IBAN;
		this.salariu = salariu;
		this.tip = tip;
		this.functie = functie;
	}

	public String getNume() {
		return nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public String getEmail() {
		return email;
	}

	public String getParola() {
		return parola;
	}

	public String getCNP() {
		return CNP;
	}

	public String getOras() {
		return oras;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getIBAN() {
		return IBAN;
	}

	public String getTip() {
		return tip;
	}

	public String getFunctie() {
		return functie;
	}

	public int getSalariu() {
		return salariu;
	}
	

}
