package top.lmqstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/02/25/16:21
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApp1020 {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApp1020.class,args);
    }
}
