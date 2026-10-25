
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    static final int MAX_TRANSACTIONS = 1000;

    static String[] types = new String[MAX_TRANSACTIONS];
    static double[] amounts = new double[MAX_TRANSACTIONS];
    static String[] categories = new String[MAX_TRANSACTIONS];

    static int transactionCount = 0;

    static double totalIncome = 0;
    static double totalExpense = 0;

    public static void main(String[] args) {

        boolean running = true;

        welcome();

        while (running) {

            showMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    addIncome();
                    break;

                case 2:
                    addExpense();
                    break;

                case 3:
                    showBalance();
                    break;

                case 4:
                    showTransactions();
                    break;

                case 5:
                    showHighestSpendingCategory();
                    break;

                case 6:
                    exitMessage();
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option! Choose 1-6.");
            }
        }

        input.close();
    }

    public static void welcome() {
        System.out.println("\n===================================");
        System.out.println("     PERSONAL EXPENSE TRACKER");
        System.out.println("===================================");
    }

    public static void showMenu() {
        System.out.println("\n1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View Total Balance");
        System.out.println("4. View All Transactions");
        System.out.println("5. Show Highest Spending Category");
        System.out.println("6. Exit");
        System.out.println("===================================");
    }

    public static int readInt(String message) {

        System.out.print(message);
        int number = input.nextInt();
        input.nextLine();

        return number;
    }

    public static double readAmount(String message) {

        System.out.print(message);
        double amount = input.nextDouble();
        input.nextLine();

        while (amount <= 0) {
            System.out.println("Amount must be greater than zero!");
            System.out.print(message);

            amount = input.nextDouble();
            input.nextLine();
        }

        return amount;
    }

    public static boolean hasSpace() {
        if (transactionCount >= MAX_TRANSACTIONS) {
            System.out.println("Transaction limit reached!");
            return false;
        }

        return true;
    }

    public static void addIncome() {
        if (!hasSpace()) {
            return;
        }

        double income = readAmount("Enter your income: ");

        amounts[transactionCount] = income;
        types[transactionCount] = "Income";
        categories[transactionCount] = "-";

        transactionCount++;
        totalIncome += income;

        System.out.println("Income added successfully!");
        System.out.printf("Income: %.2f TL%n", income);
    }

    public static void addExpense() {
        if (!hasSpace()) {
            return;
        }

        double expense = readAmount("Enter expense amount: ");

        System.out.println("\nSelect a category:");
        System.out.println("1. Food");
        System.out.println("2. Transportation");
        System.out.println("3. Education");
        System.out.println("4. Entertainment");
        System.out.println("5. Other");

        int choice;

        while (true) {
            choice = readInt("Choose a category: ");

            if (choice >= 1 && choice <= 5) {
                break;
            }

            System.out.println("Invalid category! Choose 1-5.");
        }

        String category;

        switch (choice) {
            case 1:
                category = "Food";
                break;
            case 2:
                category = "Transportation";
                break;
            case 3:
                category = "Education";
                break;
            case 4:
                category = "Entertainment";
                break;
            default:
                category = "Other";
        }

        amounts[transactionCount] = expense;
        types[transactionCount] = "Expense";
        categories[transactionCount] = category;

        transactionCount++;
        totalExpense += expense;

        System.out.println("Expense added successfully!");
        System.out.printf("Amount: %.2f TL%n", expense);
        System.out.println("Category: " + category);
    }

    public static void showBalance() {
        double balance = totalIncome - totalExpense;

        System.out.println("\n========== BALANCE ==========");
        System.out.printf("Total income:  %.2f TL%n", totalIncome);
        System.out.printf("Total expense: %.2f TL%n", totalExpense);
        System.out.printf("Total balance: %.2f TL%n", balance);
        System.out.println("=============================");
    }

    public static void showTransactions() {
        if (transactionCount == 0) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n========== ALL TRANSACTIONS ==========");

        for (int i = 0; i < transactionCount; i++) {
            System.out.print((i + 1) + ". ");
            System.out.print(types[i] + " | ");
            System.out.printf("%.2f TL", amounts[i]);

            if (types[i].equals("Expense")) {
                System.out.print(" | " + categories[i]);
            }

            System.out.println();
        }

        System.out.println("======================================");
    }

    public static void showHighestSpendingCategory() {
        if (totalExpense == 0) {
            System.out.println("No expenses found.");
            return;
        }

        String[] categoryNames = {
            "Food",
            "Transportation",
            "Education",
            "Entertainment",
            "Other"
        };

        double highestAmount = 0;
        String highestCategory = "";

        for (int i = 0; i < categoryNames.length; i++) {
            double categoryTotal = 0;

            for (int j = 0; j < transactionCount; j++) {
                if (types[j].equals("Expense")
                        && categories[j].equals(categoryNames[i])) {
                    categoryTotal += amounts[j];
                }
            }

            if (categoryTotal > highestAmount) {
                highestAmount = categoryTotal;
                highestCategory = categoryNames[i];
            }
        }

        System.out.println("\n===== HIGHEST SPENDING CATEGORY =====");
        System.out.println("Category: " + highestCategory);
        System.out.printf("Total spent: %.2f TL%n", highestAmount);
        System.out.println("=====================================");
    }

    public static void exitMessage() {
        System.out.println("Exiting the application. Goodbye!");
    }
}
