package test.pyramid.strategy.application;

import static application.FinancialSystemConfiguration.financialSystem;
import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import application.FinancialSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class FinancialSystemGetEmployeeScenarios {

    private FinancialSystem financialSystem;

    @BeforeEach
    void beforeEach() {
        financialSystem = financialSystem();
    }

    @Test
    void getExistingEmployee() {
        // Given
        financialSystem.add(employee().withId(2L).asContractor().withSalary(3000));

        // When
        var employee = financialSystem.getEmployeeBy(2L);

        // Then
        then(employee.get()).isEqualTo(employee().withId(2L).asContractor().withSalary(3000));
    }

    @Test
    void returnEmptyForNonExistingEmployee() {
        // When
        var employee = financialSystem.getEmployeeBy(2L);

        // Then
        then(employee.isPresent()).isFalse();
    }
}
