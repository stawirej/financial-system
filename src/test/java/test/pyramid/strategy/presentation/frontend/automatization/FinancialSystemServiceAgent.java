package test.pyramid.strategy.presentation.frontend.automatization;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.employee.Employee;
import org.openqa.selenium.chrome.ChromeDriver;
import presentation.service.dto.EmployeeRepresentation;
import java.io.IOException;
import java.util.Optional;

public final class FinancialSystemServiceAgent
    implements test.pyramid.strategy.presentation.frontend.automatization.FinancialSystemAgent {

    private final ChromeDriver driver;

    public FinancialSystemServiceAgent() {
        System.setProperty("webdriver.chrome.driver",
                           System.getProperty("user.dir") + "\\src\\test\\java\\test\\pyramid"
                           + "\\strategy\\presentation\\frontend\\automatization\\resources"
                           + "\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Override
    public void add(Employee employee) {

    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        return Optional.empty();
    }

    @Override
    public void giveRise(long id) {

    }

    @Override
    public void close() {
//        financialSystemService.close();
    }

    private Employee toEmployee(String text) throws IOException {
        return new ObjectMapper()
            .readValue(text, EmployeeRepresentation.class)
            .asEmployee();
    }
}
