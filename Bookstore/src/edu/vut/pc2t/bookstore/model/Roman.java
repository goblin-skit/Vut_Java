package edu.vut.pc2t.bookstore.model;

import java.util.Scanner;

public class Roman extends Kniha {
	
	String zaner;

	Scanner sc = new Scanner(System.in);
	
	
	public void setRoman() {
		
		System.out.println("Napiste nazov knihy: ");
		this.nazev = sc.nextLine();
		System.out.println("Napiste jmeno autora: "); //TODO: Implementovat support vicea autoru ??????
		this.autor = sc.nextLine();
		System.out.println("Napiste zaner knihy: ");
		this.zaner = sc.nextLine();
		System.out.println("Napiste rok vydani: ");
		this.rokVydani = sc.nextInt();
		this.jeDostupny = true; //TODO: Das tam knihu aj ked ju nemas ???????
		
	}
	
	
	public void setZaner(String zaner) {
		this.zaner = zaner;
	}
	@Override
	public String getZaner() {
		return zaner;
	}
	
	
}

