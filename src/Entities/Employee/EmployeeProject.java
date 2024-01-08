package Entities.Employee;

public class EmployeeProject {
    private final static String TABLE_NAME = "employees_projects";
    private int employeeId;
    private int projectId;

    public EmployeeProject(int employeeId, int projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    public static String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }
}
