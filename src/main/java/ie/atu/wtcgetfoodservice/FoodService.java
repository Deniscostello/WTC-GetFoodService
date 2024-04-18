package ie.atu.wtcgetfoodservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//        List<String> allFoods = new ArrayList<>();
//      //  Optional<Food> userFoodList = foodRepository.findByUserId(userId);
//        if(userFoodList.isPresent()){
//            Food food = userFoodList.get();
//            if(food.getFoods() != null){
//                return food.getFoods();
//            }
//            else{
//                if(food.getFoodName() != null){
//                    allFoods.add(food.getFoodName());
//                    return allFoods;
//                }
//            }
//        }
//        return allFoods;


//    public List<Integer> getFavRecipe(String userId){
//        Optional<Food> userFoodList = foodRepository.findByUserId(userId);
//        if(userFoodList.isPresent()) {
//            Food food = userFoodList.get();
//            food.getRecipeSaved();
//        }
//
//        return userFoodList.getRecipeSaved();
//    }

//    public List<String> getFoods(String userId){
//        Food userFoodList = foodRepository.findByUserId(userId);
//        return userFoodList.getFoods();
//    }

//    public List<String> getFoods(String userId) {
//        Optional<Food> userFoodList = foodRepository.findByUserId(userId);
//
//        if(userFoodList.isPresent()){
//            Food food = userFoodList.get();
//            List<String> foodList = food.getFoods();
//            return foodList;
//        }
//        else {
//            List<String> newfoodList = new ArrayList<>();
//            newfoodList.add(inputFood.getFoodName());
//            inputFood.setFoods(newfoodList);
//            return foodRepository.save(inputFood);
//        }
//    }
//public List<String> getFoods(String userId){
//    List<String> allFoods = new ArrayList<>();
//    Optional<Food> userFoodList = foodRepository.findByUserId(userId);
//    if(userFoodList.isPresent()){
//        Food food = userFoodList.get();
//        if(food.getFoods() != null){
//            return food.getFoods();
//        }
//        else{
//            if(food.getFoodName() != null){
//                allFoods.add(food.getFoodName());
//                return allFoods;
//            }
//        }
//    }
//    return allFoods;
//}

}
