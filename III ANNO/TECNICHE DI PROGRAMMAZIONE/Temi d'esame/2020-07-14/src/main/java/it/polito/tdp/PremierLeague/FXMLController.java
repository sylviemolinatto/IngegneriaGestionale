/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Edge;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.Team;
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

    @FXML // fx:id="btnClassifica"
    private Button btnClassifica; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbSquadra"
    private ComboBox<Team> cmbSquadra; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doClassifica(ActionEvent event) {

    	txtResult.clear();
    	Team t = this.cmbSquadra.getValue();
    	if(t==null) {
    		txtResult.appendText("Seleziona una squadra!");
    		return;
    	}
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	
    	txtResult.appendText("SQUADRE MIGLIORI : \n");
    	for(Edge e : this.model.getSquadreNonBattute(t)) {
    		txtResult.appendText(e.getT1().getName()+" ("+e.getWeight()+")\n");
    	}
    	txtResult.appendText("SQUADRE PEGGIORI : \n");
    	for(Edge e : this.model.getSquadreBattute(t)) {
    		txtResult.appendText(e.getT2().getName()+" ("+e.getWeight()+")\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	this.model.creaGrafo();
    	txtResult.appendText("GRAFO CREATO!\n");
    	txtResult.appendText("# VERTICI : "+this.model.creaGrafo().vertexSet().size()+"\n");
    	txtResult.appendText("# ARCHI : "+this.model.creaGrafo().edgeSet().size()+"\n");
    	
    	this.cmbSquadra.getItems().addAll(this.model.getSquadre());

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	int N;
    	int soglia;
    	
    	try {
    		N = Integer.parseInt(this.txtN.getText());
    		soglia = Integer.parseInt(this.txtX.getText());
    		if(!this.model.grafoCreato()) {
    			txtResult.appendText("Crea prima il grafo!");
    			return;
    		}
    		
    		this.model.simula(N, soglia);
    		txtResult.appendText("Numero di partite in cui il numero di reporter è stato sotto la soglia : "+this.model.getNumPartiteSottoSoglia()+"\n");
    		txtResult.appendText("Numero medio di reporter per partita : "+this.model.getMediaReporter());
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Inserisci due numeri interi nei campi 'N' e 'X'");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClassifica != null : "fx:id=\"btnClassifica\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbSquadra != null : "fx:id=\"cmbSquadra\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
