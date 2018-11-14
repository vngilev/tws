package ru.sbt.vng.tws.dao;

import org.junit.Test;
import ru.sbt.vng.tws.model.Card;

public class TransferDAOTest {
    @Test
    public void testTransfer(){
        CardDAO cardDAO = new CardDAO();
        TransferDAO transferDAO = new TransferDAO();
        cardDAO.getAllCards().stream().forEach(System.out::println);
        transferDAO.addTransfer(cardDAO.getCard("card1"), cardDAO.getCard("card3"),"10000.00");
        transferDAO.getAllTransfers().stream().forEach(System.out::println);
        cardDAO.getAllCards().stream().forEach(System.out::println);
    }

}