package zoo_webapp.zoomanager.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.ArrayUtils;
import zoo_webapp.zoomanager.DBConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AnimalsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AnimalsDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Animal> list(){
        String sql = "SELECT z.NR_ZWIERZE, CZY_WYTRESOWANE, CZY_W_POKAZACH, DIETA, g.GATUNEK " +
                "FROM ZWIERZETA z " +
                "INNER JOIN GATUNKI g " +
                "ON z.NR_GATUNKU=g.NR_GATUNKU";
        ArrayList<Animal> listAnimal = new ArrayList<>();

        List lista = jdbcTemplate.queryForList(sql);
        for (Object o : lista) {
            String[] row = o.toString().replaceAll("[{\\}]", "").split("[,\\=]");
            listAnimal.add(new Animal(Integer.parseInt(row[1]),row[3], row[5], row[7], row[9]));
        }
        return listAnimal;
    }

    public void save(Animal animal){
        int species_id = handleWithSpecies(animal);

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);

        insertActor.withTableName("ZWIERZETA").usingColumns("CZY_WYTRESOWANE","CZY_W_POKAZACH","DIETA","NR_ZOO","NR_GATUNKU");
        Map<String,Object> param = new HashMap<>();
        param.put("CZY_WYTRESOWANE", animal.getAreTrained());
        param.put("CZY_W_POKAZACH",animal.getPartInShows());
        param.put("DIETA",animal.getDiet());
        param.put("NR_ZOO",String.valueOf(1));
        param.put("NR_GATUNKU",String.valueOf(species_id));
        insertActor.execute(param);
    }

    public Animal get(int id){
        String sql = "SELECT z.NR_ZWIERZE, CZY_WYTRESOWANE, CZY_W_POKAZACH, DIETA, g.GATUNEK " +
                "FROM ZWIERZETA z " +
                "INNER JOIN GATUNKI g " +
                "ON z.NR_GATUNKU=g.NR_GATUNKU WHERE z.NR_ZWIERZE="+id;

        String[] list = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]");
        Animal animal = new Animal(Integer.parseInt(list[1]), list[3], list[5], list[7], list[9]);

        return animal;
    }

    public void update(Animal animal){
        int species_id = handleWithSpecies(animal);

        String sql = "UPDATE ZWIERZETA SET CZY_WYTRESOWANE=?, CZY_W_POKAZACH=?, DIETA=?, NR_GATUNKU=?" +
                " WHERE NR_ZWIERZE=?";
        try {
            PreparedStatement prepStmt = DBConnector.getConnectionDB().prepareStatement(sql);
            prepStmt.setString(1,animal.getAreTrained());
            prepStmt.setString(2,animal.getPartInShows());
            prepStmt.setString(3,animal.getDiet());
            prepStmt.setString(4,String.valueOf(species_id));
            prepStmt.setString(5,String.valueOf(animal.getId()));
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM ZWIERZETA WHERE NR_ZWIERZE = ?";
        jdbcTemplate.update(sql, id);
    }

    /*
    method check if species already exist in table of species (GATUNKI)
    if not add new species with method insertSpeciesToDB()
    at the end return id of animal's species
     */
    private int handleWithSpecies(Animal animal) {
        int species_id;
        String sql = "SELECT COUNT(*) FROM GATUNKI WHERE GATUNEK= '"+animal.getSpeciesName()+"'";
        String ans[] = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]");;

        if (Integer.parseInt(ans[1]) == 0){
            //species is new for GATUNKI table -> insert species, get id, add id to animal
            insertSpeciesToDB(animal);
        }

        sql = "SELECT NR_GATUNKU FROM GATUNKI WHERE GATUNEK= '"+animal.getSpeciesName()+"'";
        String species_id_String = jdbcTemplate.queryForList(sql).toString().replaceAll("[{\\}]", "").replace("]","").split("[,\\=]")[1];;
        species_id = Integer.parseInt(species_id_String);

        return species_id;
    }

    private void insertSpeciesToDB(Animal animal) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("GATUNKI").usingColumns("GATUNEK");
        Map<String,Object> param = new HashMap<>();
        param.put("GATUNEK", animal.getSpeciesName());
        insertActor.execute(param);
    }

}
