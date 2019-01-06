package test.pyramid.strategy.integration;

import static application.FinancialSystemConfiguration.financialSystem;

import infrastructure.ProductionRepository;
import org.junit.jupiter.api.BeforeEach;
import test.pyramid.strategy.application.FinancialSystemRiseScenarios;

final class FinancialSystemIntegrationScenarios extends FinancialSystemRiseScenarios {

    @BeforeEach
    void beforeEach() {
        financialSystem = financialSystem(ProductionRepository.newInstance());
    }
}
