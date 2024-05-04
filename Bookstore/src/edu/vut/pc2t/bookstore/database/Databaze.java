package edu.vut.pc2t.bookstore.database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.vut.pc2t.bookstore.model.Kniha;
import edu.vut.pc2t.bookstore.model.Roman;
import edu.vut.pc2t.bookstore.model.Ucebnice;

public class Databaze {
	
	List<Kniha> kniznica = new ArrayList<Kniha>();
	
	public void addKniha(Kniha kniha) {
		kniznica.add(kniha);
	}
	
	public void removeKniha(Kniha kniha) {
		//TODO: Pouzije equals ?
		kniznica.remove(kniha);
	}
	
	public Kniha getKnihaByName(String nazovKnihy) { //TODO: Implementovat duplikaty
		for(Kniha kniha : kniznica) {
			if(kniha.getNazev().equalsIgnoreCase(nazovKnihy)){
				return kniha;
			}
		}
		return null;
	}
	
	public List<Kniha> searchKnihaByName(String searchString) { //TODO: Implementovat duplikaty
		List<Kniha> searchKniha = new ArrayList<Kniha>();
		for(Kniha kniha : kniznica) {
			if(kniha.getNazev().toUpperCase().contains(searchString.toUpperCase())){
				searchKniha.add(kniha);
			}
		}
		return searchKniha;
	}
	
	public List<Kniha> searchVsetkyKnihaByAutor(String searchString) { //TODO: Sorting chronologicky podla roku vydania
		List<Kniha> knihyAutora = new ArrayList<Kniha>();
		for(Kniha kniha : kniznica) {
			if(kniha.getAutor().toUpperCase().contains(searchString.toUpperCase())){
				knihyAutora.add(kniha);
			}
		}
		return knihyAutora;
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
			if(kniha.getZaner() != null) {
				if(kniha.getZaner().equalsIgnoreCase(zaner)){
					knihyZaner.add(kniha);
				}
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
	
	public void sortKnihyPodlaRokuVydania() {
	    kniznica.sort(Comparator.comparing(Kniha::getRokVydani));
	}
	
	public void initDatabaze() {
		Roman roman1 = new Roman();
		Roman roman2 = new Roman();
		Roman roman3 = new Roman();
		Roman roman4 = new Roman();
		Ucebnice ucebnice1 = new Ucebnice();
		Ucebnice ucebnice2 = new Ucebnice();
		
		
		roman1.setAutor("Adam Jakes");
		roman1.setNazev("Ako som sa ucil programovat");
		roman1.setJeDostupny(true);
		roman1.setRokVydani(2024);
		roman1.setZaner("Autobiografia");
		
		roman2.setAutor("Roman Jakes");
		roman2.setNazev("Ako som ucil programovat");
		roman2.setJeDostupny(true);
		roman2.setRokVydani(2023);
		roman2.setZaner("Autobiografia");
		
		roman3.setAutor("Roman Jakes");
		roman3.setNazev("Test1");
		roman3.setJeDostupny(true);
		roman3.setRokVydani(2022);
		roman3.setZaner("Autobiografia");
		
		roman4.setAutor("Roman Jakes");
		roman4.setNazev("Test2");
		roman4.setJeDostupny(true);
		roman4.setRokVydani(2021);
		roman4.setZaner("Autobiografia");
		
		ucebnice1.setAutor("Pavol Hamel");
		ucebnice1.setNazev("Tanec pre zaciatocnikov");
		ucebnice1.setJeDostupny(true);
		ucebnice1.setRokVydani(1999);
		ucebnice1.setVhodnyRocnik(18);
		
		ucebnice2.setAutor("Waldemar Matuska");
		ucebnice2.setNazev("Spev pre mladych");
		ucebnice2.setJeDostupny(true);
		ucebnice2.setRokVydani(1989);
		ucebnice2.setVhodnyRocnik(12);
		
		addKniha(roman1);
		addKniha(roman2);
		addKniha(roman3);
		addKniha(roman4);
		addKniha(ucebnice1);
		addKniha(ucebnice2);
		
	}
	
}
