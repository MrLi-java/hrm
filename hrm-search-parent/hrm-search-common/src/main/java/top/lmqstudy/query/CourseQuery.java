package top.lmqstudy.query;

import lombok.Data;
@Data
public class CourseQuery extends BaseQuery {
    private String keyword;
    private Long courseTypeId;
    private String gradeId;
    private Double priceMin;
    private Double priceMax;
    private Long tenantId;
    private String sortField;
    private String sortType;
    private Integer page;
    private Integer rows;
}
