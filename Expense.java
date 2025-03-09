import java.util.*;
class Expense {
    private String category;
    private double amount;
    private String currency;

    public Expense(String category, double amount, String currency) {
        this.category = category;
        this.amount = amount;
        this.currency = currency;
    }
    
    public double convertTo(String targetCurrency) {
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("INR", 75.0);
        
        if (!exchangeRates.containsKey(currency) || !exchangeRates.containsKey(targetCurrency)) {
            return amount; // If currency is unknown, return the original amount
        }
        return (amount / exchangeRates.get(currency)) * exchangeRates.get(targetCurrency);
    }
    
    public void displayExpense() {
        System.out.println("Category: " + category + ", Amount: " + amount + " " + currency);
    }
}