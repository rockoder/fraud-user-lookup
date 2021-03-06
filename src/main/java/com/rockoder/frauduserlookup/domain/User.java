package com.rockoder.frauduserlookup.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
  private String id;
  private String email;
  private String phone;

  public User(@JsonProperty("id") String id,
      @JsonProperty("email") String email,
      @JsonProperty("phone") String phone) {
    this.id = id;
    this.email = email;
    this.phone = phone;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }
}
