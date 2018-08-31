package com.rockoder.frauduserlookup.request.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockoder.frauduserlookup.domain.User;
import java.time.LocalDateTime;

public class NewUserRequest {

  private User user;
  private LocalDateTime registrationTime;
  private Integer captchaReattempts;
  private String ipAddress;
  private Boolean isEmailVerified;
  private Boolean isMobileVerified;

  public NewUserRequest(@JsonProperty(value = "user", required = true) User user,
      @JsonProperty(value = "registrationTime", required = true) LocalDateTime registrationTime,
      @JsonProperty(value = "captchaReattempts", required = true) Integer captchaReattempts,
      @JsonProperty(value = "ipAddress", required = true) String ipAddress,
      @JsonProperty(value = "isEmailVerified", required = true) Boolean isEmailVerified,
      @JsonProperty(value = "isMobileVerified", required = true) Boolean isMobileVerified) {
    this.user = user;
    this.registrationTime = registrationTime;
    this.captchaReattempts = captchaReattempts;
    this.ipAddress = ipAddress;
    this.isEmailVerified = isEmailVerified;
    this.isMobileVerified = isMobileVerified;
  }

  public User getUser() {
    return user;
  }

  public LocalDateTime getRegistrationTime() {
    return registrationTime;
  }

  public Integer getCaptchaReattempts() {
    return captchaReattempts;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public Boolean getIsEmailVerified() {
    return isEmailVerified;
  }

  public Boolean getIsMobileVerified() {
    return isMobileVerified;
  }
}
