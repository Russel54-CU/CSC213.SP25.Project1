package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Deck{
    private final List<Card> cards;

    public Deck(int size) {
        if ((size % 4 != 0) || (size > 52) || (size < 4)){
            throw new IllegalArgumentException("Deck size must be initialized with an integer that is at least 4, at most 52, and must be divisible by 4.");
        }
        cards = new ArrayList<>();
        int count = 0;

        for (Card.Suit suit : Card.Suit.values()){
            for (Card.Rank rank : Card.Rank.values()){
                if (count < size){
                    cards.add(new Card(suit, rank));
                    count += 1;
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.size() == 0){
        throw new NoSuchElementException("Deck is empty");
        }
        Card drawn = cards.get(0);
        cards.remove(0);
        return drawn;
    }

    public void add(Card card){
        cards.add(card);
        return;
    }

    public int size() {
        return cards.size();
    }
}