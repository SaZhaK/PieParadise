package Application.Database;

import Application.Entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JDBCProductsRepository implements DBRepository<Product> {

    private JdbcTemplate jdbc;

    @Autowired
    public JDBCProductsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Product> findAll() {
        return jdbc.query("select id, name, price, ingredients, description, image from products", this::mapRowToOrder);
    }

    @Override
    public Product findById(String id) {
        return jdbc.queryForObject("select id, name, price, ingredients, description, image from products where id =?",
                this::mapRowToOrder, id);
    }

    @Override
    public Product save(Product product) {
        jdbc.update("insert into products (name, price, ingredients, description, image) values (?,?,?,?,?)",
                product.getName(),
                product.getPrice(),
                product.getIngredients(),
                product.getDescription(),
                product.getImage());
        return product;
    }

    private Product mapRowToOrder(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getString("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getString("price"));
        product.setIngredients(resultSet.getString("ingredients"));
        product.setDescription(resultSet.getString("description"));
        product.setImage(resultSet.getString("image"));

        return product;
    }
}
