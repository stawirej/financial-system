package ice.cream.cone.anti.pattern.strategy.automatization;

import domain.employee.Employee;
import java.util.Optional;

public interface FinancialSystemAgent {

    void add(Employee employee);

    Optional<Employee> getEmployeeBy(long id);

    void giveRise(long id);

    void close();
}
