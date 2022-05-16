package ice.cream.cone.anti.pattern.strategy.workflow;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import ice.cream.cone.anti.pattern.strategy.automatization.FinancialSystemAgent;
import ice.cream.cone.anti.pattern.strategy.automatization.FinancialSystemServiceAgent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FinancialSystemService_scenarios {

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
    void Report_error_when_update_salary_for_not_supported_employee() {
        // Given
        financialSystemAgent.add(employee().withId(1L).asContractor().withSalary(1000));

        // When
        assertThatThrownBy(() -> financialSystemAgent.giveRise(1L));
    }

    @Nested
    class Update_salary_for_employee {

        @Test
        void regular() {
            // Given
            financialSystemAgent.add(employee().withId(1L).asRegular().withSalary(1000));

            // When
            financialSystemAgent.giveRise(1L);

            // Then
            var updatedEmployee = financialSystemAgent.getEmployeeBy(1L);
            then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asRegular().withSalary(1100));
        }

        @Test
        void manager() {

            // Given
            financialSystemAgent.add(employee().withId(1L).asManager().withSalary(1000));

            // When
            financialSystemAgent.giveRise(1L);

            // Then
            var updatedEmployee = financialSystemAgent.getEmployeeBy(1L);
            then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asManager().withSalary(1300));
        }

        @Test
        void vice_president() {
            // Given
            financialSystemAgent.add(employee().withId(1L).asVP().withSalary(1000));

            // When
            financialSystemAgent.giveRise(1L);

            // Then
            var updatedEmployee = financialSystemAgent.getEmployeeBy(1L);
            then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asVP().withSalary(1500));
        }
    }
}
