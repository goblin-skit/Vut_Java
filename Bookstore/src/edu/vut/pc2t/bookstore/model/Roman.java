package edu.vut.pc2t.bookstore.model;

public class Roman extends Kniha {
	
	String zaner;
	
	public void setZaner(String zaner) {
		this.zaner = zaner;
	}
	
	@Override
	public String getZaner() {
		return zaner;
	}
	
	@Override
	public String printDruhKnihy() {
		return "Roman";
	}
	
public String printKniha() {
		
		String printString;
		//printString = (printDruhKnihy()+": "+getNazev()+"; "+getAutor()+"; "+getRokVydani()+"; "+getZaner()+"; "+isJeDostupny());
		printString = (printDruhKnihy()+": \n"+"\tNazev: "+getNazev()+"\n\tAutor: "+getAutor()+"\n\tRok vydani: "+getRokVydani()+"\n\tZanr: "+getZaner()+"\n\tJe dostupny: "+printIsJeDostupny()+"\n");
		
		return printString;
	}
	
}

