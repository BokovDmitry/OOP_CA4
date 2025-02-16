package org.example;

import org.example.DAOs.FinanceDaoInterface;
import org.example.DAOs.MySqlFinanceDao;
import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void menu()
    {
        FinanceDaoInterface financeDI = new MySqlFinanceDao();
        Scanner input = new Scanner(System.in);
        int option;
        do {

            System.out.println("\n<------| Finance Menu |------>");
            System.out.println("1. Display All Incomes");
            System.out.println("2. Display All Expenses");
            System.out.println("3. Add Income");
            System.out.println("4. Add Expense");
            System.out.println("5. Delete Income");
            System.out.println("6. Delete Expense");
            System.out.println("7. Display Month Left Over");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                option = input.nextInt();
                input.nextLine();

                switch(option)
                {
                    case 1:
                    {
                        try {

                            List<Income> incomes = financeDI.listAllIncomes();

                            if(incomes.isEmpty())
                                System.out.println("No Records Found");
                            else
                            {
                                for(Income i : incomes)
                                {
                                    System.out.println(i);
                                }
                            }

                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case 2:
                    {
                        try {

                            List<Expense> expenses = financeDI.listAllExpenses();

                            if(expenses.isEmpty())
                                System.out.println("No Records Found");
                            else
                            {
                                for(Expense e : expenses)
                                {
                                    System.out.println(e);
                                }
                            }

                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case 3:
                    {
                        try {
                            String title;
                            double amount;
                            Date date = new Date();

                            System.out.println("Title: ");
                            title = input.nextLine();
                            System.out.println("Amount: ");
                            amount = input.nextDouble();

                            financeDI.addRecord(new Income(title, amount, date));

                        } catch (InputMismatchException e) {
                                System.out.println("❌ Invalid input! Please enter a number.");
                                input.nextLine();
                                option = -1;
                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case 4:
                    {
                        try {
                            String title;
                            String category;
                            double amount;
                            Date date = new Date();

                            System.out.println("Title: ");
                            title = input.nextLine();
                            System.out.println("Category: ");
                            category = input.nextLine();
                            System.out.println("Amount: ");
                            amount = input.nextDouble();

                            financeDI.addRecord(new Expense(title, category, amount, date));

                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case 5:
                    {
                        try {
                            int id;
                            System.out.println("What is the ID of the income you would like to delete: ");
                            id = input.nextInt();

                            financeDI.deleteIncomeByID(id);
                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case 6:
                    {
                        try {
                            int id;
                            System.out.println("What is the ID of the expense you would like to delete: ");
                            id = input.nextInt();

                            financeDI.deleteExpenseByID(id);
                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case 7:
                        try
                        {
                            int year;
                            int month;

                            System.out.println("Enter desired year: ");
                            year = input.nextInt();
                            System.out.println("Enter desired month: ");
                            month = input.nextInt();

                            financeDI.calculateLeftOver(year, month);

                        } catch (DaoException e) {
                            throw new RuntimeException(e);
                        }
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                    {
                        System.out.println("Invalid Option, Try Again");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                input.nextLine();
                option = -1;
            }
        } while (option != 0);

        input.close();
    }

    public static void main(String[] args) {
        menu();
    }
}