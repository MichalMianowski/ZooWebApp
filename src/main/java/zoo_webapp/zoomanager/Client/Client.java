package zoo_webapp.zoomanager.Client;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    private int id;
    private String name;
    private String surname;
    private String program;
    private String disability;

    private String dateEntrance = new Timestamp(System.currentTimeMillis()).toString();
    private String dateFinish = new Timestamp(System.currentTimeMillis()).toString();

//    private String dateEntrance = (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").toString());
//    private String dateFinish = (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").toString());
//    private String dateEntrance = (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
//    private String dateFinish = (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
    
    private static int currentId;

    public Client(){

    }


    public Client(int id, String name, String surname, String program, String disability, String dateEntrance, String dateFinish) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.program = program;
        this.disability = disability;
        this.dateEntrance = dateEntrance;
        this.dateFinish = dateFinish;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDateEntrance() {
        return dateEntrance;
    }

    public void setDateEntrance(String dateEntrance) {
        this.dateEntrance = dateEntrance;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        Client.currentId = currentId;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", program='" + program + '\'' +
                ", disability='" + disability + '\'' +
                ", dateEntrance='" + dateEntrance + '\'' +
                ", dateFinish='" + dateFinish + '\'' +
                '}';
    }
}
