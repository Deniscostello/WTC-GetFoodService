package ie.atu.wtcgetfoodservice;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "usersFood")
@AllArgsConstructor
public class Food {

    @Id
    private String userId;

    @NotBlank(message = "FoodName cannot not blank")
    private String foodName;

    private List<String> foods;

}
