package edu.vut.pc2t.bookstore.database;

import java.util.ArrayList;
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
	
	public void updateKniha(Kniha knihaUpdate) {
		Kniha currentKniha = getKnihaByName(knihaUpdate.getNazev());
		if(currentKniha != null) {
			//TODO: Budeme riesit pri SQL Databaze
		}
		
	}
	
	public Kniha getKnihaByName(String nazovKnihy) { //TODO: Implementovat duplikaty
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
	
	public void initDatabaze() {
		Roman roman1 = new Roman();
		Roman roman2 = new Roman();
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
		addKniha(ucebnice1);
		addKniha(ucebnice2);
		
	}
	
}
