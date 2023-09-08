
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	Match m = this.cmbMatch.getValue();
    	if(m==null) {
    		txtResult.appendText("Seleziona un match!");
    		return;
    	}
    	this.model.creaGrafo(m);
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("# VERTICI : "+this.model.getNVertici()+"\n");
    	txtResult.appendText("# ARCHI : "+this.model.getNArchi()+"\n");
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {    	
    	
    	txtResult.clear();
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	
    	txtResult.appendText("Giocatore migliore : \n");
    	txtResult.appendText(this.model.giocatoreMigliore().toString()+" punteggio = "+this.model.getDeltaMigliore());
    }
    
    @FXML
    void doSimula(ActionEvent event) {

    	txtResult.clear();
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	if(this.cmbMatch.getValue()==null) {
    		txtResult.appendText("Seleziona un match");
    		return;
    	}
    	
    	int numAzioniSalienti;
    	try {
    		numAzioniSalienti = Integer.parseInt(this.txtN.getText());
    		this.model.simula(this.cmbMatch.getValue(), numAzioniSalienti);
    		txtResult.appendText("Risultato partita : "+this.model.getSquadra1().getNumGoal()+" / "+this.model.getSquadra2().getNumGoal()+"\n");
    		txtResult.appendText("Numero di giocatori espulsi dalla squadra 1 : "+this.model.getSquadra1().getNumEspulsi()+"\n");
    		txtResult.appendText("Numero di giocatori espulsi dalla squadra 2 : "+this.model.getSquadra2().getNumEspulsi());
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Il campo 'Azioni Salienti' deve essere un numero intero");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	List<Match> matches = new ArrayList<Match>(this.model.getAllMatches());
    	Collections.sort(matches);
    	this.cmbMatch.getItems().addAll(matches);
    }
}
