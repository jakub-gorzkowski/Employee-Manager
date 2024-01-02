package Entities;

public enum EmployeeRole {
    MANAGER,
    DEVELOPER,
    TESTER;

    @Override
    public String toString() {
        return switch (this) {
            case MANAGER -> "Manager";
            case DEVELOPER -> "Developer";
            case TESTER -> "Tester";
        };
    }
}
