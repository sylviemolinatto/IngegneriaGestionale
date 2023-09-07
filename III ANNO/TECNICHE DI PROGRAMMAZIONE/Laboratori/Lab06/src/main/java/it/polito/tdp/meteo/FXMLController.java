/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxMese"
    private ChoiceBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnUmidita"
    private Button btnUmidita; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaSequenza(ActionEvent event) {

    	txtResult.clear();
    	int mese = this.boxMese.getValue();
    	List<Citta> result = this.model.trovaSequenza(mese);
    	txtResult.appendText("Sequenza ottima per il mese "+Integer.toString(mese)+" è: \n");
    	txtResult.appendText(result.toString());
    }

    @FXML
    void doCalcolaUmidita(ActionEvent event) {

    	txtResult.clear();
    	int mese = this.boxMese.getValue();
    	Month month = Month.of(mese);
    	List<Citta> tutteLeCitta = this.model.getAllCitta();
    	
    	for(Citta c : tutteLeCitta) {
    		txtResult.appendText("L'umidità media nel mese di "+month+" nella città di "+c+" è : "+this.model.getUmiditaMedia(mese, c.toString())+"\n");
    	}
    	
    }
    
    private void setItemsChoiceBox() {
    	
    	for(int i=1;i<13;i++) {
    		boxMese.getItems().add(i);
    	}
    	
    }

    public void setModel(Model model) {
    	
    	this.model=model;
    	setItemsChoiceBox();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }
}

