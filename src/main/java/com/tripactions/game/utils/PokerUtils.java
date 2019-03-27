package com.tripactions.game.utils;

import com.tripactions.game.model.Card;
import com.tripactions.game.model.Rank;
import com.tripactions.game.model.Suit;
import java.util.*;

public class PokerUtils {

    public enum PokerHand {
        HIGH_CARD(1), ONE_PAIR(2), TWO_PAIR(3), THREE_OF_A_KIND(4), STRAIGHT(5),
        FLUSH(6), FULL_HOUSE(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10), NOTDETERMINED(-1);

        PokerHand(int value){ this.value = value;}

        private int value;

        public int getValue() {
            return value;
        }
    }

    private static final int SIZE_OF_HAND = 5;
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = SIZE_OF_HAND-1;

    private static boolean isFlush(Suit [] suits){
        Suit initialSuit = suits[MIN_INDEX];
        for(Suit suit: suits){
            if(initialSuit != suit)
                return false;
        }
        return true;
    }

    private static boolean isStraight(Rank [] values ){
        return values[MAX_INDEX].getValue() - values[MIN_INDEX].getValue() == SIZE_OF_HAND -1;
    }

    private static PokerHand checkRoyalStraightFlush(Rank [] values, Suit[] suits){
        boolean straightFlag = isStraight(values);
        boolean flushFlag = isFlush(suits);

        if(straightFlag && flushFlag){
            if(values[MAX_INDEX] == Rank.ACE) return PokerHand.ROYAL_FLUSH;
            return PokerHand.STRAIGHT_FLUSH;
        }
        if(flushFlag) return PokerHand.FLUSH;
        else if(straightFlag) return PokerHand.STRAIGHT;
        else return PokerHand.NOTDETERMINED;
    }

    private static PokerHand checkFourOfKindOrFullHouse(Rank [] values){

        int min = values[MIN_INDEX].getValue();
        int max = values[MAX_INDEX].getValue();
        int cardsWithMinRank = 0;
        int cardsWithMaxRank = 0;

        for(int counter = MIN_INDEX; counter <= MAX_INDEX; counter++){
            if(values[counter].getValue() == min)
                cardsWithMinRank++;
            if(values[counter].getValue() == max)
                cardsWithMaxRank++;
        }
        if(cardsWithMinRank == 4 || cardsWithMaxRank == 4) return PokerHand.FOUR_OF_A_KIND;
        if(cardsWithMinRank + cardsWithMaxRank == SIZE_OF_HAND) return PokerHand.FULL_HOUSE;
        else return PokerHand.NOTDETERMINED;
    }


    private static boolean isThreeOfKind(Rank [] values){
        boolean a1, a2, a3;
        a1 = values[MIN_INDEX].getValue() == values[MIN_INDEX+1].getValue() &&
                values[MIN_INDEX+1].getValue() == values[MIN_INDEX+2].getValue() ;

        a2 = values[MIN_INDEX+1].getValue() == values[MIN_INDEX+2].getValue() &&
                values[MIN_INDEX+2].getValue() == values[MIN_INDEX+3].getValue() ;


        a3 = values[MIN_INDEX+2].getValue() == values[MIN_INDEX+3].getValue() &&
                values[MIN_INDEX+3].getValue() == values[MIN_INDEX+4].getValue() ;
        return( a1 || a2 || a3 );

    }

    private static boolean isTwoPair(Rank [] values)
    {
        boolean a1, a2, a3;

        a1 = values[MIN_INDEX].getValue() == values[MIN_INDEX+1].getValue() &&
                values[MIN_INDEX+2].getValue() == values[MIN_INDEX+3].getValue() ;

        a2 = values[MIN_INDEX].getValue() == values[MIN_INDEX+1].getValue() &&
                values[MIN_INDEX+3].getValue() == values[MIN_INDEX+4].getValue() ;

        a3 = values[MIN_INDEX].getValue() == values[MIN_INDEX+1].getValue() &&
                values[MIN_INDEX+3].getValue() == values[MIN_INDEX+4].getValue() ;

        return( a1 || a2 || a3 );
    }

    private static boolean isOnePair(Rank [] values)
    {
        boolean onePair = false;

        for(int i = MIN_INDEX; i < MAX_INDEX; i++)
            if(values[i+1].getValue() - values[i].getValue() == 0) {
                onePair = true;
                break;
            }
        return onePair;
    }
    public static PokerHand findPokerHand(List<Card> cards){

        Rank [] values = new Rank[cards.size()];
        Suit[] suits = new Suit[cards.size()];

        if ( cards.size() != SIZE_OF_HAND )
            throw new IllegalArgumentException("INVALID HAND");

        Collections.sort(cards);
        for(int counter = MIN_INDEX; counter <= MAX_INDEX; counter++){
            values[counter] = cards.get(counter).getRank();
            suits[counter] = cards.get(counter).getSuit();
        }

        PokerHand typeFound = checkRoyalStraightFlush(values, suits);

        if(typeFound == PokerHand.NOTDETERMINED)
            typeFound = checkFourOfKindOrFullHouse(values);

        if(typeFound == PokerHand.NOTDETERMINED)
        {
            if(isThreeOfKind(values)) typeFound = PokerHand.THREE_OF_A_KIND;
            else if(isTwoPair(values)) typeFound = PokerHand.TWO_PAIR;
            else if(isOnePair(values)) typeFound =  PokerHand.ONE_PAIR;
            else typeFound = PokerHand.HIGH_CARD;
        }
        return typeFound;
    }
}
