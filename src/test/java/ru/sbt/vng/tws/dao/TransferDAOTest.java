package ru.sbt.vng.tws.dao;

import org.junit.Test;
import ru.sbt.vng.tws.model.Card;

import java.math.BigDecimal;

public class TransferDAOTest {
    @Test
    public void testTransfer(){
        CardDAO cardDAO = new CardDAO();
        TransferDAO transferDAO = new TransferDAO();
        transferDAO.addTransfer("card1", "card3",new BigDecimal("10000.00"),true);
        transferDAO.addTransfer("card5", "card3",new BigDecimal("10000.00"),true);
        transferDAO.addTransfer("card4", "card3",new BigDecimal("10000.00"),true);
        transferDAO.addTransfer("card1", "card3",new BigDecimal("10000.00"),true);
        transferDAO.getAllTransfers().stream().forEach(System.out::println);
    }

}