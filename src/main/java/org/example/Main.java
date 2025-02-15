package org.example;

import org.example.DAOs.FinanceDaoInterface;
import org.example.DAOs.MySqlFinanceDao;
import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        FinanceDaoInterface financeDI = new MySqlFinanceDao();

        try {
            System.out.println("Call listAllExpenses()");

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

//        try {
//            System.out.println("Call addExpense()");
//
//            LocalDate localDate = LocalDate.of(2025, 2, 9);
//            Date date = java.sql.Date.valueOf(localDate);
//
//            Expense e = new Expense( "New Keyboard", "Electronics", 100.0, date);
//
//            System.out.println(financeDI.addExpense(e));
//
//            System.out.println("Expense added successfully!");
//
//            List<Expense> expenses = financeDI.listAllExpenses();
//
//            for(Expense exp : expenses)
//            {
//                System.out.println(exp);
//            }
//
//        } catch (DaoException e) {
//            System.out.println("Could not add Expense" + e.getMessage());
//            throw new RuntimeException(e);
//        }
    }
}