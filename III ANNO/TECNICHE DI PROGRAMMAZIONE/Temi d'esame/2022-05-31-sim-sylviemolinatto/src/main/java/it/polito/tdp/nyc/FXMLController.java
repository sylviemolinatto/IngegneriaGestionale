/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.nyc;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.nyc.model.Edge;
import it.polito.tdp.nyc.model.Hotspot;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.Operatore;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="cmbProvider"
    private ComboBox<String> cmbProvider; // Value injected by FXMLLoader

    @FXML // fx:id="cmbQuartiere"
    private ComboBox<String> cmbQuartiere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="clQuartiere"
    private TableColumn<Edge, String> clQuartiere; // Value injected by FXMLLoader
 
    @FXML // fx:id="clDistanza"
    private TableColumn<Edge, Double> clDistanza; // Value injected by FXMLLoader
    
    @FXML // fx:id="tblQuartieri"
    private TableView<Edge> tblQuartieri; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	tblQuartieri.getItems().clear();
    	
    	if(this.cmbProvider.getValue()!=null) {
    		this.model.creaGrafo(this.cmbProvider.getValue());
    		this.txtResult.appendText("GRAFO CREATO!\n");
    		this.txtResult.appendText("# VERTICI : "+this.model.nVertici()+"\n");
    		this.txtResult.appendText("# ARCHI : "+this.model.nArchi()+"\n");
    		this.cmbQuartiere.getItems().clear();
    		this.cmbQuartiere.getItems().addAll(this.model.getVertici(this.cmbProvider.getValue()));
    	}
    	
    }

    @FXML
    void doQuartieriAdiacenti(ActionEvent event) {
    	
    	txtResult.clear();
    	tblQuartieri.getItems().clear();
    	if(this.cmbQuartiere.getValue()!=null && this.cmbProvider!=null) {
    		if(this.model.grafoCreato()) {
    			List<Edge> adiacenti = this.model.getQuartieriAdiacenti(this.cmbQuartiere.getValue());
        		Collections.sort(adiacenti);
        		Graph<String,DefaultWeightedEdge> grafo = this.model.creaGrafo(this.cmbProvider.getValue());
        	
        		this.tblQuartieri.setItems(FXCollections.observableArrayList(adiacenti));
    		}
    		else {
    			txtResult.appendText("Crea il grafo prima!");
    		}	
          
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    	txtResult.clear();
    	tblQuartieri.getItems().clear();
    	if(this.cmbQuartiere.getValue()!=null && this.cmbProvider!=null && !this.txtMemoria.getText().equals("")) {
    		if(this.model.grafoCreato()) {
    			try {
    				Integer numTecnici = Integer.parseInt(this.txtMemoria.getText());
    				this.model.simula(numTecnici, this.cmbQuartiere.getValue(),this.cmbProvider.getValue());
    				txtResult.appendText("Durata totale del processo :" +this.model.simula(numTecnici,this.cmbQuartiere.getValue(),this.cmbProvider.getValue())+" minuti \n");
    				for(Operatore o : this.model.getHotspotRevisionatiDaOperatori(numTecnici, this.cmbQuartiere.getValue(),this.cmbProvider.getValue()).values()) {
    					txtResult.appendText(o+"\n");
    				}
    				
    			}catch(NumberFormatException e) {
    				e.printStackTrace();
    				txtResult.appendText("Nel campo 'Tecnici' devi inserire un numero intero positivo!");
    			}
    			
    		}
    		else {
    			txtResult.appendText("Crea il grafo prima!");
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbProvider != null : "fx:id=\"cmbProvider\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbQuartiere != null : "fx:id=\"cmbQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clDistanza != null : "fx:id=\"clDistanza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clQuartiere != null : "fx:id=\"clQuartiere\" was not injected: check your FXML file 'Scene.fxml'.";
        this.clQuartiere.setCellValueFactory(new PropertyValueFactory<Edge,String>("city2"));
        this.clDistanza.setCellValueFactory(new PropertyValueFactory<Edge,Double>("weight"));
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbProvider.getItems().addAll(this.model.getProvider());
    }

}
