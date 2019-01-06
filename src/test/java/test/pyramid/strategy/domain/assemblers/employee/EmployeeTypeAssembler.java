package test.pyramid.strategy.domain.assemblers.employee;

public interface EmployeeTypeAssembler {

    EmployeeSalaryAssembler asRegular();

    EmployeeSalaryAssembler asManager();

    EmployeeSalaryAssembler asContractor();

    EmployeeSalaryAssembler asVP();
}
