package top.lmqstudy.service.impl;

import top.lmqstudy.domain.TenantType;
import top.lmqstudy.mapper.TenantTypeMapper;
import top.lmqstudy.service.ITenantTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户(机构)类型表 服务实现类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantType> implements ITenantTypeService {

}
