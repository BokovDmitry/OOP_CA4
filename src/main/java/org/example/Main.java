package org.example;

import org.example.DAOs.FinanceDaoInterface;
import org.example.DAOs.MySqlFinanceDao;
import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        FinanceDaoInterface financeDI = new MySqlFinanceDao();

//        try {
//            System.out.println("Call listAllIncomes()");
//
//            List<Income> incomes = financeDI.listAllIncomes();
//
//            if(incomes.isEmpty())
//                System.out.println("No Records Found");
//            else
//            {
//                for(Income i : incomes)
//                {
//                    System.out.println(i);
//                }
//            }
//
//        } catch (DaoException e) {
//            throw new RuntimeException(e);
//        }

        try {
            System.out.println("Call addExpense()");

            LocalDate localDate = LocalDate.of(2025, 2, 9);
            Date date = java.sql.Date.valueOf(localDate);

            Income i = new Income( 3,"Birthday Gift", 100.0, date);

            System.out.println(financeDI.deleteIncomeByID(3));

            List<Income> incomes = financeDI.listAllIncomes();

            for(Income inc : incomes)
            {
                System.out.println(inc);
            }

        } catch (DaoException e) {
            System.out.println("Could not add Expense" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}