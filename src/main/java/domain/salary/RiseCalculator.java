package domain.salary;

import domain.employee.EmployeeType;
import domain.exceptions.NotSupportedEmployee;
import java.util.Map;

public final class RiseCalculator {

    private static final Map<EmployeeType, Float> employeeType2SalaryRiseFactor = Map.of(
        EmployeeType.REGULAR, 0.1F,
        EmployeeType.VP, 0.5F,
        EmployeeType.MANAGER, 0.3F);

    public static Money calculate(Money salary, EmployeeType employeeType) {
        if (employeeType2SalaryRiseFactor.containsKey(employeeType)) {
            var riseFactor = employeeType2SalaryRiseFactor.get(employeeType);
            return salary.plus(salary.times(riseFactor));
        } else {
            throw new NotSupportedEmployee("Not supported employee: " + employeeType.name());
        }
    }
}
