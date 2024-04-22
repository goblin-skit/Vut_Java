package edu.vut.pc2t.bookstore.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import javax.security.auth.login.AccountLockedException;

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
				
				case 3: deleteBook(sc); break;
				
				case 4: setAvalabilityToBook(sc); break;
				
				case 5: printAllBooks(); break;
				
			}
			
		}
		
		return false;
	}
	
	public void addNewBook(Scanner sc) { //Pridava knihu do databazy
		
		System.out.println("Vyberte typ knihy: 1-Roman  2-Ucebnice");
		int bookType = KeyboardInput.pouzeCelaCisla(sc);
		System.out.println("Zadajte nazev knihy: ");
		String nazev = KeyboardInput.nextLine(sc);
		System.out.println("Zadajte autora knihy: ");
		String autor = KeyboardInput.nextLine(sc);
		System.out.println("Zadajte rok vydania knihy: ");
		int rokVydania = KeyboardInput.pouzeCelaCisla(sc);
		
		if(bookType == 1) {
		// Kniha je Roman
			System.out.println("Zadajte zaner romanu: ");
			String zaner = KeyboardInput.nextLine(sc); //TODO: Ivan treba checkovat nech input je String
			
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
			int odporucenyRocnik = KeyboardInput.pouzeCelaCisla(sc);
			
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
		boolean loop = true;
		
		Kniha currentKniha = searchKnihaFromKeyboardKniha(sc);
		
		System.out.println("Zadajte noveho autora knihy: "); //TODO: Ivan treba checkovat nech input je String
		
		String newAutor = KeyboardInput.nextLine(sc);
		currentKniha.setAutor(newAutor);
		
		System.out.println("Zadajte novy rok vydani knihy: ");
		
		int newRokVydania = KeyboardInput.pouzeCelaCisla(sc);
		currentKniha.setRokVydani(newRokVydania);
		
		
		System.out.println("Zadajte dostupnost knihy: 0 -Nedostupny, 1 -Dostupny ");
		int dostupnost = KeyboardInput.pouzeJednaNeboNula(sc);
		
		if(dostupnost == 1) currentKniha.setJeDostupny(true);
		else currentKniha.setJeDostupny(false);

		databaze.updateKniha(currentKniha);
	}
	
	public void deleteBook (Scanner sc) { // Maze knihu podla mena
		Kniha currentKniha = searchKnihaFromKeyboardKniha(sc);
		System.out.println("Smazat \"" + currentKniha.printKniha() + "\"? (0 - Ne, 1 - Ano ");
		
		int confirm = KeyboardInput.pouzeJednaNeboNula(sc);
		
		if(confirm == 1) databaze.removeKniha(currentKniha);
		else System.out.println("Rusim akci...");
				
	}
	
	public void setAvalabilityToBook (Scanner sc) { 
		Kniha currentKniha = searchKnihaFromKeyboardKniha(sc);
		
		System.out.println("Zadajte dostupnost knihy: 0 -Nedostupny, 1 -Dostupny ");
		int dostupnost = KeyboardInput.pouzeJednaNeboNula(sc);
		
		if(dostupnost == 1) currentKniha.setJeDostupny(true);
		else currentKniha.setJeDostupny(false);
		
		databaze.updateKniha(currentKniha);
		
		Kniha updatedKniha = databaze.getKnihaByName(currentKniha.getNazev());
		System.out.println("Zmenena kniha: "+updatedKniha.printKniha());
		
	}
	
	public Kniha searchKnihaFromKeyboardKniha (Scanner sc) { // Returne objekt kniha z databazy podla mena podla inputu
		System.out.println("Napiste nazev kniny ktoru chcete upravit: "); //TODO: Ivan treba checkovat nech input je String
		
		String nazevKnihy = KeyboardInput.nextLine(sc);
		Kniha currentKniha = new Kniha();
		
		currentKniha = databaze.getKnihaByName(nazevKnihy);
		/*
		vrati pole
		ako bude pole > ako 1 tak sa spyta ktoru knihu chceme, implementujeme to do vseobecnej metody   
		*/
		
		System.out.println("Zvolena kniha: "+currentKniha.printKniha());
		
		return currentKniha;
	}
	
	public void printAllBooks() {

		for(Kniha kniha : databaze.getVsetkyKnihy()) {
			System.out.println(databaze.getVsetkyKnihy().indexOf(kniha) + ": "+kniha.printKniha());
			System.out.println("- - - - - - - - - - - - - - - - -");
		}
	}
	
	public void initDatabaze() {
		databaze.initDatabaze();
	}
}
