package presentation.frontend.dto;

public class EmployeeId {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }
}
