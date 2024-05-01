package edu.vut.pc2t.bookstore.controller;

import java.util.Scanner;

public class KeyboardInput {

	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
			String catcherString = sc.nextLine();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static String nextLine(Scanner sc) {
		String inputString = sc.nextLine();
		//String catcherString = sc.nextLine();
		
		return inputString;
	}
	
	public static int pouzeJednaNeboNula(Scanner sc) 
	{
		int cislo = pouzeCelaCisla(sc);
		
		if(cislo == 0 || cislo == 1) {
			return cislo;
		}
		else {
			System.out.println("Zadej 0 alebo 1 !");
			cislo = pouzeJednaNeboNula(sc);
		}
		
		return cislo;
	}
	
	public static void printMenu() {
		System.out.println("Vyberte pozadovanou cinnost:");
		System.out.println("0 .. initDatabaze");
		System.out.println("1 .. pridanie novej knihy");
		System.out.println("2 .. uprava knihy");
		System.out.println("3 .. smazani knihy");
		System.out.println("4 .. oznaceni knihy jako vupujcene/vracene");
		System.out.println("5 .. vypis knih");
		System.out.println("6 .. vyhledani knihy");
		System.out.println("7 .. vypis knih autora");
		System.out.println("8 .. vypis knih v zanru");
		System.out.println("9 .. vypis vypujcenych knih");
		System.out.println("10 .. ulozit knihu do souboru");
		System.out.println("11 .. nacist knihu ze souboru");
		System.out.println("99 .. ukoncitProgram");
	}
	
}
