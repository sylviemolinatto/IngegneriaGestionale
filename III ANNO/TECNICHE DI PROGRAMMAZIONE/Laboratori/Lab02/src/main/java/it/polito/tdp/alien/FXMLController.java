/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.alien.model.Dizionario;
import it.polito.tdp.alien.model.Traduzione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Dizionario model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="btnTranslate"
    private Button btnTranslate; // Value injected by FXMLLoader

    @FXML // fx:id="txtDictionary"
    private TextArea txtDictionary; // Value injected by FXMLLoader

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML
    void doClear(ActionEvent event) {
    	String parolaDigitata = txtParola.getText();
    	String[] parole = parolaDigitata.split(" ");
    	if(parole.length==1) {
    		this.model.getListaParoleTraduzioni().remove(parolaDigitata);
    	}
    	else if(parole.length==2) {
    		this.model.getListaParoleTraduzioni().remove(parole[0]);
    	}
    }

    @FXML
    void doTranslate(ActionEvent event) {

    	String parolaDigitata = txtParola.getText();
    	String[] parole = parolaDigitata.split(" ");
    	
    	// se c'è una sola parola devo trovare la traduzione
    	if(parole.length==1) {
    		if(this.model.parolaIsLegit(parole[0])) {
    			Traduzione traduzione = model.cercaTraduzione(parole[0].toLowerCase());
    			if(traduzione!=null) {
    				txtDictionary.setText("Le traduzioni della parola "+parole[0]+" sono:\n"+traduzione);
    			}
    			else {
    				txtDictionary.setText("La parola cercata non è presente nel dizionario");
    			}
        		
    		}
    		else if(parole[0].contains("?")) {
    			String traduzione = model.cercaTraduzioneWildCard(parole[0]);
    			if(traduzione!=null) {
    				txtDictionary.setText("Le traduzioni della parola "+parole[0]+" sono:\n"+traduzione.toString());
    			}
    			else {
    				txtDictionary.setText("La parola cercata non è presente nel dizionario");
    			}
    		}
    		else {
    			txtDictionary.setText("ERRORE : gli unici caratteri ammessi sono [a-zA-Z]");
    	    }
    	}
    	
    	// se ci sono due parole devo salvare la parola e la sua traduzione
    	else if(parole.length==2) {
    		if(this.model.parolaIsLegit(parole[0], parole[1])) {
    			String parola = parole[0].toLowerCase();
        		String traduzione = parole[1].toLowerCase();
        		
        		// controllo se esiste già quella parola nel dizionario
        		if(this.model.cercaTraduzione(parola)!=null) {
        			this.model.aggiungiTraduzione(parola, traduzione);
        			txtDictionary.setText("E' stata aggiunta la traduzione "+traduzione+" alla parola "+parola);
        		}
        
        		else {
        			this.model.addParola(parola,traduzione);
            		txtDictionary.setText("E' stata aggiunta la parola "+parola+" con la traduzione "+traduzione);
        		}
        		
    		}
    		else {
    			txtDictionary.setText("ERRORE : gli unici caratteri ammessi sono [a-zA-Z]");
    		}	
    	}
    	
    	else if(parole.length==0) {
    		txtDictionary.setText("Inserire una o due parole!");
    	}
    }

    public void setModel(Dizionario model) {
    	this.model=model;
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDictionary != null : "fx:id=\"txtDictionary\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}

