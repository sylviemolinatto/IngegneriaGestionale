/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Edge;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnConnessioneMassima"
    private Button btnConnessioneMassima; // Value injected by FXMLLoader

    @FXML // fx:id="btnCollegamento"
    private Button btnCollegamento; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinuti"
    private TextField txtMinuti; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMese"
    private ComboBox<Month> cmbMese; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM1"
    private ComboBox<Match> cmbM1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM2"
    private ComboBox<Match> cmbM2; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doConnessioneMassima(ActionEvent event) {
    	
    	txtResult.clear();
    	int minutiMin;
    	int mese;
    	if(this.cmbMese.getValue()!=null) {
    		mese = this.cmbMese.getValue().getValue();
    	}
    	else {
    		txtResult.appendText("Seleziona un mese!");
    		return;
    	}
    	
    	try {
    		minutiMin = Integer.parseInt(this.txtMinuti.getText());
    		if(!this.model.grafoCreato()) {
    			txtResult.appendText("Crea prima il grafo!");
    			return;
    		}
    		List<Edge> risultato = this.model.getCoppieConnessioneMax(mese, minutiMin);
    		for(Edge e : risultato) {
    			txtResult.appendText(e+" ("+e.getWeight()+")\n");
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Inserisci un numero intero nel campo 'MIN'");
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	int minutiMin;
    	int mese;
    	if(this.cmbMese.getValue()!=null) {
    		mese = this.cmbMese.getValue().getValue();
    	}
    	else {
    		txtResult.appendText("Seleziona un mese!");
    		return;
    	}
    	
    	try {
    		minutiMin = Integer.parseInt(this.txtMinuti.getText());
    		this.model.creaGrafo(mese, minutiMin);
    		txtResult.appendText("Grafo creato!\n");
    		txtResult.appendText("#VERTICI : "+this.model.nVertici()+"\n");
    		txtResult.appendText("#ARCHI : "+this.model.nArchi()+"\n");
    		
    		this.cmbM1.getItems().addAll(this.model.getMatches(mese));
    		this.cmbM2.getItems().addAll(this.model.getMatches(mese));
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Inserisci un numero intero nel campo 'MIN'");
    	}
    	
    }

    @FXML
    void doCollegamento(ActionEvent event) {
    	
    	txtResult.clear();
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!\n");
    		return;
    	}
    	
    	Match m1 = this.cmbM1.getValue();
    	Match m2 = this.cmbM2.getValue();
    	
    	if(m1==null || m2==null) {
    		txtResult.appendText("Seleziona entrambi i campi 'm1' e 'm2'");
    		return;
    	}
    	
    	if(m1.equals(m2)) {
    		txtResult.appendText("Seleziona due match diversi!");
    		return;
    	}
    	
    	List<Match> sequenza = this.model.cercaSequenza(m1, m2);
    	
    	if(sequenza==null) {
    		txtResult.appendText("Non ci sono cammini tra i due vertici selezionati\n");
    		return;
    	}
    
    	List<Edge> sequenzaArchi = this.model.getArchiSequenza();
    	for(Edge e : sequenzaArchi) {
    		txtResult.appendText(e+" ("+e.getWeight()+")\n");
    	}
    	
    	txtResult.appendText("Il peso totale del cammino è : "+this.model.getPesoSequenza());
    	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConnessioneMassima != null : "fx:id=\"btnConnessioneMassima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCollegamento != null : "fx:id=\"btnCollegamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMinuti != null : "fx:id=\"txtMinuti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMese != null : "fx:id=\"cmbMese\" was not injected: check your FXML file 'Scene.fxml'.";        assert cmbM1 != null : "fx:id=\"cmbM1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbM2 != null : "fx:id=\"cmbM2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbMese.getItems().addAll(Month.values());
    	
    }
    
    
}
