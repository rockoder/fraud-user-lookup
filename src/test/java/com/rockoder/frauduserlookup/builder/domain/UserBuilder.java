package com.rockoder.frauduserlookup.builder.domain;

import com.rockoder.frauduserlookup.domain.User;

public class UserBuilder {

  private String id;
  private String email;
  private String phone;

  public User build() {
    return new User(
        id,
        email,
        phone);
  }

  public UserBuilder withId(String id) {
    this.id = id;
    return this;
  }

  public UserBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserBuilder withPhone(String phone) {
    this.phone = phone;
    return this;
  }
}
