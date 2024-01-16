package Model.Person;

import Model.Employee.EmployeeRole;

import java.math.BigDecimal;

public class Employee extends Person {
    private final static String TABLE_NAME = "employees";
    private EmployeeRole role;
    private BigDecimal salary;
    public Employee(String name, String surname, String email, String phoneNumber, EmployeeRole role, BigDecimal salary) {
        super(name, surname, email, phoneNumber);
        this.role = role;
        this.salary = salary;
    }

    public static String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getRole() {
        return role.toString();
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
