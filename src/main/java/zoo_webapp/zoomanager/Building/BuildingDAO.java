package zoo_webapp.zoomanager.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import zoo_webapp.zoomanager.DBConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BuildingDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Building> list() {
        String sql = "select * from BUDYNKI";
        List<Building> listBuilding = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Building.class));
        listBuilding.clear();
        List lista = jdbcTemplate.queryForList(sql);
        //System.out.println(lista.get(0).toString());
        for (Object o : lista) {
            String[] row = o.toString().replaceAll("[{\\}]", "").split("[,\\=]");
            //System.out.println(row.length + " " + row[0]);
            listBuilding.add(new Building(Integer.parseInt(row[1]), row[3], row[5], Integer.parseInt(row[7]), Integer.parseInt(row[9])));
        }
        //System.out.println(jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Enclosure.class)));
        return listBuilding;
    }

    public void save(Building building) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("BUDYNKI").usingColumns("TYP", "SEKCJA", "WIELKOSC_W_M2", "NR_ZOO");
        //BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(enclosure);
        Map<String, Object> param = new HashMap<>();
        param.put("TYP", building.getType());
        param.put("SEKCJA", building.getSection());
        param.put("WIELKOSC_W_M2", building.getSurface());
        param.put("NR_ZOO", building.getZooId());
        insertActor.execute(param);
    }

    public Building get(int id) {
        //Object[] args = id;
        //String sql = "SELECT * FROM WYBIEGI WHERE id"+args[0];
        String sql = "SELECT * FROM BUDYNKI WHERE NR_BUDYNKU=" + id;
        //Enclosure enclosure = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Enclosure.class));
        String[] list = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]", "").split("[,\\=]");
        Building building = new Building(Integer.parseInt(list[1]), list[3], list[5], Integer.parseInt(list[7]), Integer.parseInt(list[9]));
        System.out.println(list);
        return building;
    }

    public void update(Building building) {
        //TO DO index of chosen enclosure isn't correct
        String sql = "UPDATE BUDYNKI SET TYP=?, SEKCJA=?, WIELKOSC_W_M2=?," +
                "NR_ZOO=? WHERE NR_BUDYNKU=?";
        try {
            PreparedStatement prepStmt = DBConnector.getConnectionDB().prepareStatement(sql);
            prepStmt.setString(1, building.getType());
            prepStmt.setString(2, building.getSection());
            prepStmt.setString(3, String.valueOf(building.getSurface()));
            prepStmt.setString(4, String.valueOf(building.getZooId()));
            prepStmt.setString(5, String.valueOf(building.getId()));
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM BUDYNKI WHERE NR_BUDYNKU = ?";
        jdbcTemplate.update(sql, id);
    }
}
