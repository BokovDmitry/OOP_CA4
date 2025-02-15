package org.example.DAOs;

import org.example.DTOs.Expense;
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

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int expenseID = resultSet.getInt("expenseID");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                Date dateIncurred = resultSet.getDate("dateIncurred");

                expenses.add(new Expense(expenseID, title, category, amount, dateIncurred));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(resultSet != null)
                    resultSet.close();
                if (preparedStatement != null);
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
        return null;
    }

}
