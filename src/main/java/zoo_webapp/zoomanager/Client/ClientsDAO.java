package zoo_webapp.zoomanager.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import zoo_webapp.zoomanager.DBConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ClientsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientsDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Client client){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("KLIENCI").usingColumns("IMIE","NAZWISKO","PROGRAM","NIEPELNOSPRAWNOSC","CZAS_WEJSCIA",
                "CZAS_WYJSCIA","NR_ZOO");
        //System.out.println("SAve client DAO " + client.toString());
        //BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(enclosure);

        Map<String,Object> param = new HashMap<>();
        param.put("IMIE", client.getName());
        param.put("NAZWISKO", client.getSurname());
        param.put("PROGRAM", client.getProgram());
        param.put("NIEPELNOSPRAWNOSC", client.getDisability());
        param.put("CZAS_WEJSCIA", client.getDateEntrance());
        param.put("CZAS_WYJSCIA", client.getDateFinish());
        param.put("NR_ZOO",String.valueOf(1));
        if(!param.containsValue(null)){
        insertActor.execute(param);}
    }

    public void update(Client client){
        client.setId(Client.getCurrentId());
        String sql = "UPDATE KLIENCI SET IMIE=?, NAZWISKO=?, PROGRAM=?"+
                " WHERE NR_KLIENT="+client.getId();
        //System.out.println("update w clientsdao "+client.toString() + " a moje id to "+Client.getCurrentId());
        try {
            PreparedStatement prepStmt = DBConnector.getConnectionDB().prepareStatement(sql);
            prepStmt.setString(1, client.getName());
            prepStmt.setString(2, client.getSurname());
            prepStmt.setString(3, client.getProgram());
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Client get(String name, String surname){
        String sql = "SELECT * FROM KLIENCI WHERE (IMIE={name} AND NAZWISKO={surname})";
        String[]  list = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]");
        System.out.println(list);
        Client client = new Client(Integer.parseInt(list[1]), list[3], list[5],
                list[7], list[9], list[11], list[13]);
        return client;
    }

    public int getRightId(Client client)
    {
        System.out.println("HERE I'M!");
        String sql = "SELECT NR_KLIENT FROM KLIENCI WHERE (IMIE='"+client.getName()+"' AND NAZWISKO='"+client.getSurname()+"' AND " +
                "PROGRAM='"+client.getProgram()+"' AND NIEPELNOSPRAWNOSC='"+client.getDisability()+"')";

        String[]  list = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]");
        Client.setCurrentId(Integer.parseInt(list[1]));
        System.out.println(list.toString());
        return Integer.parseInt(list[1]);
    }
}
