package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Dictionary {
	
	// Map<String,String> dictionary;
	List<String> dictionaryList;
	
	public Dictionary() {
		// this.dictionary = new HashMap<String,String>();
		this.dictionaryList = new LinkedList<String>();
		// this.dictionaryList = new ArrayList<String>();
	}
	
	public void loadDictionary(String language) {
			
			try {
				FileReader fr = new FileReader(language);
				BufferedReader br = new BufferedReader(fr);
				String word;
				
				while((word=br.readLine())!=null) {
					
					// this.dictionary.put(word.toLowerCase(),word.toLowerCase());
					this.dictionaryList.add(word.toLowerCase());
				}
				
				br.close();
				fr.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore nella lettura del file");
			}
			
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		List<RichWord> rw = new LinkedList<RichWord>();
		for(String s : inputTextList) {
			if(this.dictionaryList.contains(s)) {
				rw.add(new RichWord(s,true));
			}
			else {
				rw.add(new RichWord(s,false));
			}
		}
		
		return rw;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		
		List<RichWord> rw = new LinkedList<RichWord>();
		
		int length = this.dictionaryList.size();
		boolean trovato;
		
		for(String s : inputTextList) {
			
			trovato=false;
			int low = 0;
			int high = length-1;
			
			while (low<=high && trovato==false) {
				int mid = (low+high)/2;
				if(this.dictionaryList.get(mid).compareTo(s)==0) {
					rw.add(new RichWord(s,true));
					trovato=true; //valore trovato nella posizione mid
			        }
				else if (this.dictionaryList.get(mid).compareTo(s)<0) {
					low = mid + 1;
				}
				else {
					high = mid - 1;
				}
			}
			
			if(trovato==false) {
				rw.add(new RichWord(s,false));
			}
			
		}
		
		return rw;
	}
	
	/* public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> rw = new LinkedList<RichWord>();
		for(String s : inputTextList) {
			if(this.dictionary.get(s)!=null) {
				rw.add(new RichWord(s,true));
			}
			else {
				rw.add(new RichWord(s,false));
			}
		}
		return rw;
	} */
	
}
