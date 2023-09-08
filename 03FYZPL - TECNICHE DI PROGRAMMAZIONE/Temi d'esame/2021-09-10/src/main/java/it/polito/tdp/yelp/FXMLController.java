/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.yelp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Edge;
import it.polito.tdp.yelp.model.Model;
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

    @FXML // fx:id="btnDistante"
    private Button btnDistante; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcolaPercorso"
    private Button btnCalcolaPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtX2"
    private TextField txtX2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta"
    private ComboBox<String> cmbCitta; // Value injected by FXMLLoader

    @FXML // fx:id="cmbB1"
    private ComboBox<Business> cmbB1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbB2"
    private ComboBox<Business> cmbB2; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	String city = this.cmbCitta.getValue();
    	if(city==null) {
    		txtResult.setText("Seleziona una città!");
    		return;
    	}
    	this.model.creaGrafo(city);
    	this.cmbB1.getItems().clear();
    	this.cmbB1.getItems().addAll(this.model.getVertici());
    	this.cmbB2.getItems().clear();
    	this.cmbB2.getItems().addAll(this.model.getVertici());
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("# VERTICI : "+this.model.getNVertici()+"\n");
    	txtResult.appendText("# ARCHI : "+this.model.getNArchi()+"\n");
    }

    @FXML
    void doCalcolaLocaleDistante(ActionEvent event) {

    	txtResult.clear();
    	Business b1 = this.cmbB1.getValue();
    	if(!this.model.grafoCreato()) {
    		txtResult.setText("Prima crea il grafo!");
    	}
    	else if(b1 == null) {
    		txtResult.setText("Seleziona un locale!");
    	}
    	else {
    		Edge e = this.model.getLocalePiuDistante(b1);
    		txtResult.appendText("LOCALE PIU' DISTANTE\n");
    		txtResult.appendText(e.getBusiness_2()+" = "+e.getWeight()+"\n");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {

    	txtResult.clear();
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.setText("Prima crea il grafo!");
    		return;
    	}
    	
    	String city = this.cmbCitta.getValue();
    	
    	Business localeIniziale = this.cmbB1.getValue();
    	Business localeFinale = this.cmbB2.getValue();
    	
    	if(localeIniziale==null || localeFinale==null) {
    		txtResult.setText("Selezionare entrambi i locali!");
    	    return;
    	}
    	
    	double soglia;
    	
    	try {
    		soglia = Double.parseDouble(this.txtX2.getText());
    		if(soglia!=0) {
    			if(!localeIniziale.equals(localeFinale)) {
        			List<Business> result = this.model.init(city, soglia, localeIniziale, localeFinale);
            		txtResult.appendText(result.toString()+"\n");
            		txtResult.appendText("Chilometri percorsi : "+this.model.getKilometres()+" km ");
        		} 
        		else {
        			txtResult.appendText("Seleziona due locali diversi!");
        			return;
        		}
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Il campo 'soglia' deve essere un numero!");
    		return;
    	}
    	
    	
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDistante != null : "fx:id=\"btnDistante\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX2 != null : "fx:id=\"txtX2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCitta != null : "fx:id=\"cmbCitta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbB1 != null : "fx:id=\"cmbB1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbB2 != null : "fx:id=\"cmbB2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbCitta.getItems().addAll(this.model.getCities());
    }
}
