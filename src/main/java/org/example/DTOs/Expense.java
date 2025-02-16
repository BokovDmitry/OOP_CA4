package org.example.DTOs;

import java.util.Date;

public class Expense {
    private int expenseID;
    private String title;
    private String category;
    private double amount;
    private Date dateIncurred;

    public Expense() {}

    public Expense(int incomeID, String title, String category, double amount, Date dateIncurred) {
        this.expenseID = incomeID;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public Expense(String title, String category, double amount, Date dateIncurred) {
        this.expenseID = 0;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.dateIncurred = dateIncurred;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setIncomeID(int incomeID) {
        this.expenseID = incomeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateIncurred() {
        return dateIncurred;
    }

    public void setDateIncurred(Date dateIncurred) {
        this.dateIncurred = dateIncurred;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %-20s | %-15s | %10.2f | %s",
                expenseID, title, category, amount, dateIncurred.toString());
    }
}
