package ie.atu.wtcgetfoodservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeData {
    private String url;
    private String title;
    private String description;
    private int recipeId;
    private String prepTime;
    private String cookTime;
    private String image;
    private List<String> ingredients;
    private float score;
    private List<String> steps;
}
