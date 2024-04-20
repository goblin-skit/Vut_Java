package edu.vut.pc2t.bookstore.model;

public class Ucebnice extends Kniha {
	
	int vhodnyRocnik;

	@Override
	public int getVhodnyRocnik() {
		return vhodnyRocnik;
	}

	public void setVhodnyRocnik(int vhodnyRocnik) {
		this.vhodnyRocnik = vhodnyRocnik;
	}
	
	@Override
	public String printDruhKnihy() {
		return "Ucebnice";
	}
	
public String printKniha() {
		
		String printString;
		printString = (printDruhKnihy()+": "+getNazev()+"; "+getAutor()+"; "+getRokVydani()+"; "+getVhodnyRocnik()+"; "+isJeDostupny());
		
		return printString;
	}
	
}
