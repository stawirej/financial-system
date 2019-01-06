package infrastructure;

import domain.employee.Employee;
import domain.ports.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ProductionRepository implements Repository {

    private final Map<Long, Employee> employees = new HashMap<>();

    private ProductionRepository() {

    }

    public static ProductionRepository newInstance() {
        return new ProductionRepository();
    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        System.out.println("ProductionRepository.getEmployeeBy");
        return Optional.ofNullable(employees.get(id));
    }

    @Override
    public void save(Employee employee) {
        employees.put(employee.id(), employee);
    }
}
