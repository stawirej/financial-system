package test.pyramid.strategy.domain.assemblers.employee;

public interface EmployeeIdAssembler {

    EmployeeTypeAssembler withId(long id);
}
