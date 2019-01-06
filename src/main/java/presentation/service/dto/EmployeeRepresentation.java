package presentation.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.employee.Employee;
import domain.employee.EmployeeBuilder;
import domain.employee.EmployeeType;
import domain.salary.Money;

public final class EmployeeRepresentation {

    private final long id;
    private final String type;
    private final double salary;

    private EmployeeRepresentation(@JsonProperty("id") long id,
                                   @JsonProperty("type") String type,
                                   @JsonProperty("salary") double salary) {
        this.id = id;
        this.type = type.toUpperCase().trim();
        this.salary = salary;
    }

    public static EmployeeRepresentation from(Employee employee) {
        var id = employee.id();
        var type = employee.type().name();
        var salary = employee.salary().toString();
        return new EmployeeRepresentation(id, type, Double.parseDouble(salary));
    }

    public Employee asEmployee() {
        return EmployeeBuilder
            .employee()
            .withId(id)
            .withType(EmployeeType.valueOf(type))
            .withSalary(Money.valueOf(salary));
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getSalary() {
        return salary;
    }
}
