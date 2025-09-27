/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_coe528_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class OwnerCustomerScreen implements Initializable {
    
    @FXML private TableView<Customer> CustomerTable;
    @FXML private TableColumn<Customer, String> UsernameColumn;
    @FXML private TableColumn<Customer, String> PasswordColumn;
    @FXML private TableColumn<Customer, Integer> PointsColumn;
    @FXML private TextField NameField;
    @FXML private TextField PasswordField;
    
    private ObservableList<Customer> CustomersList = FXCollections.observableArrayList();
    private final String FILE_PATH_2 = "Customers.txt";
   
    @FXML
    private void BackOwnerStartScreen(ActionEvent event){
        SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
        sceneManager.switchScene("/FXML/OwnerStartScreen.fxml");
    }
    
    
    @FXML
    private void AddCustomer(ActionEvent event) {
        String name = NameField.getText();
        String password = PasswordField.getText();

        if (!name.isEmpty() && !password.isEmpty()) {
              
            Customer newCustomer  = new Customer (name,password);
            CustomersList.add(newCustomer);
            saveCustomersToFile(); 
            NameField.clear();
            PasswordField.clear();
        }
    }
    
    @FXML
    private void DeleteCustomer(ActionEvent event) {
   
        Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();

    
        if (selectedCustomer != null) {
        
            CustomersList.remove(selectedCustomer);
            saveCustomersToFile();
        }  
    }

    private void loadCustomersFromFile() {
        File file = new File(FILE_PATH_2);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String usernames = parts[0].trim();
                        String passwords = parts[1].trim();
                        int points = Integer.parseInt(parts[2].trim());
                        CustomersList.add(new Customer(usernames, passwords,points));
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_2))) {
            for (Customer customer : CustomersList) {
                writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getPoints()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        PointsColumn.setCellValueFactory(new PropertyValueFactory<>("Points"));
        CustomerTable.setItems(CustomersList);
        loadCustomersFromFile();
        
    }   
    
    
    
}
