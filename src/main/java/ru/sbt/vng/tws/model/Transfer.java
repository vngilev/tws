package ru.sbt.vng.tws.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transfer{
    private int id;
    private String from;
    private String to;
    private BigDecimal amount;
    private Boolean approved;
    private Date date;

    public Transfer() {
    }

    public Transfer(String from, String to, BigDecimal amount, Boolean approved) {
        this.id = this.hashCode();
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = new Date();
        this.approved = approved;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
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
                ", from=" + from +
                ", to=" + to +
                ", amount=" + amount.toString() +
                ", approved=" + approved +
                ", date=" + date.toString() +
                '}';
    }
}