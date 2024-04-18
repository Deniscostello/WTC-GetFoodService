package ie.atu.wtcgetfoodservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends MongoRepository<Food, String> {

    Optional<Food> findByUserId(String userId);
}