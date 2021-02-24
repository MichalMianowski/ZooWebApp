package zoo_webapp.zoomanager.Zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ZooDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ZooDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public ArrayList<ZOO> list() {
        String sql = "SELECT NR_ZOO, NAZWA FROM ZOO";
        ArrayList<ZOO> listZoo = new ArrayList<ZOO>();
        List lista = jdbcTemplate.queryForList(sql);
        for (Object o : lista) {
            String[] row = o.toString().replaceAll("[{\\}]", "").split("[,\\=]");
            listZoo.add(new ZOO(Integer.parseInt(row[1]), row[3]));
        }
        return listZoo;
    }


}
