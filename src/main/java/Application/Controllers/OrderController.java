package Application.Controllers;

import Application.Database.DBRepository;
import Application.Email.MailSender;
import Application.Entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final DBRepository<Order> DBRepository;

    @Autowired
    public OrderController(DBRepository<Order> DBRepository) {
        this.DBRepository = DBRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@RequestBody Order order) {
        saveOrder(order);

        MailSender.send(order);

        return "redirect:/";
    }

    private void saveOrder(Order order) {
        DBRepository.save(order);
    }
}
