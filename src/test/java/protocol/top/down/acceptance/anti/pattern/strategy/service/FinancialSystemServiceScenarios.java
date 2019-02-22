package protocol.top.down.acceptance.anti.pattern.strategy.service;

import static org.assertj.core.api.BDDAssertions.then;
import static test.pyramid.strategy.domain.assemblers.employee.EmployeeAssembler.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentation.service.dto.EmployeeRepresentation;
import ratpack.test.MainClassApplicationUnderTest;
import java.io.IOException;

final class FinancialSystemServiceScenarios {

    private MainClassApplicationUnderTest applicationUnderTest;

    @BeforeEach
    void beforeEach() {
        applicationUnderTest = new MainClassApplicationUnderTest(FinancialSystemServiceInMemory.class);
    }

    @AfterEach
    void afterEach() {
        applicationUnderTest.close();
    }

    @Test
    void updateSalaryForRegularEmployee() throws IOException {
        // Given
        applicationUnderTest
            .getHttpClient()
            .requestSpec(
                requestSpec -> requestSpec
                    .body(body -> body
                        .type("application/json")
                        .text("{\"id\": 1,\"type\": \"regular\",\"salary\": 1000}")))
            .post("/employee");

        // When
        applicationUnderTest.getHttpClient().put("/employee/1/salary");

        // Then
        var text = applicationUnderTest.getHttpClient().getText("/employee/1");
        var mapper = new ObjectMapper();
        var employee = mapper.readValue(text, EmployeeRepresentation.class).asEmployee();
        then(employee).isEqualTo(employee().withId(1L).asRegular().withSalary(1100));
    }
}
