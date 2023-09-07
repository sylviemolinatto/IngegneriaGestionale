/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;



import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.itunes.model.Edge;
import it.polito.tdp.itunes.model.Genre;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.Track;
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

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="btnMassimo"
    private Button btnMassimo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCanzone"
    private ComboBox<Track> cmbCanzone; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGenere"
    private ComboBox<Genre> cmbGenere; // Value injected by FXMLLoader

    @FXML // fx:id="txtMemoria"
    private TextField txtMemoria; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void btnCreaLista(ActionEvent event) {

        Long bytes;
        txtResult.clear();
        
        try {
        	bytes = Long.parseLong(this.txtMemoria.getText());
        	Track trackScelta = this.cmbCanzone.getValue();
        	if(trackScelta!=null) {
        		List<Track> result = this.model.init(trackScelta, bytes);
        		txtResult.appendText(result.toString()+" - "+this.model.getCapacita());
        	}
        }catch(NumberFormatException e) {
        	e.printStackTrace();
        	txtResult.appendText("Il campo 'Memoria' deve essere un numero intero");	
        }
        
        // pulisco tutte le comboBox
        this.cmbCanzone.getItems().clear();;
        this.cmbGenere.setValue(null);
        this.txtMemoria.clear();
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	txtResult.clear();
    	if(this.cmbGenere.getValue()!=null) {
    		Graph<Track,DefaultWeightedEdge> grafo = this.model.creaGrafo(this.cmbGenere.getValue());
    		txtResult.appendText("Grafo creato!\n");
    		txtResult.appendText(" # VERTICI : "+grafo.vertexSet().size()+"\n");
    		txtResult.appendText(" # ARCHI : "+grafo.edgeSet().size()+"\n");
    		this.cmbCanzone.getItems().clear();
    		this.cmbCanzone.getItems().addAll(this.model.creaGrafo(this.cmbGenere.getValue()).vertexSet());
    	}
    	else
    		txtResult.appendText("Errore : selezionare un genere");
    }

    @FXML
    void doDeltaMassimo(ActionEvent event) {
    	txtResult.clear();
    	if(this.cmbGenere.getValue()!=null) {
    		List<Edge> result = this.model.getCanzoniDifferenzaDurataMassima(this.cmbGenere.getValue());
    		Map<Integer,Track> idMap = this.model.getIdMap();
    		
    		for(Edge e : result) {
    	    		txtResult.appendText(idMap.get(e.getTrackID1())+" - "+idMap.get(e.getTrackID2())+" , "+e.getWeight()+"\n");
    		}
    	}
    	else
    		txtResult.appendText("Errore : selezionare un genere");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMassimo != null : "fx:id=\"btnMassimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCanzone != null : "fx:id=\"cmbCanzone\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGenere != null : "fx:id=\"cmbGenere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMemoria != null : "fx:id=\"txtMemoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbGenere.getItems().addAll(this.model.getGenre());
    }

}
