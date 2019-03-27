package com.tripactions.game.model;


import java.util.Objects;

public final class Card implements Comparable<Card> {

    /** rank of the card */
    private final Rank rank;
    /** suit of the card */
    private final Suit suit;

    /**
     * This is the constructor of the class.
     * @param rank, suit
     *            Rank and Suit to assign to the instance fields
     * @throw IllegalArgumentException if not rank in range
     * @throw IllegalArgumentException if suit not valid
     * @return Card
     */
    public Card(char rank, char suit) {
        this.rank = Rank.of(rank);
        this.suit = Suit.of(suit);
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    /**
     * This method returns the suit followed by
     * the numeric value of the card.
     * @return suit and value of card
     */
    @Override
    public String toString() {
        StringBuilder cardValue = new StringBuilder();
        cardValue.append(rank.getDisplayName());
        cardValue.append(suit.getDisplayChar());
        return cardValue.toString();

    }

    /**
     * This method returns whether this Card and o are equal. If o is not a Card,
     * the method should return false.
     * @return true if o and Card are equal; false if o and Card aren't equal
     */
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Card)) return false;
        else {
            Card other = (Card) that;
            if((getSuit() == other.getSuit()) && (getRank() == other.getRank())) {
                return true;
            }
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    /**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted by value.
     *
     * @param other
     *            The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.rank.getValue(), other.rank.getValue());
    }
}

