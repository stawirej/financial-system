package presentation.frontend.dto;

import org.springframework.stereotype.Component;

@Component
public class EmployeeId {

    private long id;

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public long getId() {
        return id;
    }
}
