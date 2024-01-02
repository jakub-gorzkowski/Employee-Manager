package Entities;

public abstract class Person {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;

    Person(String name, String surname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
