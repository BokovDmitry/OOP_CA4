package org.example.DAOs;

import org.example.DTOs.Expense;
import org.example.Exceptions.DaoException;

import java.util.List;

public interface FinanceDaoInterface {
    public List<Expense> listAllExpenses() throws DaoException;
}
