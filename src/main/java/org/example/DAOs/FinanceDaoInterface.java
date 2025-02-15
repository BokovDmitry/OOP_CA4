package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.DTOs.Income;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface FinanceDaoInterface {
    public List<Expense> listAllExpenses() throws DaoException;
    public List<Income> listAllIncomes() throws DaoException;
    public Object addRecord(Object obj) throws DaoException;
    public boolean deleteExpenseByID(int ID) throws DaoException;
    public boolean deleteIncomeByID(int ID) throws DaoException;

}
