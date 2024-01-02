package Entities;

public class Client extends Person {
    private String company;
    Client(String name, String surname, String email, String phoneNumber, String company) {
        super(name, surname, email, phoneNumber);
        this.company = company;
    }
}
