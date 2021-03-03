package top.lmqstudy.dto;

import lombok.Data;
import top.lmqstudy.domain.Course;
import top.lmqstudy.domain.CourseDetail;
import top.lmqstudy.domain.CourseMarket;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/03/15:21
 * @Description:
 */
@Data
public class CourseDto {
    private Course course;
    private CourseMarket courseMarket;
    private CourseDetail courseDetail;
}
