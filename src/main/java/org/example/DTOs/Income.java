package org.example.DTOs;

import java.util.Date;

public class Income {
    private int expenseID;
    private String title;
    private double amount;
    private Date date;

    public Income() {}

    public Income(int expenseID, String title, double amount, Date date) {
        this.expenseID = expenseID;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public Income(String title, double amount, Date date) {
        this.expenseID = 0;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
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
                "expenseID=" + expenseID +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
