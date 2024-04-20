package edu.vut.pc2t.bookstore;

import java.util.Scanner;

import edu.vut.pc2t.bookstore.controller.KeyboardInput;
import edu.vut.pc2t.bookstore.database.Databaze;
import edu.vut.pc2t.bookstore.model.Kniznica;

public class BookStoreApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Kniznica kniznica = new Kniznica();
		System.out.println("Vytejte v knihovne.");
		
		kniznica.initDatabaze();
		kniznica.printAllBooks();
		
		System.out.println("- - - - - Test area - - - - -");
		
		kniznica.runKniznica();
		
		//TODO: Ked sa zada nieco zle tak je treba napisat userovi ze je debil a nech necrashne program
		//TODO: Nemam patchnute ked su duplikovane knihy
		
	}

}
