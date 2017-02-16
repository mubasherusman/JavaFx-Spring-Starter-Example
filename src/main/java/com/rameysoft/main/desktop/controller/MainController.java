package com.rameysoft.main.desktop.controller;


import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rameysoft.main.service.InventoryService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

@Controller
public class MainController{
	
	@Autowired
	private InventoryService mInventoryService;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane appMain;

    @FXML
    private TextArea textArea;
    
    public MainController() {
	}


    @FXML
    void onCloseAction(ActionEvent event) {

    }

    @FXML
    void onAboutMenuItemAction(ActionEvent event) {

    }

    @FXML
    void onBrowseAction(ActionEvent event) {
    	mInventoryService.addNewSupplier();
    }

    @FXML
    void initialize() {
        assert appMain != null : "fx:id=\"appMain\" was not injected: check your FXML file 'main.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'main.fxml'.";

    }  	
}
