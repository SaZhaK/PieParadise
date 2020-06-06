package Application.Entities;

import java.util.ArrayList;
import java.util.List;

public class CustomPie {
    private List<String> ingredients = new ArrayList<>();

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (String ingredient : ingredients) {
            str.append(ingredient).append(" ");
        }

        return str.toString();
    }
}
