package test.pyramid.strategy.application;

import static application.FinancialSystemConfiguration.financialSystem;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import application.FinancialSystem;
import domain.exceptions.NotExistingEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinancialSystemRiseScenarios {

    protected FinancialSystem financialSystem;

    /*Setter as junit require declaration of a single non parametrized constructor and class is re-used*/
    public void setFinancialSystem(FinancialSystem financialSystem) {
        this.financialSystem = financialSystem;
    }

    @BeforeEach
    void beforeEach() {
        financialSystem = financialSystem();
    }

    @Test
    public void updateSalaryForRegularEmployee() {
        // Given
        financialSystem.add(employee().withId(1L).asRegular().withSalary(1000));

        // When
        financialSystem.giveRise(1L);

        // Then
        var updatedEmployee = financialSystem.getEmployeeBy(1L);
        then(updatedEmployee.get()).isEqualTo(employee().withId(1L).asRegular().withSalary(1100));
    }

    @Test
    void reportErrorForNonExistingEmployee() {
        assertThatThrownBy(() -> financialSystem.giveRise(1L))
            .isInstanceOf(NotExistingEmployee.class)
            .hasMessage("Employee newInstance id: 1 does not exists!");
    }
}
