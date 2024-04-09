package edu.vut.pc2t.bookstore.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.vut.pc2t.bookstore.model.Kniha;

public class Databaze {
	
	List<Kniha> kniznica = new ArrayList<Kniha>();
	
	public void addKniha(Kniha kniha) {
		kniznica.add(kniha);
	}
	
	public void removeKniha(Kniha kniha) {
		//TODO: Pouzije equals ?
		kniznica.remove(kniha);
	}
	
	public void updateKniha(Kniha knihaUpdate) {
		Kniha currentKniha = getKnihaByName(knihaUpdate.getNazev());
		if(currentKniha != null) {
			//TODO: Budeme riesit pri SQL Databaze
		}
		
	}
	
	public Kniha getKnihaByName(String nazovKnihy) {
		for(Kniha kniha : kniznica) {
			if(kniha.getNazev().equalsIgnoreCase(nazovKnihy)){
				return kniha;
			}
		}
		return null;
	}
	
	public List<Kniha> getVsetkyKnihy() { //TODO: Sorting podla abecedy
		return kniznica;
	}
	
	public List<Kniha> getVsetkyKnihyByAutor(String autor) { //TODO: Sorting chronologicky podla roku vydania
		List<Kniha> knihyAutora = new ArrayList<Kniha>();
		for(Kniha kniha : kniznica) {
			if(kniha.getAutor().equalsIgnoreCase(autor)){
				knihyAutora.add(kniha);
			}
		}
		return knihyAutora;
	}
	
	public List<Kniha> getVsetkyKnihyByZaner(String zaner) {
		List<Kniha> knihyZaner = new ArrayList<Kniha>();
		for(Kniha kniha : kniznica) {
			if(kniha.getZaner().equalsIgnoreCase(zaner)){
				knihyZaner.add(kniha);
			}
		}
		return knihyZaner;
	}

	public List<Kniha> getVypujceneKnihy() { 
		List<Kniha> knihyVypujcene = new ArrayList<Kniha>();
		for(Kniha kniha : kniznica) {
			if(!kniha.isJeDostupny()){
				knihyVypujcene.add(kniha);
			}
		}
		return knihyVypujcene;
	}
	
}
