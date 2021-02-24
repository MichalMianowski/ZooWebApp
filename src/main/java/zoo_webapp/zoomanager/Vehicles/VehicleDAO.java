package zoo_webapp.zoomanager.Vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zoo_webapp.zoomanager.Enclosure.Enclosure;

import java.util.List;

@Repository
public class VehicleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public VehicleDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Vehicle> list(){
        String sql = "SELECT POJAZDY.NR_POJAZD, ROK_PRODUKCJI, NR_REJESTRACYJNY, TYP_PALIWA, TYP_SKRZYNI_BIEGOW, LADOWNOSC, DOSTAWCY.NAZWA, ZOO.NAZWA, MODELE.NAZWA_MODELU, MARKI.NAZWA_MARKI \n" +
                "FROM ((((POJAZDY\n" +
                "INNER JOIN DOSTAWCY ON DOSTAWCY.NR_DOSTAWCA=POJAZDY.NR_DOSTAWCA)\n" +
                "INNER JOIN ZOO ON ZOO.NR_ZOO = POJAZDY.NR_ZOO)\n" +
                "INNER JOIN MODELE ON MODELE.NR_MODELU = POJAZDY.NR_MODELU)\n" +
                "INNER JOIN MARKI ON MARKI.NR_MARKI = MODELE.NR_MARKI)";
        List<Vehicle> vehicleList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Vehicle.class));
        vehicleList.clear();
        List lista = jdbcTemplate.queryForList(sql);
        //System.out.println(lista.get(0).toString());
        for (Object o : lista) {
            String[] row = o.toString().replaceAll("[{\\}]", "").split("[,\\=]");
            //System.out.println(row.length + " " + row[0]);
            vehicleList.add(new Vehicle(Integer.parseInt(row[1]), row[3], row[5], row[7], row[9],
                    Integer.parseInt(row[11]), row[13], row[15], row[17], row[19]));
        }
        //System.out.println(jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Enclosure.class)));
        return vehicleList;
    }



    public void delete(int id){
        String sql = "DELETE FROM POJAZDY WHERE NR_POJAZD = ?";
        jdbcTemplate.update(sql, id);
    }
}
