package top.lmqstudy.service;

import top.lmqstudy.domain.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;
import top.lmqstudy.dto.EnteringDto;
import top.lmqstudy.util.AjaxResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
public interface ITenantService extends IService<Tenant> {

    AjaxResult entering(EnteringDto dto);
}
