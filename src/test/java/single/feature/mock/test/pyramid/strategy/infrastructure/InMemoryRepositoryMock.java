package single.feature.mock.test.pyramid.strategy.infrastructure;

import domain.employee.Employee;
import domain.ports.Repository;
import domain.salary.Money;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryRepositoryMock implements Repository {

    private final Map<Long, Employee> employees = new HashMap<>();

    private InMemoryRepositoryMock() {

    }

    public static InMemoryRepositoryMock newInstance() {
        return new InMemoryRepositoryMock();
    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public void save(Employee employee) {
        employees.put(employee.id(), employee);
    }

    public Money salaryForEmployee(long id) {
        return employees.get(id).salary();
    }
}
