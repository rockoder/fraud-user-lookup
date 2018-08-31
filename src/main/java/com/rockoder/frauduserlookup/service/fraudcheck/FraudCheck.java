package com.rockoder.frauduserlookup.service.fraudcheck;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.response.v1.UserFraudScoreResponse;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface FraudCheck {
  public UserFraudScore getFraudScore(NewUserRequest newUserRequest);
}
