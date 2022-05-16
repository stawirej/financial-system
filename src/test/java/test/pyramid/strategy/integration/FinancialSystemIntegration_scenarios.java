package test.pyramid.strategy.integration;

import static application.FinancialSystemConfiguration.financialSystem;

import infrastructure.ProductionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import test.pyramid.strategy.application.FinancialSystemRise_scenarios;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class FinancialSystemIntegration_scenarios extends FinancialSystemRise_scenarios {

    @BeforeEach
    void beforeEach() {
        financialSystem = financialSystem(ProductionRepository.newInstance());
    }
}
