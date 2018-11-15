package ru.sbt.vng.tws.dao;

import java.util.*;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;


@Repository
public class CardDAO {
    private static final Map<String, BigDecimal> cardMap = new HashMap<>();

    static {
        initCards();
    }

    private static void initCards() {
        cardMap.put("card1", new BigDecimal("20000.00"));
        cardMap.put("card2", new BigDecimal("20000.00"));
        cardMap.put("card3", new BigDecimal("20000.00"));
    }

    public BigDecimal getBalance(String cardId) {
        if(cardMap.containsKey(cardId)){
            return cardMap.get(cardId);
        }
        else{
            throw new IllegalArgumentException("Illegal cardId " + cardId);
        }
    }

    public String addCard(String cardId, String amount) {
        cardMap.put(cardId, new BigDecimal(amount));
        return cardId;
    }

    public String updateCard(String cardId, BigDecimal amount) {
        if(cardMap.containsKey(cardId)){
            cardMap.put(cardId, amount);
        }
        else{
            throw new IllegalArgumentException("Illegal cardId: " + cardId);
        }
        return cardId;
    }

    public void deleteCard(String cardId) {
        if(cardMap.containsKey(cardId)){
            cardMap.remove(cardId);
        }
        else{
            throw new IllegalArgumentException("Illegal cardId " + cardId);
        }
    }

    public Map<String, BigDecimal> getAllCards() {
/*        Collection<Card> c = cardMap.values();
        List<Card> list = new ArrayList<Card>();
        list.addAll(c);
        return list;*/
        Map<String, BigDecimal> cM = new HashMap<>();
        cM.putAll(cardMap);
        return cardMap;
    }

}