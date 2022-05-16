package single.feature.mock.test.pyramid.strategy.domain.salary;

import static domain.salary.RiseCalculator.calculate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

import domain.employee.EmployeeType;
import domain.exceptions.NotSupportedEmployee;
import domain.salary.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class RiseCalculator_scenarios {

    @Test
    void Report_error_when_calculate_rise_for_not_supported_employee() {
        assertThatThrownBy(() -> calculate(Money.valueOf(1), EmployeeType.CONTRACTOR))
            .isInstanceOf(NotSupportedEmployee.class)
            .hasMessage("Not supported employee: CONTRACTOR");
    }

    @Nested
    class Calculate_rise_for_employee {

        @Test
        void regular() {
            // Given
            var currentSalary = Money.valueOf(1000);

            // When
            var newSalary = calculate(currentSalary, EmployeeType.REGULAR);

            // Then
            then(newSalary).isEqualTo(Money.valueOf(1100));
        }

        @Test
        void manager() {
            // Given
            var currentSalary = Money.valueOf(1000);

            // When
            var newSalary = calculate(currentSalary, EmployeeType.MANAGER);

            // Then
            then(newSalary).isEqualTo(Money.valueOf(1300));
        }

        @Test
        void vice_president() {
            // Given
            var currentSalary = Money.valueOf(1000);

            // When
            var newSalary = calculate(currentSalary, EmployeeType.VP);

            // Then
            then(newSalary).isEqualTo(Money.valueOf(1500));
        }
    }
}
