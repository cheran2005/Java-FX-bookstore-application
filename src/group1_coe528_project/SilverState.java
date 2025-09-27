/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_coe528_project;


public class SilverState implements State {
    
    @Override
    public void earnPoints(Customer customer, int amount){
        customer.addPoints(amount*10);                        //Earning 10 points for every CAD spent
        
        if (customer.getPoints() >= 1000){                    //If the customer's points reach 1000, they get promoted to Gold status
            customer.setStatus(new GoldState());
        }
    }
    
    @Override
    public int redeemPoints(Customer customer, int cost){
        int redeemable_money = customer.getPoints()/100;      //Points are converted to CAD
        int discount = Math.min(redeemable_money, cost);      //Making sure the discount isn't greater than the cost of the book(s)
        customer.subtractPoints(discount * 100);              //Reducing the total number of points if they use it in a transaction
        int finalCost = Math.max(cost - discount, 0);         //Making sure the final cost is never below 0
        
        return finalCost;                               //Returning the total cost after applying the discount
    }
    
    @Override
    public String getStatus(){
        return "Silver";
    }  
}
