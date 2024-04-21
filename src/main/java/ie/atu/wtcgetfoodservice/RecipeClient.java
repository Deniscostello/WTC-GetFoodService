package ie.atu.wtcgetfoodservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="recipe-service", url = "http://localhost:8084")
public interface RecipeClient {
    @PostMapping("/findRecipes")
    List<RecipeData> foodDetails(List<String> usersFood);

    @PostMapping("/findRecipeById")
    List<RecipeData> findById(List<Integer> favRecipes);
}
