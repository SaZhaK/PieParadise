package Application.Controllers;

import Application.Database.DBRepository;
import Application.Entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

    private final DBRepository<Product> DBRepository;

    @Autowired
    public HomeController(DBRepository<Product> DBRepository) {
        this.DBRepository = DBRepository;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        List<Product> products = new ArrayList<>((Collection<? extends Product>) DBRepository.findAll());

        model.addAttribute("products", products);

        return "home";
    }
}