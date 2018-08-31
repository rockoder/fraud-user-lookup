package com.rockoder.frauduserlookup.service.fraudcheck.strategy;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;

public interface FraudCheckStrategy {
  public UserFraudScore getAggregatedFraudScore(NewUserRequest newUserRequest);
}
