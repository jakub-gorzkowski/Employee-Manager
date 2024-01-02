package Entities;

public class Client extends Person {
    private final String TABLE_NAME = "clients";
    private String company;

    public Client(String name, String surname, String email, String phoneNumber) {
        super(name, surname, email, phoneNumber);
    }
    public Client(String name, String surname, String email, String phoneNumber, String company) {
        super(name, surname, email, phoneNumber);
        this.company = company;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getCompany() {
        return company;
    }
}
