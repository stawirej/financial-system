package infrastructure;

import domain.employee.Employee;
import domain.ports.Repository;
import java.util.Optional;

/**
 * Simulation of slow production repository.
 */
public final class ProductionRepository implements Repository {

    private final InMemoryRepository inMemoryRepository = new InMemoryRepository();

    private ProductionRepository() {
    }

    public static ProductionRepository newInstance() {
        return new ProductionRepository();
    }

    @Override
    public Optional<Employee> getEmployeeBy(long id) {
        try {
            Thread.sleep(800);
            System.out.println("ProductionRepository.getEmployeeBy");
            return inMemoryRepository.getEmployeeBy(id);
        } catch (InterruptedException exception) {
            throw new RuntimeException("Production database failed!");
        }
    }

    @Override
    public void save(Employee employee) {
        try {
            System.out.println("ProductionRepository.save");
            Thread.sleep(800);
            inMemoryRepository.save(employee);
        } catch (InterruptedException exception) {
            throw new RuntimeException("Production database failed!");
        }
    }
}
