/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Edge;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
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

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n\n");
    	
    	int numPorzioni;
    	try {
    		numPorzioni=Integer.parseInt(this.txtPorzioni.getText());
    		txtResult.appendText(this.model.creaGrafo(numPorzioni));
    		this.boxFood.getItems().clear();
    		this.boxFood.getItems().addAll(this.model.getVertici());
    		if(numPorzioni==0) {
    			txtResult.appendText("Il campo 'porzioni' deve essere un intero positivo");
    			return;
    		}
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Il campo 'porzioni' deve essere un intero positivo");
    	}
    	
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Analisi calorie...\n\n");
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	else {
    		Food f = this.boxFood.getValue();
    		if(f==null) {
    			txtResult.appendText("Seleziona un cibo!");
    			return;
    		}
    		else {
    			List<Edge> archiAdiacenti = this.model.getMaxCalorieCongiunte(f);
    			if(archiAdiacenti.size()>5) {
    				txtResult.appendText("ARCHI ADIACENTI CON IL MASSIMO DI 'CALORIE CONGIUNTE' : \n");
    				for(int i=0; i<5; i++) {
    					txtResult.appendText(archiAdiacenti.get(i).getF2()+" calorie congiunte : "+archiAdiacenti.get(i).getWeight()+"\n");
    				}
    				
    			}
    			else {
    				for(Edge e : archiAdiacenti) {
    					txtResult.appendText(e.getF2()+" calorie congiunte : "+e.getWeight()+"\n");
    				}
    			}
    		}
    		
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...\n\n");
    	
    	int K;
    	try {
    		K = Integer.parseInt(this.txtK.getText());
    		if(K<=0 || K>10) {
    			txtResult.appendText("Il campo 'K' deve essere un interno positivo compreso tra 1 e 10!");
    			return;
    		}
    		if(!this.model.grafoCreato()) {
    			txtResult.appendText("Crea prima il grafo!");
        		return;
    		}
    		if(this.boxFood.getValue()==null) {
    			txtResult.appendText("Seleziona un cibo!");
    			return;
    		}
    		
    		double tempo = this.model.simula(K, this.boxFood.getValue());
    		List<Food> cibi = this.model.getCibiPreparati();
    		
    		txtResult.appendText("Tempo totale : "+tempo+" minuti\n\n");
    		txtResult.appendText("Cibi preparati : \n");
    		for(Food f : cibi) {
    			txtResult.appendText(f.getDisplay_name()+"\n");
    		}
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Il campo 'K' deve essere un interno positivo!");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
