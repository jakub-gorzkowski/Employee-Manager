package Entities;

import java.math.BigDecimal;

enum Role {
    MANAGER,
    DEVELOPER,
    TESTER
}

public class Employee extends Person {
    private Role role;
    private BigDecimal salary;
    Employee(String name, String surname, String email, String phoneNumber, Role role, BigDecimal salary) {
        super(name, surname, email, phoneNumber);
        this.role = role;
        this.salary = salary;
    }
}
