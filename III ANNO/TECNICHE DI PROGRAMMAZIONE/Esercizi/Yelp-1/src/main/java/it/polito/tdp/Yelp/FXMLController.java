/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.Yelp;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.Yelp.model.Business;
import it.polito.tdp.Yelp.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="colName"
    private TableColumn<Business,String> colName; // Value injected by FXMLLoader

    @FXML // fx:id="colStars"
    private TableColumn<Business,Double> colStars; // Value injected by FXMLLoader

    @FXML // fx:id="tblBusiness"
    private TableView<Business> tblBusiness; // Value injected by FXMLLoader

    @FXML
    void handleLoadData(ActionEvent event) {

    	tblBusiness.setItems(FXCollections.observableArrayList(model.getBusinesses()));
    }
    
    public void setModel(Model model) {
    	this.model=model;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colStars != null : "fx:id=\"colStars\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblBusiness != null : "fx:id=\"tblBusiness\" was not injected: check your FXML file 'Scene.fxml'.";
        
        colName.setCellValueFactory(new PropertyValueFactory<Business,String>("businessName"));
        colStars.setCellValueFactory(new PropertyValueFactory<Business,Double>("stars"));
       
    }

}
