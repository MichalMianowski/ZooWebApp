package zoo_webapp.zoomanager.Enclosure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ArrayUtils;
import zoo_webapp.zoomanager.DBConnector;

import java.sql.*;
import java.util.*;

@Repository
public class EnclosuresDAO {
    //Data Access Class Object
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EnclosuresDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Enclosure> list(){
        String sql = "select * from WYBIEGI";
        //String sql = "SELECT WYBIEGI.NR_WYBIEG, WYBIEGI.TYP, WYBIEGI.POWIERZCHNIA_W_M2, WYBIEGI.ATRAKCJE_DLA_ZWIERZECIA, WYBIEGI.CZY_GOSCIE_MOGA_WEJSC, BUDYNKI.TYP FROM(\n" +
          //          "WYBIEGI INNER JOIN BUDYNKI ON BUDYNKI.NR_BUDYNKU = WYBIEGI.NR_BUDYNKU)";
        ArrayList<Enclosure> listEnclosure = new ArrayList<>();
                //jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Enclosure.class));
        //listEnclosure.clear();
        List lista = jdbcTemplate.queryForList(sql);
        //System.out.println(lista.get(0).toString());
        for (Object o : lista) {
            String[] row = o.toString().replaceAll("[{\\}]", "").split("[,\\=]");
            //System.out.println(row.length + " " + row[0]);
            listEnclosure.add(new Enclosure(Integer.parseInt(row[1]), row[3], Integer.parseInt(row[5]), row[7], row[9], Integer.parseInt(row[11])));
        }
        //System.out.println(jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Enclosure.class)));
        return listEnclosure;
    }

    public void save(Enclosure enclosure){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("WYBIEGI").usingColumns("TYP","POWIERZCHNIA_W_M2","ATRAKCJE_DLA_ZWIERZECIA",
                "CZY_GOSCIE_MOGA_WEJSC","NR_BUDYNKU");
        //BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(enclosure);
        Map<String,Object> param = new HashMap<>();
        param.put("TYP", enclosure.getTypeEnclosure());
        param.put("POWIERZCHNIA_W_M2",String.valueOf(enclosure.getSurface()));
        param.put("ATRAKCJE_DLA_ZWIERZECIA",enclosure.getAttractions());
        param.put("CZY_GOSCIE_MOGA_WEJSC",enclosure.getAreVisitorsAllowed());
        param.put("NR_BUDYNKU",String.valueOf(enclosure.getNrBuilding()));
        insertActor.execute(param);
    }

    public Enclosure get(int id){
        //Object[] args = id;
        String sql = "SELECT * FROM WYBIEGI WHERE NR_WYBIEG="+id;
        //String sql = "SELECT WYBIEGI.NR_WYBIEG, WYBIEGI.TYP, WYBIEGI.POWIERZCHNIA_W_M2, WYBIEGI.ATRAKCJE_DLA_ZWIERZECIA, WYBIEGI.CZY_GOSCIE_MOGA_WEJSC, BUDYNKI.TYP FROM(\\n\" +\n" +
          //      "                    \"WYBIEGI INNER JOIN BUDYNKI ON BUDYNKI.NR_BUDYNKU = WYBIEGI.NR_BUDYNKU) WHERE NR_WYBIEG="+ id;
        //Enclosure enclosure = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Enclosure.class));
        String[]  list = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]");
        Enclosure enclosure = new Enclosure(Integer.parseInt(list[1]), list[3], Integer.parseInt(list[5]), list[7], list[9], Integer.parseInt(list[11]));
        //System.out.println(list);
        return enclosure;
    }

    public void update(Enclosure encl){
        //TO DO index of chosen enclosure isn't correct
        String sql = "UPDATE WYBIEGI SET TYP=?, POWIERZCHNIA_W_M2=?, ATRAKCJE_DLA_ZWIERZECIA=?,"+
                " CZY_GOSCIE_MOGA_WEJSC=?, NR_BUDYNKU=? WHERE NR_WYBIEG=?";
        try {
            PreparedStatement prepStmt = DBConnector.getConnectionDB().prepareStatement(sql);
            prepStmt.setString(1,encl.getTypeEnclosure());
            prepStmt.setString(2,String.valueOf(encl.getSurface()));
            prepStmt.setString(3,encl.getAttractions());
            prepStmt.setString(4,encl.getAreVisitorsAllowed());
            prepStmt.setString(5,String.valueOf(encl.getNrBuilding()));
            prepStmt.setString(6,String.valueOf(encl.getId()));
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id){
        String sql = "DELETE FROM WYBIEGI WHERE NR_WYBIEG = ?";
        jdbcTemplate.update(sql, id);
    }



}
