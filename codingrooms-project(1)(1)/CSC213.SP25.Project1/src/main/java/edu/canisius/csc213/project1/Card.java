package edu.canisius.csc213.project1;

import java.util.Objects;

/**
 * Represents a playing card with a suit and rank.
 */
public class Card {

    private Suit cardSuit;
    private Rank cardRank;

    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Rank { 
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, 
        JACK, QUEEN, KING, ACE 
    }

    public Card(Suit suit, Rank rank){
        cardSuit = suit;
        cardRank = rank;
    }

    public Suit getSuit(){
        return cardSuit;
    }
    public Rank getRank(){
        return cardRank;
    }

    @Override
    public String toString(){
        return "" + cardRank + " of " + cardSuit;
    }

    // TODO: Override equals() and hashCode() for comparisons.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        return Objects.equals(this.cardSuit, card.cardSuit) && Objects.equals(this.cardRank, card.cardRank);
    }
    @Override
    public int hashCode() {
        return Objects.hash(cardSuit, cardRank);
    }
}