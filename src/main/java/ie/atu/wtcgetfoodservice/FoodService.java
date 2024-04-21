package ie.atu.wtcgetfoodservice;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<String> getFoods(String userId) {
        Optional<Food> existingFood = foodRepository.findByUserId(userId);

        if(existingFood.isEmpty()){
            Food food = new Food();
            food.setUserId(userId);
            foodRepository.save(food);
            return food.getFoods();
        }
        else {
            Food userExists= existingFood.get();
            return userExists.getFoods();
        }
    }



    @Async
    public CompletableFuture<List<String>> getFoodsAsync(String userId){
        List<String> foods = getFoods(userId);
        return CompletableFuture.completedFuture(foods);
    }

    @Async
    public CompletableFuture<List<Integer>> getFavRecipesAsync(String userId){
        List<Integer> favRecipes = getFavRecipe(userId);
        return CompletableFuture.completedFuture(favRecipes);
    }


    public List<Integer> getFavRecipe(String userId){
        try {
            Optional<Food> userFoodList = foodRepository.findByUserId(userId);
            if(userFoodList.isPresent()) {
                Food food = userFoodList.get();
                return food.getRecipeSaved();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }





}
