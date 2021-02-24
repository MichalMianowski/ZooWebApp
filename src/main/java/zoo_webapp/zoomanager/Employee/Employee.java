package zoo_webapp.zoomanager.Employee;

public class Employee {

    private int id;
    private String name;
    private String surname;
    private String birthDate;
    private int salary;
    private String bankAccountNr;
    private int phoneNumber;
    private String mail;
    private String employmentDate;
    private int zooId;

    public Employee(){}

    public Employee(int id, String name, String surname, String birthDate, int salary, String bankAccountNr, int phoneNumber, String mail, String employmentDate, int zooId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.salary = salary;
        this.bankAccountNr = bankAccountNr;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.employmentDate = employmentDate;
        this.zooId = zooId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getBankAccountNr() {
        return bankAccountNr;
    }

    public void setBankAccountNr(String bankAccountNr) {
        this.bankAccountNr = bankAccountNr;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public int getZooId() {
        return zooId;
    }

    public void setZooId(int zooId) {
        this.zooId = zooId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", salary=" + salary +
                ", bankAccountNr='" + bankAccountNr + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", mail='" + mail + '\'' +
                ", employmentDate='" + employmentDate + '\'' +
                ", zooId=" + zooId+ '\'' +
                '}';
    }
}
