package domain.employee;

import domain.salary.Money;

public interface EmployeeSalaryBuilder {

    Employee withSalary(Money salary);
}
