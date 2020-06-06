package Application.Database;

import Application.Entities.CustomPie;
import Application.Entities.Order;
import Application.Entities.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCOrderRepository implements DBRepository<Order> {

    private JdbcTemplate jdbc;

    @Autowired
    public JDBCOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Order> findAll() {
        return jdbc.query("select name, lastname, address, cardnumber from clients", this::mapRowToOrder);
    }

    @Override
    public Order findById(String id) {
        return jdbc.queryForObject("select name, lastname, address, cardnumber from clients where id =?",
                this::mapRowToOrder, id);
    }

    @Override
    public Order save(Order order) {
        order.parse();

        List<ProductInfo> productInfoList = order.getProductInfoList();
        List<CustomPie> customPieList = order.getCustomPieList();

        // clients
        jdbc.update("insert into clients (name, lastname, mail, phone, address, cardnumber) values (?,?,?,?,?,?)",
                order.getName(),
                order.getLastName(),
                order.getMail(),
                order.getPhone(),
                order.getAddress(),
                order.getCardNumber());

        //getting client ID
        String clientId = jdbc.queryForObject("select id from clients where name=? and lastname=? and cardnumber=?",
                this::mapId, order.getName(), order.getLastName(), order.getCardNumber());

        // orders
        jdbc.update("insert into orders (clientId, customId) values (?,?)",
                clientId,
                1);

        //getting order ID
        String orderId = jdbc.queryForObject("select id from orders where clientId=?",
                this::mapId, clientId);

        // order_to_product
        for (int i = 0; i < productInfoList.size(); i++) {
            jdbc.update("insert into order_to_product (orderId, productId, amount) values (?,?,?)",
                    orderId, productInfoList.get(i).getId(), productInfoList.get(i).getAmount());
        }

        // custom_to_order
        for (int pieId = 0; pieId < customPieList.size(); pieId++) {
            CustomPie customPie = customPieList.get(pieId);
            List<String> ingredients = customPie.getIngredients();

            for (int i = 0; i < ingredients.size(); i++) {
                String ingredientId = jdbc.queryForObject("select id from ingredients where name=?",
                        this::mapId, ingredients.get(i));

                jdbc.update("insert into custom_to_order (orderId, ingredientId, pieId) values (?,?,?)",
                        orderId, ingredientId, pieId);
            }
        }

        return order;
    }

    private Order mapRowToOrder(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();
        order.setName(resultSet.getString("name"));
        order.setLastName(resultSet.getString("lastname"));
        order.setMail(resultSet.getString("mail"));
        order.setPhone(resultSet.getString("phone"));
        order.setAddress(resultSet.getString("address"));
        order.setCardNumber(resultSet.getString("cardnumber"));

        return order;
    }

    private String mapId(ResultSet resultSet, int rowNum) throws SQLException {
        return resultSet.getString("id");
    }
}
