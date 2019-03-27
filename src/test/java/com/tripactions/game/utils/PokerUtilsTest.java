package com.tripactions.game.utils;

import com.tripactions.game.model.Hand;
import com.tripactions.game.utils.PokerUtils.PokerHand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class PokerUtilsTest {

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] {
          {Hand.fromString("TD, QD, AD, JD, KD "), PokerHand.ROYAL_FLUSH},
          {Hand.fromString("2C, 3C, TC, QC, 6C"), PokerHand.FLUSH},
          {Hand.fromString("2C, 3C, 4C, 5C, 6C"), PokerHand.STRAIGHT_FLUSH},
          {Hand.fromString("2C, 3C, 4C, 5C, 6D"), PokerHand.STRAIGHT},
          {Hand.fromString("2C, 2D, 3D, 4H, 5S"), PokerHand.ONE_PAIR},
          {Hand.fromString("2C, 2D, 3D, 4H, 4S"), PokerHand.TWO_PAIR},
          {Hand.fromString("2C, 2D, 2H, 4H, 4S"), PokerHand.FULL_HOUSE},
          {Hand.fromString("TC, TD, TH, TS, 5S"), PokerHand.FOUR_OF_A_KIND},
          {Hand.fromString("2C, 2D, 2H, 4H, 5S"), PokerHand.THREE_OF_A_KIND},
          {Hand.fromString("2C, 5S, AD, 7H, 3D"), PokerHand.HIGH_CARD},
          {Hand.fromString("TC, 8D, KC, QS, 2C"), PokerHand.HIGH_CARD},
          {Hand.fromString("9C, 9D, AC, QS, 2C"), PokerHand.ONE_PAIR}
        });
  }

  private Hand inputHand;
  private PokerHand expectedHandType;

  public PokerUtilsTest(Hand inputHand, PokerHand expectedHandType) {
    this.inputHand = inputHand;
    this.expectedHandType = expectedHandType;
  }

  @Test
  public void testHandType() {
    assertThat(inputHand.getPokerHandType(), is(equalTo(expectedHandType)));
  }
}
