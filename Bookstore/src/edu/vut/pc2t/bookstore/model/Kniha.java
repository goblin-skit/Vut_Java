package edu.vut.pc2t.bookstore.model;

import java.util.Objects;

public class Kniha {
	
	String nazev;
	String autor;
	int rokVydani;
	boolean jeDostupny = false;
	
	public String getNazev() {
		return nazev;
	}
	public String getNazevAsUpperCase() {
		return nazev.toUpperCase();
	}
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getRokVydani() {
		return rokVydani;
	}
	public void setRokVydani(int rokVydani) {
		this.rokVydani = rokVydani;
	}
	public boolean isJeDostupny() {
		return jeDostupny;
	}
	public void setJeDostupny(boolean jeDostupny) {
		this.jeDostupny = jeDostupny;
	}
	@Override
	public int hashCode() {
		return Objects.hash(autor, nazev);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kniha other = (Kniha) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(nazev, other.nazev) && Objects.equals(rokVydani, other.rokVydani);
	}
	
	public String printDruhKnihy() {
		return null;
	}
	
	public String getZaner() {
		return "";
	}
	
	public int getVhodnyRocnik() {
		return -1;
	}

	public String printKniha() {
		return null;
	}
	
	public String printIsJeDostupny() {
		if (isJeDostupny() == true) return "Ano";
		else return "Ne";
	}
	
	
	
}
