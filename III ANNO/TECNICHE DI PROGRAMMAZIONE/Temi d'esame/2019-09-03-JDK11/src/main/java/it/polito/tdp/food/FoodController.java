/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.food.model.Edge;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Portion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...\n\n");
    	
    	if(this.boxPorzioni.getValue()==null) {
    		txtResult.appendText("Seleziona una porzione!");
    		return;
    	}
    	else if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	else {
    		int lunghezza;
    		try {
    			lunghezza = Integer.parseInt(this.txtPassi.getText());
    			List<String> result = this.model.cercaSequenza(lunghezza, this.boxPorzioni.getValue());
    			
    			for(String s : result){
    				txtResult.appendText(s+"\n");
    			}
    			
    			txtResult.appendText("Peso totale : "+this.model.getPeso());
    		}catch(NumberFormatException e) {
    			e.printStackTrace();
    			txtResult.appendText("Il campo 'Passi' deve essere un intero");
    		}
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n\n");
    	
    	String porzione = this.boxPorzioni.getValue();
    	
    	if(porzione==null) {
    		txtResult.appendText("Seleziona una porzione!");
    		return;
    	}
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!\n");
    	}
    	else {
    		List<String> vicini = this.model.getVicini(porzione);
    		Graph<String,DefaultWeightedEdge> grafo = this.model.getGrafo();
    		for(String s : vicini) {
    			txtResult.appendText(porzione+"-"+s+" ("+grafo.getEdgeWeight(grafo.getEdge(porzione, s))+")\n");
    		}
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n\n");
    	
    	int C;
    	
    	try {
    		C = Integer.parseInt(this.txtCalorie.getText());
    		
    		this.model.creaGrafo(C);
    		txtResult.appendText("GRAFO CREATO : "+this.model.getNVertici()+" vertici, "+this.model.getNArchi()+" archi\n\n");
    		
    		this.boxPorzioni.getItems().clear();
    		this.boxPorzioni.getItems().addAll(this.model.getVertici());
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Il campo 'calorie' deve essere un intero!");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
