import java.util.*;

class ExpenseTracker {
    private List<Expense> expenses = new ArrayList<>();
    private Set<String> categories = new HashSet<>(Arrays.asList("Food", "Travel", "Entertainment", "Utilities"));
    
    public void addExpense(String category, double amount, String currency) {
        if (!categories.contains(category)) {
            System.out.println("Category does not exist! Add it first.");
            return;
        }
        expenses.add(new Expense(category, amount, currency));
        System.out.println("Expense added successfully!");
    }
    
    public void addCategory(String category) {
        categories.add(category);
        System.out.println("Category '" + category + "' added successfully!");
    }
    
    public void removeCategory(String category) {
        if (categories.remove(category)) {
            System.out.println("Category '" + category + "' removed successfully!");
        } else {
            System.out.println("Category not found!");
        }
    }
    
    public void showCategories() {
        System.out.println("Available Categories:");
        for (String category : categories) {
            System.out.println("- " + category);
        }
    }
    
    public void showExpenses() {
        System.out.println("Expenses:");
        for (Expense e : expenses) {
            e.displayExpense();
        }
    }
}
