package application;

import static domain.employee.EmployeeBuilder.employee;

import domain.employee.Employee;
import domain.exceptions.EmployeeAlreadyExists;
import domain.exceptions.NotExistingEmployee;
import domain.ports.Repository;
import domain.salary.RiseCalculator;
import java.util.Optional;

final class SimpleFinancialSystem implements FinancialSystem {

    private final Repository repository;

    private SimpleFinancialSystem(Repository repository) {
        this.repository = repository;
    }

    static FinancialSystem newInstance(Repository repository) {
        return new SimpleFinancialSystem(repository);
    }

    @Override
    public void giveRise(long id) {
        var employee = getEmployeeFromRepositoryBy(id);
        var updatedEmployee = calculateNewSalaryFor(employee);
        repository.save(updatedEmployee);
    }

    @Override
    public void add(Employee employee) {
        var foundEmployee = getEmployeeBy(employee.id());

        if (foundEmployee.isEmpty()) {
            repository.save(employee);
        } else {
            throw new EmployeeAlreadyExists("Employee newInstance id: " + employee.id() + " already exists!");
        }
    }

    @Override
    public Optional<Employee> getEmployeeBy(long employeeId) {
        return repository.getEmployeeBy(employeeId);
    }

    private Employee calculateNewSalaryFor(Employee employee) {

        var newSalary = RiseCalculator.calculate(employee.salary(), employee.type());

        return employee()
            .withId(employee.id())
            .withType(employee.type())
            .withSalary(newSalary);
    }

    private Employee getEmployeeFromRepositoryBy(long id) {
        return repository
            .getEmployeeBy(id)
            .orElseThrow(() -> new NotExistingEmployee("Employee newInstance id: " + id + " does not exists!"));
    }
}
