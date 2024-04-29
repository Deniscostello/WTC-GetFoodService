package ie.atu.wtcgetfoodservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class FoodController {

    private final FoodService foodService;
    private final RecipeClient recipeClient;

    public FoodController(FoodService foodService, RecipeClient recipeClient) {
        this.foodService = foodService;
        this.recipeClient = recipeClient;
    }


    @PostMapping("food/getAllFood")
    public ResponseEntity<Map<String, List<String>>> getAllFood(@RequestBody Food food) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        CompletableFuture<List<String>> foodsFuture = foodService.getFoodsAsync(food.getUserId());
        List<String> foods = foodsFuture.get();
        Map<String, List<String>> response = new HashMap<>();
        response.put("foods", foods);
        long endTime = System.currentTimeMillis();
       // System.out.println("Total time = " + (endTime - startTime) + " ms" );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping ("food/getFoodForRecipe")
    public ResponseEntity<Map<String, List<RecipeData>>> getFoodForRecipe(@RequestBody Food food) throws ExecutionException, InterruptedException{
        try{
           CompletableFuture<List<String>> usersFoodFuture = foodService.getFoodsAsync(food.getUserId());
           List<String> usersFood = usersFoodFuture.get();
           // List<String> usersFood = foodService.getFoods(food.getUserId());
            if(usersFood == null){
                return null;
            }
            List<RecipeData> recipesFound = recipeClient.foodDetails(usersFood);
            Map<String, List<RecipeData>> response = new HashMap<>();
            response.put("recipes", recipesFound);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalArgumentException("No food added ", e);
        }
    }

    @PostMapping ("food/getFavRecipe")
    public ResponseEntity<Map<String, List<RecipeData>>> getFavRecipe(@RequestBody Food food) throws ExecutionException, InterruptedException{
        try {
            CompletableFuture<List<Integer>> favRecipeFuture = foodService.getFavRecipesAsync(food.getUserId());
            List<Integer> favRecipes = favRecipeFuture.get();
           // List<Integer> favRecipes = foodService.getFavRecipe(food.getUserId());
            if(favRecipes == null){
                return null;
            }
            List<RecipeData> favRecipesFound = recipeClient.findById(favRecipes);
            Map<String, List<RecipeData>> response = new HashMap<>();
            response.put("FavRecipes", favRecipesFound);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e){
            throw new IllegalArgumentException("No favorite recipes ", e);
        }

    }
}
