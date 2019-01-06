package domain.employee;

import domain.salary.Money;

public final class EmployeeBuilder implements EmployeeIdBuilder, EmployeeTypeBuilder, EmployeeSalaryBuilder {

    private long id;
    private EmployeeType employeeType;

    public static EmployeeIdBuilder employee() {
        return new EmployeeBuilder();
    }

    @Override
    public EmployeeTypeBuilder withId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public EmployeeSalaryBuilder withType(EmployeeType employeeType) {
        this.employeeType = employeeType;
        return this;
    }

    @Override
    public Employee withSalary(Money salary) {
        return new Employee(id, employeeType, salary);
    }
}
