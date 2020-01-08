package test.pyramid.strategy.presentation.frontend.automatization;

import domain.employee.Employee;
import domain.employee.EmployeeBuilder;
import domain.employee.EmployeeType;
import domain.exceptions.NotSupportedEmployee;
import domain.salary.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Optional;

public final class FinancialSystemFrontendAgent implements FinancialSystemAgent {

    private static final String ADD_EMPLOYEE_URL = "http://localhost:8085/add-employee-page";
    private static final String GET_EMPLOYEE_URL = "http://localhost:8085/get-employee-page";
    private static final String GIVE_RISE_URL = "http://localhost:8085/give-rise-page";

    private static final String WEBDRIVER_PATH = System.getProperty("user.dir") + "/src/test/java/test/pyramid"
                                                + "/strategy/presentation/frontend/automatization/resources/chromedriver";
    private final ChromeDriver driver;

    public FinancialSystemFrontendAgent() {
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Override
    public void add(Employee employee) {
        driver.get(ADD_EMPLOYEE_URL);

        var id = driver.findElement(By.name("id"));
        var type = driver.findElement(By.name("type"));
        var salary = driver.findElement(By.id("salary"));
        var addEmployeeForm = driver.findElement(By.id("addEmployeeForm"));

        id.sendKeys(String.valueOf(employee.id()));
        type.sendKeys(String.valueOf(employee.type()));
        salary.sendKeys(employee.salary().toString());
        addEmployeeForm.submit();
    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        driver.get(GET_EMPLOYEE_URL);

        var employeeId = driver.findElement(By.name("id"));
        var getEmployeeForm = driver.findElement(By.id("getEmployeeForm"));

        employeeId.sendKeys(String.valueOf(id));
        getEmployeeForm.submit();

        var pageSource = driver.getPageSource();
        var employee = employeeFromPageSource(pageSource);
        return Optional.of(employee);
    }

    @Override
    public void giveRise(long id) {
        driver.get(GIVE_RISE_URL);

        var employeeId = driver.findElement(By.name("id"));
        var giveRiseForm = driver.findElement(By.id("giveRiseForm"));

        employeeId.sendKeys(String.valueOf(id));
        giveRiseForm.submit();

        var pageSource = driver.getPageSource();
        if (notSupportedEmployeeErrorOn(pageSource)) {
            throw new NotSupportedEmployee(String.format("Not supported employee with id: %s", id));
        }
    }

    @Override
    public void close() {
        driver.quit();
    }

    private Employee employeeFromPageSource(String pageSource) {
        var startEmployeeDescriptionIndex = pageSource.indexOf("Employee{");
        var endEmployeeDescriptionIndex = pageSource.indexOf("}");

        var employee = pageSource
            .substring(startEmployeeDescriptionIndex, endEmployeeDescriptionIndex)
            .replace("Employee{id=", "")
            .replace(" employeeType=", "")
            .replace(" salary=", "")
            .split(",");

        var returnedEmployeeId = Long.parseLong(employee[0]);
        var returnedEmployeeType = EmployeeType.valueOf(employee[1]);
        var returnedSalary = Money.valueOf(Double.parseDouble(employee[2]));

        return EmployeeBuilder.employee()
                              .withId(returnedEmployeeId)
                              .withType(returnedEmployeeType)
                              .withSalary(returnedSalary);
    }

    private boolean notSupportedEmployeeErrorOn(String pageSource) {

        return pageSource.contains("Not supported employee");
    }
}
