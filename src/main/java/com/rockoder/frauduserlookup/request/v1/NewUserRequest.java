package com.rockoder.frauduserlookup.request.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rockoder.frauduserlookup.domain.User;
import java.time.LocalDateTime;

public class NewUserRequest {

  @JsonProperty("user")
  private User user;

  @JsonProperty("registrationTime")
  private LocalDateTime registrationTime;

  @JsonProperty("captchaReattempts")
  private Integer captchaReattempts;

  @JsonProperty("ipAddress")
  private String ipAddress;

  @JsonProperty("isEmailVerified")
  private Boolean isEmailVerified;

  @JsonProperty("isMobileVerified")
  private Boolean isMobileVerified;

  public NewUserRequest(@JsonProperty("user") User user,
      @JsonProperty("registrationTime") LocalDateTime registrationTime,
      @JsonProperty("captchaReattempts") Integer captchaReattempts,
      @JsonProperty("ipAddress") String ipAddress,
      @JsonProperty("isEmailVerified") Boolean isEmailVerified,
      @JsonProperty("isMobileVerified") Boolean isMobileVerified) {
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
