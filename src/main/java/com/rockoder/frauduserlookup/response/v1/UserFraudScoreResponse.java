package com.rockoder.frauduserlookup.response.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockoder.frauduserlookup.domain.UserFraudScore;

public class UserFraudScoreResponse {

  @JsonProperty("userId")
  private String userId;

  @JsonProperty("score")
  private UserFraudScore score;

  public UserFraudScoreResponse(@JsonProperty("userId") String userId) {
    this(userId, UserFraudScore.LOW);
  }

  public UserFraudScoreResponse(@JsonProperty("userId") String userId,
      @JsonProperty("score") UserFraudScore score) {
    this.userId = userId;
    this.score = score;
  }

  public String getUserId() {
    return userId;
  }

  public UserFraudScore getScore() {
    return score;
  }
}
