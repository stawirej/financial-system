package domain.ports;

import domain.employee.Employee;
import java.util.Optional;

public interface Repository {

    Optional<Employee> getEmployeeBy(long id);

    void save(Employee employee);
}
