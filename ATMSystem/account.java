package com.lxh.ATMSystem;

public class account {
    private String card_Id;
    private String user_Name;
    private String password;
    private double money;
    private double quote_money;

    public account() {
    }

    public account(String card_Id, String user_Name, String password, double money, double quote_money) {
        this.card_Id = card_Id;
        this.user_Name = user_Name;
        this.password = password;
        this.money = money;
        this.quote_money = quote_money;
    }

    public String getCard_Id() {
        return card_Id;
    }

    public void setCard_Id(String card_Id) {
        this.card_Id = card_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuote_money() {
        return quote_money;
    }

    public void setQuote_money(double quote_money) {
        this.quote_money = quote_money;
    }
}
