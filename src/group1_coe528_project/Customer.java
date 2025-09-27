/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_coe528_project;

//customer class
public class Customer {
    //initalizing customer variables
    private String username;
    private String password;
    private int points;
    private State status;
    
//2 customer constructors used due to one for username and password and one for points
//so when reading files to know if the points have been updated after closing application
//and reopening
public Customer (String username, String password){

    this.username = username;
    this.password = password;
    this.points = 0;
    this.status = new SilverState();
}

public Customer(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
        this.status = new SilverState();
    }


//getters to get costumer variables
public String getUsername(){
    return username;
}

public String getPassword(){
    return password;
}

//Methods to calculate and get calculated points and status of customer
public void earnPoints(int amount){
    status.earnPoints(this, amount);
}

public int redeemPoints(int cost){
    return status.redeemPoints(this, cost);   
}

public void setPoints(int points){
    this.points = points;   
}

public void addPoints(int amount){
    points += amount;
}

public void subtractPoints(int amount){
    points -= amount;
}
          
public int getPoints(){
    return points;
}

public String getStatus(){
    return status.getStatus();
}
    
public void setStatus(State newStatus){
    this.status = newStatus;
}

public State getState() {
    return status; 
}

}
