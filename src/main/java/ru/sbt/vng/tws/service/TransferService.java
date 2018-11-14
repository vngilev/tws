package ru.sbt.vng.tws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sbt.vng.tws.dao.CardDAO;
import ru.sbt.vng.tws.dao.TransferDAO;
import ru.sbt.vng.tws.dto.TransferDTO;
import ru.sbt.vng.tws.model.Card;
import ru.sbt.vng.tws.model.Transfer;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private TransferDAO transferDAO;

    public List<Card> getCards() {
        List<Card> list = cardDAO.getAllCards();
        return list;
    }

    public List<Transfer> getTransfers() {
        List<Transfer> list = transferDAO.getAllTransfers();
        return list;
    }

    public List<Transfer> getTransfersBySenderName(String cardSender) {
        return transferDAO.getTransfersBySenderName(cardSender);
    }

    public List<Transfer> getTransfersByRecieverName(String cardReciever) {
        return transferDAO.getTransfersByRecieverName(cardReciever);
    }

    public Transfer addTransfer(TransferDTO transferDto) {

        return transferDAO.addTransfer(cardDAO.getCard(transferDto.getFrom()),
                cardDAO.getCard(transferDto.getTo()),transferDto.getAmount().toString());
    }

}