/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group1_coe528_project;


public class GoldState implements State {
        
    @Override
    public void earnPoints(Customer customer, int amount){
        customer.addPoints(amount*10);
    }
    
    @Override
    public int redeemPoints(Customer customer, int cost){
        int redeemable_money = customer.getPoints()/100;      //Points are converted to CAD
        int discount = Math.min(redeemable_money, cost);      //Making sure the discount isn't greater than the cost of the book(s)
        customer.subtractPoints(discount * 100);
        int finalCost = Math.max(cost - discount, 0);         //Making sure the final cost can never be below 0
                
          if (customer.getPoints() < 1000){                   //Downgrading to silver status if the points get below 1000
            customer.setStatus(new SilverState());
        } 
        return finalCost;
    }
    
    @Override
    public String getStatus(){
        return "Gold";
    }  
}

