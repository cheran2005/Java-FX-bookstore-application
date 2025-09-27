/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_coe528_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class CustomerCostScreen implements Initializable{
//initalized variables for gui and different elements needed to display the costumers cost, discount price, and status
    @FXML private Label totalCostLabel;
    @FXML private Label pointsLabel;
    

    private double totalCost;
    private int points;
    private String status;
    private boolean isRedeem;

    //method that outputs the cost, points and status
    public void setCostDetails(double totalCost, int points, String status, boolean isRedeem) {
        this.totalCost = totalCost;
        this.points = points;
        this.status = status;
        this.isRedeem = isRedeem;

        
        totalCostLabel.setText("Total Cost: " + totalCost);
        pointsLabel.setText("Points: " + points + ", Status: " + status);

        if (isRedeem) {
            totalCostLabel.setText("Total Cost (after redeeming points): " + totalCost);
        }
    }

   //gui button action event to go back to login page
    @FXML
    private void BacktoLogin(ActionEvent event){
        SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
        sceneManager.switchScene("/FXML/LoginScreen.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
}

