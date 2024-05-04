package edu.vut.pc2t.bookstore.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.vut.pc2t.bookstore.controller.KeyboardInput;
import edu.vut.pc2t.bookstore.database.Databaze;
import edu.vut.pc2t.bookstore.database.FileDatabaze;
import edu.vut.pc2t.bookstore.database.H2Database;

public class Kniznica {
	
	public static final String[] VSETKY_ZANRE = {"Sci-Fi","Fantasy","Detektivka","Autobiografia","Kucharka"};
	
	private Databaze databaze;

	private H2Database sqlDatabaze;
	
	public Kniznica() {
		super();
		databaze = new Databaze();
		sqlDatabaze = new H2Database();
	}
	
	public boolean runKniznica() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		//TODO: na zacitatku musi byt read SQL databaza
		
		boolean runLoop = true;
		
		while(runLoop) {
			
			KeyboardInput.printMenu();
			int input = KeyboardInput.pouzeCelaCisla(sc);
			
			switch(input) {
				case 00: initDatabaze(); break;
			
				case 1: addNewBook(sc); break;
				
				case 2: editBook(sc); break;
				
				case 3: deleteBook(sc); break;
				
				case 4: setAvalabilityToBook(sc); break;
				
				case 5: printAllBooks(); break;
				
				case 6: searchBook(sc); break;
				
				case 7: getKnihyAutora(sc); break;
				
				case 8: getKnhyVZanri(sc); break;
				
				case 9: vypujceneKnihy(); break;
				
				case 10: ulozKnihu(sc); break;
				
				case 11: nacitajKnihu(sc); break;
				
				case 99:  runLoop = false; break;
			}
			
		}
		
