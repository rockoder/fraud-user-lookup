package com.rockoder.frauduserlookup.service;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.response.v1.UserFraudScoreResponse;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import com.rockoder.frauduserlookup.service.fraudcheck.strategy.factory.FraudCheckStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLookupService {

  @Autowired
  private FraudCheckStrategyFactory fraudCheckStrategyFactory;

  public UserFraudScoreResponse getNewUserScore(NewUserRequest newUserRequest) {
    return calculateUserFraudScore(newUserRequest);
  }

  private UserFraudScoreResponse calculateUserFraudScore(NewUserRequest newUserRequest) {

    UserFraudScore userFraudScore = fraudCheckStrategyFactory
        .getFraudCheckStrategyConfig().getAggregatedFraudScore(newUserRequest);

    return new UserFraudScoreResponse(newUserRequest.getUser().getId(),
        userFraudScore);
  }
}
