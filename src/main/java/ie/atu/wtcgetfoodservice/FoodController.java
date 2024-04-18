package ie.atu.wtcgetfoodservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodController {

    private final FoodService foodService;
    private final RecipeClient recipeClient;

    public FoodController(FoodService foodService, RecipeClient recipeClient) {
        this.foodService = foodService;
        this.recipeClient = recipeClient;
    }


    @PostMapping("food/getAllFood")
    public ResponseEntity<Map<String, List<String>>> getAllFood(@RequestBody Food food){
        List<String> foods = foodService.getFoods(food.getUserId());
        Map<String, List<String>> response = new HashMap<>();
        response.put("foods", foods);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping ("food/getFoodForRecipe")
//    public ResponseEntity<Map<String, List<RecipeData>>> getFoodForRecipe(@RequestBody Food food){
//        List<String> usersFood = foodService.getFoods(food.getUserId());
//        List<RecipeData> recipesFound = recipeClient.foodDetails(usersFood);
//        Map<String, List<RecipeData>> response = new HashMap<>();
//        response.put("recipes", recipesFound);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//
//    @PostMapping ("food/getFavRecipe")
//    public ResponseEntity<Map<String, List<RecipeData>>> getFavRecipe(@RequestBody Food food){
//        List<Integer> favRecipes = foodService.getFavRecipe(food.getUserId());
//        System.out.println(favRecipes);
//        List<RecipeData> favRecipesFound = recipeClient.findById(favRecipes);
//        Map<String, List<RecipeData>> response = new HashMap<>();
//        response.put("FavRecipes", favRecipesFound);
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
}
