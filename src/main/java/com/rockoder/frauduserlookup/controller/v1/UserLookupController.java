package com.rockoder.frauduserlookup.controller.v1;

import com.rockoder.frauduserlookup.Rest;
import com.rockoder.frauduserlookup.response.v1.UserFraudScoreResponse;
import com.rockoder.frauduserlookup.request.v1.NewUserRequest;
import com.rockoder.frauduserlookup.service.UserLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user-lookup")
public class UserLookupController {

  @Autowired
  private UserLookupService userLookupService;

  @PostMapping(value = "/new-user", consumes = Rest.JSON, produces = Rest.JSON)
  public UserFraudScoreResponse newUserLookup(@RequestBody NewUserRequest newUser) throws Exception {
    return userLookupService.getNewUserScore(newUser);
  }
}
