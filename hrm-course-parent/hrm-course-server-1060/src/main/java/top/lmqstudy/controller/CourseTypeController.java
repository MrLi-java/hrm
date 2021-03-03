package top.lmqstudy.controller;

//这里要先设置idea的自动导包功能。代码生成后，重新打开该文件，让idea自动帮助导包

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.config.Constant;
import top.lmqstudy.domain.CourseType;
import top.lmqstudy.query.CourseTypeQuery;
import top.lmqstudy.service.ICourseTypeService;
import top.lmqstudy.service.RedisService;
import top.lmqstudy.util.AjaxResult;
import top.lmqstudy.util.PageList;

import java.util.List;

@RestController
@RequestMapping("/courseType")
public class CourseTypeController {
    @Autowired
    public ICourseTypeService courseTypeService;
    @Autowired
    private RedisService redisService;


    @GetMapping("/treeData")
    public AjaxResult treeData(){
        return courseTypeService.treeData();
    }

    /**
     * 保存和修改公用的
     * @param courseType 传递的实体
     * @return Ajaxresult转换结果
     * @author Mr.Li
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody CourseType courseType) {
        try {
            if (courseType.getId() != null){
                    courseTypeService.updateById(courseType);
            }else{
                    courseTypeService.save(courseType);
            }
            redisService.deleteByKey(Constant.REDIS_COURSE_TYPE_LIST);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    * @author Mr.Li
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            courseTypeService.removeById(id);
            redisService.deleteByKey(Constant.REDIS_COURSE_TYPE_LIST);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！" + e.getMessage());
        }
    }

    /**
     * 获取用户
     * @Author Mr.Li
     * @Param [id]
     * @return
     **/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CourseType get(@PathVariable("id") Long id) {
        return courseTypeService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    * @author Mr.Li
    */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CourseType> list() {

        return courseTypeService.list();
    }


    /**
    * 分页查询数据(不带查询条件))
    *
    * @param query 查询对象
    * @return PageList 分页对象
    * @author Mr.Li
    *
    */
    @RequestMapping(value = "/pagelist", method = RequestMethod.POST)
    public PageList<CourseType> json(@RequestBody CourseTypeQuery query) {
        Page<CourseType> page = new Page<CourseType>(query.getPage(), query.getRows());
        page = courseTypeService.page(page);
        return new PageList<CourseType>(page.getTotal(),page.getRecords());
    }
}
