package Application.Database;

import Application.Entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCIngredientRepository implements DBRepository<Ingredient> {

    private final JdbcTemplate jdbc;

    @Autowired
    public JDBCIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type, price from ingredients", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("select id, name, type, price from ingredients where id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("insert into ingredients (id, name, type, price) values (?, ?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString(),
                ingredient.getPrice());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")),
                resultSet.getString("price"));
    }
}
