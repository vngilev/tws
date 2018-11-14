package ru.sbt.vng.tws.model;

import javafx.util.Builder;

import java.math.BigDecimal;
import java.util.Date;

public class Transfer{
    private int id;
    private Card from;
    private Card to;
    private BigDecimal amount;
    private Boolean approved;
    private Date date;

    public Transfer() {
    }

    public Transfer(Card from, Card to, BigDecimal amount) {
        this.id = this.hashCode();
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = new Date();
        if (from.getBalance().compareTo(amount) != -1) {
            from.setBalance(from.getBalance().add(amount.negate()));
            to.setBalance(to.getBalance().add(amount));
            this.approved = true;
        } else this.approved = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Card getFrom() {
        return from;
    }

    public void setFrom(Card from) {
        this.from = from;
    }

    public Card getTo() {
        return to;
    }

    public void setTo(Card to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", from=" + from.getName() +
                ", to=" + to.getName() +
                ", amount=" + amount.toString() +
                ", approved=" + approved +
                ", date=" + date.toString() +
                '}';
    }
}