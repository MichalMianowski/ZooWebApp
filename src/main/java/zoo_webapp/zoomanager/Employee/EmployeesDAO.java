package zoo_webapp.zoomanager.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import zoo_webapp.zoomanager.Animal.Animal;
import zoo_webapp.zoomanager.DBConnector;
import zoo_webapp.zoomanager.Enclosure.Enclosure;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeesDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> list(){
        String sql = "SELECT * FROM PRACOWNICY";
        ArrayList<Employee> listEmployee = new ArrayList<>();
        List lista = jdbcTemplate.queryForList(sql);
        for (Object o : lista) {
            String[] row = o.toString().replaceAll("[{\\}]", "").split("[,\\=]");
            listEmployee.add(new Employee(Integer.parseInt(row[1]), row[3], row[5], row[7], Integer.parseInt(row[9]),
                    row[11], Integer.parseInt(row[13]), row[15], row[17], Integer.parseInt(row[19])));
        }
        return listEmployee;
    }

    public void save(Employee employee){
//        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
//        insertActor.withTableName("PRACOWNICY").usingColumns("IMIE","NAZWISKO","DATA_URODZENIA","WYNAGRODZENIE",
//                "NR_KONTA", "TELEFON_SLUZBOWY", "ADRES_EMAIL", "DATA_ZATRUDNIENIA", "NR_ZOO");
//
//        Map<String,Object> param = new HashMap<>();
//        param.put("IMIE", employee.getName());
//        param.put("NAZWISKO",employee.getSurname());
//        param.put("DATA_URODZENIA",employee.getBirthDate().);
//        param.put("WYNAGRODZENIE",String.valueOf(employee.getSalary()));
//        param.put("NR_KONTA", employee.getBankAccountNr());
//        param.put("TELEFON_SLUZBOWY", String.valueOf(employee.getPhoneNumber()));
//        param.put("ADRES_EMAIL", employee.getMail());
//        param.put("DATA_ZATRUDNIENIA", employee.getEmploymentDate());
//        param.put("NR_ZOO", String.valueOf(employee.getZooId()));
//        insertActor.execute(param);

        //a gdyby tak
        String sql = "INSERT INTO PRACOWNICY" +
                "(IMIE, NAZWISKO, DATA_URODZENIA, WYNAGRODZENIE, NR_KONTA, " +
                "TELEFON_SLUZBOWY, ADRES_EMAIL, DATA_ZATRUDNIENIA, NR_ZOO) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prepStmt = DBConnector.getConnectionDB().prepareStatement(sql);
            prepStmt.setString(1, employee.getName());
            prepStmt.setString(2, employee.getSurname());
            prepStmt.setString(3, employee.getBirthDate());
            prepStmt.setString(4, String.valueOf(employee.getSalary()));
            prepStmt.setString(5, employee.getBankAccountNr());
            prepStmt.setString(6, String.valueOf(employee.getPhoneNumber()));
            prepStmt.setString(7, employee.getMail());
            prepStmt.setString(8, employee.getEmploymentDate());
            prepStmt.setString(9, "1");
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee get(int id){
        String sql = "SELECT * FROM PRACOWNICY WHERE NR_PRACOWNIK="+ id;

        String[]  list = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]");
        Employee employee = new Employee(Integer.parseInt(list[1]), list[3], list[5], list[7], Integer.parseInt(list[9]), list[11], Integer.parseInt(list[13]), list[15], list[17], Integer.parseInt(list[19]));

        return employee;
    }

    public void update(Employee employee) {
        String sql = "UPDATE PRACOWNICY SET IMIE=?, NAZWISKO=?, DATA_URODZENIA=?," +
                " WYNAGRODZENIE=?, NR_KONTA=?, TELEFON_SLUZBOWY=?, ADRES_EMAIL=?," +
                "DATA_ZATRUDNIENIA=? WHERE NR_PRACOWNIK=?";
        try {
            PreparedStatement prepStmt = DBConnector.getConnectionDB().prepareStatement(sql);
            prepStmt.setString(1, employee.getName());
            prepStmt.setString(2, employee.getSurname());
            prepStmt.setString(3, employee.getBirthDate());
            prepStmt.setString(4, String.valueOf(employee.getSalary()));
            prepStmt.setString(5, employee.getBankAccountNr());
            prepStmt.setString(6, String.valueOf(employee.getPhoneNumber()));
            prepStmt.setString(7, employee.getMail());
            prepStmt.setString(8, employee.getEmploymentDate());
            prepStmt.setString(9, String.valueOf(employee.getId()));
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void delete(int id){
            String sql = "DELETE FROM PRACOWNICY WHERE NR_PRACOWNIK = ?";
            jdbcTemplate.update(sql, id);
        }

}
