package org.example.DTOs;

import java.util.Date;

public class Income {
    private int incomeID;
    private String title;
    private double amount;
    private Date date;

    public Income() {}

    public Income(int expenseID, String title, double amount, Date date) {
        this.incomeID = expenseID;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public Income(String title, double amount, Date date) {
        this.incomeID = 0;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public int getExpenseID() {
        return incomeID;
    }

    public void setExpenseID(int expenseID) {
        this.incomeID = expenseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Income{" +
                "expenseID=" + incomeID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
