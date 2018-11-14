package ru.sbt.vng.tws.dto;

import java.math.BigDecimal;

public class TransferDTO {
    private String from;
    private String to;
    private BigDecimal amount;

    public TransferDTO() {
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
}