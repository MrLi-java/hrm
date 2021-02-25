package top.lmqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/02/25/18:04
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class SystemServerApp1040 {
    public static void main(String[] args) {
        SpringApplication.run(SystemServerApp1040.class,args);
    }
}
