/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
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

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

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
    	
    	String categoriaReato = this.boxCategoria.getValue();
    	if(this.boxMese.getValue()==null) {
    		txtResult.appendText("Seleziona un mese!");
    		return;
    	}
    	
    	int mese = this.boxMese.getValue();
    	
    	if(categoriaReato==null) {
    		txtResult.appendText("Seleziona una categoria!");
    		return;
    	}
    	
    	txtResult.appendText(this.model.creaGrafo(categoriaReato, mese)+"\n");
    	txtResult.appendText("PESO MEDIO : "+this.model.getPesoMedioGrafo()+"\n");
    	txtResult.appendText("Archi con peso sopra la media : \n");
    	for(Edge e : this.model.getArchiSopraLaMedia()) {
    		txtResult.appendText(e+" = "+e.getWeight()+"\n");
    	}
    	
    	this.boxArco.getItems().clear();
    	this.boxArco.getItems().addAll(this.model.getArchi());
    }
    
    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso...\n");
    	
    	Edge e = this.boxArco.getValue();
    	if(e==null) {
    		txtResult.appendText("Seleziona un arco!");
    		return;
    	}
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	
    	List<String> result = this.model.trovaPercorso(e.getId1(), e.getId2());
    	List<Edge> percorso = this.model.getPercorso();
    	
    	txtResult.appendText("PERCORSO : \n");
    	for(Edge ed : percorso) {
    		txtResult.appendText(ed+" = "+ed.getWeight()+"\n");
    	}
    	txtResult.appendText("Numero di vertici toccati : "+result.size());
    	
    	
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxCategoria.getItems().addAll(this.model.getCategorie());
    	this.boxMese.getItems().addAll(this.model.getMesi());
    }
}
