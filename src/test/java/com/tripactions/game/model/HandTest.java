package com.tripactions.game.model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class HandTest {
  Hand thisHand = Hand.fromString("7S, 5C, 5S, 5D, 5H");
  Hand thatHand = Hand.fromString("7S, 5C, 5S, 5D, 5H");
  Hand otherHand = Hand.fromString("KS, KC, 5S, 5C, 6D");

  @Test(expected = IllegalArgumentException.class)
  public void invalidhandSize() throws Exception {
    Hand.fromString("7S, 5S, 5S, 5D");
  }

  @Test(expected = IllegalArgumentException.class)
  public void duplicateCardsInHand() throws Exception {
    Hand.fromString("7S, 5S, 5S, 5D, 5S");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCardValue() throws Exception {
    Hand.fromString("7S, 5SS, 5S, 5D, 5S");
    Hand.fromString("7S5SS, 5S, 5D, 5S");
    Hand.fromString("7S, ;5SS, 5S, 5D, 5S");
  }

  @Test
  public void equalsTest() {
    assertEquals(thatHand, thisHand);
    assertTrue(thatHand.equals(thisHand));
    assertTrue(thisHand.equals(thisHand));
    assertFalse(thisHand.equals(otherHand));
  }

  @Test
  public void hashCodeTest() {
    assertEquals(thatHand.hashCode(), thisHand.hashCode());
    assertNotEquals(otherHand.hashCode(), thisHand.hashCode());
  }

  @Test
  public void toStringTest() {
    assertEquals(thatHand.toString(), "<hand [5C, 5S, 5D, 5H, 7S], FOUR_OF_A_KIND>");
  }

  @Test
  public void sortOrderByHandTypeTest() {
    Hand flush = Hand.fromString("7S, 5C, 5H, 5D, 5S");
    Hand onePair = Hand.fromString("KS, KC, 5S, 6S, 7S");
    Hand twoPair = Hand.fromString("KS, KC, 5S, 5C, 6D");
    List<Hand> orderedList = List.of(onePair, twoPair, flush);
    List<Hand> unorderedList = List.of(twoPair, flush, onePair);

    List<Hand> testList = new ArrayList<>();
    testList.add(twoPair);
    testList.add(flush);
    testList.add(onePair);
    Collections.sort(testList);

    assertEquals(testList, orderedList);
    assertNotEquals(testList, unorderedList);
  }
}
