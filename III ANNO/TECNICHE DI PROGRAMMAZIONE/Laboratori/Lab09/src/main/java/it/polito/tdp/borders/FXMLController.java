
package it.polito.tdp.borders;


import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.FXCollections;
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

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnCalcolaConfini"
    private Button btnCalcolaConfini; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxStati"
    private ComboBox<Country> comboBoxStati; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {

    	txtResult.clear();
    	String anno = txtAnno.getText();
    	int annoInt=0;
    	try {
    		annoInt=Integer.parseInt(anno);
    		if(annoInt>=1816 && annoInt<=2016) {
    			// txtResult.setText("Correct");
    			Graph grafo = this.model.creaGrafo(annoInt);
    			Set<Country> vertici = grafo.vertexSet();
    			txtResult.appendText("Numero di componenti connesse nel grafo : " +this.model.getNumberOfConnectedComponents()+"\n");
    			for(Country c : vertici) {
    				txtResult.appendText(c+" ("+this.model.degreeOf(grafo, c)+")\n");
    			}
    		}
    		else {
    			txtResult.setText("ERRORE : inserire un numero compreso tra 1816 e 2016");
    		}
    	}catch(NumberFormatException e) {
    		txtResult.setText("ERRORE : inserire un numero compreso tra 1816 e 2016");
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void calcolaPercorso(ActionEvent event) {
    	
    	txtAnno.clear();
    	txtResult.clear();
    	Country countrySelected = this.comboBoxStati.getValue();
    	if(countrySelected!=null) {
    		List<Country> visitati = this.model.visitaGrafoRicorsiva(countrySelected);
    		Collections.sort(visitati);
    		txtResult.appendText("Reachable countries : "+visitati.size()+"\n");
    		for(Country c : visitati) {
    			txtResult.appendText(c+"\n");
    		}
    		
    	}
    	
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaConfini != null : "fx:id=\"btnCalcolaConfini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBoxStati != null : "fx:id=\"comboBoxStati\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.comboBoxStati.setItems(FXCollections.observableArrayList(this.model.getCountries()));
    	
    }
}
