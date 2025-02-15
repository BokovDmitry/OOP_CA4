package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlFinanceDao extends MySqlDao implements FinanceDaoInterface{
    @Override
    public List<Expense> listAllExpenses() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Expense> expenses = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM expenses";
            preparedStatement = connection.prepareStatement(query);

            double totalExpense = 0;

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int expenseID = resultSet.getInt("expenseID");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                Date dateIncurred = resultSet.getDate("dateIncurred");

                totalExpense += amount;

                expenses.add(new Expense(expenseID, title, category, amount, dateIncurred));
            }
            System.out.println("Total expenses: "+totalExpense);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return expenses;
    }

    @Override
    public Object addRecord(Object obj) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String succsessMessage = "";

        try {
            connection = this.getConnection();

            if(obj instanceof Income i)
            {
                String query = "INSERT INTO incomes VALUES (?, ?, ?, ?);";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, i.getIncomeID());
                preparedStatement.setString(2, i.getTitle());
                preparedStatement.setDouble(3, i.getAmount());
                preparedStatement.setDate(4, new java.sql.Date(i.getDate().getTime()));

                succsessMessage = "Income successfully added";
            }
            else if (obj instanceof Expense e)
            {
                String query = "INSERT INTO expenses VALUES (?, ?, ?, ?, ?);";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, e.getExpenseID());
                preparedStatement.setString(2, e.getTitle());
                preparedStatement.setString(3, e.getCategory());
                preparedStatement.setDouble(4, e.getAmount());
                preparedStatement.setDate(5, new java.sql.Date(e.getDateIncurred().getTime()));

                succsessMessage = "Expense successfully added";
            }
            else {
                System.out.println("Can't resolve an object" + obj);
            }

            preparedStatement.executeUpdate();
            System.out.println(succsessMessage);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return obj;
    }

    @Override
    public boolean deleteExpenseByID(int ID) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM expenses WHERE expenseID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, ID);

            result = preparedStatement.executeUpdate() > 0;
            System.out.println("Expense Deleted Successfully: " + result);

            return result;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public boolean deleteIncomeByID(int ID) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM incomes WHERE incomeID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, ID);

            result = preparedStatement.executeUpdate() > 0;
            System.out.println("Income Deleted Successfully : " + result);
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<Income> listAllIncomes() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Income> incomes = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM incomes";
            preparedStatement = connection.prepareStatement(query);

            double totalIncome = 0;

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int incomeID = resultSet.getInt("incomeID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("dateEarned");

                totalIncome += amount;

                incomes.add(new Income(incomeID, title, amount, dateEarned));
            }
            System.out.println("Total income: "+ totalIncome);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return incomes;
    }

    @Override
    public void calculateLeftOver(int year, int month) throws DaoException {
        Connection connection = null;
        PreparedStatement incomeStatement = null;
        PreparedStatement expenseStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getConnection();

            //https://www.w3schools.com/sql/func_sqlserver_month.asp
            //https://www.w3schools.com/sql/func_sqlserver_year.asp
            String incomeQuery = "SELECT * FROM incomes WHERE YEAR(dateEarned) = ? AND MONTH(dateEarned) = ?";
            incomeStatement = connection.prepareStatement(incomeQuery);
            incomeStatement.setInt(1, year);
            incomeStatement.setInt(2, month);

            double monthIncome = 0;
            double monthExpense = 0;

            resultSet = incomeStatement.executeQuery();
            System.out.println("Incomes:");
            while (resultSet.next()) {
                int incomeID = resultSet.getInt("incomeID");
                String title = resultSet.getString("title");
                double amount = resultSet.getDouble("amount");
                Date dateEarned = resultSet.getDate("dateEarned");

                System.out.printf("%-5d %-20s %-10.2f %tB %<td\n", incomeID, title, amount, dateEarned);
                monthIncome += amount;
            }

            System.out.println("\nTotal Month Income: "+ monthIncome + "\n");
            resultSet.close();

            //https://www.w3schools.com/sql/func_sqlserver_month.asp
            //https://www.w3schools.com/sql/func_sqlserver_year.asp
            String expenseQuery = "SELECT * FROM expenses WHERE YEAR(dateIncurred) = ? AND MONTH(dateIncurred) = ?";
            expenseStatement = connection.prepareStatement(expenseQuery);
            expenseStatement.setInt(1, year);
            expenseStatement.setInt(2, month);

            resultSet = expenseStatement.executeQuery();
            System.out.println("\nExpenses:");
            while (resultSet.next()) {
                int expenseID = resultSet.getInt("expenseID");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                Date dateIncurred = resultSet.getDate("dateIncurred");

                System.out.printf("%-5d %-20s %-15s %-10.2f %tB %<td\n", expenseID, title, category, amount, dateIncurred);
                monthExpense += amount;
            }
            System.out.printf("\nTotal Month Expenses: %.2f\n", monthExpense);

            System.out.println("\nLeftover: " + (monthIncome - monthExpense));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (incomeStatement != null) incomeStatement.close();
                if (expenseStatement != null) expenseStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
