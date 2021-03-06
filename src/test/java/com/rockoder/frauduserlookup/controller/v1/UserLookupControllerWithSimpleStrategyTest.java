package com.rockoder.frauduserlookup.controller.v1;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.rockoder.frauduserlookup.IntegrationTestCase;
import com.rockoder.frauduserlookup.builder.domain.NewUserRequestBuilder;
import com.rockoder.frauduserlookup.builder.domain.UserBuilder;
import com.rockoder.frauduserlookup.domain.User;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "fraudlookup.strategy=fraudCheckSimpleStrategy")
public class UserLookupControllerWithSimpleStrategyTest extends IntegrationTestCase {

  private static final String NEW_USER_ENDPOINT = "/v1/user-lookup/new-user";

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
  public void givenVerifiedUserShouldReturnLowFraudScore() {
    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(user)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(true)
        .withIsMobileVerified(true)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    Response response = given()
        .contentType(ContentType.JSON)
        .body(newUserRequest)
        .post(NEW_USER_ENDPOINT);

    response.then()
        .statusCode(HttpStatus.OK.value())
        .body("userId", is(userId))
        .body("score", is("LOW"));
  }

  @Test
  public void givenUnverifiedEmailAndPhoneShouldReturnMediumFraudScore() {
    NewUserRequest newUserRequest = new NewUserRequestBuilder()
        .withUser(user)
        .withCaptchaReattempts(1)
        .withIpAddress(ip)
        .withIsEmailVerified(false)
        .withIsMobileVerified(false)
        .withRegistrationTime(LocalDateTime.now())
        .build();

    Response response = given()
        .contentType(ContentType.JSON)
        .body(newUserRequest)
        .post(NEW_USER_ENDPOINT);

    response.then()
        .statusCode(HttpStatus.OK.value())
        .body("userId", is(userId))
        .body("score", is("MEDIUM"));
  }
}
