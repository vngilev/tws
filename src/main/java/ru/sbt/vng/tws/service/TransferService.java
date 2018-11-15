package ru.sbt.vng.tws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbt.vng.tws.dao.CardDAO;
import ru.sbt.vng.tws.dao.TransferDAO;
import ru.sbt.vng.tws.dto.TransferDTO;
import ru.sbt.vng.tws.model.Transfer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private TransferDAO transferDAO;

    public Map<String, BigDecimal> getCards() {
        return cardDAO.getAllCards();
    }

    public List<Transfer> getTransfers() {
        return transferDAO.getAllTransfers();
    }

    public List<Transfer> getTransfersByCardSender(String cardSender) {
        return transferDAO.getTransfersByCardSender(cardSender);
    }

    public List<Transfer> getTransfersByCardReceiver(String cardReceiver) {
        return transferDAO.getTransfersByCardReceiver(cardReceiver);
    }

    public Transfer addTransfer(TransferDTO transferDto) {
        BigDecimal amount = transferDto.getAmount();
        String cardSender = (transferDto.getFrom()).trim();
        String cardReceiver = (transferDto.getTo()).trim();
        BigDecimal balanceSender = new BigDecimal("0.00");
        BigDecimal balanceReceiver = new BigDecimal("0.00");

        boolean approved;


        //Если новая карта отправителя - добавим ее
        try {
            balanceSender = cardDAO.getBalance(cardSender);}
        catch (IllegalArgumentException e){
            cardDAO.addCard(cardSender,amount.toString());
            balanceSender = amount;
        }

        //Если новая карта получателя - добавим ее
        try {
            balanceReceiver = cardDAO.getBalance(cardReceiver);}
        catch (IllegalArgumentException e) {
            cardDAO.addCard(cardReceiver, "0.00");
        }

        //Если карта отправителя и получателя одна и та же - фиксируем

        if (cardSender.equals(cardReceiver)) return transferDAO.addTransfer(cardSender,
                cardReceiver, amount, false);

        if (balanceSender.compareTo(amount) != -1) {
            cardDAO.updateCard(cardSender, balanceSender.add(amount.negate()));
            cardDAO.updateCard(cardReceiver, balanceReceiver.add(amount));
            approved = true;
        } else approved = false;

        return transferDAO.addTransfer(cardSender,
                cardReceiver, amount, approved);
    }
}