/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Anagramma;
import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCalcolaAnagrammi"
    private Button btnCalcolaAnagrammi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnagrammiCorretti"
    private TextArea txtAnagrammiCorretti; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnagrammiErrati"
    private TextArea txtAnagrammiErrati; // Value injected by FXMLLoader

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML
    void doCalcolaAnagrammi(ActionEvent event) {

    	String parola = txtParola.getText();
    	List<Anagramma> anagrammi = this.model.anagramma(parola);
    	String txtCorretti="";
    	String txtErrati="";
    	for(Anagramma a : anagrammi) {
    		if(this.model.isCorrect(a.getAnagramma())){
    			a.setCorretto(true);
    			txtCorretti+=a.getAnagramma()+"\n";
    		}
    		else {
    			txtErrati+=a.getAnagramma()+"\n";
    		}
    	}
    	txtAnagrammiCorretti.setText(txtCorretti);
    	txtAnagrammiErrati.setText(txtErrati);
    }

    @FXML
    void doReset(ActionEvent event) {

    	txtParola.clear();
    	txtAnagrammiCorretti.clear();
    	txtAnagrammiErrati.clear();
    	
    }

    public void setModel(Model model) {
    	this.model=model;
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCalcolaAnagrammi != null : "fx:id=\"btnCalcolaAnagrammi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnagrammiCorretti != null : "fx:id=\"txtAnagrammiCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnagrammiErrati != null : "fx:id=\"txtAnagrammiErrati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
