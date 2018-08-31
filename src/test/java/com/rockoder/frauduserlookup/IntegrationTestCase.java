package com.rockoder.frauduserlookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {FraudUserLookupApplication.class}
)
@ActiveProfiles("testing")
@DirtiesContext
public abstract class IntegrationTestCase {

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void bootstrap() {
    RestAssured.config = RestAssuredConfig.config()
        .objectMapperConfig(
            new ObjectMapperConfig().jackson2ObjectMapperFactory((className, str) -> objectMapper)
        );
    RestAssured.port = port;
  }
}
