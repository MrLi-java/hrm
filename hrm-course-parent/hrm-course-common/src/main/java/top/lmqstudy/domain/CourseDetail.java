package top.lmqstudy.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_course_detail")
@ApiModel(value="CourseDetail对象", description="")
public class CourseDetail extends Model<CourseDetail> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "详情")
    private String description;

    @ApiModelProperty(value = "简介")
    private String intro;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
