/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Libretto model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbPunti"
    private ComboBox<Integer> cmbPunti; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtVoti"
    private TextArea txtVoti; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtStatus"
    private Label txtStatus; // Value injected by FXMLLoader

    @FXML
    void handleNuovoVoto(ActionEvent event) {

    	// 1. acquisizione e controllo dati
    	String nome = txtNome.getText();
    	Integer punti = cmbPunti.getValue();
    	
    	// controlli di validità
    	if(nome.equals("")|| punti==null) {
    		// errore, non posso eseguire l'operazione
    		txtStatus.setText("ERRORE: occorre inserire nome e voto\n");
    		return;
    	}
    	
    	// 2. esecuzione dell'operazione (== chiedere al Model di farla)
    	boolean ok = model.add(new Voto(nome, punti));
    		
    	
    	// 3. visualizzazione/aggiornamento del risultato
    	if(ok==true) {
    		List<Voto> voti = model.getVoti();
        	txtVoti.clear();
        	txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
        	for(Voto v : voti) {
        		txtVoti.appendText(v.toString()+"\n");
        	}
        	txtNome.clear();
        	cmbPunti.setValue(null);
        	txtStatus.setText("");
    	}
    	else {
    		txtStatus.setText("ERRORE: esame già presente");
    	}
    	
    	
    	
    }
    
    public void setModel(Libretto model) {
    	this.model=model;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbPunti.getItems().clear();
        for(int p=18; p<=30; p++) {
        	cmbPunti.getItems().add(p);
        }
    }

}
