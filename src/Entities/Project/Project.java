package Entities.Project;

public class Project {
    private final static String TABLE_NAME = "projects";
    private String name;
    private int clientId;
    private boolean isActive;

    public Project(String name, int clientId, boolean isActive) {
        this.name = name;
        this.clientId = clientId;
        this.isActive = isActive;
    }

    public static String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getName() {
        return name;
    }

    public int getClientId() {
        return clientId;
    }

    public boolean isActive() {
        return isActive;
    }
}
