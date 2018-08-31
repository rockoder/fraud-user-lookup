package com.rockoder.frauduserlookup.builder.domain;

import com.rockoder.frauduserlookup.domain.User;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import java.time.LocalDateTime;

public class NewUserRequestBuilder {

  private User user;
  private LocalDateTime registrationTime;
  private Integer captchaReattempts;
  private String ipAddress;
  private Boolean isEmailVerified;
  private Boolean isMobileVerified;

  public NewUserRequest build() {
    return new NewUserRequest(
        user,
        registrationTime,
        captchaReattempts,
        ipAddress,
        isEmailVerified,
        isMobileVerified);
  }

  public NewUserRequestBuilder withUser(User user) {
    this.user = user;
    return this;
  }

  public NewUserRequestBuilder withRegistrationTime(LocalDateTime registrationTime) {
    this.registrationTime = registrationTime;
    return this;
  }

  public NewUserRequestBuilder withCaptchaReattempts(Integer captchaReattempts) {
    this.captchaReattempts = captchaReattempts;
    return this;
  }

  public NewUserRequestBuilder withIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  public NewUserRequestBuilder withIsEmailVerified(Boolean isEmailVerified) {
    this.isEmailVerified = isEmailVerified;
    return this;
  }

  public NewUserRequestBuilder withIsMobileVerified(Boolean isMobileVerified) {
    this.isMobileVerified = isMobileVerified;
    return this;
  }
}
