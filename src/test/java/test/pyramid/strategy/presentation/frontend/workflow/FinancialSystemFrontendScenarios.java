package test.pyramid.strategy.presentation.frontend.workflow;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

class FinancialSystemFrontendScenarios {

    @Test
    void foo() {
        // Create an instance of the driver
//        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\java\\test\\pyramid"
                                                      + "\\strategy\\presentation\\frontend\\automatization\\resources"
                                                      + "\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Navigate to a web page
        driver.get("http://localhost:8085/add-employee-page");

        // Perform actions on HTML elements, entering text and submitting the form
        WebElement id = driver.findElement(By.name("id"));
        WebElement type = driver.findElement(By.name("type"));
        WebElement salary = driver.findElement(By.id("salary"));
        WebElement addEmployeeForm = driver.findElement(By.id("addEmployeeForm"));

        id.sendKeys("1");
        salary.sendKeys("1000");
        addEmployeeForm.submit();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //
        driver.get("http://localhost:8085/get-employee-page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement id2 = driver.findElement(By.name("id"));
        id2.sendKeys("1");
        WebElement getEmployeeForm = driver.findElement(By.id("getEmployeeForm"));
        getEmployeeForm.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //
        driver.get("http://localhost:8085/give-rise-page");
        WebElement id3 = driver.findElement(By.name("id"));
        id3.sendKeys("1");
        WebElement giveRiseForm = driver.findElement(By.id("giveRiseForm"));
        giveRiseForm.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //
        driver.get("http://localhost:8085/get-employee-page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement id4 = driver.findElement(By.name("id"));
        id4.sendKeys("1");
        WebElement getEmployeeForm2 = driver.findElement(By.id("getEmployeeForm"));
        getEmployeeForm2.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //

        then(driver.getPageSource().contains("Employee{id=1, employeeType=REGULAR, salary=1100.00}")).isTrue();

        // Conclude a test
        driver.quit();
    }
}
