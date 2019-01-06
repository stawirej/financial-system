package test.pyramid.strategy.domain.assemblers.employee;

import domain.employee.Employee;

public interface EmployeeSalaryAssembler {

    Employee withSalary(double salary);
}
