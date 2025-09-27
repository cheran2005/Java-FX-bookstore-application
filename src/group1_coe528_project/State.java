package group1_coe528_project;


public interface State {
    void earnPoints(Customer customer, int amount);
    int redeemPoints(Customer customer, int cost);
    String getStatus(); 
}
