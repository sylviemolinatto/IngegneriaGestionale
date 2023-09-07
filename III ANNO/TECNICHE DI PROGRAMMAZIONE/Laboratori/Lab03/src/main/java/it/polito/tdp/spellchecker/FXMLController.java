/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cbLingua"
    private ComboBox<String> cbLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtError"
    private Label txtError; // Value injected by FXMLLoader

    @FXML // fx:id="txtIput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private Label txtTime; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {

    	txtInput.clear();
    	txtOutput.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

    	String language = cbLingua.getValue();
    	
    	if(language==null) {
    		txtError.setText("Devi selezionare una lingua");
    		return;
    	}
    	
    	else {
    	
    		language=language+".txt";
    		model.loadDictionary(language);
        	String testo = txtInput.getText();
        	testo = testo.replaceAll("[?.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
        	testo=testo.toLowerCase();
        	String[] parole =testo.split(" ");
        	List<String> listaParole = new LinkedList<String>();
        	int contatoreParoleErrate=0;
        	
        	for(String s : parole) {
        		listaParole.add(s);
        	}
        	
        	double start = System.nanoTime();
        	List<RichWord> result = this.model.spellCheckTextLinear(listaParole);
        	double end = System.nanoTime();
        	String paroleErrate="";
        	
        	for(RichWord rw : result) {
        		if(rw.isCorrect()==false) {
        			contatoreParoleErrate++;
        			paroleErrate+=rw.getParolaInput()+"\n";
        		}
        	}
        	
        	txtOutput.setText(paroleErrate);
        	txtError.setText("The text contains "+contatoreParoleErrate+" errors");
        	txtTime.setText("Spell check completed in "+((end-start)/1e9)+" secondi");
        	
    	}
    	
    	
    }
    
    static ObservableList<String> lingue = FXCollections.observableArrayList(
            "English",
            "Italian"
        );

    public void setModel(Dictionary model) {
    	this.model=model;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cbLingua != null : "fx:id=\"cbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtIput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";
        cbLingua.setItems(lingue);
        
    }
    
    // Parole cercate "maNusCri-pt DisU.se"
    // tempo per la ricerca lineare con LinkedList = 0,00368 s
    // tempo per la ricerca dicotomica con LinkedList = 0,0021782 s
    // tempo per la ricerca lineare con ArrayList = 9,337 * 10^-4 s
    // tempo per la ricerca dicotomica con ArrayList = 6,006 * 10^-4 s

}





