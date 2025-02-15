package org.example.DAOs;

import com.sun.jdi.request.ClassPrepareRequest;
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
    public Expense addExpense(Expense e) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getConnection();

            String query = "INSERT INTO expenses VALUES (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, e.getExpenseID());
            preparedStatement.setString(2, e.getTitle());
            preparedStatement.setString(3, e.getCategory());
            preparedStatement.setDouble(4, e.getAmount());
            preparedStatement.setDate(5, new java.sql.Date(e.getDateIncurred().getTime()));
            preparedStatement.executeUpdate();
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
        return e;
    }

    @Override
    public Expense deleteExpenseByID(int ID) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Expense e = null;

        try {
            connection = this.getConnection();
            String query = "DELETE FROM expenses WHERE expenseID = ?";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, ID);

            preparedStatement.executeUpdate();
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
        return null;
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
                Date dateIncurred = resultSet.getDate("dateIncurred");

                totalIncome += amount;

                incomes.add(new Income(incomeID, title, amount, dateIncurred));
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

}
