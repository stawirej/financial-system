package test.pyramid.strategy.presentation.service.workflow;

import application.FinancialSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.pyramid.strategy.application.FinancialSystemRiseScenarios;
import test.pyramid.strategy.presentation.service.automatization.FinancialSystemServiceAgent;

class FinancialSystemServiceScenarios {

    private FinancialSystem financialSystem;
    private FinancialSystemRiseScenarios financialSystemRiseScenarios;

    @BeforeEach
    void beforeEach() {
        financialSystem = new FinancialSystemServiceAgent();
        financialSystemRiseScenarios = new FinancialSystemRiseScenarios();
        financialSystemRiseScenarios.setFinancialSystem(financialSystem);
    }

    @AfterEach
    void afterEach() {
        ((FinancialSystemServiceAgent)financialSystem).close();
    }

    @Test
    void updateSalaryForRegularEmployee() {
        financialSystemRiseScenarios.updateSalaryForRegularEmployee();
    }
}
