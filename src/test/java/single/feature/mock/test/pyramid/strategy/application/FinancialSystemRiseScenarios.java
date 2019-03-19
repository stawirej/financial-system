package single.feature.mock.test.pyramid.strategy.application;

import static domain.employee.EmployeeBuilder.employee;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

import application.FinancialSystem;
import application.FinancialSystemConfiguration;
import domain.employee.EmployeeType;
import domain.exceptions.NotExistingEmployee;
import domain.salary.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import single.feature.mock.test.pyramid.strategy.infrastructure.InMemoryRepositoryMock;

final class FinancialSystemRiseScenarios {

    private FinancialSystem financialSystem;
    private InMemoryRepositoryMock repositoryMock;

    @BeforeEach
    void beforeEach() {
        repositoryMock = InMemoryRepositoryMock.newInstance();
        financialSystem = FinancialSystemConfiguration.financialSystem(repositoryMock);
    }

    @Test
    void updateSalaryForRegularEmployee() {
        // Given
        var regular = employee().withId(1L).withType(EmployeeType.REGULAR).withSalary(Money.valueOf(1000));
        repositoryMock.save(regular);

        // When
        financialSystem.giveRise(1L);

        // Then
        then(repositoryMock.salaryForEmployee(1L)).isEqualTo(Money.valueOf(1100));
    }

    @Test
    void reportErrorForNonExistingEmployee() {
        assertThatThrownBy(() -> financialSystem.giveRise(1L))
            .isInstanceOf(NotExistingEmployee.class)
            .hasMessage("Employee with id: 1 does not exists!");
    }
}
