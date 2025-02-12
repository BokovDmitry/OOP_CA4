package org.example.DTOs;

import java.util.Date;

public class Income {
    private int id;
    private String title;
    private double amount;
    private Date date;

    public Income() {}

    public Income(int id, String title, double amount, Date date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public Income(String title, double amount, Date date) {
        this.id = 0;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
