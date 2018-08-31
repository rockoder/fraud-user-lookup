package com.rockoder.frauduserlookup.service.fraudcheck.strategy;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import com.rockoder.frauduserlookup.service.fraudcheck.FraudCheck;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckAdvanceStrategy implements FraudCheckStrategy {

  @Autowired
  private List<FraudCheck> fraudChecks;

  // Strategy - Two or more MEDIUM will be considered as HIGH
  @Override
  public UserFraudScore getAggregatedFraudScore(NewUserRequest newUserRequest) {
    Map<UserFraudScore, Long> map = fraudChecks.stream()
        .map(fraudCheck -> fraudCheck.getFraudScore(newUserRequest))
        .filter(userFraudScore -> userFraudScore != UserFraudScore.LOW)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    if (map.containsKey(UserFraudScore.HIGH)) {
      return UserFraudScore.HIGH;
    } else if (map.containsKey(UserFraudScore.MEDIUM)) {
      if (map.get(UserFraudScore.MEDIUM) > 1) {
        return UserFraudScore.HIGH;
      }
      return UserFraudScore.MEDIUM;
    } else {
      return UserFraudScore.LOW;
    }
  }
}
