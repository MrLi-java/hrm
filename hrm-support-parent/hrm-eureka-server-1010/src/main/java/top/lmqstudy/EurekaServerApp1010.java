package top.lmqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/02/25/16:16
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp1010 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp1010.class,args);
    }
}
