package domain.employee;

import domain.salary.Money;
import java.util.Objects;

public final class Employee {

    private final long id;
    private final EmployeeType employeeType;
    private final Money salary;

    Employee(long id, EmployeeType employeeType, Money salary) {
        this.id = id;
        this.employeeType = employeeType;
        this.salary = salary;
    }

    public Money salary() {
        return salary;
    }

    public long id() {
        return id;
    }

    public EmployeeType type() {
        return employeeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee)o;
        return id == employee.id &&
               employeeType == employee.employeeType &&
               Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeType, salary);
    }
}
