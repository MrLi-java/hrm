package top.lmqstudy.mapper;

import org.apache.ibatis.annotations.Select;
import top.lmqstudy.domain.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("select * from t_employee where username = #{username}")
    Employee findByUsername(String username);

}
