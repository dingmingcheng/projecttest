package springbootdmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@yuntu-inc.com
 * @Description
 * @Date: Created in 下午12:03 2017/12/28
 * @Modifyed By:
 */
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.dmc"})
@ImportResource("classpath:*_spring.xml")
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
