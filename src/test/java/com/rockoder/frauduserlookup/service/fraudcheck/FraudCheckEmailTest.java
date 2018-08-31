package com.rockoder.frauduserlookup.service.fraudcheck;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.rockoder.frauduserlookup.FraudUserLookupApplication;
import com.rockoder.frauduserlookup.FraudUserLookupApplicationTests;
import com.rockoder.frauduserlookup.IntegrationTestCase;
import com.rockoder.frauduserlookup.builder.domain.NewUserRequestBuilder;
import com.rockoder.frauduserlookup.builder.domain.UserBuilder;
import com.rockoder.frauduserlookup.domain.User;
import com.rockoder.frauduserlookup.domain.UserFraudScore;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

public class FraudCheckEmailTest {

  private FraudCheckEmail fraudCheckEmail = new FraudCheckEmail();

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
  public void givenValidEmailShouldReturnLowFraudScore() {
    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(user)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(true)
        .withIsMobileVerified(true)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    UserFraudScore userFraudScore = fraudCheckEmail.getFraudScore(newUserRequest);

    assertThat(userFraudScore, equalTo(UserFraudScore.LOW));
  }

  @Test
  public void givenInvalidEmailShouldReturnHighFraudScore() {
    User userWithInvalidEmail = new UserBuilder()
        .withId(userId)
        .withEmail("invalidemail.com")
        .withPhone(phone)
        .build();

    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(userWithInvalidEmail)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(false)
        .withIsMobileVerified(true)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    UserFraudScore userFraudScore = fraudCheckEmail.getFraudScore(newUserRequest);

    assertThat(userFraudScore, equalTo(UserFraudScore.HIGH));
  }

  @Test
  public void givenEmailContainsRestrictedWordsShouldReturnMediumFraudScore() {
    User userWithInvalidEmail = new UserBuilder()
        .withId(userId)
        .withEmail("fakeuser123@gmail.com")
        .withPhone(phone)
        .build();

    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(userWithInvalidEmail)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(true)
        .withIsMobileVerified(true)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    UserFraudScore userFraudScore = fraudCheckEmail.getFraudScore(newUserRequest);

    assertThat(userFraudScore, equalTo(UserFraudScore.MEDIUM));
  }

  @Test
  public void givenUnverifiedEmailShouldReturnMediumFraudScore() {
    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(user)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(false)
        .withIsMobileVerified(true)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    UserFraudScore userFraudScore = fraudCheckEmail.getFraudScore(newUserRequest);

    assertThat(userFraudScore, equalTo(UserFraudScore.MEDIUM));
  }
}
