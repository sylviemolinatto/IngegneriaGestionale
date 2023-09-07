/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.yelp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Edge;
import it.polito.tdp.yelp.model.Model;
import it.polito.tdp.yelp.model.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnMiglioramento"
    private Button btnMiglioramento; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta"
    private ComboBox<String> cmbCitta; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLocale"
    private ComboBox<Business> cmbLocale; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doRiempiLocali(ActionEvent event) {
    	this.cmbLocale.getItems().clear();
    	String citta = this.cmbCitta.getValue();
    	if(citta != null) {
    		//TODO popolare la tendina dei locali per la città selezionata
    		this.cmbLocale.getItems().addAll(this.model.getBusinessByCity(citta));
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	String citta = this.cmbCitta.getValue();
    	Business business = this.cmbLocale.getValue();
    	
    	if(citta!=null & business!=null) {
    		this.model.creaGrafo(business);
    		txtResult.appendText("Grafo creato : "+this.model.getNVertici()+" vertici, "+this.model.getNArchi()+" archi\n\n");
    		txtResult.appendText("Recensione/i con maggior numero di archi uscenti : \n");
    		for(Review r : this.model.getMaxArchiUscenti()) {
    			txtResult.appendText(r.getReviewId()+ "   # ARCHI USCENTI : "+this.model.getMax()+"\n");
    		}
    	}
    	else {
    		txtResult.appendText("Selezionare una città e un locale!");
    		return;
    	}
    }

    @FXML
    void doTrovaMiglioramento(ActionEvent event) {
    	
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    	}
    	else {
    		List<Review> result = this.model.cercaSequenza();
    		int numGiorni = this.model.calcolaNumGiorni();
    		txtResult.appendText("\n\nSEQUENZA MIGLIORE : \n");
    		for(Review r : result) {
    			txtResult.appendText(r.getReviewId()+"\n");
    		}
    		txtResult.appendText("Numero di giorni : "+numGiorni);
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMiglioramento != null : "fx:id=\"btnMiglioramento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCitta != null : "fx:id=\"cmbCitta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLocale != null : "fx:id=\"cmbLocale\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbCitta.getItems().addAll(this.model.getAllCities());
    }
}
