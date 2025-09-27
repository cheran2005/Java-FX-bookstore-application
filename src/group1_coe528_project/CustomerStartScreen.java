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
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;



public class CustomerStartScreen implements Initializable{
    //initalizing gui elements and variables needed to display the users name at the top with points status,
    //initalizing a table that the user sees to buy books 
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    @FXML private TableView<Book> CustomerTable;
    
    @FXML private TableColumn<Book, String> BookName;
    @FXML private TableColumn<Book, String> BookPrice;
    @FXML private TableColumn<Book, Boolean> Select;
    @FXML private Button RedeemBuy;
    
    @FXML private Label welcomeMessage;
    private Customer customer;
    
    private String username;  
    private int points;
    private String status;
    //a method to welcom,e the user and show there points and status in the gui and taking in the username , points and status
    //from login page
    public void setUser(String username,int points,String status) {
        this.username = username;
        this.points = points;
        this.status = status;
        welcomeMessage.setText("Welcome, " + username + " you have "+points+" points. Your status is " + status+".");
    }
    
    //calculates total cost user clicks on books
    private double calculateTotalCost() {
    double totalCost = 0.0;
    for (Book book : bookList) {
        if (book.isSelected()) {  
            totalCost += book.getPrice();
        }
    }
    return totalCost;
}

    //reads the file books.txt and adds to the bookList arraylist apart of the javafx UI
    private void loadBooksFromFile() {
        File file = new File("books.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String title = parts[0].trim();
                        double price = Double.parseDouble(parts[1].trim());
                        bookList.add(new Book(title, price));
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
   
    //method that connects with the logout button in the gui to go back to the login screen
   @FXML
    private void LogoutCustomer(ActionEvent event) {
        
         SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
            sceneManager.switchScene("/FXML/LoginScreen.fxml");
    }
    
    
    //method that goes to cost screen and using FXML loader loads the customercostscreen page
    @FXML
    private void GotoCostScreen(double totalCost, boolean isRedeem,ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CustomerCostScreen.fxml"));
            Parent root = loader.load();
    
            CustomerCostScreen controller = loader.getController();
            controller.setCostDetails(totalCost, customer.getPoints(), customer.getStatus(), isRedeem);
    
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //method for the buy button when the user clicks after selecting books uses the customer class
    //to handel calculations and then gives the info to the gotocostscreen method
   @FXML
    private void Buy(ActionEvent event) {
        double totalCost = calculateTotalCost();
        customer.getState().earnPoints(customer, (int) totalCost);
        updateCustomerPoints();
        GotoCostScreen(totalCost, false, event);   
}

    //method for the Redeembuy button when the user clicks after selecting books uses the customer class
    //to handel calculations and then gives the info to the gotocostscreen method but uses the customer class
    //to handle redeeming points too
    @FXML 
    private void RedeemBuy(ActionEvent event) {
        double totalCost = calculateTotalCost();
        int finalCost = customer.getState().redeemPoints(customer, (int) totalCost);
        updateCustomerPoints();
        GotoCostScreen(finalCost, true, event); 
    }
    
    //updates the textfile after the points are calculated and changed
    private void updateCustomerPoints() {
    File file = new File("Customers.txt");
    if (!file.exists()) return;

    StringBuilder updatedData = new StringBuilder();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String storedUsername = parts[0].trim();
                String storedPassword = parts[1].trim();
                int storedPoints = Integer.parseInt(parts[2].trim());

                if (storedUsername.equals(username)) {
                    
                    storedPoints = points;
                }
                updatedData.append(storedUsername).append(",").append(storedPassword).append(",").append(storedPoints).append("\n");
            }
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }

    try (java.io.FileWriter writer = new java.io.FileWriter(file, false)) {
        writer.write(updatedData.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    //setcellvaluefactor fornthe different columns in the table and loads the textfile books to the table
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        BookPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        Select.setCellValueFactory(cellData -> {
            BooleanProperty selectedProp = cellData.getValue().selectedProperty();
            return selectedProp;
        });

   
        Select.setCellFactory(CheckBoxTableCell.forTableColumn(Select));






        CustomerTable.setItems(bookList);
        loadBooksFromFile();
    }  
}
