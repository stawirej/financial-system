package domain.salary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {

    private final BigDecimal denomination;

    public static Money valueOf(double denomination) {
        return new Money(BigDecimal.valueOf(denomination));
    }

    private Money(BigDecimal denomination) {
        this.denomination = denomination.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Money times(float factor) {
        var product = denomination.multiply(BigDecimal.valueOf(factor));
        return new Money(product);
    }

    public Money plus(Money money) {
        var sum = denomination.add(money.denomination);
        return new Money(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var money = (Money)o;
        return Objects.equals(denomination, money.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }

    @Override
    public String toString() {
        return denomination.toString();
    }
}
