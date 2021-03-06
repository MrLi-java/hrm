package top.lmqstudy.doc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

//ES的文档对象 :
// doc对象应该有哪些字段？ES需要存储课程的哪些字段？你的搜索课程的页面需要用到哪些字段？
//展示的字段+排序的字段+搜索条件用到的字段 的总和
//注意事项：使用es创建文档对象时，一般不建议使用 组合类，即es中要存储的所有字段统一用一个实体类来保存

//那些字段要分词：要进行关键字搜索的就要分词
@Data
@Document(indexName = "hrm", type = "course")
public class CourseDoc {
    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String searchField; //这个字段的内容是：  name + description
    /**
     * 课程名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String name;
    /**
     * 适用人群
     */
    private String users;
    /**
     * 课程大分类
     */
    private Long courseTypeId;

    private String gradeName;
    /**
     * 课程等级
     */
    private Long grade;
    /**
     * 教育机构
     */
    private Long tenantId;
    private String tenantName;
    private Date startTime;
    private Date endTime;
    private String pic;
    /**
     * 收费规则，对应数据字典,免费，收费
     */
    private Long charge;
    /**
     * 原价
     */
    private Float priceOld;
    /**
     * 价格
     */
    private float price;
    /**
     * 简介
     */
    @Field(type = FieldType.Keyword)
    private String description;

    //排序条件
    //上线时间
    private Date onlineTime = new Date();
    //销量
    private int saleCount = 0;
    //评论数
    private int commentCount = 0;
}
