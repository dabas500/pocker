package com.tripactions.game.model;

public enum Suit {
  HEARTS('H'),
  CLUBS('C'),
  DIAMONDS('D'),
  SPADES('S');

  private final char displayChar;

  Suit(char displayChar) {
    this.displayChar = displayChar;
  }

  public char getDisplayChar() {
    return displayChar;
  }

  public static Suit of(char displayChar) {
    return java.util.Arrays.stream(Suit.values())
        .filter(suit -> suit.getDisplayChar() == displayChar)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid suit: " + displayChar));
  }
}
