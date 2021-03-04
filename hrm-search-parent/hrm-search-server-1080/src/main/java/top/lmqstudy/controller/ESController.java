package top.lmqstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.lmqstudy.doc.CourseDoc;
import top.lmqstudy.repository.CourseDocRepository;
import top.lmqstudy.util.AjaxResult;

//ES接口
@RestController
public class ESController {

    @Autowired
    private CourseDocRepository courseDocRepository;

    //添加
    @RequestMapping(value = "/es/save",method = RequestMethod.POST)
    public AjaxResult save(@RequestBody CourseDoc courseDoc){
        courseDocRepository.save(courseDoc);
        return AjaxResult.me();
    }

    //删除
    @RequestMapping(value = "/es/delete",method = RequestMethod.DELETE)
    public AjaxResult delete(@RequestBody CourseDoc courseDoc){
        //courseDocRepository.delete(courseDoc);
        courseDocRepository.deleteById(courseDoc.getId());
        return AjaxResult.me();
    }
}
