package test.pyramid.strategy.presentation.frontend.workflow;

import application.FinancialSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.pyramid.strategy.application.FinancialSystemRiseScenarios;
import test.pyramid.strategy.presentation.frontend.automatization.FinancialSystemFrontendAgent;

class FinancialSystemFrontendScenarios {

    private FinancialSystem financialSystem;
    private FinancialSystemRiseScenarios financialSystemRiseScenarios;

    @BeforeEach
    void beforeEach() {
        financialSystem = new FinancialSystemFrontendAgent();
        financialSystemRiseScenarios = new FinancialSystemRiseScenarios();
        financialSystemRiseScenarios.setFinancialSystem(financialSystem);
    }

    @AfterEach
    void afterEach() {
        ((FinancialSystemFrontendAgent)financialSystem).close();
    }

    @Test
    void updateSalaryForRegularEmployee() {
        financialSystemRiseScenarios.updateSalaryForRegularEmployee();
    }
}
