package top.lmqstudy.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.lmqstudy.doc.CourseDoc;
import top.lmqstudy.util.AjaxResult;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/3/4 15:39
 */
@FeignClient(value = "hrm-es-server")
public interface SearchFeignClient {

    //保存课程
    @RequestMapping(value = "/es/save",method = RequestMethod.POST)
    AjaxResult saveCourse(@RequestBody CourseDoc courseDoc);


    //删除课程
    @RequestMapping(value = "/es/delete",method = RequestMethod.DELETE)
    AjaxResult deleteCourse(@RequestBody CourseDoc courseDoc);

}
