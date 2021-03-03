package top.lmqstudy.service.impl;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.config.Constant;
import top.lmqstudy.domain.CourseType;
import top.lmqstudy.mapper.CourseTypeMapper;
import top.lmqstudy.service.ICourseTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.lmqstudy.service.RedisService;
import top.lmqstudy.util.AjaxResult;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-03-01
 */
@Service
@Transactional
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {
    @Autowired
    private RedisService redisService;

    @Override
    public AjaxResult treeData() {
        List<CourseType> parent = new ArrayList<>();
        List courseTypeList = redisService.getKeyObjectValue(Constant.REDIS_COURSE_TYPE_LIST, List.class);
        if(courseTypeList == null){
            parent = baseMapper.findByPid(0L);
            getChildList(parent);
            redisService.setStringKeyAndValue(Constant.REDIS_COURSE_TYPE_LIST, JSONArray.toJSONString(parent));
        }else {
            parent = courseTypeList;
        }

        return AjaxResult.me().setResultObj(parent);
    }

    public void getChildList(List<CourseType> parent){
        for (CourseType child : parent) {
            List<CourseType> children = baseMapper.findByPid(child.getId());
            child.setChildren(children);
            getChildList(children);
        }
    }
}
