package presentation.frontend;

import application.FinancialSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import presentation.frontend.dto.EmployeeId;
import presentation.frontend.dto.EmployeeRepresentation;
import java.util.Map;

@Controller
public class FinancialSystemController {

    @Autowired
    private FinancialSystem financialSystem;

    @RequestMapping("/index")
    public String viewHomePage() {
        return "index";
    }

    @RequestMapping("/add-employee-redirect")
    public String addEmployeeRedirect() {
        return "redirect:/add-employee-page";
    }

    @RequestMapping("/add-employee-page")
    public String viewAddEmployePage() {
        return "addemployee";
    }

    @PostMapping("add-employee")
    public String addEmployee(@ModelAttribute("EmployeeRepresentation") EmployeeRepresentation employeeRepresentation) {
        financialSystem.add(employeeRepresentation.asEmployee());
        return "addemployee";
    }

    @RequestMapping("/get-employee-redirect")
    public String getEmployeeRedirect() {
        return "redirect:/get-employee-page";
    }

    @RequestMapping("/get-employee-page")
    public String viewGeetEmployePage() {
        return "getemployee";
    }

    @PostMapping("get-employee")
    public String getEmployee(@ModelAttribute("EmployeeId") EmployeeId employeeId, Map<String, Object> model) {
        financialSystem
            .getEmployeeBy(employeeId.getId())
            .ifPresent(employee -> model.put("employee", employee.toString()));
        return "getemployee";
    }

    @RequestMapping("/give-rise-redirect")
    public String giveRiseRedirect() {
        return "redirect:/give-rise-page";
    }

    @RequestMapping("/give-rise-page")
    public String viewGiveRisePage() {
        return "giverise";
    }

    @PostMapping("give-rise")
    public String giveRise(@ModelAttribute("EmployeeId") EmployeeId employeeId) {
        financialSystem.giveRise(employeeId.getId());
        return "giverise";
    }
}
