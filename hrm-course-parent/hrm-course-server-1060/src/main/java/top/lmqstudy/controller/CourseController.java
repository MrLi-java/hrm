package top.lmqstudy.controller;

//这里要先设置idea的自动导包功能。代码生成后，重新打开该文件，让idea自动帮助导包

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.domain.Course;
import top.lmqstudy.dto.CourseDto;
import top.lmqstudy.query.CourseQuery;
import top.lmqstudy.service.ICourseService;
import top.lmqstudy.util.AjaxResult;
import top.lmqstudy.util.PageList;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    public ICourseService courseService;

    //课程上线 onLineCourse
    @RequestMapping(value="/onLineCourse/{id}",method= RequestMethod.POST)
    public AjaxResult onLineCourse(@PathVariable("id") Long id){
        try {
            courseService.onLineCourse(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上线失败！"+e.getMessage());
        }
    }

    //课程下线 offLineCourse
    @RequestMapping(value="/offLineCourse/{id}",method= RequestMethod.POST)
    public AjaxResult offLineCourse(@PathVariable("id") Long id){
        try {
            courseService.offLineCourse(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("下线失败！"+e.getMessage());
        }
    }


    /**
     * 保存和修改公用的
     * @param course 传递的实体
     * @return Ajaxresult转换结果
     * @author Mr.Li
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody CourseDto dto) {
        try {
            if (dto.getCourse().getId() != null){
                    courseService.updateById(dto.getCourse());
            }else{
                    courseService.add(dto);
            }
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
                courseService.removeById(id);
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
    public Course get(@PathVariable("id") Long id) {
        return courseService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    * @author Mr.Li
    */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Course> list() {

        return courseService.list();
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
    public PageList<Course> json(@RequestBody CourseQuery query) {
        Page<Course> page = new Page<Course>(query.getPage(), query.getRows());
        page = courseService.page(page);
        return new PageList<Course>(page.getTotal(),page.getRecords());
    }
}
