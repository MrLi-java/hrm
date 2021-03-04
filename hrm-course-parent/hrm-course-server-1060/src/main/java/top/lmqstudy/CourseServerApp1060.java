package top.lmqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/01/18:57
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CourseServerApp1060 {
    public static void main(String[] args) {
        SpringApplication.run(CourseServerApp1060.class,args);
    }
}
