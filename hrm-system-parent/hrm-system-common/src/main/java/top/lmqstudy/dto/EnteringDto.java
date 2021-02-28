package top.lmqstudy.dto;

import lombok.Data;
import top.lmqstudy.domain.Employee;
import top.lmqstudy.domain.Tenant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Mr.Li
 * @Date: 2021/02/28/14:02
 * @Description:
 */
@Data
public class EnteringDto {
    private Tenant tenant;
    private Employee employee;
    private Long mealId;
}
