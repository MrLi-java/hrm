package top.lmqstudy.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_meal")
@ApiModel(value="Meal对象", description="")
public class Meal extends Model<Meal> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "套餐名称")
    private String mealName;

    @ApiModelProperty(value = "套餐价格")
    private BigDecimal mealPrice;

    @ApiModelProperty(value = "套餐状态，0正常，1禁用")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
