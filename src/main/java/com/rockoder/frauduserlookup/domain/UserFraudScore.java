package com.rockoder.frauduserlookup.domain;

public enum UserFraudScore {
  LOW(100),
  MEDIUM(200),
  HIGH(300);

  private int score;

  private UserFraudScore(int score) {
    this.score = score;
  }

  public static UserFraudScore defaultUserFraudScore() {
    return defaultOptimistic();
  }

  private static UserFraudScore defaultOptimistic() {
    return LOW;
  }

  public static UserFraudScore getHighest(UserFraudScore score1, UserFraudScore score2) {
    if (score1.score > score2.score) return score1;
    return score2;
  }
}
