package ru.sbt.vng.tws.model;

import java.math.BigDecimal;

public class Card {

    private String cardId;
    private BigDecimal balance;
    //there is may be add others parameters of card


    public Card() {
    }

    public Card(String cardId, BigDecimal balance) {
        this.cardId = cardId;
        this.balance = balance;
    }

    public String getCardId() {
        return cardId;
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
                "cardId='" + cardId + '\'' +
                ", balance=" + balance.toString() +
                '}';
    }
}