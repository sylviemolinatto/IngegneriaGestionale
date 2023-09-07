/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Edge;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
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

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creato grafo con "+this.model.creaGrafo().vertexSet().size()+" vertici e "+this.model.creaGrafo().edgeSet().size()+" archi");
    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {

    	txtResult.clear();
    	Genes geneSelezionato = this.cmbGeni.getValue();
    	if(geneSelezionato!=null) {
    		txtResult.appendText("Geni adiacenti a : "+geneSelezionato+"\n");
    		for(Edge e : this.model.geniAdiacenti(geneSelezionato)) {
    			txtResult.appendText(e.getG2().toString()+"  "+e.getWeight()+"\n");
    		}
    	}
    	else {
    		txtResult.appendText("Selezionare un gene");
    	}
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	int nIngegneri=0;
    	try {
    		nIngegneri= Integer.parseInt(this.txtIng.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un numero di ingegneri");
    	}
    	if(nIngegneri>0 && this.cmbGeni.getValue()!=null) {
    		Map<Genes,Integer> result = this.model.geniInStudioENumIng(nIngegneri,this.cmbGeni.getValue());
    		txtResult.appendText(result.toString());
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbGeni.getItems().addAll(this.model.getAllGenes());
    }
    
}
