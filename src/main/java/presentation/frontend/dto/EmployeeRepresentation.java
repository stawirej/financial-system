package presentation.frontend.dto;

import domain.employee.Employee;
import domain.employee.EmployeeBuilder;
import domain.employee.EmployeeType;
import domain.salary.Money;

public class EmployeeRepresentation {

    private long id;
    private String type;
    private double salary;

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSalary(String salary) {
        this.salary = Double.parseDouble(salary);
    }

    public Employee asEmployee() {
        return EmployeeBuilder
            .employee()
            .withId(id)
            .withType(EmployeeType.valueOf(type))
            .withSalary(Money.valueOf(salary));
    }

}
