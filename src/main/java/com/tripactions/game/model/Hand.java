package com.tripactions.game.model;

import com.tripactions.game.utils.PokerUtils;
import com.tripactions.game.utils.PokerUtils.PokerHand;
import java.util.*;

public final class Hand implements Comparable<Hand> {

  private final PokerHand pokerHandType;
  private final List<Card> pokerHand;

  private Hand(PokerHand pokerHandType, List<Card> pokerHand) {
    this.pokerHandType = pokerHandType;
    this.pokerHand = pokerHand;
  }

  private static boolean validateHand(List<Card> pokerHand) throws IllegalArgumentException {
    if (pokerHand.stream().distinct().count() == pokerHand.size()) return true;
    else throw new IllegalArgumentException("Invalid Hand. Duplicate Cards" + pokerHand);
  }

  public static Hand fromString(String cardsHand) {
    Objects.requireNonNull(cardsHand, "Hand must not be null");

    String[] cardsArray = cardsHand.toUpperCase().split(",");

    List<Card> cards = new LinkedList<>();

    for (String card : cardsArray) {
      String validCardString = card.trim();
      if (validCardString.length() != 2)
        throw new IllegalArgumentException("Incorrect card value" + validCardString);
      cards.add(new Card(validCardString.charAt(0), validCardString.charAt(1)));
    }

    if (cards.size() != 5)
      throw new IllegalArgumentException(
          "Incorrect Hand.  5 cards required. Given:  " + cards.size());

    PokerHand handType = PokerUtils.findPokerHand(cards);

    validateHand(cards);

    return new Hand(handType, cards);
  }

  public PokerUtils.PokerHand getPokerHandType() {
    return pokerHandType;
  }

  public List<Card> getPokerHand() {
    return List.copyOf(pokerHand);
  }

  @Override
  public String toString() {
    return "<hand " + pokerHand + ", " + pokerHandType + ">";
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Hand)) return false;
    else {
      Hand that = (Hand) obj;
      if (pokerHand.equals(that.pokerHand)) {
        return true;
      }
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(pokerHand);
  }

  @Override
  public int compareTo(Hand that) {
    return Integer.compare(this.pokerHandType.getValue(), that.pokerHandType.getValue());
  }
}
