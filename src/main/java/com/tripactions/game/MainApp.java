package com.tripactions.game;

import com.tripactions.game.model.Hand;
import com.tripactions.game.utils.PokerUtils;
import java.util.*;

public class MainApp {

  public static void main(String... args) {

    Hand flush = Hand.fromString("7S, 5C, 5H, 5D, 5S");
    Hand onePair = Hand.fromString("KS, KC, 5S, 6S, 7S");
    Hand twoPair = Hand.fromString("KS, KC, 5S, 5C, 6D");

    List<Hand> hands = new ArrayList<>();
    hands.add(flush);
    hands.add(onePair);
    hands.add(twoPair);

    PokerUtils.PokerHand x = flush.getPokerHandType();

    Collections.sort(hands);
    for (Hand hand : hands) System.out.println(hand + "   " + hand.getPokerHandType().getValue());
  }
}
