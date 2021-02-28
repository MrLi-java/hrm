package top.lmqstudy.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.lmqstudy.config.ParamerterException;
import top.lmqstudy.domain.Meal;
import top.lmqstudy.domain.Tenant;
import top.lmqstudy.domain.TenantMeal;
import top.lmqstudy.dto.EnteringDto;
import top.lmqstudy.mapper.EmployeeMapper;
import top.lmqstudy.mapper.MealMapper;
import top.lmqstudy.mapper.TenantMapper;
import top.lmqstudy.mapper.TenantMealMapper;
import top.lmqstudy.service.ITenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.lmqstudy.util.AjaxResult;
import top.lmqstudy.util.ErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
@Service
@Transactional
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TenantMealMapper tenantMealMapper;

    @Override
    public AjaxResult entering(EnteringDto dto) {
        //添加tenant信息
        dto.getTenant().setRegisterTime(LocalDate.now());
        baseMapper.insert(dto.getTenant());

        //验证公司是否为空
        if(StringUtils.isBlank(dto.getEmployee().getUsername())){
            throw new ParamerterException(ErrorCode.CODE_104_EXIST_EMPLOYEE_USERNAME);
        }
        //验证用户名是否重复
        if(employeeMapper.findByUsername(dto.getEmployee().getUsername()) != null){
            throw new ParamerterException(ErrorCode.CODE_103_EXIST_EMPLOYEE_USERNAME);
        }


        //添加employee信息
        dto.getEmployee().setState(0);
        dto.getEmployee().setInputTime(LocalDate.now());
        dto.getEmployee().setTenantId(dto.getTenant().getId());
        dto.getEmployee().setType(1);
        employeeMapper.insert(dto.getEmployee());




        //添加tenant_meal到数据库中
        TenantMeal tenantMeal = new TenantMeal();
        tenantMeal.setMealId(dto.getMealId());
        tenantMeal.setTenantId(dto.getTenant().getId());
        Date newDate = DateUtils.addDays(new Date(), 7);
        LocalDateTime localDateTime = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        tenantMeal.setExpireDate(localDateTime);
        tenantMealMapper.insert(tenantMeal);

        return AjaxResult.me();
    }
}
