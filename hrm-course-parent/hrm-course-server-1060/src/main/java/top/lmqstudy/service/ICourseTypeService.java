package top.lmqstudy.service;

import top.lmqstudy.domain.CourseType;
import com.baomidou.mybatisplus.extension.service.IService;
import top.lmqstudy.util.AjaxResult;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-03-01
 */
public interface ICourseTypeService extends IService<CourseType> {

    AjaxResult treeData();
}
