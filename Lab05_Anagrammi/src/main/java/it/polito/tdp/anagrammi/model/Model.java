package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {

	private AnagrammaDAO anagrammaDao;
	
	public Model() {
		anagrammaDao = new AnagrammaDAO();
	}
	
	public boolean parolaInDizionario(String parola) {
		return anagrammaDao.isCorrect(parola);
	}
	
	public List<String> getAnagrammi(String parola){
		List<String> risultato = new ArrayList<String>();
		permutaRicorsiva("", parola, 0, risultato);
		return risultato;
	}
	
	public void permutaRicorsiva(String parziale, String lettere, int livello, List<String> risultato) {
		
		if(lettere.length()==0) {
			risultato.add(parziale);
		}
		
		else {
			for(int posizione=0; posizione<lettere.length(); posizione++) {
				
				char tentativo = lettere.charAt(posizione);
				String nuovaParziale = parziale+tentativo;
				String nuovaLettere = lettere.substring(0,posizione)+lettere.substring(posizione+1);
				
				permutaRicorsiva(nuovaParziale, nuovaLettere, livello+1, risultato);
			}
		}
	}
	
	public List<String> anagrammiCorretti(String parola) {
		List<String> anagrammiCorretti = new ArrayList<String>();
		for(String s: this.getAnagrammi(parola)) {
			if(this.parolaInDizionario(s))
				anagrammiCorretti.add(s);
		}
		
		return anagrammiCorretti;
	}
	
	public List<String> anagrammiErrati(String parola) {
		List<String> anagrammiErrati = new ArrayList<String>();
		for(String s: this.getAnagrammi(parola)) {
			if(!this.parolaInDizionario(s))
				anagrammiErrati.add(s);
		}
		
		return anagrammiErrati;
	}
}
