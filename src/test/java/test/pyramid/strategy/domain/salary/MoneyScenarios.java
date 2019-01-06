package test.pyramid.strategy.domain.salary;

import static org.assertj.core.api.BDDAssertions.then;

import domain.salary.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class MoneyScenarios {

    @ParameterizedTest
    @CsvSource({
                   "1000, 0.3, 300",
                   "1000, 0.1, 100"
               })
    void multiplyMoneyByFactor(double denomination, float factor, double expectedDenomination) {
        // Given
        var money = Money.valueOf(denomination);

        // When
        var newMoney = money.times(factor);

        // Then
        then(newMoney).isEqualTo(Money.valueOf(expectedDenomination));
    }

    @ParameterizedTest
    @CsvSource({
                   "9.99, 10.01, 20.00",
                   "1.00, 2.00, 3.00",
               })
    void addMoney(double denomination1, double denomination2, double expectedSum) {
        // Given
        var money1 = Money.valueOf(denomination1);
        var money2 = Money.valueOf(denomination2);

        // When
        var sum = money1.plus(money2);

        // Then
        then(sum).isEqualTo(Money.valueOf(expectedSum));
    }

    @Test
    void displaySalary() {
        then(Money.valueOf(123.45).toString()).isEqualTo("123.45");
    }
}
