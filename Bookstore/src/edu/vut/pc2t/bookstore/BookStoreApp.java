package edu.vut.pc2t.bookstore;

import java.sql.SQLException;
import java.util.Scanner;

import edu.vut.pc2t.bookstore.controller.KeyboardInput;
import edu.vut.pc2t.bookstore.database.Databaze;
import edu.vut.pc2t.bookstore.model.Kniznica;

public class BookStoreApp {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Kniznica kniznica = new Kniznica();
		System.out.println("Vytejte v knihovne.");
		
	
		// kniznica.initDatabaze();
		kniznica.printAllBooks();
		
		System.out.println("- - - - - Test area - - - - -");
		
		try {
			kniznica.getSqlDatabaze().connectToSQLDB();
			kniznica.getSqlDatabaze().readAllToMemory(kniznica.getDatabaze());
			kniznica.runKniznica();
			System.out.println("Closing program ...");
			kniznica.getSqlDatabaze().writeAllToSQLDB(kniznica.getDatabaze());
			//kniznica.getSqlDatabaze().disconnectFromSQLDB();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			kniznica.getSqlDatabaze().disconnectFromSQLDB();
		}
		
		//TODO: Ked sa zada nieco zle tak je treba napisat userovi ze je debil a nech necrashne program
		//TODO: Nemam patchnute ked su duplikovane knihy
		
	}

}
