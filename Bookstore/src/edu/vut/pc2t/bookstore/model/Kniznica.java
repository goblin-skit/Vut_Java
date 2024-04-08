package edu.vut.pc2t.bookstore.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Scanner;

import edu.vut.pc2t.bookstore.controller.KeyboardInput;
import edu.vut.pc2t.bookstore.database.Databaze;

public class Kniznica {
	
	private Databaze databaze;

	public Kniznica() {
		super();
		databaze = new Databaze();
	}
	
	public boolean runKniznica() {
		
		Scanner sc = new Scanner(System.in);
		//TODO: na zacitatku musi byt read SQL databaza
		
		boolean runLoop = true;
		
		while(runLoop) {
			
			KeyboardInput.printMenu();
			int input = KeyboardInput.pouzeCelaCisla(sc);
			
			switch(input) {
				case 1: addNewBook(sc); break;
				
				case 2: editBook(sc); break;
			}
			
		}
		
		return false;
	}
	
	public void addNewBook(Scanner sc) { //Pridava knihu do databazy
		
		System.out.println("Vyberte typ knihy: 1-Roman  2-Ucebnice");
		int bookType = KeyboardInput.pouzeCelaCisla(sc);
		
		System.out.println("Zadajte nazev knihy: ");
		String nazev = sc.nextLine();
		System.out.println("Zadajte autora knihy: ");
		String autor = sc.nextLine();
		System.out.println("Zadajte rok vydania knihy: ");
		int rokVydania = sc.nextInt();
		
		if(bookType == 1) {
		// Kniha je Roman
			System.out.println("Zadajte zaner romanu: ");
			String zaner = sc.nextLine();
			
			Roman newRoman = new Roman();
			newRoman.setNazev(nazev);
			newRoman.setAutor(autor);
			newRoman.setRokVydani(rokVydania);
			newRoman.setJeDostupny(true); //Pri pridani je dostupna 
			newRoman.setZaner(zaner);
			databaze.addKniha(newRoman);
			
		}
		else {
		// Kniha je Ucebnice
			System.out.println("Zadajte odporucany rocnik pre ucebnicu: ");
			int odporucenyRocnik = sc.nextInt();
			
			Ucebnice newUcebnice = new Ucebnice();
			newUcebnice.setNazev(nazev);
			newUcebnice.setAutor(autor);
			newUcebnice.setRokVydani(rokVydania);
			newUcebnice.setJeDostupny(true); //Pri pridani je dostupna 
			newUcebnice.setVhodnyRocnik(odporucenyRocnik);
			databaze.addKniha(newUcebnice);
			
		}
		
	}
	
	public void editBook(Scanner sc) { //Edituje knihu podla mena
		
		
		
	}
	
}
