package top.lmqstudy.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.lmqstudy.doc.CourseDoc;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/3/4 15:37
 */
public interface CourseDocRepository extends ElasticsearchRepository<CourseDoc,Long> {
}
