/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model ;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnContaArchi"
    private Button btnContaArchi; // Value injected by FXMLLoader

    @FXML // fx:id="btnRicerca"
    private Button btnRicerca; // Value injected by FXMLLoader

    @FXML // fx:id="txtSoglia"
    private TextField txtSoglia; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doContaArchi(ActionEvent event) {

    	txtResult.clear();
    	Double soglia;
    	
    	this.model.creaGrafo();
		txtResult.appendText("Numero vertici : "+this.model.getGrafo().vertexSet().size()+"\n");
		txtResult.appendText("Numero archi : "+this.model.getGrafo().edgeSet().size()+"\n");
        txtResult.appendText("Peso minimo = "+this.model.getPesoMinimo()+", peso massimo = "+this.model.getPesoMassimo()+"\n");
    	
    	if(!txtSoglia.getText().equals("")) {
    		
    		try {
        		soglia = Double.parseDouble(txtSoglia.getText());
        		txtResult.appendText("Numero di archi il sui peso è sopra la soglia indicata : "+this.model.getNumArchiSopraSoglia(soglia).size()+"\n");
        		txtResult.appendText("Numero di archi il cui peso è sotto la soglia indicata : "+(this.model.getGrafo().edgeSet().size()-this.model.getNumArchiSopraSoglia(soglia).size()));
        		
        		
        	}catch(NumberFormatException e) {
        		txtResult.setText("Il valore soglia deve essere un numero");
        		e.printStackTrace();
        	}
    	}
    	
    	
    }

    @FXML
    void doRicerca(ActionEvent event) {

    	txtResult.clear();
    	double soglia;
    	try {
    		soglia = Double.parseDouble(txtSoglia.getText());
    		List<Integer> cammino = this.model.visitaGrafo(soglia);
    		List<DefaultWeightedEdge> archi = new LinkedList<DefaultWeightedEdge>();
    		for(int i=0; i<cammino.size()-1;i++) {
    			archi.add(i,this.model.getEdge(cammino.get(i), cammino.get(i+1)));
    		}
    		txtResult.appendText(cammino.toString()+"\n");
    		
    		for(DefaultWeightedEdge e : archi) {
    			txtResult.appendText(e+" weight = "+this.model.getWeight(e)+"\n");
    		}
   
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserisci un valore soglia numerico");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnContaArchi != null : "fx:id=\"btnContaArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSoglia != null : "fx:id=\"txtSoglia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		
	}
}
