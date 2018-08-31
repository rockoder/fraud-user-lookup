package com.rockoder.frauduserlookup.service.fraudcheck;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckPhone implements FraudCheck {

  @Override
  public UserFraudScore getFraudScore(NewUserRequest newUserRequest) {
    UserFraudScore userFraudScore = UserFraudScore.defaultUserFraudScore();

    if (!newUserRequest.getIsMobileVerified()) {
      userFraudScore = UserFraudScore.MEDIUM;
    }

    return userFraudScore;
  }
}
