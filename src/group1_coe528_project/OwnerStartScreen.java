/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package group1_coe528_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.IOException;

public class OwnerStartScreen implements Initializable {
    
@FXML
    private void LogoutOwner(ActionEvent event) {
        
         SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
            sceneManager.switchScene("/FXML/LoginScreen.fxml");
    }
    
    
    @FXML
    private void OwnerBooks(ActionEvent event) {
        
         SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
         sceneManager.switchScene("/FXML/OwnerBooksScreen.fxml");
    }
    
    
    @FXML
    private void OwnerCustomers(ActionEvent event) {
        
         SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
         sceneManager.switchScene("/FXML/OwnerCustomersScreen.fxml"); 
    }
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}



    