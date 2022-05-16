package presentation.service;


import static ratpack.core.jackson.Jackson.fromJson;

import application.FinancialSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.employee.Employee;
import presentation.service.dto.EmployeeRepresentation;
import ratpack.core.handling.Chain;
import ratpack.core.handling.Handler;
import ratpack.func.Action;

final class Handlers {

    private final FinancialSystem financialSystem;

    private Handlers(FinancialSystem financialSystem) {
        this.financialSystem = financialSystem;
    }

    static Action<Chain> newActionChain(FinancialSystem financialSystem) {
        return new Handlers(financialSystem).actionChain();
    }

    private Action<Chain> actionChain() {
        return chain -> {
            chain.get(welcomeHandler());
            chain.get("employee/:id", getEmployeeHandler());
            chain.post("employee", addEmployeeHandler());
            chain.put("employee/:id/salary", riseSalaryHandler());
        };
    }

    private Handler welcomeHandler() {
        return context -> context.render("Welcome to Financial System!!!");
    }

    private Handler getEmployeeHandler() {
        return context -> {
            var id = Long.parseLong(context.getPathTokens().get("id"));
            financialSystem
                .getEmployeeBy(id)
                .ifPresentOrElse(employee -> context.render(toJson(employee)),
                                 () -> context.render("Employee not found!"));
        };
    }

    private Handler addEmployeeHandler() {
        return context -> context
            .parse(fromJson(EmployeeRepresentation.class))
            .map(EmployeeRepresentation::asEmployee)
            .then(employee -> {
                financialSystem.add(employee);
                context.render("Employee added!");
            });
    }

    private Handler riseSalaryHandler() {
        return context -> {
            var employeeId = Long.parseLong(context.getPathTokens().get("id"));
            financialSystem.giveRise(employeeId);
            context.render("Rise applied!");
        };
    }

    private String toJson(Employee employee) {
        try {
            var employeeRepresentation = EmployeeRepresentation.from(employee);
            return new ObjectMapper()
                .writeValueAsString(employeeRepresentation);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
