package infrastructure;

import domain.employee.Employee;
import domain.ports.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryRepository implements Repository {

    private final Map<Long, Employee> employees = new HashMap<>();

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public void save(Employee employee) {
        employees.put(employee.id(), employee);
    }
}
