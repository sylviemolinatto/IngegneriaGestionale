package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Artist;
import it.polito.tdp.artsmia.model.Edge;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola artisti connessi\n\n");
    	
    	if(!this.model.grafoCreato()) {
    		txtResult.appendText("Crea prima il grafo!");
    		return;
    	}
    	else {
    		for(Edge e : this.model.getArchi()) {
    			txtResult.appendText(e+"\n");
    		}
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso\n\n");
    	
    	int artist_id;
    	
    	try {
    		artist_id = Integer.parseInt(this.txtArtista.getText());
    		if(!this.model.grafoCreato()) {
    			txtResult.appendText("Crea prima il grafo!");
    			return;
    		}
    		
    		if(!this.model.idEsistente(artist_id)) {
    			txtResult.appendText("Crea prima il grafo!");
    			return;
    		}
    		
    		List<Artist> result = this.model.cercaPercorso(artist_id);
    		txtResult.appendText("PERCORSO MASSIMO : \n");
    		for(Artist a : result) {
    			txtResult.appendText(a+"\n");
    		}
    		txtResult.appendText("Numero esposizioni : "+this.model.getNumeroEsposizioni());
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("Inserisci un id di artista valido!");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Crea grafo\n\n");
    	
    	String ruolo = this.boxRuolo.getValue();
    	if(ruolo==null) {
    		txtResult.appendText("Seleziona un ruolo!");
    		return;
    	}
    	
    	txtResult.appendText(this.model.creaGrafo(ruolo));
    	
    }

    public void setModel(Model model) {
    	this.model = model;
    	this.boxRuolo.getItems().addAll(this.model.getRoles());
    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
}
