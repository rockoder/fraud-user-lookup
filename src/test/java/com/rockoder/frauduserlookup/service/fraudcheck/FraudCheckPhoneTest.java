package com.rockoder.frauduserlookup.service.fraudcheck;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.rockoder.frauduserlookup.builder.domain.NewUserRequestBuilder;
import com.rockoder.frauduserlookup.builder.domain.UserBuilder;
import com.rockoder.frauduserlookup.domain.User;
import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class FraudCheckPhoneTest {

  private FraudCheckPhone fraudCheckPhone = new FraudCheckPhone();

  private String userId;
  private String emailId;
  private String phone;

  private User user;

  private String ip;

  @Before
  public void setup() {
    userId = "rockoder";
    emailId = "ganesh@rockoder.com";
    phone = "1234567890";

    user = new UserBuilder()
        .withId(userId)
        .withEmail(emailId)
        .withPhone(phone)
        .build();
  }

  @Test
  public void givenVerifiedPhoneShouldReturnLowFraudScore() {
    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(user)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(true)
        .withIsMobileVerified(true)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    UserFraudScore userFraudScore = fraudCheckPhone.getFraudScore(newUserRequest);

    assertThat(userFraudScore, equalTo(UserFraudScore.LOW));
  }

  @Test
  public void givenUnverifiedPhoneShouldReturnMediumFraudScore() {
    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(user)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(true)
        .withIsMobileVerified(false)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    UserFraudScore userFraudScore = fraudCheckPhone.getFraudScore(newUserRequest);

    assertThat(userFraudScore, equalTo(UserFraudScore.MEDIUM));
  }
}
