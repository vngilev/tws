package ru.sbt.vng.tws.model;

import java.math.BigDecimal;

public class Card {
    private String name;
    private BigDecimal balance;

    public Card() {
    }

    public Card(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    BigDecimal getBalance() {
        return balance;
    }

    void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", balance=" + balance.toString() +
                '}';
    }
}