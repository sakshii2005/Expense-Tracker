
import java.io.*;
import java.util.*;

class Expense {
    private String category;
    private double amount;
    private String description;
    
    public Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "Category: " + category + ", Amount: " + amount + ", Description: " + description;
    }
}

class ExpenseTracker {
    private List<Expense> expenses;
    
    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }
    
    public void addExpense(String category, double amount, String description) {
        Expense expense = new Expense(category, amount, description);
        expenses.add(expense);
        System.out.println("Expense added: " + expense);
    }
    
    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else {
            System.out.println("All Expenses:");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }
    
    public void generateReport() {
        double totalExpense = 0;
        Map<String, Double> categoryExpenseMap = new HashMap<>();
        
        for (Expense expense : expenses) {
            totalExpense += expense.getAmount();
            categoryExpenseMap.put(expense.getCategory(),
                                   categoryExpenseMap.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }
        
        System.out.println("Total Expenses: " + totalExpense);
        System.out.println("Expenses Breakdown by Category:");
        for (Map.Entry<String, Double> entry : categoryExpenseMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    public void saveExpensesToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(expenses);
            System.out.println("Expenses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }
    
    public void loadExpensesFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            expenses = (List<Expense>) in.readObject();
            System.out.println("Expenses loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        String filename = "expenses.dat";
        
        tracker.loadExpensesFromFile(filename);  // Load expenses from file if any
        
        while (true) {
            System.out.println("\n------ Expense Tracker ------");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Generate Expense Report");
            System.out.println("4. Save Expenses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    tracker.addExpense(category, amount, description);
                    break;
                case 2:
                    tracker.viewExpenses();
                    break;
                case 3:
                    tracker.generateReport();
                    break;
                case 4:
                    tracker.saveExpensesToFile(filename);
                    break;
                case 5:
                    tracker.saveExpensesToFile(filename);  // Save before exiting
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
