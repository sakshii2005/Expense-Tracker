import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        System.out.println("Welcome to Expense Tracker!");

        // Registration
        System.out.print("Register - Enter Username: ");
        String regUsername = scanner.next();
        System.out.print("Enter Password: ");
        String regPassword = scanner.next();
        User.register(regUsername, regPassword);

        // Login
        System.out.print("Login - Enter Username: ");
        String loginUser = scanner.next();
        System.out.print("Enter Password: ");
        String loginPass = scanner.next();

        if (User.login(loginUser, loginPass)) {
            System.out.println("Login successful!");
            
            // User is now logged in, continue to expense tracking
            boolean running = true;
            while (running) {
                System.out.println("\n1. Add Expense\n2. Add Category\n3. Show Categories\n4. Show Expenses\n5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        System.out.print("Enter currency (USD/EUR/INR): ");
                        String currency = scanner.next();
                        tracker.addExpense(category, amount, currency);
                        break;
                    case 2:
                        System.out.print("Enter new category: ");
                        String newCategory = scanner.nextLine();
                        tracker.addCategory(newCategory);
                        break;
                    case 3:
                        tracker.showCategories();
                        break;
                    case 4:
                        tracker.showExpenses();
                        break;
                    case 5:
                        System.out.println("Exiting... Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }

        scanner.close();
    }
}

