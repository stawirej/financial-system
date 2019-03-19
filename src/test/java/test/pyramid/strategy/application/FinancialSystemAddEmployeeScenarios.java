package test.pyramid.strategy.application;

import static application.FinancialSystemConfiguration.financialSystem;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import application.FinancialSystem;
import domain.exceptions.EmployeeAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class FinancialSystemAddEmployeeScenarios {

    private FinancialSystem financialSystem;

    @BeforeEach
    void beforeEach() {
        financialSystem = financialSystem();
    }

    @Test
    void addNonExistingEmployee() {
        // When
        financialSystem.add(employee().withId(1L).asManager().withSalary(5000));

        // Then
        var savedManager = financialSystem.getEmployeeBy(1L);
        then(savedManager.get()).isEqualTo(employee().withId(1L).asManager().withSalary(5000));
    }

    @Test
    void reportErrorOnAddingExistingEmployee() {
        // Given
        financialSystem.add(employee().withId(1L).asManager().withSalary(5000));

        // Then
        assertThatThrownBy(() -> financialSystem.add(employee().withId(1L).asManager().withSalary(5000)))
            .isInstanceOf(EmployeeAlreadyExists.class)
            .hasMessage("Employee with id: 1 already exists!");
    }
}
