package ice.cream.cone.anti.pattern.strategy.workflow;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import ice.cream.cone.anti.pattern.strategy.automatization.FinancialSystemAgent;
import ice.cream.cone.anti.pattern.strategy.automatization.FinancialSystemServiceAgent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinancialSystemServiceScenarios {

    private FinancialSystemAgent financialSystemAgent;

    @BeforeEach
    void beforeEach() {
        financialSystemAgent = new FinancialSystemServiceAgent();
    }

    @AfterEach
    void afterEach() {
        financialSystemAgent.close();
    }

    @Test
    void updateSalaryForRegularEmployee() {
        // Given
        financialSystemAgent.add(employee().withId(1L).asRegular().withSalary(1000));

        // When
        financialSystemAgent.giveRise(1L);

        // Then
        var updatedEmployee = financialSystemAgent.getEmployeeBy(1L);
        then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asRegular().withSalary(1100));
    }

    @Test
    void updateSalaryForManagerEmployee() {

        // Given
        financialSystemAgent.add(employee().withId(1L).asManager().withSalary(1000));

        // When
        financialSystemAgent.giveRise(1L);

        // Then
        var updatedEmployee = financialSystemAgent.getEmployeeBy(1L);
        then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asManager().withSalary(1300));
    }

    @Test
    void updateSalaryForVPEmployee() {
        // Given
        financialSystemAgent.add(employee().withId(1L).asVP().withSalary(1000));

        // When
        financialSystemAgent.giveRise(1L);

        // Then
        var updatedEmployee = financialSystemAgent.getEmployeeBy(1L);
        then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asVP().withSalary(1500));
    }

    @Test
    void reportErrorWhenUpdateSalaryForNotSupportedEmployee() {
        // Given
        financialSystemAgent.add(employee().withId(1L).asContractor().withSalary(1000));

        // When
        assertThatThrownBy(() -> financialSystemAgent.giveRise(1L));
    }
}
