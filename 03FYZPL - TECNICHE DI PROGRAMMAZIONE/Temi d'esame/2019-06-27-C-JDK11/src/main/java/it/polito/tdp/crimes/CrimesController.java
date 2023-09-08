/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Edge;
import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class CrimesController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCategoria"
    private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<LocalDate> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="boxArco"
    private ComboBox<Edge> boxArco; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Crea grafo...\n");
    	
    	String categoria = this.boxCategoria.getValue();
    	LocalDate data = this.boxGiorno.getValue();
    	
    	if(categoria==null) {
    		txtResult.appendText("Seleziona una Categoria Reato!");
    		return;
    	}
    	
    	if(data==null) {
    		txtResult.appendText("Seleziona un giorno!");
    		return;
    	}
    	
    	txtResult.appendText(this.model.creaGrafo(categoria, data));
    	txtResult.appendText("\n\npeso mediano = "+this.model.getPesoMediano()+"\n");
    	txtResult.appendText("ARCHI CON PESO INFERIORE AL PESO MEDIANO : \n");
    	for(Edge e : this.model.getArchiSottoPesoMediano()) {
    		txtResult.appendText(e+"\n");
    	}
    	
    	this.boxArco.getItems().clear();
    	this.boxArco.getItems().addAll(this.model.getArchi());
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso...\n\n");
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	 
    	Edge e = this.boxArco.getValue();
    	if(e==null) {
    		txtResult.appendText("Seleziona un arco!");
    		return;
    	}
    	
    	String primo = e.getId1();
    	String ultimo = e.getId2();
    	
    	List<String> result = this.model.calcolaPercorso(primo, ultimo);
    	int pesoTot = this.model.calcolaPeso(result);
    	
    	txtResult.appendText("Peso cammino : "+pesoTot+"\n");
    	for(String s : result) {
    		txtResult.appendText(s+"\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxCategoria.getItems().addAll(this.model.getOffenseCatgories());
    	this.boxGiorno.getItems().addAll(this.model.getDays());
    	
    }
}
