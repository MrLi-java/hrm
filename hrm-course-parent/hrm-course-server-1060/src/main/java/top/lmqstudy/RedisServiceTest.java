package top.lmqstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.lmqstudy.domain.CourseType;
import top.lmqstudy.service.RedisService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/03/10:31
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    @Test
    public void test1(){
//        redisTemplate.opsForValue().set("name","李四");
//        Object name = redisTemplate.opsForValue().get("name");
//        System.out.println(name);


//        redisTemplate.opsForHash().put("user","username","mark");
//        Object o = redisTemplate.opsForHash().get("user", "username");
//        System.out.println(o);


//        redisTemplate.opsForList().leftPush("course",99);
//        redisTemplate.opsForList().leftPush("course",91);
//        redisTemplate.opsForList().rightPush("course",92);
//        redisTemplate.opsForList().rightPush("course",93);

//        Object course = redisTemplate.opsForList().rightPop("course");
//        System.out.println(course);
        Long size = redisTemplate.opsForList().size("course");
        for (Long i = 0L; i < size; i++) {
            Object course = redisTemplate.opsForList().index("course", i);
            System.out.println(course);
        }
    }

    @Test
    public void test2(){
//        CourseType courseType = new CourseType();
//        courseType.setId(10000L);
//        courseType.setName("mark");
//        redisService.setStringKeyAndValue("type", courseType);
//        CourseType type = redisService.getKeyObjectValue("type", CourseType.class);
//        System.out.println(type);

        String s = "hhhh";
        redisService.setStringKeyAndValue("username",s);
        String username = redisService.getKeyObjectValue("username", String.class);
        System.out.println(username);
    }
}
