package group1_coe528_project;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

//Importing libraries

//book class
public class Book {
    private final SimpleStringProperty Name;
    private final SimpleDoubleProperty price;
    private BooleanProperty selected;
    //initalizing variables for book
    
    //book constructor
    public Book(String title, double price) {
        this.Name = new SimpleStringProperty(title);
        this.price = new SimpleDoubleProperty(price);
        this.selected = new SimpleBooleanProperty(false);
    }
    
    //methods to get book variables
    public String getTitle() { return Name.get(); }
    

    public double getPrice() { return price.get(); }
    
    //for when books are selected by customer with java fx
    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
   
}
