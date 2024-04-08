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

    public List<String> getFoods(String userId){
        List<String> allFoods = new ArrayList<>();
        Optional<Food> userFoodList = foodRepository.findByUserId(userId);
        if(userFoodList.isPresent()){
            Food food = userFoodList.get();
            if(food.getFoods() != null){
                return food.getFoods();
            }
            else{
                if(food.getFoodName() != null){
                    allFoods.add(food.getFoodName());
                    return allFoods;
                }
            }
        }
        return allFoods;
    }
}
