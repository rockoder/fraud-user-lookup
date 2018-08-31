package com.rockoder.frauduserlookup.service.fraudcheck.strategy.factory;

import com.rockoder.frauduserlookup.service.fraudcheck.strategy.FraudCheckSimpleStrategy;
import com.rockoder.frauduserlookup.service.fraudcheck.strategy.FraudCheckStrategy;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FraudCheckStrategyFactory {

  // TODO: Check dynamic loading using @RefreshScope
  @Value("${fraudlookup.strategy}")
  private String fraudCheckStrategyConfig;

  @Autowired
  private FraudCheckSimpleStrategy fraudCheckSimpleStrategy;

  @Autowired
  private Map<String, FraudCheckStrategy> fraudCheckStrategies;

  public FraudCheckStrategy getFraudCheckStrategyConfig() {
    FraudCheckStrategy fraudCheckStrategy = fraudCheckStrategies.getOrDefault(
        fraudCheckStrategyConfig,
        fraudCheckSimpleStrategy);

    return fraudCheckStrategy;
  }
}
