package com.rockoder.frauduserlookup.service.fraudcheck;

import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class FraudCheckEmail implements FraudCheck {

  @Override
  public UserFraudScore getFraudScore(NewUserRequest newUserRequest) {
    UserFraudScore userFraudScore = UserFraudScore.defaultUserFraudScore();

    if (!passHighPriorityChecks(newUserRequest)) {
      return UserFraudScore.HIGH;
    }

    if (!passMediumPriorityChecks(newUserRequest)) {
      return UserFraudScore.MEDIUM;
    }

    return userFraudScore;
  }

  private boolean passHighPriorityChecks(NewUserRequest newUserRequest) {
    String emailId = newUserRequest.getUser().getEmail();
    return isValid(emailId);
  }

  private boolean passMediumPriorityChecks(NewUserRequest newUserRequest) {
    String emailId = newUserRequest.getUser().getEmail();

    return !containsRestrictedWords(emailId) && newUserRequest.getIsEmailVerified();
  }

  private boolean isValid(String emailId) {
    return EmailValidator.getInstance(false).isValid(emailId);
  }

  private boolean containsRestrictedWords(String emailId) {
    Set<String> restrictedWords = new HashSet<>();
    restrictedWords.add("dummy");
    restrictedWords.add("fake");
    restrictedWords.add("throwaway");

    return restrictedWords.parallelStream().
        filter(emailId::contains).
        findFirst().
        isPresent();
  }
}
