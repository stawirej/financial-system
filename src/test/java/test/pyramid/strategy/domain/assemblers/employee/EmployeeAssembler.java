package test.pyramid.strategy.domain.assemblers.employee;

import domain.employee.Employee;
import domain.employee.EmployeeBuilder;
import domain.employee.EmployeeType;
import domain.salary.Money;

public final class EmployeeAssembler implements EmployeeIdAssembler,
                                                EmployeeTypeAssembler,
                                                EmployeeSalaryAssembler {

    private long id;
    private EmployeeType employeeType;

    private EmployeeAssembler() {

    }

    public static EmployeeIdAssembler employee() {
        return new EmployeeAssembler();
    }

    public EmployeeTypeAssembler withId(long id) {
        this.id = id;
        return this;
    }

    public EmployeeSalaryAssembler asRegular() {
        employeeType = EmployeeType.REGULAR;
        return this;
    }

    public EmployeeSalaryAssembler asManager() {
        employeeType = EmployeeType.MANAGER;
        return this;
    }

    public EmployeeSalaryAssembler asContractor() {
        employeeType = EmployeeType.CONTRACTOR;
        return this;
    }

    public EmployeeSalaryAssembler asVP() {
        employeeType = EmployeeType.VP;
        return this;
    }

    public Employee withSalary(double salary) {
        return EmployeeBuilder
            .employee()
            .withId(id)
            .withType(employeeType)
            .withSalary(Money.valueOf(salary));
    }
}
