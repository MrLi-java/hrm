package top.lmqstudy.controller;

//这里要先设置idea的自动导包功能。代码生成后，重新打开该文件，让idea自动帮助导包

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.domain.Employee;
import top.lmqstudy.query.EmployeeQuery;
import top.lmqstudy.service.IEmployeeService;
import top.lmqstudy.util.AjaxResult;
import top.lmqstudy.util.PageList;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    public IEmployeeService employeeService;

    /**
     * 保存和修改公用的
     * @param employee 传递的实体
     * @return Ajaxresult转换结果
     * @author Mr.Li
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Employee employee) {
        try {
            if (employee.getId() != null){
                    employeeService.updateById(employee);
            }else{
                    employeeService.save(employee);
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
                employeeService.removeById(id);
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
    public Employee get(@PathVariable("id") Long id) {
        return employeeService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    * @author Mr.Li
    */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Employee> list() {

        return employeeService.list();
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
    public PageList<Employee> json(@RequestBody EmployeeQuery query) {
        Page<Employee> page = new Page<Employee>(query.getPage(), query.getRows());
        page = employeeService.page(page);
        return new PageList<Employee>(page.getTotal(),page.getRecords());
    }
}
