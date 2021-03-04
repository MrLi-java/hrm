package top.lmqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/04/16:41
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class SearchServerApp1080 {
    public static void main(String[] args) {
        SpringApplication.run(SearchServerApp1080.class,args);
    }
}
