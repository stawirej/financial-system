package application;

import domain.employee.Employee;
import java.util.Optional;

public interface FinancialSystem {

    void giveRise(long id);

    void add(Employee employee);

    Optional<Employee> getEmployeeBy(long employeeId);
}
