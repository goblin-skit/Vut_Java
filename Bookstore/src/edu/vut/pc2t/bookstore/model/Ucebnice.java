package edu.vut.pc2t.bookstore.model;

import java.util.Scanner;

public class Ucebnice extends Kniha {
	
	Scanner sc = new Scanner(System.in);
	int vhodnyRocnik;

public void setRoman() {
		
		System.out.println("Napiste nazov ucebnice: ");
		this.nazev = sc.nextLine();
		System.out.println("Napiste jmeno autora: "); //TODO: Implementovat support vicea autoru ??????
		this.autor = sc.nextLine();
		System.out.println("Napiste rocnik pre ktory je ucebnice vhodna: ");
		this.vhodnyRocnik = sc.nextInt();
		System.out.println("Napiste rok vydani: ");
		this.rokVydani = sc.nextInt();
		this.jeDostupny = true; //TODO: Das tam knihu aj ked ju nemas ???????
		
	}
	
}
