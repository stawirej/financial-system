package test.pyramid.strategy.domain.employee;

import static org.mutabilitydetector.unittesting.MutabilityAssert.assertImmutable;

import domain.employee.Employee;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
final class Employee_Scenarios {

    @Test
    void Verify_equals() {

        EqualsVerifier
            .forClass(Employee.class)
            .verify();
    }

    @Test
    void Verify_immutability() {

        assertImmutable(Employee.class);
    }
}
