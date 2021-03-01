package top.lmqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/01/14:04
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class FileServerApp1050 {
    public static void main(String[] args) {
        SpringApplication.run(FileServerApp1050.class,args);
    }
}
