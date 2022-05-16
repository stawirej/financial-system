package test.pyramid.strategy.presentation.frontend.workflow;

import application.FinancialSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import test.pyramid.strategy.application.FinancialSystemRise_scenarios;
import test.pyramid.strategy.presentation.frontend.automatization.FinancialSystemFrontendAgent;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FinancialSystemFrontend_scenarios {

    private FinancialSystem financialSystem;
    private FinancialSystemRise_scenarios financialSystemRiseScenarios;

    @BeforeEach
    void beforeEach() {
        financialSystem = new FinancialSystemFrontendAgent();
        financialSystemRiseScenarios = new FinancialSystemRise_scenarios();
        financialSystemRiseScenarios.setFinancialSystem(financialSystem);
    }

    @AfterEach
    void afterEach() {
        ((FinancialSystemFrontendAgent)financialSystem).close();
    }

    @Test
    void updateSalaryForRegularEmployee() {
        financialSystemRiseScenarios.Update_salary_for_regular_employee();
    }
}
