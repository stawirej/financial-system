package ice.cream.cone.anti.pattern.strategy.automatization;

import static presentation.service.FinancialSystemServiceConfiguration.financialSystemService;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.employee.Employee;
import io.netty.handler.codec.http.HttpStatusClass;
import presentation.service.dto.EmployeeRepresentation;
import ratpack.http.Status;
import ratpack.test.MainClassApplicationUnderTest;
import java.io.IOException;
import java.util.Optional;

public final class FinancialSystemServiceAgent implements FinancialSystemAgent {

    private final MainClassApplicationUnderTest financialSystemService;

    public static void main(String[] args) throws Exception {
        financialSystemService();
    }

    public FinancialSystemServiceAgent() {
        financialSystemService = new MainClassApplicationUnderTest(FinancialSystemServiceAgent.class);
    }

    @Override
    public void add(Employee employee) {
        financialSystemService
            .getHttpClient()
            .requestSpec(
                requestSpec -> requestSpec
                    .body(body -> body
                        .type("application/json")
                        .text(toJson(employee))))
            .post("/employee");
    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        try {
            var text = financialSystemService
                .getHttpClient()
                .getText(String.format("/employee/%s", id));

            var employee = toEmployee(text);
            return Optional.of(employee);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void giveRise(long id) {
        var status = financialSystemService
            .getHttpClient()
            .put(String.format("/employee/%s/salary", id))
            .getStatus();

        checkStatus(status);
    }

    @Override
    public void close() {
        financialSystemService.close();
    }

    private void checkStatus(Status status) {
        if (!HttpStatusClass.SUCCESS.contains(status.getCode())) {
            throw new RuntimeException(status.getMessage());
        }
    }

    private Employee toEmployee(String text) throws IOException {
        return new ObjectMapper()
            .readValue(text, EmployeeRepresentation.class)
            .asEmployee();
    }

    private String toJson(Employee employee) {
        try {
            var employeeRepresentation = EmployeeRepresentation.from(employee);
            return new ObjectMapper()
                .writeValueAsString(employeeRepresentation);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
