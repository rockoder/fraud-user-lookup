package com.rockoder.frauduserlookup.service.fraudcheck.strategy;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import com.rockoder.frauduserlookup.service.fraudcheck.FraudCheck;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckSimpleStrategy implements FraudCheckStrategy {

  @Autowired
  private List<FraudCheck> fraudChecks;

  // Strategy - Get the highest score among all fraud checks
  @Override
  public UserFraudScore getAggregatedFraudScore(NewUserRequest newUserRequest) {
    return fraudChecks.stream()
        .map(fraudCheck -> fraudCheck.getFraudScore(newUserRequest))
        .reduce((a, b) -> { return UserFraudScore.getHighest(a, b); })
        .get();
  }
}
