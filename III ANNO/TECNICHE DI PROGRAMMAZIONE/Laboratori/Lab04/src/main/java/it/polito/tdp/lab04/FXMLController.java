/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<Corso> corsi;
	private List<Studente> studenti;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaNomeCognome"
    private Button btnCercaNomeCognome; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxCorsi"
    private ComboBox<Corso> comboBoxCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML
    void CercaCorsi(ActionEvent event) {

    	Corso cc = new Corso("",0,"",0);
    	comboBoxCorsi.setValue(cc);
    	txtArea.clear();
    	
    	Integer matricola = Integer.parseInt(txtMatricola.getText());
    	this.studenti = this.model.getTuttiGliStudenti();
    	
    	if(this.model.getStudente(matricola)==null) {
    		txtArea.setText("Matricola inesistente");
    	}
    	
    	else {
    		List<Corso> corsiStudente = this.model.getCorsiStudente(matricola);
    		StringBuilder sb = new StringBuilder();
    		for(Corso c : corsiStudente) {
    			sb.append(String.format("%-10s ", c.getCodins()));
    			sb.append(String.format("%-5s ", c.getCrediti()));
    			sb.append(String.format("%-50s ", c.getNome()));
    			sb.append(String.format("%-5s", c.getPd()));
    			sb.append("\n");
    			
    		}
    		
    		txtArea.appendText(sb.toString());
    	}
    	
    }

    @FXML
    void CercaIscrittiCorso(ActionEvent event) {

    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtArea.clear();
    	Corso corsoSelezionato = comboBoxCorsi.getValue();
    	
    	if(corsoSelezionato.equals("")) {
    		txtArea.setText("Nessun corso selezionato");
    	}
    	else {
    	
    		List<Studente> studentiIscrittiAlCorso = this.model.getStudentiIscrittiAlCorso(corsoSelezionato);
    		StringBuilder sb = new StringBuilder();
    		for(Studente s : studentiIscrittiAlCorso) {
    			sb.append(String.format("%-10s ", s.getMatricola()));
    			sb.append(String.format("%-20s ", s.getCognome()));
    			sb.append(String.format("%-20s ", s.getNome()));
    			sb.append(String.format("%-10s ", s.getCDS()));
    			sb.append("\n");   			
    		}
    		txtArea.appendText(sb.toString());
    	}
    }

    @FXML
    void Iscrivi(ActionEvent event) {
    	
    	txtArea.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	boolean isIscritto=false;
    	
    	Corso corso = comboBoxCorsi.getValue();
    	Integer matricola = Integer.parseInt(txtMatricola.getText());
    	Studente studente = this.model.getStudente(matricola);
    	
    	if(studente==null) {
    		txtArea.setText("Matricola inesistente");
    	}
    	else {
    		if(this.model.studenteIsIscritto(studente, corso)) {
    			txtArea.setText("Lo studente con matricola "+matricola+" è già iscritto al corso selezionato");
    		}
    		else {
    			if(this.model.IscriviStudenteACorso(studente, corso)==true) {
    				txtArea.setText("Studente iscritto al corso!");
    			}
    			else {
    				txtArea.setText("Errore nell'iscrizione dello studente al corso");
    			}
    		}
    	}

    }

    @FXML
    void Reset(ActionEvent event) {

    	txtArea.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	Corso cc = new Corso("",0,"",0);
    	comboBoxCorsi.setValue(cc);
    	
    }
    
    @FXML
    void cercaNomeCognome(ActionEvent event) {
    	
    	txtArea.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	Corso c = new Corso("",0,"",0);
    	comboBoxCorsi.setValue(c);
    	
    	Integer matricola=null;
    	
    	try {
    		 matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(NumberFormatException e) {
    		System.out.println("ERRORE : Matricola deve essere un intero");
    		e.printStackTrace();
    		
    	}
    	
    	String nome = this.model.getNomeStudente(matricola);
    	String cognome = this.model.getCognomeStudente(matricola);
    	
    	if(nome==null || cognome==null) {
    		txtArea.setText("Matricola inesistente");
    		return;
    	}
    	else {
    		txtNome.setText(nome);
    		txtCognome.setText(cognome);
    	}
    	

    }
    
    private void setComboBoxItems() {
    	Corso c = new Corso("",0,"",0);
    	comboBoxCorsi.getItems().add(c);
    	corsi = model.getTuttiICorsi();
    	Collections.sort(corsi);
    	comboBoxCorsi.getItems().addAll(corsi);
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	setComboBoxItems();
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNomeCognome != null : "fx:id=\"btnCercaNomeCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";  
       
    }

}
