package top.lmqstudy.service;


import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import top.lmqstudy.controller.HighlightResultMapper;
import top.lmqstudy.doc.CourseDoc;
import top.lmqstudy.query.CourseQuery;
import top.lmqstudy.repository.CourseDocRepository;
import top.lmqstudy.util.AjaxResult;
import top.lmqstudy.util.PageList;

@Service
public class SearchService {
    @Autowired
    private CourseDocRepository courseDocRepository;

    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private HighlightResultMapper highlightResultMapper;

    public AjaxResult saveCourseBeanDoc(CourseDoc courseDoc) {
        courseDocRepository.save(courseDoc);
        return AjaxResult.me();
    }

    /**
     * 进行高级查询操作
     *
     * @param courseQuery
     * @return
     */
    public AjaxResult searchCourse(CourseQuery courseQuery) {
        //1.把courseQeury转成ES的查询对象(条件构造器)
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //==================================================================
        //排序
        String sortField = courseQuery.getSortField();
        if (StringUtils.isNotBlank(sortField)) {
            //排序的字段========================= 排序字段：前端传过来的统一是 price
            String sortFiledName = "price";
            switch (sortField.toLowerCase()) {
                case "xl"://销量
                    sortFiledName = "saleCount";
                    break;
                case "xp"://新品
                    sortFiledName = "onlineTime";
                    break;
                case "pl"://评论
                    sortFiledName = "commentCount";
                    break;
            }

            //排序方式 desc  -> SortOrder.DESC ，asc  -> SortOrder.ASC
            String sortType = courseQuery.getSortType();
            //默认排序方式：升序
            SortOrder sortOrder = SortOrder.ASC;

            sortOrder = StringUtils.isNotBlank(sortType)
                    && sortType.toLowerCase().equals("desc") ? SortOrder.DESC : SortOrder.ASC;

            builder.withSort(new FieldSortBuilder(sortFiledName).order(sortOrder));
        }
        //分页
        builder.withPageable(PageRequest.of(courseQuery.getPage() - 1, courseQuery.getRows()));

        //boolean组合查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(courseQuery.getKeyword())) {
            //DSL查询 ： keyword -> must -> matchQuery（根据查询的条件进行分词后的模糊查询）
            boolQueryBuilder.must(QueryBuilders.matchQuery("name", courseQuery.getKeyword()));
        }
        if (null != courseQuery.getCourseTypeId()) {//课程分类ID
            //DSL过滤 : productType -> term
            boolQueryBuilder.filter(QueryBuilders.termQuery("courseTypeId", courseQuery.getCourseTypeId()));
        }
        if (null != courseQuery.getPriceMin()) {
            //DSL过滤 : priceMax;priceMin -> range
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(courseQuery.getPriceMin()));
        }
        if (null != courseQuery.getPriceMax()) {
            //DSL过滤 : priceMax;priceMin -> range
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(courseQuery.getPriceMax()));
        }
        builder.withQuery(boolQueryBuilder);
        //==================================================================
//        //2.调用courseElasticsearchRepository进行查询
//        Page<CourseDoc> page = courseRepository.search(builder.build());

        //对结果进行高亮的设置
            //1. 指定需要进行高亮设置的字段。【注意：被设置高亮的字段，需要出现在上面的查询条件中】
        //设置高亮显示。参数1：name:设置需要进行高亮设置的字段名；参数2：preTags：设置高亮的前缀；参数3：设置高亮字段的后缀
        HighlightBuilder.Field field = new HighlightBuilder.Field("name").preTags("<font style='color:green'><b>").postTags("</b></font>");
        builder.withHighlightFields(field);  // 名字高亮
            //2.对高亮字段设置完成后，必须要调用ElasticsearchTemplate模块进行查询
        AggregatedPage<CourseDoc> page = template.queryForPage(builder.build(),CourseDoc.class,highlightResultMapper);



        //3.把结果封装成 PageList<CourseDoc> ，设置给 AjaxResult 返回
        return AjaxResult.me().setResultObj(
                new PageList<CourseDoc>(page.getTotalElements(), page.getContent()));


    }
}
