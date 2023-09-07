package it.polito.tdp.parole;

import it.polito.tdp.parole.model.Parole;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Parole elenco ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnInserisci;
    
    @FXML
    private Button btnCancella;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML // fx:id="txtTime"
    private TextArea txtTime; // Value injected by FXMLLoader
    
    @FXML
    void doInsert(ActionEvent event) {
    	// TODO
    	
    	// pulisco il risultato e il tempo impiegato
    	txtResult.clear();
    	txtTime.clear();
    	// aggiungo la parola all'elenco
    	String parola = txtParola.getText();
    	double start = System.nanoTime();
    	elenco.addParola(parola);
    	double end = System.nanoTime();
    	
    	// creo un nuovo elenco ordinato di parole
    	Parole p = new Parole(elenco.getElenco());
    	txtResult.setText(p.toString());
    	txtParola.clear();
    	txtTime.setText("Tempo impiegato: "+(end-start)/1e9+" secondi");
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	// TODO
    	double start = System.nanoTime();
    	elenco.reset();
    	double end = System.nanoTime();
    	
    	txtResult.clear();
    	txtParola.clear();
    	txtTime.setText("Tempo impiegato: "+(end-start)/1e9+" secondi");
    }
   
    
    @FXML
    void doCancella(ActionEvent event) {
    	
    	   String wordSelected = txtResult.getSelectedText();
    	   
    	   double start = System.nanoTime();
           elenco.removeParola(wordSelected);
           double end = System.nanoTime();
           
           txtParola.clear();
           txtResult.clear();
           
           Parole p = new Parole(elenco.getElenco());
           
           txtResult.setText(p.toString());
           txtTime.setText("Tempo impiegato: "+(end-start)/1e9+" secondi");
    }

    @FXML
    void initialize() {
    	assert btnCancella != null : "fx:id=\"btnCancella\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

        elenco = new Parole() ;
    }
}
