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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class OwnerBooksScreen implements Initializable {
    
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, Double> priceColumn;
    @FXML private TextField TitleField;
    @FXML private TextField PriceField;
    
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private final String FILE_PATH = "books.txt";
    
    @FXML
    private void BackOwnerStartScreen(ActionEvent event){
        SceneManager sceneManager = new SceneManager((Stage) ((Node) event.getSource()).getScene().getWindow());
        sceneManager.switchScene("/FXML/OwnerStartScreen.fxml");
    }
    
      

    @FXML
    private void AddBook(ActionEvent event) {
        String title = TitleField.getText();
        String priceText = PriceField.getText();

        if (!title.isEmpty() && !priceText.isEmpty()) {
            
            
            try {
                boolean bookExists = false;
                for (Book book : bookList) {
                    if (book.getTitle().equalsIgnoreCase(title)) {  
                        bookExists = true;
                        break;  
                    }
                }

                if (!bookExists) {
                    
                    Book newBook = new Book(title,Double.parseDouble(priceText));
                    bookList.add(newBook);
                    saveBooksToFile();  
                    TitleField.clear();
                    PriceField.clear();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input!");
            }
        }
    }
    
    @FXML
    private void RemoveBook(ActionEvent event) {
   
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();

    
        if (selectedBook != null) {
        
            bookList.remove(selectedBook);
            saveBooksToFile();
        }  
    }

    private void loadBooksFromFile() {
        File file = new File(FILE_PATH);
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

    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Book book : bookList) {
                writer.write(book.getTitle() + "," + book.getPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookTable.setItems(bookList);
        loadBooksFromFile();
    }   
    
}
