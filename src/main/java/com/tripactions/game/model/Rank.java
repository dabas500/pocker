package com.tripactions.game.model;

public enum Rank {
  ACE(14, 'A'),
  TWO(2, '2'),
  THREE(3, '3'),
  FOUR(4, '4'),
  FIVE(5, '5'),
  SIX(6, '6'),
  SEVEN(7, '7'),
  EIGHT(8, '8'),
  NINE(9, '9'),
  TEN(10, 'T'),
  JACK(11, 'J'),
  QUEEN(12, 'Q'),
  KING(13, 'K');

  private final int value;
  private final char displayName;

  Rank(int value, char displayName) {
    this.value = value;
    this.displayName = displayName;
  }

  public int getValue() {
    return value;
  }

  public char getDisplayName() {
    return displayName;
  }

  public static Rank of(char displayName) {
    return java.util.Arrays.stream(Rank.values())
        .filter(rank -> rank.getDisplayName() == displayName)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid card value: " + displayName));
  }
}
