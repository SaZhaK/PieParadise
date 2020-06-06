package Application.Entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Cake {
    @NotNull(message = "You have to select at least one ingredient")
    @Size(min = 1, message = "You have to select at least one ingredient")
    private List<String> ingredients;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuilder ingredientsStr = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientsStr.append(ingredients.get(i)).append(", ");
        }

        return "Cake: ingredients " + ingredientsStr;
    }
}
