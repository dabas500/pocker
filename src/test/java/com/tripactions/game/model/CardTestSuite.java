package com.tripactions.game.model;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class CardTestSuite {

  @RunWith(Parameterized.class)
  public static class CardRankTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(
          new Object[][] {
            {new Card('2', 'S'), Rank.TWO},
            {new Card('K', 'C'), Rank.KING},
            {new Card('J', 'D'), Rank.JACK},
            {new Card('A', 'S'), Rank.ACE}
          });
    }

    private Card card;
    private Rank rank;

    public CardRankTest(Card card, Rank rank) {
      this.card = card;
      this.rank = rank;
    }

    @Test
    public void testCardRank() {
      assertEquals(card.getRank(), rank);
    }
  }

  @RunWith(Parameterized.class)
  public static class CardSuitTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(
          new Object[][] {
            {new Card('2', 'C'), Suit.CLUBS},
            {new Card('K', 'D'), Suit.DIAMONDS},
            {new Card('J', 'H'), Suit.HEARTS},
            {new Card('A', 'S'), Suit.SPADES}
          });
    }

    private Card card;
    private Suit suit;

    public CardSuitTest(Card card, Suit suit) {
      this.card = card;
      this.suit = suit;
    }

    @Test
    public void testCardSuit() {
      assertEquals(card.getSuit(), suit);
    }
  }

  public static class InvalidCardTestClass {

    private Card thisCard = new Card('2', 'D');
    private Card thatCard = new Card('2', 'D');
    private Card otherCard = new Card('2', 'S');

    @Test(expected = IllegalArgumentException.class)
    public void invalidRank() throws Exception {
      new Card('1', 'D');
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidSuit() throws Exception {
      new Card('2', 'T');
    }

    @Test
    public void equalsTest() {
      assertEquals(thatCard, thisCard);
      assertTrue(thatCard.equals(thisCard));
      assertTrue(thisCard.equals(thisCard));
      assertFalse(thisCard.equals(otherCard));
      assertFalse(thisCard.equals(new Object()));
    }

    @Test
    public void hashCodeTest() {
      assertEquals(thatCard.hashCode(), thisCard.hashCode());
      assertNotEquals(otherCard.hashCode(), thisCard.hashCode());
    }
  }
}
