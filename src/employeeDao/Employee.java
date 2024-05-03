package employeeDao;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String title;
    private Employee reportsTo;
    private String birthDate;
    private String hireDate;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private int phone;
    private String fax;
    private String email;

    public Employee(int id, String lastName, String firstName, String title, Employee reportsTo,
                    String birthDate, String hireDate, String address, String city, String state,
                    String country, String postalCode, int phone, String fax, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.reportsTo = reportsTo;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
               "id=" + id +
               ", lastName='" + lastName + '\'' +
               ", firstName='" + firstName + '\'' +
               ", title='" + title + '\'' +
               ", reportsTo=" + reportsTo +
               ", birthDate='" + birthDate + '\'' +
               ", hireDate='" + hireDate + '\'' +
               ", address='" + address + '\'' +
               ", city='" + city + '\'' +
               ", state='" + state + '\'' +
               ", country='" + country + '\'' +
               ", postalCode='" + postalCode + '\'' +
               ", phone=" + phone +
               ", fax='" + fax + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
