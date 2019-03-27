package com.tripactions.game;


import com.tripactions.game.model.Hand;
import com.tripactions.game.utils.PokerUtils;
import org.apache.camel.main.Main;
import java.util.*;


/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) {
        Main main = new Main();

        Hand flush = Hand.fromString("7S, 5C, 5H, 5D, 5S");
        Hand onePair = Hand.fromString("KS, KC, 5S, 6S, 7S");
        Hand twoPair = Hand.fromString("KS, KC, 5S, 5C, 6D");

        List<Hand> hands = new ArrayList<>();
        hands.add(flush);
        hands.add(onePair);
        hands.add(twoPair);
        //List<Hand> hands = List.of(flush, onePair, twoPair);
        PokerUtils.PokerHand x = flush.getPokerHandType();

        //flush.getPockerHand().add(new Card('5','S'));
        Collections.sort(hands);
        for(Hand hand: hands)
            System.out.println(hand + "   "+ hand.getPokerHandType().getValue());


    }
}

