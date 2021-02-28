package top.lmqstudy.controller;

//这里要先设置idea的自动导包功能。代码生成后，重新打开该文件，让idea自动帮助导包

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lmqstudy.domain.Systemdictionaryitem;
import top.lmqstudy.query.SystemdictionaryitemQuery;
import top.lmqstudy.service.ISystemdictionaryitemService;
import top.lmqstudy.util.AjaxResult;
import top.lmqstudy.util.PageList;

import java.util.List;

@RestController
@RequestMapping("/systemdictionaryitem")
public class SystemdictionaryitemController {
    @Autowired
    public ISystemdictionaryitemService systemdictionaryitemService;

    /**
     * 保存和修改公用的
     * @param systemdictionaryitem 传递的实体
     * @return Ajaxresult转换结果
     * @author Mr.Li
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Systemdictionaryitem systemdictionaryitem) {
        try {
            if (systemdictionaryitem.getId() != null){
                    systemdictionaryitemService.updateById(systemdictionaryitem);
            }else{
                    systemdictionaryitemService.save(systemdictionaryitem);
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
                systemdictionaryitemService.removeById(id);
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
    public Systemdictionaryitem get(@PathVariable("id") Long id) {
        return systemdictionaryitemService.getById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    * @author Mr.Li
    */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Systemdictionaryitem> list() {

        return systemdictionaryitemService.list();
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
    public PageList<Systemdictionaryitem> json(@RequestBody SystemdictionaryitemQuery query) {
        Page<Systemdictionaryitem> page = new Page<Systemdictionaryitem>(query.getPage(), query.getRows());
        page = systemdictionaryitemService.page(page);
        return new PageList<Systemdictionaryitem>(page.getTotal(),page.getRecords());
    }
}
