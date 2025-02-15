package org.example;

import org.example.DAOs.FinanceDaoInterface;
import org.example.DAOs.MySqlFinanceDao;
import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

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
    }
}