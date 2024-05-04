package edu.vut.pc2t.bookstore;

import java.io.IOException;
import java.sql.SQLException;

import edu.vut.pc2t.bookstore.model.Kniznica;

public class BookStoreApp {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		Kniznica kniznica = new Kniznica();
		System.out.println("--- Vitejte v knihovne. ---");
	
		// kniznica.printAllBooks();
		
		try {
			kniznica.getSqlDatabaze().connectToSQLDB();
			kniznica.getSqlDatabaze().readAllToMemory(kniznica.getDatabaze());
			kniznica.getSqlDatabaze().disconnectFromSQLDB();
			
			kniznica.runKniznica();
			
			kniznica.getSqlDatabaze().connectToSQLDB();
			kniznica.getSqlDatabaze().writeAllToSQLDB(kniznica.getDatabaze());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			kniznica.getSqlDatabaze().disconnectFromSQLDB();
			System.out.println("Closing program ...");
		}
	}
}
