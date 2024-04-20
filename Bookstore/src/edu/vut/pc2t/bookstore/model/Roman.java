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
		printString = (printDruhKnihy()+": "+getNazev()+"; "+getAutor()+"; "+getRokVydani()+"; "+getZaner()+"; "+isJeDostupny());
		
		return printString;
	}
	
}

