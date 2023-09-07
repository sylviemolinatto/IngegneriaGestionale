/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.imdb;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.imdb.model.Director;
import it.polito.tdp.imdb.model.Edge;
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

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaAffini"
    private Button btnCercaAffini; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxRegista"
    private ComboBox<Director> boxRegista; // Value injected by FXMLLoader

    @FXML // fx:id="txtAttoriCondivisi"
    private TextField txtAttoriCondivisi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	txtResult.clear();
    	int anno = this.boxAnno.getValue();
    	
    	if(anno==0) {
    		txtResult.appendText("Selezionare un anno!\n");
    		return;
    	}
    	
    	txtResult.appendText(this.model.creaGrafo(anno)+"\n\n");
    	this.boxRegista.getItems().clear();
    	this.boxRegista.getItems().addAll(this.model.getDirectors());
    }

    @FXML
    void doRegistiAdiacenti(ActionEvent event) {
    	
    	Director d = this.boxRegista.getValue();
    	if(d==null) {
    		txtResult.appendText("Seleziona un regista!");
    		return;
    	}
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	
    	txtResult.appendText("REGISTI ADIACENTI A : "+d+"\n");
    	List<Edge> adiacenti = this.model.getAdiacenti(d);
    	for(Edge e : adiacenti) {
    		txtResult.appendText(e.getD2()+" - # attori condivisi : "+e.getWeight()+"\n");
    	}
    }

    @FXML
    void doRicorsione(ActionEvent event) {

    	int c;
    	try {
    		c = Integer.parseInt(this.txtAttoriCondivisi.getText());
    		if(!this.model.grafoCreato()) {
        		txtResult.appendText("Crea prima il grafo!");
        		return;
        	}
    		Director d = this.boxRegista.getValue();
        	if(d==null) {
        		txtResult.appendText("Seleziona un regista!");
        		return;
        	}
        	
        	this.model.cercaSequenza(d, c);
        	
        	txtResult.appendText("PERCORSO : \n");
        	for(Edge e : this.model.getPercorso()) {
        		txtResult.appendText(e.getD1()+" - "+ e.getD2()+" # attori condivisi : "+e.getWeight()+"\n");
        	}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Il campo 'c' deve essere un numero intero positivo!");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaAffini != null : "fx:id=\"btnCercaAffini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxRegista != null : "fx:id=\"boxRegista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAttoriCondivisi != null : "fx:id=\"txtAttoriCondivisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
   public void setModel(Model model) {
    	
    	this.model = model;
    	
    	List<Integer> anni = new ArrayList<Integer>();
    	
    	for(int anno=2004; anno<=2006; anno++) {
    		anni.add(anno);
    	}
    	
    	this.boxAnno.getItems().addAll(anni);
    	
    }
    
}
