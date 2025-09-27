# BookStore Project

This is a simple Java project built in NetBeans that simulates a basic online bookstore.  
Customers can buy books either with money or by redeeming points they have earned from past purchases.  

The system keeps track of:  
- Customer points  
- Customer status (Silver or Gold)  
- Book purchases and redemptions  

The project uses the **State Design Pattern** to handle the changing customer status:  
- Silver status: less than 1000 points  
- Gold status: 1000 points or more  

By using this pattern, the customer’s status can switch automatically at runtime without complicated conditional code, making the program easier to understand and extend.  
