package top.lmqstudy.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.lmqstudy.util.CustomLocatDateSerializer;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.Li
 * @since 2021-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_course_market")
@ApiModel(value="CourseMarket对象", description="")
public class CourseMarket extends Model<CourseMarket> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    private Long id;

    @ApiModelProperty(value = "收费规则：，收费1免费，2收费")
    private Integer charge;

    @ApiModelProperty(value = "营销截止时间")
    @JsonSerialize(using = CustomLocatDateSerializer.class)
    private LocalDate expires;

    @ApiModelProperty(value = "咨询qq")
    private String qq;

    @ApiModelProperty(value = "价格")
    private Float price;

    @ApiModelProperty(value = "原价")
    private Float priceOld;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
