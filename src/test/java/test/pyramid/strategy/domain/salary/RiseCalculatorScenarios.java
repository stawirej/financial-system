package test.pyramid.strategy.domain.salary;

import static domain.salary.RiseCalculator.calculate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

import domain.employee.EmployeeType;
import domain.exceptions.NotSupportedEmployee;
import domain.salary.Money;
import org.junit.jupiter.api.Test;

final class RiseCalculatorScenarios {

    @Test
    void calculateRiseForRegularEmployee() {
        // Given
        var currentSalary = Money.valueOf(1000);

        // When
        var newSalary = calculate(currentSalary, EmployeeType.REGULAR);

        // Then
        then(newSalary).isEqualTo(Money.valueOf(1100));
    }

    @Test
    void calculateRiseForManagerEmployee() {
        // Given
        var currentSalary = Money.valueOf(1000);

        // When
        var newSalary = calculate(currentSalary, EmployeeType.MANAGER);

        // Then
        then(newSalary).isEqualTo(Money.valueOf(1300));
    }

    @Test
    void calculateRiseForVPEmployee() {
        // Given
        var currentSalary = Money.valueOf(1000);

        // When
        var newSalary = calculate(currentSalary, EmployeeType.VP);

        // Then
        then(newSalary).isEqualTo(Money.valueOf(1500));
    }

    @Test
    void reportErrorWhenCalculateRiseForNotSupportedEmployee() {
        assertThatThrownBy(() -> calculate(Money.valueOf(1), EmployeeType.CONTRACTOR))
            .isInstanceOf(NotSupportedEmployee.class)
            .hasMessage("Not supported employee: CONTRACTOR");
    }
}
