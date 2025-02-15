package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface FinanceDaoInterface {
    public List<Expense> listAllExpenses() throws DaoException;
    public List<Income> listAllIncomes() throws DaoException;
    public Expense addExpense(Expense e) throws DaoException;
    public Expense deleteExpenseByID(int ID) throws DaoException;
}
