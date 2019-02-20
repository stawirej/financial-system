package test.pyramid.strategy.presentation.frontend.automatization;

import domain.employee.Employee;
import domain.employee.EmployeeBuilder;
import domain.employee.EmployeeType;
import domain.salary.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
        driver.get("http://localhost:8085/add-employee-page");
        var id = driver.findElement(By.name("id"));
        var type = driver.findElement(By.name("type"));
        var salary = driver.findElement(By.id("salary"));
        var addEmployeeForm = driver.findElement(By.id("addEmployeeForm"));

        id.sendKeys(String.valueOf(employee.id()));
        salary.sendKeys(employee.salary().toString());
        addEmployeeForm.submit();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        driver.get("http://localhost:8085/get-employee-page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        var id2 = driver.findElement(By.name("id"));
        id2.sendKeys(String.valueOf(id));
        WebElement getEmployeeForm = driver.findElement(By.id("getEmployeeForm"));
        getEmployeeForm.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int start = driver.getPageSource().indexOf("Employee{");
        int end = driver.getPageSource().indexOf("}");
        String substring = driver.getPageSource().substring(start, end);
        String[] split = substring
            .replace("Employee{id=", "")
            .replace(" employeeType=", "")
            .replace(" salary=", "")
            .split(",");

        long employeeId = Long.parseLong(split[0]);
        EmployeeType employeeType = EmployeeType.valueOf(split[1]);
        Money money = Money.valueOf(Double.parseDouble(split[2]));
        Employee employee = EmployeeBuilder.employee()
                                           .withId(employeeId)
                                           .withType(employeeType)
                                           .withSalary(money);
        return Optional.of(employee);
//        driver.getPageSource()..contains("Employee{id=1, employeeType=REGULAR, salary=1100.00}");

    }

    @Override
    public void giveRise(long id) {
        driver.get("http://localhost:8085/give-rise-page");
        var id3 = driver.findElement(By.name("id"));
        id3.sendKeys(String.valueOf(id));
        WebElement giveRiseForm = driver.findElement(By.id("giveRiseForm"));
        giveRiseForm.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        driver.quit();
    }
}
