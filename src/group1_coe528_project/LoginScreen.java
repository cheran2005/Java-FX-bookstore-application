/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_coe528_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





public class LoginScreen implements Initializable {
    
    //initalizing variables needed for the login screen to take in usernames and passwords and check if they are in the system
    private final String FILE_PATH_2 = "Customers.txt";

    
    @FXML
    private TextField usernameField;
    
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label test;
    

    @FXML
    private void LoginCheck(ActionEvent event) {
        
        
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();
        
        File file = new File(FILE_PATH_2);
        //check if the admin login is entered to go to the admin page
        if (enteredUsername.equals("admin") && enteredPassword.equals("admin")) {
           SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
           sceneManager.switchScene("/FXML/OwnerStartScreen.fxml");
        }
        //reads the costumer file and checks if the costumer is valid entered if the costumer file exists in the first place
        else if (file.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String usernames = parts[0].trim();
                        String passwords = parts[1].trim();
                        int points = Integer.parseInt(parts[2].trim());
                        if (enteredUsername.equals(usernames) && enteredPassword.equals(passwords)){
                            
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CustomerStartScreen.fxml"));
                            Parent root = loader.load();
                    
                            Customer newcus  = new Customer (usernames,passwords);
                            newcus.setPoints(points);
                            //checks if the points is gold state or silver state and setuser as that which the info is used in the costumer screens
                            if (points >= 1000) {
                                newcus.setStatus(new GoldState());
                                } else {
                                   newcus.setStatus(new SilverState());
                                }
                            CustomerStartScreen controller = loader.getController();
                            controller.setUser(enteredUsername,points,newcus.getStatus());
                            
                           
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //for invalid login credintials
        else {
            test.setText("Invalid username or password.");
        }

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
