/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Actor;
import it.polito.tdp.imdb.model.Model;
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

    @FXML // fx:id="btnSimili"
    private Button btnSimili; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimulazione"
    private Button btnSimulazione; // Value injected by FXMLLoader

    @FXML // fx:id="boxGenere"
    private ComboBox<String> boxGenere; // Value injected by FXMLLoader

    @FXML // fx:id="boxAttore"
    private ComboBox<Actor> boxAttore; // Value injected by FXMLLoader

    @FXML // fx:id="txtGiorni"
    private TextField txtGiorni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doRiempiAttori(ActionEvent event) {
    	this.boxAttore.getItems().clear();
    	if(this.model.grafoCreato()) {
    		List<Actor> attori = this.model.getActors();
    		Collections.sort(attori);
    		this.boxAttore.getItems().addAll(attori);
    	}
    	
    }
    
    @FXML
    void doAttoriSimili(ActionEvent event) {

    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!\n");
    		return;
    	}
    	else {
    		Actor attore = this.boxAttore.getValue();
    		if(attore==null) {
    			txtResult.appendText("Seleziona un attore!\n");
    			return;
    		}
    		
    		txtResult.appendText("ATTORI SIMILI A : "+attore+"\n");
    		List<Actor> simili = this.model.attoriSimili(attore);
    		Collections.sort(simili);
    		for(Actor a : simili ) {
    			txtResult.appendText(a+"\n");
    		}
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	txtResult.clear();
    	String genre = this.boxGenere.getValue();
    	if(genre==null) {
    		txtResult.appendText("Seleziona un genere!");
    		return;
    	}
    	else {
    		this.model.creaGrafo(genre);
    		txtResult.appendText("GRAFO CREATO : "+this.model.getNVertici()+" vertici, "+this.model.getNArchi()+" archi\n\n");
    		this.boxAttore.getItems().clear();
    		List<Actor> attori = this.model.getActors();
    		Collections.sort(attori);
    		this.boxAttore.getItems().addAll(attori);
    	}
    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	else {
    		int numGiorni;
    		try {
    			numGiorni = Integer.parseInt(this.txtGiorni.getText());
    			
    			this.model.simula(numGiorni);
    			txtResult.appendText("\n\nAttori Intervistati : \n");
    			for(Actor a : this.model.getIntervistati()) {
    				txtResult.appendText(a+"\n");
    			}
    			txtResult.appendText("Numero di pause : "+this.model.numPause());
    		}catch(NumberFormatException e) {
    			e.printStackTrace();
    			txtResult.appendText("Il campo 'n' deve essere un numero intero!");
    		}
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimili != null : "fx:id=\"btnSimili\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimulazione != null : "fx:id=\"btnSimulazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGenere != null : "fx:id=\"boxGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAttore != null : "fx:id=\"boxAttore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGiorni != null : "fx:id=\"txtGiorni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxGenere.getItems().addAll(this.model.listAllGenres());
    }
}
