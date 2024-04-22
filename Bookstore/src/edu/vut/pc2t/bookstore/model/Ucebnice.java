package edu.vut.pc2t.bookstore.model;

import java.util.Scanner;

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
		printString = (printDruhKnihy()+": \n"+"\tNazev: "+getNazev()+"\n\tAutor: "+getAutor()+"\\n\\tRok vydani: "+getRokVydani()+"\\n\\tVhodny rocnik: "+getVhodnyRocnik()+"\\n\\tJe dostupny: "+printIsJeDostupny());
				
		return printString;
	}
	
}
