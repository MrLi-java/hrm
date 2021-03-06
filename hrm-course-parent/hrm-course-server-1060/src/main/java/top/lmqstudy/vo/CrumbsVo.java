package top.lmqstudy.vo;

import lombok.Data;
import top.lmqstudy.domain.Course;
import top.lmqstudy.domain.CourseType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/03/06/14:24
 * @Description:
 */
@Data
public class CrumbsVo {
    private CourseType courseType;
    private List<CourseType> brotherCourseTypes;
}
