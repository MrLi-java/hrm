package top.lmqstudy.service;

import top.lmqstudy.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import top.lmqstudy.dto.CourseDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-03-01
 */
public interface ICourseService extends IService<Course> {

    void add(CourseDto dto);
}
