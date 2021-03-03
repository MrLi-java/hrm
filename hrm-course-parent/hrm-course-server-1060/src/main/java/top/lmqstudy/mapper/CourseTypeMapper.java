package top.lmqstudy.mapper;

import org.apache.ibatis.annotations.Select;
import top.lmqstudy.domain.CourseType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程目录 Mapper 接口
 * </p>
 *
 * @author Mr.Li
 * @since 2021-03-01
 */
public interface CourseTypeMapper extends BaseMapper<CourseType> {

    @Select("select * from t_course_type where pid = #{pid}")
    List<CourseType> findByPid(Long pid);
}