		return false;
	}
	
	public void addNewBook(Scanner sc) { //Pridava knihu do databazy
		
		System.out.println("Vyberte typ knihy: 0-Roman  1-Ucebnice");
		int bookType = KeyboardInput.pouzeJednaNeboNula(sc);
		System.out.println("Zadajte nazev knihy: ");
		String nazev = KeyboardInput.nextLine(sc); //TODO: Duplicitne knihy nepovolujeme
		System.out.println("Zadajte autora knihy: ");
		String autor = KeyboardInput.nextLine(sc);
		System.out.println("Zadajte rok vydania knihy: ");
		int rokVydania = KeyboardInput.pouzeCelaCisla(sc);
		
		if(bookType == 0) {
		// Kniha je Roman
			System.out.println("Zadajte zaner romanu: ");
			String zaner = vyberZaner(sc);
			
			Roman newRoman = new Roman();
			newRoman.setNazev(nazev);
			newRoman.setAutor(autor);
			newRoman.setRokVydani(rokVydania);
			newRoman.setJeDostupny(true); //Pri pridani je dostupna 
			newRoman.setZaner(zaner);
			if(knihaExistuje(newRoman)) {
				System.out.println("Kniha uz existuje.");
				return;
			}
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
			if(knihaExistuje(newUcebnice)) {
				System.out.println("Kniha uz existuje.");
				return;
			}
			databaze.addKniha(newUcebnice);
			
		}
		
	}
	
	public void editBook(Scanner sc) { //Edituje knihu podla mena
		Kniha currentKniha;
		
		currentKniha = selectKnihaByName(sc);
		
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
	}
	
	public void deleteBook (Scanner sc) { // Maze knihu podla mena
		Kniha currentKniha;
		
		currentKniha = selectKnihaByName(sc);
		
		System.out.println("Smazat \"" + currentKniha.getNazev() + "\" ? (0 - Ne, 1 - Ano )");
		
		int confirm = KeyboardInput.pouzeJednaNeboNula(sc);
		
		if(confirm == 1) databaze.removeKniha(currentKniha);
		else System.out.println("Rusim akci...");
				
	}
	
	public void setAvalabilityToBook (Scanner sc) { 
		Kniha currentKniha;
		
		currentKniha = selectKnihaByName(sc);
		
		System.out.println("Zadajte dostupnost knihy: 0 -Nedostupny, 1 -Dostupny ");
		int dostupnost = KeyboardInput.pouzeJednaNeboNula(sc);
		
		if(dostupnost == 1) currentKniha.setJeDostupny(true);
		else currentKniha.setJeDostupny(false);
	}
	
	public Kniha selectKnihaByName(Scanner sc) {
		Kniha currentKniha;
		
		boolean loop = false;
		do {
			currentKniha = searchKnihaFromKeyboardByName(sc);
			if(currentKniha == null) {
				loop = true;
			} else loop = false;
		} while(loop);
		
		return currentKniha;
	}
	
	public Kniha searchKnihaFromKeyboardByName (Scanner sc) { // Returne objekt kniha z databazy podla mena podla inputu
		System.out.println("Napiste nazev kniny: "); //TODO: Ivan treba checkovat nech input je String
		
		String nazevKnihy = KeyboardInput.nextLine(sc);
		Kniha currentKniha = new Kniha();
		List<Kniha> najdeneKnihy =  new ArrayList<Kniha>();

		najdeneKnihy = databaze.searchKnihaByName(nazevKnihy);
		
		if(najdeneKnihy.size() > 1) {
			currentKniha = vyberKnihuZoZoznamu(najdeneKnihy, sc);
		}else if (najdeneKnihy.size() == 1) {
			currentKniha = najdeneKnihy.get(0);
		} else if (najdeneKnihy.size() == 0) {
			System.out.println("Kniha s nazvom: "+nazevKnihy+" neexistuje.");
			return null;
		}
		System.out.println("Zvolena kniha: "+currentKniha.printKniha());
		
		return currentKniha;
	}
	
	public Kniha vyberKnihuZoZoznamu(List<Kniha> knihy, Scanner sc) {
		for(Kniha kniha : knihy) {
			System.out.println(knihy.indexOf(kniha) + ": "+kniha.printKniha());
			System.out.println("- - - - - - - - - - - - - - - - -");
		}
		boolean loop = true;
		int index = 0;
		while(loop) {
			System.out.println("Zadaj index knihy ktoru chces vybrat: ");
			index = KeyboardInput.pouzeCelaCisla(sc);
			if(index+1 > knihy.size()) {
				System.out.println("Kniha s indexom neni na vyber !!!");
			}
			else loop = false;
		}
		return knihy.get(index);
	}
	
	public void printAllBooks() {
		
		List<Kniha> vsetkyKnihy = databaze.getVsetkyKnihy();
		
		vsetkyKnihy.sort(Comparator.comparing(Kniha::getNazevAsUpperCase));
		for(Kniha kniha : vsetkyKnihy) {
			System.out.println(vsetkyKnihy.indexOf(kniha) + ": "+kniha.printKniha());
			System.out.println("- - - - - - - - - - - - - - - - -");
		}
	}
	
	public boolean knihaExistuje(Kniha knihaCheck) {
		return databaze.getVsetkyKnihy().contains(knihaCheck);
	}
	
	public void searchBook(Scanner sc) {
		System.out.println("Napiste nazev kniny ktoru chcete vyhladat: ");
		
		String nazevKnihy = KeyboardInput.nextLine(sc);
		Kniha currentKniha = new Kniha();
		
		currentKniha = databaze.getKnihaByName(nazevKnihy);
		
		System.out.println(currentKniha.printKniha());
	}
	
	public void getKnihyAutora(Scanner sc) { //TODO: Chronologicke vypisane podla roku vydania
		System.out.println("Napiste meno autora ktoreho knihy chcete vypsat: ");
		
		List<Kniha> knihyAutora = new ArrayList<Kniha>();
		
		//knihyAutora = databaze.getVsetkyKnihyByAutor(KeyboardInput.nextLine(sc));
		knihyAutora = databaze.searchVsetkyKnihaByAutor(KeyboardInput.nextLine(sc));
		
		knihyAutora.sort(Comparator.comparing(Kniha::getRokVydani));
		
		if(knihyAutora.isEmpty()) System.out.println("Autor neexistuje!");
		else {
			for(Kniha kniha : knihyAutora) {
				System.out.println(kniha.printKniha());
			}
		}
	}
	
	public String vyberZaner(Scanner sc) {
		int i = 0;
		for(String zaner : VSETKY_ZANRE) {
			System.out.println(i + ": " + zaner);
			System.out.println("- - - - - - - - - - - - - - - - -");
			i++;
		}
		boolean loop = true;
		int index = 0;
		while(loop) {
			System.out.println("Zadaj index zanru ktory chces vybrat: ");
			index = KeyboardInput.pouzeCelaCisla(sc);
			if(index+1 > VSETKY_ZANRE.length) {
				System.out.println("Zaner s indexom neni na vyber !!!");
			}
			else loop = false;
		}
		return VSETKY_ZANRE[index];
	}
	
	public void getKnhyVZanri(Scanner sc) {
		System.out.println("Napiste zaner z ktoreho chcete vypsat knihy: ");
		
		List<Kniha> knihyVZanru = new ArrayList<Kniha>();
		
		knihyVZanru = databaze.getVsetkyKnihyByZaner(vyberZaner(sc));
		
		if(knihyVZanru.isEmpty()) System.out.println("V zanru nejsu zadne knihy!");
		else {
			for(Kniha kniha : knihyVZanru) {
				System.out.println(kniha.printKniha());
			}
		}
	}
	
	public void vypujceneKnihy() {
		List<Kniha> vypujceneKnihy = new ArrayList<Kniha>();
		
		vypujceneKnihy = databaze.getVypujceneKnihy();
		
		if(vypujceneKnihy.isEmpty()) System.out.println("Nejsu vypujcene zadne knihy");
		else {
			for(Kniha kniha : vypujceneKnihy) {
				System.out.println(kniha.printKniha());
			}
		}
		
	}
	
	public void ulozKnihu(Scanner sc) throws IOException {
		Kniha currentKniha;
		
		currentKniha = selectKnihaByName(sc);
		
		FileDatabaze.writeKnihaToFile(currentKniha);
	}
	
	public void nacitajKnihu(Scanner sc) throws IOException {
		List<String> allFileNames = FileDatabaze.listFilesForFolder();
		
			for(String fileName : allFileNames) {
				System.out.println(allFileNames.indexOf(fileName) + ": "+fileName);
			}
			boolean loop = true;
			int index = 0;
			while(loop) {
				System.out.println("Zadaj index suboru ktory chces nacitat: ");
				index = KeyboardInput.pouzeCelaCisla(sc);
				if(index+1 > allFileNames.size()) {
					System.out.println("Subor s indexom neni na vyber !!!");
				}
				else loop = false;
			}
			String selectedFileName = allFileNames.get(index);
			
			Kniha newKniha = FileDatabaze.readKnihaFromFile(selectedFileName);
			
			if(newKniha == null) {
				System.out.println("!! Kniha sa nepodarila nacitat zo suboru !!");
			}
			
			if(knihaExistuje(newKniha)) {
				System.out.println("Kniha uz existuje.");
				return;
			}
			databaze.addKniha(newKniha);
			
			System.out.println("Pridana kniha: \n" + newKniha.printKniha());
	}
	
	public Databaze getDatabaze() {
		return databaze;
	}

	public H2Database getSqlDatabaze() {
		return sqlDatabaze;
	}

	public void initDatabaze() {
		databaze.initDatabaze();
	}
}
