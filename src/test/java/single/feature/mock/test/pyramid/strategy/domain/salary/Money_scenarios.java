package single.feature.mock.test.pyramid.strategy.domain.salary;

import static org.assertj.core.api.BDDAssertions.then;

import domain.salary.Money;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class Money_scenarios {

    @ParameterizedTest
    @CsvSource({
        "1000, 0.3, 300",
        "1000, 0.1, 100"
    })
    void Multiply_money_by_factor(double denomination, float factor, double expectedDenomination) {
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
    void Add_money(double denomination1, double denomination2, double expectedSum) {
        // Given
        var money1 = Money.valueOf(denomination1);
        var money2 = Money.valueOf(denomination2);

        // When
        var sum = money1.plus(money2);

        // Then
        then(sum).isEqualTo(Money.valueOf(expectedSum));
    }

    @Test
    void Display_salary() {
        then(Money.valueOf(123.45).toString()).isEqualTo("123.45");
    }
}
