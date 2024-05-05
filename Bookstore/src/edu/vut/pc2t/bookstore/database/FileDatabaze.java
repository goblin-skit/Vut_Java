package edu.vut.pc2t.bookstore.database;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import edu.vut.pc2t.bookstore.model.Kniha;
import edu.vut.pc2t.bookstore.model.Roman;
import edu.vut.pc2t.bookstore.model.Ucebnice;

public class FileDatabaze {

	public static final String DELIMITER_CHARACTER = "#";
	
	private FileDatabaze() {
		throw new UnsupportedOperationException("Utility class, no instances allowed!");
	}
	
	public static boolean writeKnihaToFile(Kniha kniha) throws IOException {
		
		String dataString = exportKniha(kniha);
		String fileName = kniha.getNazev()+"_"+kniha.getRokVydani();
		
		Path path = Path.of("./data/"+fileName+".txt");
		Files.writeString(path, dataString, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		
		return true;
	}
	
	public static Kniha readKnihaFromFile(String fileName) throws IOException {
		
		Path path = Path.of("./data/"+fileName);
		
		String fileContent = Files.readString(path, StandardCharsets.UTF_8);
		
		return createKniha(fileContent);
	}
	
	//Source: https://stackoverflow.com/questions/1844688/how-can-i-read-all-files-in-a-folder-from-java
	public static List<String> listFilesForFolder() {
		final File folder = new File("./data");
		List<String> fileNames = new ArrayList<String>();
		
	    for (final File fileEntry : folder.listFiles()) {
	            fileNames.add(fileEntry.getName());
	    }
	    return fileNames;
	}

	private static String exportKniha(Kniha kniha) {
		String exportString = "";
		
		exportString = exportString+kniha.printDruhKnihy()+DELIMITER_CHARACTER;
		exportString = exportString+kniha.getNazev()+DELIMITER_CHARACTER;
		exportString = exportString+kniha.getAutor()+DELIMITER_CHARACTER;
		exportString = exportString+String.valueOf(kniha.getRokVydani())+DELIMITER_CHARACTER;
		exportString = exportString+String.valueOf(kniha.getVhodnyRocnik())+DELIMITER_CHARACTER;
		exportString = exportString+kniha.getZaner()+DELIMITER_CHARACTER;
		exportString = exportString+String.valueOf(kniha.isJeDostupny());
		
		return exportString;
	}
	
	private static Kniha createKniha(String dataString) {
		
		String[] data = dataString.split(DELIMITER_CHARACTER);
		
		String druhKnihy = data[0];
		String nazov = data[1];
		String autor = data[2];
		int rokVydania = Integer.valueOf(data[3]);;
		int vhodnyRocnik = Integer.valueOf(data[4]);
		String zaner = data[5];
		boolean dostupny = Boolean.valueOf(data[6]);
		
		if(druhKnihy.equalsIgnoreCase("Roman")) {
			Roman newRoman = new Roman();
			newRoman.setNazev(nazov);
			newRoman.setAutor(autor);
			newRoman.setRokVydani(rokVydania);
			newRoman.setJeDostupny(dostupny);
			newRoman.setZaner(zaner);
			
			return newRoman;
		}
		else if(druhKnihy.equalsIgnoreCase("Ucebnice")) {
			Ucebnice newUcebnice = new Ucebnice();
			newUcebnice.setNazev(nazov);
			newUcebnice.setAutor(autor);
			newUcebnice.setRokVydani(rokVydania);
			newUcebnice.setJeDostupny(dostupny);
			newUcebnice.setVhodnyRocnik(vhodnyRocnik);
		
			return newUcebnice;
		}
		
		return null;
	}
}
