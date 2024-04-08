package ie.atu.wtcgetfoodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WtcGetFoodServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtcGetFoodServiceApplication.class, args);
    }

}
