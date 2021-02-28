package top.lmqstudy.service.impl;

import top.lmqstudy.domain.Employee;
import top.lmqstudy.mapper.EmployeeMapper;
import top.lmqstudy.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
