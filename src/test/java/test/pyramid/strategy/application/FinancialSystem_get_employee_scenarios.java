package test.pyramid.strategy.application;

import static application.FinancialSystemConfiguration.financialSystem;
import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import application.FinancialSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class FinancialSystem_get_employee_scenarios {

    private FinancialSystem financialSystem;

    @BeforeEach
    void beforeEach() {
        financialSystem = financialSystem();
    }

    @Test
    void Get_existing_employee() {
        // Given
        financialSystem.add(employee().withId(2L).asContractor().withSalary(3000));

        // When
        var employee = financialSystem.getEmployeeBy(2L);

        // Then
        then(employee.get()).isEqualTo(employee().withId(2L).asContractor().withSalary(3000));
    }

    @Test
    void Return_empty_for_non_existing_employee() {
        // When
        var employee = financialSystem.getEmployeeBy(2L);

        // Then
        then(employee.isPresent()).isFalse();
    }
}
