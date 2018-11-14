package ru.sbt.vng.tws.dao;

import java.util.*;
import java.math.BigDecimal;
import ru.sbt.vng.tws.model.Card;
import org.springframework.stereotype.Repository;

@Repository
public class CardDAO {
    private static final Map<String, Card> cardMap = new HashMap<String, Card>();

    static {
        initCards();
    }

    private static void initCards() {
        Card card1 = new Card("card1", new BigDecimal("20000.00"));
        Card card2 = new Card("card2", new BigDecimal("15000.00"));
        Card card3 = new Card("card3", new BigDecimal("1000.00"));

        cardMap.put(card1.getName(), card1);
        cardMap.put(card2.getName(), card2);
        cardMap.put(card3.getName(), card3);
    }

    public Card getCard(String cardName) {
        return cardMap.get(cardName);
    }

    public Card addCard(String cardName, String amount) {
        Card currentCard = new Card(cardName, new BigDecimal(amount));
        cardMap.put(cardName, currentCard);
        return currentCard;
    }

    public Card updateCard(Card card) {
        cardMap.put(card.getName(), card);
        return card;
    }

    public void deleteCard(String cardName) {
        cardMap.remove(cardName);
    }

    public List<Card> getAllCards() {
/*        Collection<Card> c = cardMap.values();
        List<Card> list = new ArrayList<Card>();
        list.addAll(c);
        return list;*/
        return new ArrayList<>(cardMap.values());
    }

}