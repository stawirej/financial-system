package test.pyramid.strategy.presentation.service.workflow;

import application.FinancialSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import test.pyramid.strategy.application.FinancialSystemRise_scenarios;
import test.pyramid.strategy.presentation.service.automatization.FinancialSystemServiceAgent;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FinancialSystemService_scenarios {

    private FinancialSystem financialSystem;
    private FinancialSystemRise_scenarios financialSystemRiseScenarios;

    @BeforeEach
    void beforeEach() {
        financialSystem = new FinancialSystemServiceAgent();
        financialSystemRiseScenarios = new FinancialSystemRise_scenarios();
        financialSystemRiseScenarios.setFinancialSystem(financialSystem);
    }

    @AfterEach
    void afterEach() {
        ((FinancialSystemServiceAgent)financialSystem).close();
    }

    @Test
    void Update_salary_for_regular_employee() {
        financialSystemRiseScenarios.Update_salary_for_regular_employee();
    }
}
