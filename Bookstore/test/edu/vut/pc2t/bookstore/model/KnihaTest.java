package edu.vut.pc2t.bookstore.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KnihaTest {

	@Test
	void equalsTest() {
		
		Kniha kniha1 = new Kniha();
		Kniha kniha2 = new Kniha();
		
		kniha1.setAutor("Adam Jakes");
		kniha1.setNazev("Kniha1");
		
		kniha2.setAutor("Roman Jakes");
		kniha2.setNazev("Kniha1");
		
		assertFalse(kniha1 == kniha2);
		assertFalse(kniha1.equals(kniha2));
		
		kniha1.setAutor(kniha2.getAutor());
		kniha1.setNazev(kniha2.getNazev());
		
		assertFalse(kniha1 == kniha2);
		assertTrue(kniha1.equals(kniha2));

	}

}
