package top.lmqstudy.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.controller.SearchFeignClient;
import top.lmqstudy.doc.CourseDoc;
import top.lmqstudy.domain.Course;
import top.lmqstudy.domain.CourseDetail;
import top.lmqstudy.domain.CourseMarket;
import top.lmqstudy.dto.CourseDto;
import top.lmqstudy.mapper.CourseDetailMapper;
import top.lmqstudy.mapper.CourseMapper;
import top.lmqstudy.mapper.CourseMarketMapper;
import top.lmqstudy.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-03-01
 */
@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMarketMapper courseMarketMapper;
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Autowired
    private SearchFeignClient searchFeignClient;


    @Override
    public void add(CourseDto dto) {
        //1. 数据操作前的校验


        //2. 保存数据到 t_course表
        //当一个实体类，某些状态字段的值，如果是预先指定的值，那么在程序赋值时，可以定义常量来描述
        dto.getCourse().setStatus(Course.STATUS_OFFLINE);//0下线；1上线
        //下面的几个值，是设置一个测试值，在完成登录功能后，需要将下面的值动态读取
        dto.getCourse().setUserId(42l);
        dto.getCourse().setUserName("yhptest1");
        dto.getCourse().setTenantId(26l);
        dto.getCourse().setTenantName("源码时代");
        //执行新增操作后，mybatis-plus会将新增后，mysql生成的自增长主键返回给当前对象，以供后面的方法使用
        baseMapper.insert(dto.getCourse());

        //3. 保存数据到 t_course_marker
        dto.getCourseMarket().setId(dto.getCourse().getId());
        courseMarketMapper.insert(dto.getCourseMarket());
        //4. 保存数据到 t_course_detail
        dto.getCourseDetail().setId(dto.getCourse().getId());
        courseDetailMapper.insert(dto.getCourseDetail());

    }

    @Override
    public void onLineCourse(Long id) {
        Course course = baseMapper.selectById(id);

        course.setStatus(Course.STATUS_ONLINE);
        baseMapper.updateById(course);

        CourseDetail courseDetail = courseDetailMapper.selectById(id);
        CourseMarket courseMarket = courseMarketMapper.selectById(id);

        CourseDoc courseDoc = new CourseDoc();

        BeanUtils.copyProperties(course,courseDoc);
        BeanUtils.copyProperties(courseDetail,courseDoc);
        BeanUtils.copyProperties(courseMarket,courseDoc);

        searchFeignClient.saveCourse(courseDoc);


    }

    @Override
    public void offLineCourse(Long id) {
        Course course = baseMapper.selectById(id);

        course.setStatus(Course.STATUS_OFFLINE);
        baseMapper.updateById(course);

        CourseDoc courseDoc = new CourseDoc();
        BeanUtils.copyProperties(course,courseDoc);

        searchFeignClient.deleteCourse(courseDoc);
    }
}
