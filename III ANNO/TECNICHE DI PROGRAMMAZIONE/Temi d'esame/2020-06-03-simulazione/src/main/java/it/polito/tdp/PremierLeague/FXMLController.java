/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.PremierLeague.model.Edge;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML // fx:id="btnTopPlayer"
    private Button btnTopPlayer; // Value injected by FXMLLoader

    @FXML // fx:id="btnDreamTeam"
    private Button btnDreamTeam; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="txtGoals"
    private TextField txtGoals; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	txtResult.clear();
    	double goalFatti;
    	try {
    		goalFatti=Double.parseDouble(this.txtGoals.getText());
    		Graph<Player,DefaultWeightedEdge> grafo = this.model.creaGrafo(goalFatti);
    		txtResult.appendText("Grafo creato!\n");
    		txtResult.appendText("# VERTICI : "+grafo.vertexSet().size()+"\n");
    		txtResult.appendText("# ARCHI : "+grafo.edgeSet().size()+"\n");
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Il campo 'Goal fatti' deve essere un numero");
    	}
    	
    }

    @FXML
    void doDreamTeam(ActionEvent event) {

    	txtResult.clear();
    	int numGiocatori;
    	
    	try {
    		numGiocatori = Integer.parseInt(this.txtK.getText());
    		List<Player> result = this.model.cercaDreamTeam(numGiocatori);
    		txtResult.appendText("DREAM TEAM - grado di titolarità : "+this.model.calcolaGradoDiTitolarita(result)+"\n\n");
    		for(Player p : result) {
    			txtResult.appendText(p.getPlayerID()+" "+p.getName()+"\n");
    		}
    		
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Errore : il valore k deve essere un numero intero");
    	}
    }

    @FXML
    void doTopPlayer(ActionEvent event) {
    	
    	txtResult.clear();
    	double goalFatti;
    	try {
    		goalFatti=Double.parseDouble(this.txtGoals.getText());
    		Graph<Player,DefaultWeightedEdge> grafo = this.model.creaGrafo(goalFatti);
    		Player topPlayer = this.model.getTopPlayer();
    		List<Edge> avversari = this.model.getAvversariBattuti();
    		Collections.sort(avversari);
    		txtResult.appendText("TOP PLAYER : "+topPlayer+"\n\n");
    		txtResult.appendText("AVVERSARI BATTUTI\n");
    		Map<Integer,Player> idMap = this.model.getIdMap();
    		for(Edge e : avversari) {
    			txtResult.appendText(idMap.get(e.getP2())+" | "+e.getWeight()+"\n");
    		}
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.setText("Il campo 'Goal fatti' deve essere un numero");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTopPlayer != null : "fx:id=\"btnTopPlayer\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGoals != null : "fx:id=\"txtGoals\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
