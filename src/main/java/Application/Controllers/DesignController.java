package Application.Controllers;

import Application.Database.DBRepository;
import Application.Entities.Cake;
import Application.Entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignController {

    private final DBRepository<Ingredient> DBRepository;

    @Autowired
    public DesignController(DBRepository<Ingredient> DBRepository) {
        this.DBRepository = DBRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        fillIngredients(model);
        model.addAttribute("cake", new Cake());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Cake cake, Errors errors, Model model) {
        if (errors.hasErrors()) {
            fillIngredients(model);

            return "design";
        }
        System.out.println("Created cake: " + cake);
        return "redirect:/orders/current";
    }

    private void fillIngredients(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        DBRepository.findAll().forEach(ingredients::add);

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    ingredients.stream().filter(ingredient ->
                            ingredient.getType().equals(type)).collect(Collectors.toList()));
        }
    }
}
